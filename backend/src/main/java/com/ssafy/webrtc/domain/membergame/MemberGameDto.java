package com.ssafy.webrtc.domain.membergame;

import com.ssafy.webrtc.domain.game.Game;
import com.ssafy.webrtc.domain.member.entity.Member;
import com.ssafy.webrtc.domain.membergame.entity.MatchResultType;
import com.ssafy.webrtc.domain.membergame.entity.MemberGame;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberGameDto {

    private Long id;

    private MatchResultType matchResult;

    private LocalDateTime matchDate;

    private Member member;

    private Game game;

    public static MemberGameDto ofMemberGameDto(MemberGame memberGame) {
        return MemberGameDto.builder()
                .id(memberGame.getId())
                .matchResult(memberGame.getMatchResult())
                .matchDate(memberGame.getCreateDate())
                .member(memberGame.getMember())
                .game(memberGame.getGame())
                .build();
    }

}
