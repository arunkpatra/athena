package org.athena.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "MerchantBreakageByCustomerSegmentResponse", description = "Forecasted Breakage for a merchant by customer segment")
public class MerchantBreakageByCustomerSegmentResponse {

    @ApiModelProperty(value = "Forecasted breakage for a card")
    private final List<CardBreakage> cardBreakages;

    @JsonCreator
    public MerchantBreakageByCustomerSegmentResponse(List<CardBreakage> cardBreakages) {
        this.cardBreakages = cardBreakages;
    }

    public List<CardBreakage> getCardBreakages() {
        return cardBreakages;
    }
}
