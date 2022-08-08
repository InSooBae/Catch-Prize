package com.ssafy.webrtc.domain.game.dto;

import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.enums.AccessType;
import com.ssafy.webrtc.domain.game.enums.RoomType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameSessionResponseDto {
    private String id;
    private AccessType accessType;
    private String roomName;
    private RoomType roomType;

    public static GameSessionResponseDto of(GameSession gameSession) {
        return GameSessionResponseDto
                .builder()
                .id(gameSession.getRoomId())
                .accessType(gameSession.getAccessType())
                .roomName(gameSession.getRoomName())
                .roomType(gameSession.getRoomType())
                .build();
    }
}
