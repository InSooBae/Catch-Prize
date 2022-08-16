package com.ssafy.webrtc.domain.game.dto;

import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.enums.RoomType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameSessionResponseDto {
    private String roomId;
    private String roomName;
    private String hostName;
    private int maxParticipants;
    private int participantsCnt;
    private RoomType roomType;
//    private RoomType roomType;

    public static GameSessionResponseDto of(GameSession gameSession) {
        return GameSessionResponseDto
                .builder()
                .roomId(gameSession.getRoomId())
                .roomName(gameSession.getRoomName())
                .roomType(gameSession.getRoomType())
                .hostName(gameSession.getHostName())
                .maxParticipants(gameSession.getMaxParticipants())
                .participantsCnt(gameSession.getPlayerMap().size())
                .build();
    }

}
