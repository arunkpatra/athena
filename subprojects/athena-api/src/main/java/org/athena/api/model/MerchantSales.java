package org.athena.api.model;

public class MerchantSales {
    private final String merchantName;
    private final double merchantSales;

    public MerchantSales(String merchantName, double merchantSales) {
        this.merchantName = merchantName;
        this.merchantSales = merchantSales;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public double getMerchantSales() {
        return merchantSales;
    }
}
