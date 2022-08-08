package com.ssafy.webrtc.domain.game.entity;


import io.openvidu.java.client.OpenViduRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 핸드, 땅에 있는 카드로 분리
 */
@Getter
@Setter
@Builder
public class Player implements Serializable {

    @Id
    private final String id;

    private final String nickname;

    // 자기만 보는 카드
    private List<Card> hands = new ArrayList<>();

    // 땅바닥에 깔아서 보여주는 카드
    private List<Card> ground = new ArrayList<>();

    private boolean alive;

    private final String token;

    private final OpenViduRole openViduRole;

    public static Player of(String id, String nickname, String token, OpenViduRole openViduRole) {
        return Player
                .builder()
                .id(id)
                .nickname(nickname)
                .token(token)
                .openViduRole(openViduRole)
                .build();
    }

}
