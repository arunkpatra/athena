package org.athena.api.model;

public class CardBreakage {

    private final String cardName;
    private final double cardBreakage;

    public CardBreakage(String cardName, double cardBreakage) {
        this.cardName = cardName;
        this.cardBreakage = cardBreakage;
    }

    public String getCardName() {
        return cardName;
    }

    public double getCardBreakage() {
        return cardBreakage;
    }
}
