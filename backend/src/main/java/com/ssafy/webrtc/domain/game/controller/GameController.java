package com.ssafy.webrtc.domain.game.controller;

import com.ssafy.webrtc.domain.game.dto.GameDto;
import com.ssafy.webrtc.domain.game.dto.GameSessionRequestDto;
import com.ssafy.webrtc.domain.game.dto.GameSessionResponseDto;
import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.service.GameService;
import com.ssafy.webrtc.domain.game.service.GameSessionService;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import com.ssafy.webrtc.global.service.GameServerService;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    // 게임 이름으로 게임 정보 조회
    @GetMapping("/{gameTitle}")
    public ResponseEntity<GameDto> findGameByTitle(@PathVariable("gameTitle") String gameTitle) {

        return new ResponseEntity<GameDto>(gameService.findByTitle(gameTitle), HttpStatus.OK);
    }


    // 게임 아이디로 게임 정보 조회
//    @GetMapping("/{gameId}")
//    public ResponseEntity<GameDto> findGameById(@PathVariable("gameId") Long gameId) {
//
//        return new ResponseEntity<GameDto>(gameService.findById(gameId), HttpStatus.OK);
//    }
    
    /*
    게임 추가는 사실상 추가 로직 생성이라
    개발자단에서 추가해야 하기 때문에, CRUD 중 READ만 활성화
     */

}
