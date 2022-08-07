package com.ssafy.webrtc.domain.game.entity;

import com.ssafy.webrtc.domain.game.enums.CardGameType;
import com.ssafy.webrtc.domain.game.enums.CardType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardGames {

    private static final int TOTAL_CARD_CNT = 64;
    private Cards cards;

    private CardGameType cardGameType;

    // 정적 팩토리 메서드


    private CardGames(CardGameType cardGameType) {
        this.cards = new Cards();
        this.cardGameType = cardGameType;
    }

    public static CardGames selectCardGame(CardGameType cardGameType) {
        return new CardGames(cardGameType);
    }

    public void start() {
        cards.createCard(TOTAL_CARD_CNT, CardType.values().length);
    }

}
