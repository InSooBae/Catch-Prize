package com.ssafy.webrtc.domain.game.stomp.dto;

import com.ssafy.webrtc.domain.game.stomp.enums.StompMessageType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameSessionStompLeaveResponseDto {

    private final StompMessageType type = StompMessageType.LEAVE;
    private final String hostName;
    private final String leftPlayerName;

    public static GameSessionStompLeaveResponseDto of(String hostName, String leftPlayerName) {
        return GameSessionStompLeaveResponseDto
                .builder()
                .hostName(hostName)
                .leftPlayerName(leftPlayerName)
                .build();
    }
}
