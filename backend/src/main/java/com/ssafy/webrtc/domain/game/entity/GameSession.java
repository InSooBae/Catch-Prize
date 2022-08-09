package com.ssafy.webrtc.domain.game.entity;

import com.ssafy.webrtc.domain.game.enums.GamePhase;
import com.ssafy.webrtc.domain.game.enums.GameState;
import com.ssafy.webrtc.domain.game.enums.RoomType;
import io.openvidu.java.client.Session;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Map;

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
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @NonNull
    @Enumerated(EnumType.STRING)
    private GameState state;

    @Enumerated(EnumType.STRING)
    private GamePhase phase;

    @NonNull
//    @CreationTimestamp
    private LocalDateTime createTime;

    @NonNull
    private LocalDateTime finishedTime;

    private String lastEnter;

    @NonNull
    private final Session session;

    private String hostId;

    private int maxParticipants;



    public static GameSessionBuilder builder(String roomId, String creator, String roomName, LocalDateTime createTime, Session session, Map<String, Player> playerMap) {

        return new GameSessionBuilder()
                .roomId(roomId)
                .roomName(roomName)
                .creator(creator)
                .state(GameState.WAIT)
                .createTime(createTime)
                .session(session)
                .playerMap(playerMap);
    }

}
