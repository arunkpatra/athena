package org.athena.api.model;

public class Merchant {

    private String merchantCode;
    private String merchantName;

    public Merchant(String merchantCode, String merchantName) {
        this.merchantCode = merchantCode;
        this.merchantName = merchantName;
    }

    public Merchant() {
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "merchantCode='" + merchantCode + '\'' +
                ", merchantName='" + merchantName + '\'' +
                '}';
    }
}
