package org.athena.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "MerchantBreakageByBusinessModelResponse", description = "Breakage for a merchant by business model")
public class MerchantBreakageByBusinessModelResponse {

    @ApiModelProperty(value = "Breakage for a business model")
    private final List<BusinessModelBreakage> businessModelBreakages;
    private final String merchantCode;

    @JsonCreator
    public MerchantBreakageByBusinessModelResponse(List<BusinessModelBreakage> businessModelBreakages, String merchantCode) {
        this.businessModelBreakages = businessModelBreakages;
        this.merchantCode = merchantCode;
    }

    public List<BusinessModelBreakage> getBusinessModelBreakages() {
        return businessModelBreakages;
    }

    public String getMerchantCode() {
        return merchantCode;
    }
}
