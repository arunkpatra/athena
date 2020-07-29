package org.athena.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CardBreakageForecastResponse", description = "Breakage forecast for a card")
public class CardBreakageForecastResponse {

    @ApiModelProperty(value = "Forecasted breakage for a card")
    private final CardBreakageForecast forecast;

    @JsonCreator
    public CardBreakageForecastResponse(CardBreakageForecast forecast) {
        this.forecast = forecast;
    }

    public CardBreakageForecast getForecast() {
        return forecast;
    }
}
