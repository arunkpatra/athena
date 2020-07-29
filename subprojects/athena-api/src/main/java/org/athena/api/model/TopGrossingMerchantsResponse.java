package org.athena.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "TopGrossingMerchantsResponse", description = "Top grossing merchants by sales")
public class TopGrossingMerchantsResponse {

    @ApiModelProperty(value = "Top grossing merchants")
    private final List<MerchantSales> merchantSales;

    @JsonCreator
    public TopGrossingMerchantsResponse(List<MerchantSales> merchantSales) {
        this.merchantSales = merchantSales;
    }

    public List<MerchantSales> getMerchantSales() {
        return merchantSales;
    }
}
