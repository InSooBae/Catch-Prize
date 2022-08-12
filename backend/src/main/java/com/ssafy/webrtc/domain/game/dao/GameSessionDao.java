package com.ssafy.webrtc.domain.game.dao;

import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.entity.Player;
import com.ssafy.webrtc.domain.game.enums.GamePhase;
import com.ssafy.webrtc.domain.game.enums.GameState;
import com.ssafy.webrtc.domain.game.enums.RoomType;
import io.openvidu.java.client.OpenViduRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@RedisHash("GameSession")
@Builder
public class GameSessionDao {

    @Id
    private final String roomId;

    private final Map<String, Player> playerMap;

    private final String roomName;

    @Indexed
    private final String creator;

    @Enumerated(EnumType.STRING)
    private final RoomType roomType;

    @Enumerated(EnumType.STRING)
    private final GameState state;

    @Enumerated(EnumType.STRING)
    private final GamePhase phase;

    private final LocalDateTime createTime;

    private final LocalDateTime finishedTime;

    private final String lastEnter;

    private final String sessionId;

    private final String hostName;

    private final int maxParticipants;

    private final Map<String, OpenViduRole> mapSessionNamesTokens;

    public static GameSessionDao of(GameSession gameSession) {

        return GameSessionDao
                .builder()
                .roomId(gameSession.getRoomId())
                .roomType(gameSession.getRoomType())
                .roomName(gameSession.getRoomName())
                .creator(gameSession.getCreator())
                .state(gameSession.getState())
                .phase(gameSession.getPhase())
                .createTime(gameSession.getCreateTime())
                .lastEnter(gameSession.getLastEnter())
                .sessionId(gameSession.getSession().getSessionId())
                .hostName(gameSession.getHostName())
                .finishedTime(gameSession.getFinishedTime())
                .maxParticipants(gameSession.getMaxParticipants())
                .playerMap(gameSession.getPlayerMap())
                .build();
    }

}
