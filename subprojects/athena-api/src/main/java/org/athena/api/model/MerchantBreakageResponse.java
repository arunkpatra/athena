package org.athena.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "MerchantBreakageResponse", description = "Breakage by merchant")
public class MerchantBreakageResponse {

    @ApiModelProperty(value = "Merchant breakages")
    private final List<MerchantBreakage> merchantBreakages;

    @JsonCreator
    public MerchantBreakageResponse(List<MerchantBreakage> merchantBreakages) {
        this.merchantBreakages = merchantBreakages;
    }

    public List<MerchantBreakage> getMerchantBreakages() {
        return merchantBreakages;
    }
}
