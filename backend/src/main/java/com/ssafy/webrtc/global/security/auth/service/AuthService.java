package com.ssafy.webrtc.global.security.auth.service;

import com.ssafy.webrtc.domain.friend.Friend;
import com.ssafy.webrtc.domain.friend.FriendService;
import com.ssafy.webrtc.domain.friend.repository.EmitterRepository;
import com.ssafy.webrtc.domain.friend.repository.FriendRepository;
import com.ssafy.webrtc.domain.member.MemberRepository;
import com.ssafy.webrtc.domain.member.entity.Member;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import com.ssafy.webrtc.global.security.jwt.JwtTokenProvider;
import com.ssafy.webrtc.global.security.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${app.auth.token.refresh-cookie-key}")
    private String cookieKey;

    private final MemberRepository memberRepository;
    private final JwtTokenProvider tokenProvider;
    private final EmitterRepository emitterRepository;
    private final FriendRepository friendRepository;
    private final FriendService friendService;

    @Transactional
    public String refreshToken(HttpServletRequest request, HttpServletResponse response, String oldAccessToken) {
        // 1. Validation Refresh Token
        String oldRefreshToken = CookieUtil.getCookie(request, cookieKey)
                .map(Cookie::getValue).orElseThrow(() -> new RuntimeException("no Refresh Token Cookie"));

        tokenProvider.validateToken(oldRefreshToken);


        // 2. 유저정보 얻기
        Authentication authentication = tokenProvider.getAuthentication(oldAccessToken);
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        UUID id = user.getId();

        // 3. Match Refresh Token
        String savedToken = memberRepository.getRefreshTokenById(id);

        if (!savedToken.equals(oldRefreshToken)) {
            throw new RuntimeException("Not Matched Refresh Token");
        }

        // 4. JWT 갱신
        String accessToken = tokenProvider.createAccessToken(authentication);
        tokenProvider.createRefreshToken(authentication, response);

        return accessToken;
    }

    @Transactional
    public void logout(UUID memberId) {
        Member member;
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isPresent()) {
            member = memberOptional.get();
            member.logout();
            memberRepository.save(member);

            List<Friend> allFriendsFromMe = friendRepository.findAllFriendsFromMe(memberId);

            allFriendsFromMe.forEach(friend -> {
                friendService.send(friend.getToMember().getId(), friend);
            });
        }
        String id = String.valueOf(memberId);
        emitterRepository.deleteAllStartWithId(id);
        emitterRepository.deleteAllEventCacheStartWithId(id);
    }
}