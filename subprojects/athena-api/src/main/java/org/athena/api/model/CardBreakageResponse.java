package org.athena.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "CardBreakageResponse", description = "Breakage by card")
public class CardBreakageResponse {

    @ApiModelProperty(value = "Card breakages")
    private final List<CardBreakage> cardBreakages;

    @JsonCreator
    public CardBreakageResponse(List<CardBreakage> cardBreakages) {
        this.cardBreakages = cardBreakages;
    }

    public List<CardBreakage> getCardBreakages() {
        return cardBreakages;
    }
}
