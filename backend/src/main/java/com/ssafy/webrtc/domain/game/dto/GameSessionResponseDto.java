package com.ssafy.webrtc.domain.game.dto;

import com.ssafy.webrtc.domain.game.entity.GameSession;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameSessionResponseDto {
    private String id;
    private String roomName;
//    private RoomType roomType;

    public static GameSessionResponseDto of(GameSession gameSession) {
        return GameSessionResponseDto
                .builder()
                .id(gameSession.getRoomId())
                .roomName(gameSession.getRoomName())
//                .roomType(gameSession.getRoomType())
                .build();
    }
}
