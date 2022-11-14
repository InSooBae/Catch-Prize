package com.ssafy.webrtc.domain.game.entity;


import io.openvidu.java.client.OpenViduRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * 핸드, 땅에 있는 카드로 분리
 */
@Getter
@Setter
@Builder
public class Player implements Serializable {

    private final UUID id;

    @Id
    private final String nickname;

    private final String token;

    private final OpenViduRole openViduRole;

    public static Player of(UUID id, String nickname, String token, OpenViduRole openViduRole) {
        return Player
                .builder()
                .id(id)
                .nickname(nickname)
                .token(token)
                .openViduRole(openViduRole)
                .build();
    }

}
