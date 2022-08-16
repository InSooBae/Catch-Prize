package com.ssafy.webrtc.domain.friend;

import com.ssafy.webrtc.domain.friend.repository.EmitterRepository;
import com.ssafy.webrtc.domain.friend.repository.FriendRepository;
import com.ssafy.webrtc.domain.member.MemberRepository;
import com.ssafy.webrtc.domain.member.entity.Member;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FriendService {
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    private final FriendRepository friendRepository;

    private final MemberRepository memberRepository;

    private final EmitterRepository emitterRepository;


    // 우선 로그인한 친구의 리스트
    // Front -> HashMap(친구의 리스트)<ID, Object>들고있어
    // 그 온 정보의 ID put()

    // 액터 (본인 -> 로그인)
    // 1. 본인 친구의 리스트를 가져옴(초기화)
    // 2. 다른 친구가 로그인 -> 로그인한 본인의 친구들에게 자기 자신의 상태를 날린다!
    // 2-1. 다른 친구 A가 (오프라인 -> 온라인) 이 상태를 A의 친구들에게 날려야함. -> 실시간으로 A의 상태를 업데이트 가능
    // 3. 다른 친구 A가 본인(X)에게 친추걸면, 본인(X)에게 이 상태를 알려줘야함.
    // 4. 로그아웃하면 로그아웃한 멤버의 친구들에게 알려줘야함.

    // 실시간 정보가 업데이트
    // 소켓, [SSE] 채택
    public SseEmitter subscribe(UUID userId, String lastEventId) {

        String id = userId + "_" + System.currentTimeMillis();

        SseEmitter emitter = emitterRepository.save(id, new SseEmitter(DEFAULT_TIMEOUT));
        emitter.onCompletion(() -> emitterRepository.deleteById(id));
        emitter.onTimeout(() -> emitterRepository.deleteById(id));

        // 503 에러를 방지하기 위한 더미"" 이벤트 전송
        sendToClient(emitter, id, "EventStream Created. [userId=" + userId + "]");

        // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
        if (!lastEventId.isEmpty()) {
            Map<String, Object> events = emitterRepository.findAllEventCacheStartWithId(String.valueOf(userId));
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
        }

        return emitter;
    }

    private void sendToClient(SseEmitter emitter, String id, Object data) {

        try {
            emitter.send(SseEmitter.event()
                    .id(id)
                    .name("sse")
                    .data(data));
        } catch (IOException exception) {
            emitterRepository.deleteById(id);
//            throw new RuntimeException("연결 오류!");
        }
    }

    public void send(UUID userId, Friend friend) {
        String id = String.valueOf(userId);

        log.info("Friend Service send - id = {}", id);
        Map<String, SseEmitter> sseEmitters = emitterRepository.findAllStartWithById(id);
        sseEmitters.forEach(
                (key, emitter) -> {
//                    friendRepository.save(friend);
                    emitterRepository.saveEventCache(key, friend);
                    sendToClient(emitter, key, FriendResponseDto.of(friend));
                }
        );
    }

    //    1. 친구 목록 전체 조회
//    (select * from friend where tomemberid=나 and isFriend=true)
//    3. 나한테 친구 요청한 리스트 가져오기
//    (select * from friend where tomemberid=나 and pending=true)
    @Transactional(readOnly = true)
    public List<FriendResponseDto> findAllFriends() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails) principal;

        UUID myId = userDetails.getId();

        log.info("findAllFriends - {}", myId);
        List<Friend> friends = friendRepository.findAllFriendsToMe(myId);

        return friends.stream().map(FriendResponseDto::of).collect(Collectors.toList());
    }

    //    2. 친구 추가 요청하기 post("/{친구닉네임}")
