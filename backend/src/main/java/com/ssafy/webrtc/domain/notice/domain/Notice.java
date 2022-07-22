package com.ssafy.webrtc.domain.notice.domain;

import com.ssafy.webrtc.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
// FIXME: 우선 Setter 열어두고 개발 추후 생성자로 변경 예정
@Setter
public class Notice {

    @Id
    @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
