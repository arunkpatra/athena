package org.athena.api.model;

public class CardBreakageForecast {

    private final String cardCode;
    private final double breakageForecast;

    public CardBreakageForecast(String cardCode, double breakageForecast) {
        this.cardCode = cardCode;
        this.breakageForecast = breakageForecast;
    }

    public String getCardCode() {
        return cardCode;
    }

    public double getBreakageForecast() {
        return breakageForecast;
    }

    @Override
    public String toString() {
        return "CardBreakageForecast{" +
                "cardCode='" + cardCode + '\'' +
                ", breakageForecast=" + breakageForecast +
                '}';
    }
}
