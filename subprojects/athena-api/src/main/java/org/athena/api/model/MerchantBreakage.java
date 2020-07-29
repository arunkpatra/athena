package org.athena.api.model;

public class MerchantBreakage {

    private final String merchantName;
    private final double merchantBreakage;

    public MerchantBreakage(String merchantName, double merchantBreakage) {
        this.merchantName = merchantName;
        this.merchantBreakage = merchantBreakage;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public double getMerchantBreakage() {
        return merchantBreakage;
    }
}
