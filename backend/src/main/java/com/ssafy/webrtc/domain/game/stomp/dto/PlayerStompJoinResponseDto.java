package com.ssafy.webrtc.domain.game.stomp.dto;

import com.ssafy.webrtc.domain.game.entity.Player;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlayerStompJoinResponseDto {
    private final String nickname;

    public static PlayerStompJoinResponseDto of(Player player) {
        return PlayerStompJoinResponseDto
                .builder()
                .nickname(player.getNickname())
                .build();
    }
}
