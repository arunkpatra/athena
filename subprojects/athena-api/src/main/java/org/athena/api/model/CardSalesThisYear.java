package org.athena.api.model;

public class CardSalesThisYear {

    private final String cardTypeCode;
    private final double totalSales;
    private final int year;

    public CardSalesThisYear(String cardTypeCode, double totalSales, int year) {
        this.cardTypeCode = cardTypeCode;
        this.totalSales = totalSales;
        this.year = year;
    }

    public String getCardTypeCode() {
        return cardTypeCode;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public int getYear() {
        return year;
    }
}
