package org.athena.api.model;

public class TopSellingCardByQuantity {

    private final String cardName;
    private final long quantity;

    public TopSellingCardByQuantity(String cardName, long quantity) {
        this.cardName = cardName;
        this.quantity = quantity;
    }

    public String getCardName() {
        return cardName;
    }

    public long getQuantity() {
        return quantity;
    }
}
