package com.ssafy.webrtc.domain.membergame;

import com.ssafy.webrtc.domain.game.Game;
import com.ssafy.webrtc.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "member_game")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
// FIXME: 우선 Setter 열어두고 개발 추후 생성자로 변경 예정
@Setter
public class MemberGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_game_id")
    private Long id;

    @Column(name = "win_cnt")
    private int winCnt;

    @Column(name = "lose_cnt")
    private int loseCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;
}
