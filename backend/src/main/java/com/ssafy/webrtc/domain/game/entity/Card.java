package com.ssafy.webrtc.domain.game.entity;

import com.ssafy.webrtc.domain.game.enums.CardType;

public class Card {

    private int id;

    private CardType cardType;

    public Card(int id, CardType cardType) {
        this.id = id;
        this.cardType = cardType;
    }
}
