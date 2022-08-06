package com.ssafy.webrtc.domain.member;

import com.ssafy.webrtc.domain.member.entity.Member;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


/**
 * Test Class
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public MemberDto getCurrentUser(@AuthenticationPrincipal CustomUserDetails user) {
        MemberDto byLoginMember = memberService.findByLoginMember(user.getId());


        return byLoginMember;
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkDuplicateNickname(String nickname) {
        Long isDuplicate = memberService.findByNickname(nickname);

        return new ResponseEntity<>(isDuplicate, HttpStatus.OK);

    }
}