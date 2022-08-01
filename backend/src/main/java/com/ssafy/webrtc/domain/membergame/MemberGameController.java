package com.ssafy.webrtc.domain.membergame;

import com.ssafy.webrtc.domain.notice.NoticeDto;
import com.ssafy.webrtc.domain.notice.NoticeService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/membergame")
public class MemberGameController {

    private final MemberGameService memberGameService;

    // 특정 유저의 최근 전적 조회
    @GetMapping("")
    public ResponseEntity<List<MemberGameDto>> findRecentMatchResult(@RequestParam("size") Integer size) {

        return new ResponseEntity<List<MemberGameDto>>(memberGameService.findRecentMatchResult(size) ,HttpStatus.OK);
    }

    // 특정 유저의 최근 전적에 추가
    @PostMapping("")
    public ResponseEntity<Long> createMemberGame(@RequestBody MemberGameDto memberGameDto) {
        return new ResponseEntity<Long>(memberGameService.create(memberGameDto) , HttpStatus.OK);
    }

}
