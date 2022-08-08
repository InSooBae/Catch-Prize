package com.ssafy.webrtc.domain.game.dto;

import com.ssafy.webrtc.domain.game.enums.AccessType;
import com.ssafy.webrtc.domain.game.enums.RoomType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameSessionRequestDto {

    RoomType roomType;

    AccessType accessType;

    String roomName;
}
