package com.ssafy.webrtc.domain.game.service;

import com.ssafy.webrtc.domain.game.dto.GameSessionJoinResponseDto;
import com.ssafy.webrtc.domain.game.dto.GameSessionRequestDto;
import com.ssafy.webrtc.domain.game.dto.GameSessionResponseDto;
import com.ssafy.webrtc.domain.game.entity.Game;
import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.enums.GameState;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;

import java.util.List;

public interface GameSessionService {

    GameSessionResponseDto makeSession(CustomUserDetails user, GameSessionRequestDto gameSessionRequestDto) throws OpenViduJavaClientException, OpenViduHttpException;;

    public List<GameSession> findAll();
    GameSession enterSession(CustomUserDetails user, String roomId);

    void removeSession(GameSession gameSession);

    public GameSessionJoinResponseDto addUser(String roomId, String nickname);

    public GameSession removeUser(String roomId, String userId);

    GameSession findById(String id);

    void update(GameSession update);

    GameState getGameSessionState(String roomId);


}
