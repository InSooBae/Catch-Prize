package com.ssafy.webrtc.domain.game.entity;

import com.ssafy.webrtc.domain.game.enums.AccessType;
import com.ssafy.webrtc.domain.game.enums.GamePhase;
import com.ssafy.webrtc.domain.game.enums.GameState;
import com.ssafy.webrtc.domain.game.enums.RoomType;
import com.ssafy.webrtc.domain.member.entity.Member;
import io.openvidu.java.client.Session;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RedisHash("GameSession")
@Builder
public class GameSession {

    @Id
    private String roomId;

    private List<Player> players;

    @NonNull
    private String roomName;

    @NonNull
    private String creator;

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
    @CreationTimestamp
    private LocalDateTime createTime;

    private String lastEnter;

    @NonNull
    private final Session session;
}
