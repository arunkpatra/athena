package org.athena.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "MerchantBreakageByCardMediumResponse", description = "Forecasted Breakage for a merchant by card medium (physical, digital)")
public class MerchantBreakageByCardMediumResponse {

    @ApiModelProperty(value = "Forecasted breakage for a card")
    private final List<CardBreakage> cardBreakages;

    @JsonCreator
    public MerchantBreakageByCardMediumResponse(List<CardBreakage> cardBreakages) {
        this.cardBreakages = cardBreakages;
    }

    public List<CardBreakage> getCardBreakages() {
        return cardBreakages;
    }
}
