package com.ssafy.webrtc.domain.game.stomp.dto;

import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.stomp.enums.StompMessageType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class GameSessionStompLeaveResponseDto {

    private final StompMessageType type = StompMessageType.LEAVE;
    private final String hostId;
    private final String leftPlayerId;

    public static GameSessionStompLeaveResponseDto of(String hostId, String leftPlayerId) {
        return GameSessionStompLeaveResponseDto
                .builder()
                .hostId(hostId)
                .leftPlayerId(leftPlayerId)
                .build();
    }
}
