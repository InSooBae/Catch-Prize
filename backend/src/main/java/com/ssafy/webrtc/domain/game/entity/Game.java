package com.ssafy.webrtc.domain.game.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "game")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
// FIXME: 우선 Setter 열어두고 개발 추후 생성자로 변경 예정
@Setter
public class Game {

    @Id @GeneratedValue
    @Column(name = "game_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "min_participant")
    private Integer minParticipant;

    @Column(name = "max_participant")
    private Integer maxParticipant;
}
