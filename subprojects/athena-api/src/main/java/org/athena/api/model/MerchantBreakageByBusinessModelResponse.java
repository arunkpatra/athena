package org.athena.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "MerchantBreakageByBusinessModelResponse", description = "Forecasted Breakage for a merchant by business model")
public class MerchantBreakageByBusinessModelResponse {

    @ApiModelProperty(value = "Forecasted breakage for a card")
    private final List<BusinessModelBreakage> cardBreakages;
    private final String merchantCode;

    @JsonCreator
    public MerchantBreakageByBusinessModelResponse(List<BusinessModelBreakage> cardBreakages, String merchantCode) {
        this.cardBreakages = cardBreakages;
        this.merchantCode = merchantCode;
    }

    public List<BusinessModelBreakage> getCardBreakages() {
        return cardBreakages;
    }

    public String getMerchantCode() {
        return merchantCode;
    }
}
