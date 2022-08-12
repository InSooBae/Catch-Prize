package com.ssafy.webrtc.domain.game.stomp.handler;

import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.enums.GameState;
import com.ssafy.webrtc.domain.game.service.GameSessionService;
import com.ssafy.webrtc.domain.game.stomp.dto.GameSessionStompJoinResponseDto;
import com.ssafy.webrtc.domain.game.stomp.dto.GameSessionStompLeaveResponseDto;
import com.ssafy.webrtc.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class RoomController {

    private final GameSessionService gameSessionService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final JwtTokenProvider jwtTokenProvider;

    @MessageMapping("/{roomId}/join")
    public void joinGameSession(@DestinationVariable String roomId, @Header("Authorization") String token) {
        String userNameFromJwt = jwtTokenProvider.getUserNameFromJwt(token);
        GameSessionStompJoinResponseDto res = GameSessionStompJoinResponseDto.of(gameSessionService.findById(roomId));
        simpMessagingTemplate.convertAndSend("/sub/" + roomId, res);
    }

    @MessageMapping("/{roomId}/leave")
    public void leaveGameSession(@DestinationVariable String roomId, @Header("Authorization") String token) {

        String userNameFromJwt = jwtTokenProvider.getUserNameFromJwt(token);

        GameSession gameSession = gameSessionService.removeUser(roomId, userNameFromJwt);
        GameSessionStompLeaveResponseDto res = GameSessionStompLeaveResponseDto.of(gameSession.getHostName(), userNameFromJwt);
        simpMessagingTemplate.convertAndSend("/sub/" + roomId, res);
    }


    // 방장이 시작하면 시작할건지, 그냥 레디 다 하고 방장이 시작하면 할건지
    @MessageMapping("/{roomId}/start")
    public void startGame(@DestinationVariable String roomId, @Header("Authorization") String token) {
        String userNameFromJwt = jwtTokenProvider.getUserNameFromJwt(token);
        // 방장이 시작했는지 확인
        GameSession gameSession = gameSessionService.findById(roomId);

        if (gameSession.getState() == GameState.STARTED || !userNameFromJwt.equals(gameSession.getHostName())) {
            return;
        }

        // 게임 시작 (게임 서버에 데이터 보내기)
    }
}
