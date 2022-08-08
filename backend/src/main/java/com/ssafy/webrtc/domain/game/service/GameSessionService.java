package com.ssafy.webrtc.domain.game.service;

import com.ssafy.webrtc.domain.game.dto.GameSessionJoinResponseDto;
import com.ssafy.webrtc.domain.game.dto.GameSessionRequestDto;
import com.ssafy.webrtc.domain.game.entity.Game;
import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;

public interface GameSessionService {

    GameSession makeSession(CustomUserDetails user, GameSessionRequestDto gameSessionRequestDto) throws OpenViduJavaClientException, OpenViduHttpException;;

    GameSession enterSession(CustomUserDetails user, String roomId);

    void removeSession(GameSession gameSession);

    public GameSessionJoinResponseDto addUser(String roomId, String nickname);

    public boolean removeUser(String roomId, String token);

    GameSession findById(String id);

    void update(GameSession update);

}
