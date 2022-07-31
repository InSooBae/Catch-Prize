package com.ssafy.webrtc.domain.notice;

import com.ssafy.webrtc.domain.member.MemberRepository;
import com.ssafy.webrtc.domain.member.entity.Member;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

// void 반환값 수정 필요

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    // 공지사항 전체조회
//    public List<NoticeDto> findAll() {
//        List<Notice> notices = noticeRepository.findAll();
//
//        List<NoticeDto> noticeDtos = notices.stream().map(notice ->
//                modelMapper.map(notice, NoticeDto.class)).collect(Collectors.toList());
//
//
//        return noticeDtos;
//    }

    // 공지사항 글 한개 상세조회
    public NoticeDto findById(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).get();
//      NoticeDto noticeDto = NoticeDto.ofNoticeDto(notice);
        NoticeDto noticeDto = modelMapper.map(notice, NoticeDto.class);
        return noticeDto;
    }

    // 공지사항 글 페이징 조회
    public Page<NoticeDto> findByPgno(Pageable pageable) {
        // 페이지 넘버는 0페이지부터 시작
        Page<Notice> notices = noticeRepository.findAllByOrderByRegDateDesc(pageable);

        Page<NoticeDto> noticeDtos = notices.map(persistedNotice -> {
           NoticeDto noticeDto = NoticeDto.ofNoticeDto(persistedNotice);

           return noticeDto;
        });

        return noticeDtos;
    }

    // 공지사항 작성
    public Long create(NoticeDto noticeDto) {
        String title = noticeDto.getTitle();
        String content = noticeDto.getTitle();

        // 어떤 유저인지 식별해야함
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails)principal;

        UUID memberId = ((CustomUserDetails) principal).getId();

        Member member = memberRepository.findById(memberId).get();
        
        Notice notice = Notice.of(title, content, member);

        return noticeRepository.save(notice).getId();
    }

    // 공지사항 수정
    public Long update(NoticeDto noticeDto) {
        Notice notice = noticeRepository.findById(noticeDto.getId()).get();
        String title = noticeDto.getTitle();
        String content = noticeDto.getContent();

        notice.edit(title, content);

        return noticeRepository.save(notice).getId();
    }

    // 공지사항 삭제
    public void delete(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).get();

        noticeRepository.delete(notice);
    }

}
