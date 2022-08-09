package com.ssafy.webrtc.domain.game.dto;

import com.ssafy.webrtc.domain.game.enums.RoomType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameSessionRequestDto {

    private RoomType roomType;

    private int maxParticipants;

    private String roomName;
}
