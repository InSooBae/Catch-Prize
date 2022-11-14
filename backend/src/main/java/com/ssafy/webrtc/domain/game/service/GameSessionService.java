package com.ssafy.webrtc.domain.game.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.ssafy.webrtc.domain.game.dto.GameSessionJoinResponseDto;
import com.ssafy.webrtc.domain.game.dto.GameSessionRequestDto;
import com.ssafy.webrtc.domain.game.dto.GameSessionResponseDto;
import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.enums.GameState;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;

import java.util.List;

public interface GameSessionService {

    GameSession makeSession(CustomUserDetails user, GameSessionRequestDto gameSessionRequestDto) throws OpenViduJavaClientException, OpenViduHttpException;;

    List<GameSessionResponseDto> findAll();

    void removeSession(GameSession gameSession);

    GameSessionJoinResponseDto addUser(String roomId, CustomUserDetails user);

    GameSession removeUser(String roomId, String userName);

    GameSession findById(String id);

    void update(GameSession update);

    GameState getGameSessionState(String roomId);

    GameSession startSession(GameSession gameSession);

    GameSession endSession(GameSession gameSession);
}
