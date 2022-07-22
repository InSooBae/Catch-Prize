package com.ssafy.webrtc.domain.membergame.domain;

import com.ssafy.webrtc.domain.game.domain.Game;
import com.ssafy.webrtc.domain.member.domain.Member;

import javax.persistence.*;

@Entity
public class MemberGame {

    @Id @GeneratedValue
    @Column(name = "member_game_id")
    private Long id;

    @Column(name = "win_cnt")
    private Integer winCnt;

    @Column(name = "lose_cnt")
    private Integer loseCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;


}
