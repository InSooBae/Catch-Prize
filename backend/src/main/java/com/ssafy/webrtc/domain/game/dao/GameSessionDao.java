package com.ssafy.webrtc.domain.game.dao;

import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.entity.Player;
import com.ssafy.webrtc.domain.game.enums.AccessType;
import com.ssafy.webrtc.domain.game.enums.GamePhase;
import com.ssafy.webrtc.domain.game.enums.GameState;
import com.ssafy.webrtc.domain.game.enums.RoomType;
import io.openvidu.java.client.OpenViduRole;
import io.openvidu.java.client.Session;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
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

    private final AccessType accessType;

    @Enumerated(EnumType.STRING)
    private final RoomType roomType;

    @Enumerated(EnumType.STRING)
    private final GameState state;

    @Enumerated(EnumType.STRING)
    private final GamePhase phase;

    private final LocalDateTime createTime;

    private final String lastEnter;

    private final Session session;

    private final String masterId;

    private final Map<String, OpenViduRole> mapSessionNamesTokens;

    public static GameSessionDao of(GameSession gameSession) {

        return GameSessionDao
                .builder()
                .roomId(gameSession.getRoomId())
                .roomType(gameSession.getRoomType())
                .accessType(gameSession.getAccessType())
                .roomName(gameSession.getRoomName())
                .creator(gameSession.getCreator())
                .state(gameSession.getState())
                .phase(gameSession.getPhase())
                .createTime(gameSession.getCreateTime())
                .lastEnter(gameSession.getLastEnter())
                .session(gameSession.getSession())
                .masterId(gameSession.getMasterId())
                .mapSessionNamesTokens(gameSession.getMapSessionNamesTokens())
                .playerMap(gameSession.getPlayerMap())
                .build();
    }

}
