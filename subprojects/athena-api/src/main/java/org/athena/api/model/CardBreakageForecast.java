package org.athena.api.model;

public class CardBreakageForecast {

    private final String cardCode;
    private final String cardName;
    private final double breakageForecast;

    public CardBreakageForecast(String cardCode, String cardName, double breakageForecast) {
        this.cardCode = cardCode;
        this.cardName = cardName;
        this.breakageForecast = breakageForecast;
    }

    public String getCardCode() {
        return cardCode;
    }

    public String getCardName() {
        return cardName;
    }

    public double getBreakageForecast() {
        return breakageForecast;
    }
}
