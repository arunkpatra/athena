package org.athena.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "TopSellingCardsByQuantityResponse", description = "Top selling cards by quantity")
public class TopSellingCardsByQuantityResponse {

    @ApiModelProperty(value = "The list of Top selling cards by quantity")
    private final List<TopSellingCardByQuantity> topSellingCardByQuantities;

    @JsonCreator
    public TopSellingCardsByQuantityResponse(List<TopSellingCardByQuantity> topSellingCardByQuantities) {
        this.topSellingCardByQuantities = topSellingCardByQuantities;
    }

    public List<TopSellingCardByQuantity> getTopSellingCardByQuantities() {
        return topSellingCardByQuantities;
    }
}
