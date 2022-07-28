package com.ssafy.webrtc.domain.notice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    // 공지사항 전체조회
    @GetMapping("")
    public ResponseEntity<List<NoticeDto>> findAllNotice() {
        log.info("test123");
        // Exception 처리 추가
        return new ResponseEntity<List<NoticeDto>>(noticeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeDto> findNoticeById(@PathVariable("noticeId") Long noticeId) {
        // Exception 처리 추가
        return new ResponseEntity<NoticeDto>(noticeService.findById(noticeId),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> createNotice(@RequestBody NoticeDto noticeDto) {
        // Exception 처리 추가
        // create 성공 시 success 문자열 반환
        noticeService.create(noticeDto);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/{noticeId}")
    public ResponseEntity<String> updateNotice(@RequestBody NoticeDto noticeDto) throws Exception {
        // Exception 처리 추가(Controller advice 추가 필요)
        // update 성공 시 success 문자열 반환
        noticeService.update(noticeDto);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity<String> deleteNotice(@PathVariable("noticeId") Long noticeId) {
        // Exception 처리 추가
        // delete 성공 시 success 문자열 반환
        noticeService.delete(noticeId);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }


}
