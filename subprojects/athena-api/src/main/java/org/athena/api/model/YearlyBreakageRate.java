package org.athena.api.model;

public class YearlyBreakageRate {

    private final String cartTypeCode;
    private final float breakageRate;
    private final int year;

    public YearlyBreakageRate(String cartTypeCode, float breakageRate, int year) {
        this.cartTypeCode = cartTypeCode;
        this.breakageRate = breakageRate;
        this.year = year;
    }

    public String getCartTypeCode() {
        return cartTypeCode;
    }

    public float getBreakageRate() {
        return breakageRate;
    }

    public int getYear() {
        return year;
    }
}
