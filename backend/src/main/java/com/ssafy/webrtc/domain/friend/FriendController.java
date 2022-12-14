package com.ssafy.webrtc.domain.friend;

import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/friend")
public class FriendController {

    private final FriendService friendService;
    
    // 유저 아이디로 친구목록 & 친구신청 목록 조회
    @GetMapping("")
    public ResponseEntity<?> findAllFriends() {
        return new ResponseEntity<List<FriendResponseDto>>(friendService.findAllFriends(), HttpStatus.OK);
    }

    // 친구 추가 요청 보내기
    @PostMapping("/{friendNickname}")
    public ResponseEntity<Long> addFriend(@PathVariable("friendNickname") String friendNickname) {
        return new ResponseEntity<Long>(friendService.addFriend(friendNickname), HttpStatus.OK);
    }

    // 친구 수락하기 (쌍방향 record 추가)
    @PutMapping("/{friendNickname}")
    public  ResponseEntity<Long> acceptFriend(@PathVariable("friendNickname") String friendNickname) {
        return new ResponseEntity<Long>(friendService.acceptFriend(friendNickname), HttpStatus.OK);
    }

    @DeleteMapping("/{friendNickname}")
    public  ResponseEntity<Long> deleteFriend(@PathVariable("friendNickname") String friendNickname) {
        return new ResponseEntity<Long>(friendService.deleteFriend(friendNickname), HttpStatus.OK);
    }

    @GetMapping(value = "/subscribe", produces = "text/event-stream")
    public SseEmitter subscribe(@AuthenticationPrincipal CustomUserDetails user, @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        log.info("Friend Controller - username = {}",user.getName());
        return friendService.subscribe(user.getId(), lastEventId);
    }
}
