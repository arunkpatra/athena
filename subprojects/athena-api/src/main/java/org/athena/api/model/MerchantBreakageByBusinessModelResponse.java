package org.athena.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "MerchantBreakageByBusinessModelResponse", description = "Forecasted Breakage for a merchant by business model")
public class MerchantBreakageByBusinessModelResponse {

    @ApiModelProperty(value = "Forecasted breakage for a card")
    private final List<CardBreakage> cardBreakages;

    @JsonCreator
    public MerchantBreakageByBusinessModelResponse(List<CardBreakage> cardBreakages) {
        this.cardBreakages = cardBreakages;
    }

    public List<CardBreakage> getCardBreakages() {
        return cardBreakages;
    }
}
