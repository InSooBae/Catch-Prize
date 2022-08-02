package com.ssafy.webrtc.domain.friend;

import com.ssafy.webrtc.domain.game.Game;
import com.ssafy.webrtc.domain.game.GameDto;
import com.ssafy.webrtc.domain.game.GameRepository;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    private final ModelMapper modelMapper;


//    1. 친구 목록 전체 조회
//    (select * from friend where tomemberid=나 and isFriend=true)
//
//    3. 나한테 친구 요청한 리스트 가져오기
//    (select * from friend where tomemberid=나 and pending=true)
    public List<FriendDto> findAllFriends() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails)principal;

        UUID myId = ((CustomUserDetails) principal).getId();

        List<FriendDto> friends = friendRepository.findAllFriends(myId);

        return friends;
    }

//    2. 친구 추가 요청하기
//    (insert into friend values (id_auto, frommemberid="나", tomemberid="너", pending=true, isFriend=False)



    /*


    4. 친구 수락하기 (쌍방향 record 추가)
    (select * from friend where tomemberid=나 and frommemberid=너 and pending=true )
    (insert into friend values
    // update insert (한명이 추가한 경우)
    // update update (둘다 추가해둔 경우)

    // (to:나 and from:너 and pending=true) -> pending=false, isfriend=true
    -> if(select * from friend select * from friend where frommemberid=나 and tomemberid=너 and pending=true)
        { // 나도 보내놓은 경우
            update (from:나 and to:너 and pending=true) -> pending=false, isfriend=true
        }
        else {
        // 나는 안보낸 경우
        insert into values (id_auto, frommemberid=나 and tomemberid=너, isfriend=true)

        }

    4-1. 친구 거절하기
    (delete from friend where tomemberid=나 and frommemberid=너 and pending=true)

    5. 친구 삭제하기 (쌍방향 record 제거)
    (delete from friend where frommemberid=나 and tomemberid=너)
    (delete from friend where frommemberid=너 and tomemberid=나)

     */



}
