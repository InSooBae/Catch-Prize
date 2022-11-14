package com.ssafy.webrtc.domain.member;

import com.ssafy.webrtc.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberDto findByLoginMember(UUID memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalStateException("유저 존재 안함"));
//        member.login();
//        memberRepository.save(member);

        return MemberDto.builder()
                .username(member.getNickname())
                .build();
    }

    @Transactional(readOnly = true)
    public Long findByNickname(String nickname) {
        Optional<Member> optionalMember = memberRepository.findByNickname(nickname);

        if (optionalMember.isPresent()) {
            // 중복
            return 1L;
        } else {
            // 중복 아님
            return 0L;
        }
    }
}
