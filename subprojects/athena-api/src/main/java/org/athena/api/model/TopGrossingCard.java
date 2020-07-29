package org.athena.api.model;

public class TopGrossingCard {

    private final String cardName;
    private final float sales;

    public TopGrossingCard(String cardName, float sales) {
        this.cardName = cardName;
        this.sales = sales;
    }

    public String getCardName() {
        return cardName;
    }

    public float getSales() {
        return sales;
    }
}
