package com.ssafy.webrtc.domain.game.stomp.handler;

import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.service.GameSessionService;
import com.ssafy.webrtc.domain.game.stomp.dto.GameSessionStompJoinResponseDto;
import com.ssafy.webrtc.domain.game.stomp.dto.GameSessionStompLeaveResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class RoomHandler {

    private final GameSessionService gameSessionService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/{roomId}/join")
    public void joinGameSession(@DestinationVariable String roomId) {
        GameSessionStompJoinResponseDto res = GameSessionStompJoinResponseDto.of(gameSessionService.findById(roomId));
        simpMessagingTemplate.convertAndSend("/sub/" + roomId, res);
    }

    @MessageMapping("/{roomId}/leave")
    public void leaveGameSession(SimpMessageHeaderAccessor accessor,
                                 @DestinationVariable String roomId) {
        String playerId = accessor.getUser().getName();

        GameSession gameSession = gameSessionService.removeUser(roomId, playerId);
        GameSessionStompLeaveResponseDto res = GameSessionStompLeaveResponseDto.of(gameSession.getHostId(), playerId);
        simpMessagingTemplate.convertAndSend("/sub/" + roomId, res);
    }
}
