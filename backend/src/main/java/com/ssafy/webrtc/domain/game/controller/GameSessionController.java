package com.ssafy.webrtc.domain.game.controller;

import com.ssafy.webrtc.domain.game.dto.GameSessionRequestDto;
import com.ssafy.webrtc.domain.game.dto.GameSessionResponseDto;
import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.service.GameSessionService;
import com.ssafy.webrtc.domain.member.MemberRepository;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("/gamesession")
@RestController
@RequiredArgsConstructor
public class GameSessionController {

    private final GameSessionService gameSessionService;

    private final MemberRepository memberRepository;


    @PostMapping("")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public GameSessionResponseDto makeRoom(@ApiIgnore @AuthenticationPrincipal CustomUserDetails user, GameSessionRequestDto gameSessionRequestDto) {
        GameSession gamesession = gameSessionService.makeRoom(user, gameSessionRequestDto);
        return GameSessionResponseDto.of(gamesession);
    }

    @GetMapping("/{roomId}")
    public GameSessionResponseDto enterRoom(@ApiIgnore @AuthenticationPrincipal CustomUserDetails user, @PathVariable("roomId") String roomId) {
        GameSession gameSession = gameSessionService.enterRoom(user, roomId);

        return GameSessionResponseDto.of(gameSession);
    }
}
