package com.ssafy.webrtc.domain.friend;

import com.ssafy.webrtc.domain.game.GameDto;
import com.ssafy.webrtc.domain.game.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/friend")
public class FriendController {

    private final FriendService friendService;
    
    // 유저 아이디로 친구목록 & 친구신청 목록 조회
    @GetMapping("")
    public ResponseEntity<List<FriendDto>> findAllFriends() {
        return new ResponseEntity<List<FriendDto>>(friendService.findAllFriends(), HttpStatus.OK);
    }




}
