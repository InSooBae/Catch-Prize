package com.ssafy.webrtc.domain.friend;

import com.ssafy.webrtc.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "friend")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
// FIXME: 우선 Setter 열어두고 개발 추후 생성자로 변경 예정
@Setter
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fr_from_member", referencedColumnName = "member_id", nullable = false, updatable = false)
    private Member fromMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fr_to_member", referencedColumnName = "member_id", nullable = false, updatable = false)
    private Member toMemberId;

    @Column(name = "accept", columnDefinition = "boolean default false")
    private boolean isAccept;
}
