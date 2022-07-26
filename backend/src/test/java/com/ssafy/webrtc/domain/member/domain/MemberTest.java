package com.ssafy.webrtc.domain.member.domain;

import com.ssafy.webrtc.domain.member.entity.JoinPathType;
import com.ssafy.webrtc.domain.member.entity.Member;
import com.ssafy.webrtc.domain.member.entity.RoleType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;


class MemberTest {

    @Test
    @DisplayName("사용자 생성 검증")
    public void memberCreateValid() throws Exception {
        //given
        Member member = Member.of("test", "fqeqfe1d1jd11", JoinPathType.GOOGLE, RoleType.USER);

        assertAll(
                () -> assertThat(member.getNickname()).isEqualTo("test"),
                () -> assertThat(member.getToken()).isEqualTo("fqeqfe1d1jd11"),
                () -> assertThat(member.getJoinPath()).isEqualTo(JoinPathType.GOOGLE),
                () -> assertThat(member.getRole()).isEqualTo(RoleType.USER),
                () -> assertThat(member.getPoint()).isZero(),
                () -> assertThat(member.isActive()).isTrue()
        );
    }
}