package com.ssafy.webrtc.domain.game.controller;

import com.ssafy.webrtc.domain.game.dto.GameSessionLeaveRequestDto;
import com.ssafy.webrtc.domain.game.dto.GameSessionRequestDto;
import com.ssafy.webrtc.domain.game.dto.GameSessionResponseDto;
import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.service.GameSessionService;
import com.ssafy.webrtc.domain.member.MemberRepository;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import com.ssafy.webrtc.global.util.UrlUtils;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequestMapping("/gamesession")
@RestController
@RequiredArgsConstructor
public class GameSessionController {

    private final GameSessionService gameSessionService;

    private final MemberRepository memberRepository;


    @PostMapping("")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public GameSessionResponseDto makeSession(
            @ApiIgnore @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody GameSessionRequestDto gameSessionRequestDto)
            throws OpenViduJavaClientException, OpenViduHttpException {
        return gameSessionService.makeSession(user, gameSessionRequestDto);
    }

    // FIXME : RESPONSE DTO 내용
    @GetMapping("")
    public List<GameSession> findAll() {
        return gameSessionService.findAll();
    }

//    @GetMapping("/{roomId}")
//    public GameSessionStateDto showSessionState(@PathVariable("roomId") String roomId) {
//        gameSessionService
//    }

    @PostMapping("/{roomId}")
    public GameSessionResponseDto enterSession(
            @ApiIgnore @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable("roomId") String roomId) {
        GameSession gameSession = gameSessionService.enterSession(user, roomId);

        return GameSessionResponseDto.of(gameSession);
    }

    @DeleteMapping("/{roomId}")
    public boolean leaveSession(@PathVariable("roomId") String roomId, @RequestBody GameSessionLeaveRequestDto gameSessionLeaveRequestDto) {
        String token = gameSessionLeaveRequestDto.getToken();
        if (!token.startsWith("wss://") || !UrlUtils.getUrlQueryParam(token, "session").isPresent()
                || UrlUtils.getUrlQueryParam(token, "token").isEmpty()) {
            throw new RuntimeException("잘못된 형식의 토큰입니다. - reqeusted token : " + token);
        }
        return gameSessionService.removeUser(roomId, gameSessionLeaveRequestDto.getUserId());
    }


}
