package com.ssafy.webrtc.domain.notice;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime regDate;

    private String token;

    public static NoticeDto ofNoticeDto(Notice notice) {
        return NoticeDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .regDate(notice.getRegDate())
                .token(notice.getMember().getToken())
                .build();
    }

}
