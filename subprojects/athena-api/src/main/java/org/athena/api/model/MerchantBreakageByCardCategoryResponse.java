package org.athena.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "MerchantBreakageByBusinessModelResponse", description = "Forecasted Breakage for a merchant by card category")
public class MerchantBreakageByCardCategoryResponse {

    @ApiModelProperty(value = "Forecasted breakage for a card")
    private final List<CardBreakage> cardBreakages;

    @JsonCreator
    public MerchantBreakageByCardCategoryResponse(List<CardBreakage> cardBreakages) {
        this.cardBreakages = cardBreakages;
    }

    public List<CardBreakage> getCardBreakages() {
        return cardBreakages;
    }
}
