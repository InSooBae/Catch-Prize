package com.ssafy.webrtc.domain.game.stomp.entity;

import java.security.Principal;

public class PlayerStompPrincipal implements Principal {
    private final String name;

    public PlayerStompPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}