package com.ssafy.webrtc.domain.game.entity;

import com.ssafy.webrtc.domain.game.dao.GameSessionDao;
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

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
public class GameSession {

    @NonNull
    private final String roomId;

    private final Map<String, Player> playerMap;

    @NonNull
    private String roomName;

    @NonNull
    private final String creator;

    @NonNull
    private AccessType accessType;

    @NonNull
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @NonNull
    @Enumerated(EnumType.STRING)
    private GameState state = GameState.WAIT;

    @Enumerated(EnumType.STRING)
    private GamePhase phase;

    @NonNull
//    @CreationTimestamp
    private LocalDateTime createTime;

    private String lastEnter;

    @NonNull
    private final Session session;

    @NonNull
    private String hostId;

    private int maxParticipants;

    private final Map<String, OpenViduRole> mapSessionNamesTokens;

    public static GameSession of(GameSessionDao gameSessionDao) {
        return GameSession
                .builder()
                .roomId(gameSessionDao.getRoomId())
                .roomType(gameSessionDao.getRoomType())
                .accessType(gameSessionDao.getAccessType())
                .roomName(gameSessionDao.getRoomName())
                .creator(gameSessionDao.getCreator())
                .state(gameSessionDao.getState())
                .phase(gameSessionDao.getPhase())
                .createTime(gameSessionDao.getCreateTime())
                .lastEnter(gameSessionDao.getLastEnter())
                .session(gameSessionDao.getSession())
                .hostId(gameSessionDao.getHostId())
                .mapSessionNamesTokens(gameSessionDao.getMapSessionNamesTokens())
                .playerMap(gameSessionDao.getPlayerMap())
                .build();
    }

}
