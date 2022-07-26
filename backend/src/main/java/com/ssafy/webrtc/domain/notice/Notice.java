package com.ssafy.webrtc.domain.notice;

import com.ssafy.webrtc.domain.member.entity.Member;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Notice(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.regDate = LocalDateTime.now();
        // 작성자 찾아와서 초기화
        this.member = member;
    }

    public static Notice of(String title, String content, Member member) {
        return new Notice(title, content, member);
    }

    public void edit(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
