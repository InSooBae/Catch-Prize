package com.ssafy.webrtc.domain.game.dto;

import com.ssafy.webrtc.domain.game.entity.GameSession;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameSessionJoinResponseDto {

    private String token;
    private String userName;

}
