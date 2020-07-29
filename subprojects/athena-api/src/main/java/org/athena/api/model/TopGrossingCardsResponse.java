package org.athena.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "TopGrossingCardsResponse", description = "Top selling cards by volume")

public class TopGrossingCardsResponse {

    @ApiModelProperty(value = "Top grossing cards")
    private final List<TopGrossingCard> topGrossingCards;

    @JsonCreator
    public TopGrossingCardsResponse(List<TopGrossingCard> topGrossingCards) {
        this.topGrossingCards = topGrossingCards;
    }

    public List<TopGrossingCard> getTopGrossingCards() {
        return topGrossingCards;
    }
}
