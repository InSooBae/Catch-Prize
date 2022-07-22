package com.ssafy.webrtc.domain.member.entity;

import com.fasterxml.uuid.Generators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"nickname"})
// FIXME: 우선 Setter 열어두고 개발 추후 생성자로 변경 예정
@Setter
public class Member {

    @Id
    @Column(name = "member_id", columnDefinition = "binary(16)", updatable = false)
    private UUID id;

    @Column(name = "nickname", length = 20)
    private String nickname;

    @Column(name = "token")
    private String token;

    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "join_path")
    private JoinPathType joinPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleType role;

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean isActive;

    @Column(name = "point", columnDefinition = "integer default 0")
    private Integer point;

    @PrePersist
    public void createMemberUniqueId() {
        //sequential uuid 생성
        String uuidStr = getUUIDString();
        UUID uuid;
        uuid = buildUUID(uuidStr);
        this.id = uuid;
    }

    private UUID buildUUID(String uuidStr) {
        UUID uuid;
        StringBuffer sb = new StringBuffer(uuidStr);

        sb.insert(8, "-");
        sb.insert(13, "-");
        sb.insert(18, "-");
        sb.insert(23, "-");

        uuid = UUID.fromString(sb.toString());
        return uuid;
    }

    private String getUUIDString() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        String[] uuidArr = uuid.toString().split("-");
        return uuidArr[2] + uuidArr[1] + uuidArr[0] + uuidArr[3] + uuidArr[4];
    }
}
