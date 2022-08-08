package com.ssafy.webrtc.domain.game.dto;

import lombok.Getter;

@Getter
public class GameSessionLeaveRequestDto {
    private String token;
    private String userId;
}