//    (insert into friend values (id_auto, frommemberid="나", tomemberid="너", pending=true, isFriend=False)
    public Long addFriend(String friendNickname) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails) principal;

        UUID myId = userDetails.getId();

        Optional<Member> fromMember = memberRepository.findById(myId);

        Optional<Member> toMember = memberRepository.findByNickname(friendNickname);

        log.info("addFriend {} -> {}", friendNickname, toMember.get().getId());

        Optional<Friend> duplicatePending = friendRepository.findDuplicatePending(fromMember.get().getId(), toMember.get().getId());
        //

        // 이미 친구 요청 보냈거나 친구이면
        if (duplicatePending.isPresent()) {
            return -1L; // 리턴
        }

        // 친구 요청
        Friend friend = Friend.of(fromMember.get(), toMember.get(), true, false);


        Long id = friendRepository.save(friend).getId();
        // 친구 요청 받는 사람에게 데이터 전달
        send(toMember.get().getId(), friend);

        return id;
    }


    // 친구 수락하여 친구 추가됨
    public Long acceptFriend(String friendNickname) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails) principal;

        UUID myId = userDetails.getId();

        Optional<Member> fromMember = memberRepository.findById(myId);

        // 상대방 멤버
        Optional<Member> toMember = memberRepository.findByNickname(friendNickname);

        Member requester = (Member) assertExistMember(fromMember);
        Member receiver = (Member) assertExistMember(toMember);

        // 요청자가 보낸 친구 요청 확인
        Optional<Friend> fromMeOptional = friendRepository.findDuplicatePending(requester.getId(), receiver.getId());
        // 요청자가 받은 친구 요청 가져오기
        Optional<Friend> toMeOptional = friendRepository.findDuplicatePending(receiver.getId(), requester.getId());
        
        if (toMeOptional.isPresent()) {
            Friend toMe = toMeOptional.get();
            log.info("friendService tome : {}", toMe.getId());
            toMe.allowFriend();

            friendRepository.save(toMe);
            // 친구 상태 보내주기
            send(requester.getId(), toMe);
        }
        
        Friend fromMe;
        if (fromMeOptional.isPresent()) {
            fromMe = fromMeOptional.get();
            fromMe.allowFriend();
        } else {
            fromMe = Friend.of(requester, receiver, false, true);
        }

        Long id = friendRepository.save(fromMe).getId();
        // 친구 상태 보내주기
        send(receiver.getId(), fromMe);

        return id; // 처리되면 쓰레기값 던지기
    }

    private Object assertExistMember(Optional<?> member) {
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new IllegalStateException("친구 요청 보낸 멤버가 존재하지 않습니다!");
        }
    }


    /*
    4-1. 친구 거절하기
    // 쌍방 삭제로 합쳐두기
    delete("/{친구닉네임}")
    (delete from friend where tomemberid=나 and frommemberid=너 and pending=true)
    5. 친구 삭제하기 (쌍방향 record 제거)
    (delete from friend where frommemberid=나 and tomemberid=너)
    (delete from friend where frommemberid=너 and tomemberid=나)
     */
    public Long deleteFriend(String friendNickname) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails) principal;

        UUID myId = userDetails.getId();

        Optional<Member> toMemberOptional = memberRepository.findByNickname(friendNickname);

        Member toMember;

        if (toMemberOptional.isPresent()) {
            toMember = toMemberOptional.get();
            Optional<Friend> fromMeOptional = friendRepository.findDuplicatePending(myId, toMember.getId());
            Optional<Friend> toMeOptional = friendRepository.findDuplicatePending(toMember.getId(), myId);


            if (fromMeOptional.isPresent()) {
                Friend fromMe = fromMeOptional.get();
                fromMe.deleteFriend();

                send(toMember.getId(), fromMe);
                friendRepository.delete(fromMe);
            }

            if (toMeOptional.isPresent()) {
                Friend toMe = toMeOptional.get();
                toMe.deleteFriend();

                send(myId, toMe);
                friendRepository.delete(toMe);
            }
        }



        return 1L;

    }


}
