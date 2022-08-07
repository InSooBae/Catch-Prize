package com.ssafy.webrtc.domain.game.service;

import com.ssafy.webrtc.domain.game.dto.GameSessionRequestDto;
import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;

public interface GameSessionService {

    GameSession makeRoom(CustomUserDetails user, GameSessionRequestDto gameSessionRequestDto);

    GameSession enterRoom(CustomUserDetails user, String roomId);
}
