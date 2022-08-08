package com.ssafy.webrtc.domain.game.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameSessionJoinResponseDto {

    private String token;
    private String userId;
}
