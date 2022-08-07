package com.ssafy.webrtc.domain.game.entity;

import com.ssafy.webrtc.domain.game.enums.CardType;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final CardType[] cardTypes = CardType.values();

    private List<Card> cardList = new ArrayList<>();

    // 플레이어 인원에 따라 카드의 수를 조정(추후) 64 장
    public void createCard(int cardCnt, int categoryCnt) {
        for (int i = 0; i < cardCnt; i++) {

            this.cardList.add(new Card(i, cardTypes[i / categoryCnt]));
        }
    }
}
