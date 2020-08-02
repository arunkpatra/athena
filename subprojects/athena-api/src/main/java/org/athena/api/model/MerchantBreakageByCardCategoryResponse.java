package org.athena.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "MerchantBreakageByCardCategoryResponse", description = "Breakage for a merchant by card category")
public class MerchantBreakageByCardCategoryResponse {

    @ApiModelProperty(value = "Breakage for a card category")
    private final List<CardCategoryBreakage> cardCategoryBreakages;

    @JsonCreator
    public MerchantBreakageByCardCategoryResponse(List<CardCategoryBreakage> cardCategoryBreakages) {
        this.cardCategoryBreakages = cardCategoryBreakages;
    }

    public List<CardCategoryBreakage> getCardCategoryBreakages() {
        return cardCategoryBreakages;
    }
}
