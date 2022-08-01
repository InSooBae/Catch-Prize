package com.ssafy.webrtc.domain.membergame;

import com.ssafy.webrtc.domain.game.Game;
import com.ssafy.webrtc.domain.game.GameDto;
import com.ssafy.webrtc.domain.game.GameRepository;
import com.ssafy.webrtc.domain.member.MemberRepository;
import com.ssafy.webrtc.domain.member.entity.Member;
import com.ssafy.webrtc.domain.membergame.entity.MemberGame;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberGameService {

    private final MemberGameRepository memberGameRepository;

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    // 특정 유저의 최근전적 size 개수만큼 받기
    public List<MemberGameDto> findRecentMatchResult(Integer size) {
        // 어떤 유저인지 식별해야함
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails)principal;

        UUID memberId = ((CustomUserDetails) principal).getId();

        List<MemberGameDto> memberGameDtos = memberGameRepository.findByMemberIdOrOrderByCreateDateDesc(memberId, size);

        return memberGameDtos;
    }

    // 특정 유저의 전적 추가
    public Long create(MemberGameDto memberGameDto) {
        // 어떤 유저인지 식별해야함
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails)principal;

        UUID memberId = ((CustomUserDetails) principal).getId();

        MemberGame memberGame = modelMapper.map(memberGameDto, MemberGame.class);

        return memberGameRepository.save(memberGame).getId();
    }

}
