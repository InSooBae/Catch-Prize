package com.ssafy.webrtc.domain.game.entity;

import com.ssafy.webrtc.domain.game.enums.AccessType;
import com.ssafy.webrtc.domain.game.enums.GamePhase;
import com.ssafy.webrtc.domain.game.enums.GameState;
import com.ssafy.webrtc.domain.game.enums.RoomType;
import com.ssafy.webrtc.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
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

    private String roomName;

    private String creator;

    private AccessType accessType;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Enumerated(EnumType.STRING)
    private GameState state;

    @Enumerated(EnumType.STRING)
    private GamePhase phase;

    @CreationTimestamp
    private LocalDateTime createTime;

    private String lastEnter;
}
