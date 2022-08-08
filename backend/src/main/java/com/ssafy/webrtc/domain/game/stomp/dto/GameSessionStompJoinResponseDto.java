package com.ssafy.webrtc.domain.game.stomp.dto;

import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.stomp.enums.StompMessageType;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
public class GameSessionStompJoinResponseDto {

  private final StompMessageType type = StompMessageType.JOIN;
  private final String hostId;
  private final Map<String, PlayerStompJoinResponseDto> playerMap;

  public static GameSessionStompJoinResponseDto of(GameSession gameSession) {
    Map<String, PlayerStompJoinResponseDto> newPlayerMap = new HashMap<>();
    gameSession.getPlayerMap().forEach((playerId, player) ->
        newPlayerMap.put(playerId, PlayerStompJoinResponseDto.of(player))
    );
    return new GameSessionStompJoinResponseDto(gameSession.getHostId(), newPlayerMap);
  }
}