package com.ssafy.webrtc.domain.notice;

import com.ssafy.webrtc.domain.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// void 반환값 수정 필요

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    // 공지사항 전체조회
    public List<NoticeDto> findAll() {
        List<NoticeDto> noticeDtos = new ArrayList<NoticeDto>();
        List<Notice> notices = noticeRepository.findAll();

        for (Notice notice : notices) {
            NoticeDto noticeDto = NoticeDto.ofNoticeDto(notice);
            noticeDtos.add(noticeDto);
        }

        return noticeDtos;
    }

    // 공지사항 글 한개 상세조회
    public NoticeDto findById(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).get();
        NoticeDto noticeDto = NoticeDto.ofNoticeDto(notice);
        return noticeDto;
    }

    // 공지사항 작성
    public void create(NoticeDto noticeDto) {
        // 어떤 유저인지 식별해야함
        String title = noticeDto.getTitle();
        String content = noticeDto.getTitle();
        
        String token = noticeDto.getToken();
        // findByToken 메서드
//        Member member = memberRepository.findByToken(token).get();
        
//        Notice notice = Notice.of(title, content, member);
//        noticeRepository.save(notice); // create
    }

    // 공지사항 수정
    public void update(NoticeDto noticeDto) throws Exception {
        Notice notice = noticeRepository.findById(noticeDto.getId()).orElseThrow(
                () -> new Exception("찾을 수 없습니다"));
        String title = noticeDto.getTitle();
        String content = noticeDto.getContent();

        notice.edit(title, content);

        noticeRepository.save(notice);// update
        
    }

    // 공지사항 삭제
    public void delete(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).get();

        noticeRepository.delete(notice);
    }

}
