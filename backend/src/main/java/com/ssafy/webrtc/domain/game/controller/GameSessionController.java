package com.ssafy.webrtc.domain.game.controller;

import com.ssafy.webrtc.domain.game.dto.GameSessionLeaveRequestDto;
import com.ssafy.webrtc.domain.game.dto.GameSessionRequestDto;
import com.ssafy.webrtc.domain.game.dto.GameSessionResponseDto;
import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.service.GameSessionService;
import com.ssafy.webrtc.domain.member.MemberRepository;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import com.ssafy.webrtc.global.util.UrlUtils;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequestMapping("/gamesession")
@RestController
@RequiredArgsConstructor
public class GameSessionController {

    private final GameSessionService gameSessionService;

    private final MemberRepository memberRepository;


    @ApiOperation(value = "방 생성", notes = "<strong>RoomName, maxPlayer ...</strong>를 통해 방을 생성한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "사용자 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<GameSession> makeSession(
            @ApiIgnore @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody GameSessionRequestDto gameSessionRequestDto)
            throws OpenViduJavaClientException, OpenViduHttpException {
        return new ResponseEntity<GameSession>(gameSessionService.makeSession(user, gameSessionRequestDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "방 목록 조회", notes = "<strong>방 목록 조회</strong>")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "사용자 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    // FIXME : RESPONSE DTO 내용
    @GetMapping("")
    public ResponseEntity<List<GameSession>> findAll() {
        return new ResponseEntity<>(gameSessionService.findAll(), HttpStatus.OK);
    }

//    @GetMapping("/{roomId}")
//    public GameSessionStateDto showSessionState(@PathVariable("roomId") String roomId) {
//        gameSessionService
//    }

//    @ApiOperation(value = "방 참여", notes = "<strong>RoomName, maxPlayer ...</strong>를 통해 방을 생성한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "사용자 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/{roomId}")
    public ResponseEntity<GameSessionResponseDto> enterSession(
            @ApiIgnore @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable("roomId") String roomId) {
        GameSession gameSession = gameSessionService.enterSession(user, roomId);

        return new ResponseEntity<>(GameSessionResponseDto.of(gameSession), HttpStatus.OK);
    }

//    @ApiOperation(value = "방 생성", notes = "<strong>RoomName, maxPlayer ...</strong>를 통해 방을 생성한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "사용자 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> leaveSession(@PathVariable("roomId") String roomId, @RequestBody GameSessionLeaveRequestDto gameSessionLeaveRequestDto) {
        String token = gameSessionLeaveRequestDto.getToken();
        if (!token.startsWith("wss://") || UrlUtils.getUrlQueryParam(token, "session").isEmpty()
                || UrlUtils.getUrlQueryParam(token, "token").isEmpty()) {
            throw new RuntimeException("잘못된 형식의 토큰입니다. - reqeusted token : " + token);
        }
        gameSessionService.removeUser(roomId, gameSessionLeaveRequestDto.getUserId());

        return new ResponseEntity<>(true, HttpStatus.OK);
    }


}
