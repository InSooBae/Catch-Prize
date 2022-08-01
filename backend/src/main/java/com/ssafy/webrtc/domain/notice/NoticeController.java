package com.ssafy.webrtc.domain.notice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    // 공지사항 전체조회
//    @GetMapping("")
//    public ResponseEntity<List<NoticeDto>> findAllNotice() {
//
//        return new ResponseEntity<List<NoticeDto>>(noticeService.findAll(), HttpStatus.OK);
//    }
    
    // 공지사항 글 상세조회
    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeDto> findNoticeById(@PathVariable("noticeId") Long noticeId) {

        return new ResponseEntity<NoticeDto>(noticeService.findById(noticeId),HttpStatus.OK);
    }

    // 공지사항 페이징 조회
    @GetMapping("")
    public ResponseEntity<List<NoticeDto>> findNoticeByPgno(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        PageRequest pageRequest = PageRequest.of(page -1, size); // page index가 내부적으로 0부터 시작
        return new ResponseEntity<List<NoticeDto>>(noticeService.findByPgno(pageRequest).getContent(), HttpStatus.OK);
    }

    // 공지사항 글 작성
    @PostMapping("")
    public ResponseEntity<Long> createNotice(@RequestBody NoticeDto noticeDto) {
        // create 성공 시 작성 글의 id 반환
        Long noticeId = noticeService.create(noticeDto);

        return new ResponseEntity<Long>(noticeId, HttpStatus.OK);
    }
    
    // 공지사항 글 수정
    @PutMapping("/{noticeId}")
    public ResponseEntity<Long> updateNotice(@RequestBody NoticeDto noticeDto) {
        // update 성공 시 수정된 글의 id 반환
        Long noticeId = noticeService.update(noticeDto);
        return new ResponseEntity<Long>(noticeId, HttpStatus.OK);
    }

    // 공지사항 글 삭제
    @DeleteMapping("/{noticeId}")
    public ResponseEntity<Long> deleteNotice(@PathVariable("noticeId") Long noticeId) {
        // delete 성공 시 삭제된 글의 id 반환
        noticeService.delete(noticeId);
        return new ResponseEntity<Long>(noticeId, HttpStatus.OK);
    }


}
