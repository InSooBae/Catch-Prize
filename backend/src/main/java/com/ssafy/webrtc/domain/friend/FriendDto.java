package com.ssafy.webrtc.domain.friend;

import com.ssafy.webrtc.domain.notice.Notice;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendDto {

    private Long id;

    private UUID toMemberId;

    private boolean pending;

    private boolean isFriend;

    public static FriendDto ofFriendDto(Friend friend) {
        return FriendDto.builder()
                .id(friend.getId())
                .toMemberId(friend.getToMember().getId())
                .pending(friend.isPending())
                .isFriend(friend.isFriend())
                .build();
    }
}
