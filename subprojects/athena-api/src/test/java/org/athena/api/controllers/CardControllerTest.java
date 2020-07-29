package org.athena.api.controllers;

import org.athena.api.AbstractTest;
import org.athena.api.model.ErrorResponse;
import org.athena.api.model.TopGrossingCardsResponse;
import org.athena.api.model.TopSellingCardsByQuantityResponse;
import org.athena.api.queries.NativeQueries;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CardControllerTest extends AbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardControllerTest.class);

    @Test
    public void getTopSellingCardsByQuantityTest() throws Exception {
        LOGGER.info("Query: " + NativeQueries.TOP_SELLING_CARDS_QUANTITY);
        TopSellingCardsByQuantityResponse response = mockHttpExchange(
                get("/api/card/topselling"),
                status().isOk(),
                Optional.empty(),
                Optional.empty(),
                TopSellingCardsByQuantityResponse.class);
        Assert.assertTrue("Empty response is unexpected", response.getTopSellingCardByQuantities().size() != 0);
        Assert.assertNotNull("Expected nun null value", response.getTopSellingCardByQuantities().get(0).getCardName());
        Assert.assertTrue("Was not expecting negative value", response.getTopSellingCardByQuantities().get(0).getQuantity() >= 0);
    }

    @Test
    public void getTopGrossingCardsTest() throws Exception {
        LOGGER.info("Query: " + NativeQueries.TOP_GROSSING_CARDS);
        TopGrossingCardsResponse response = mockHttpExchange(
                get("/api/card/topgrossing"),
                status().isOk(),
                Optional.empty(),
                Optional.empty(),
                TopGrossingCardsResponse.class);
        Assert.assertTrue("Empty response is unexpected", response.getTopGrossingCards().size() != 0);
        Assert.assertNotNull("Expected nun null value", response.getTopGrossingCards().get(0).getCardName());
        Assert.assertTrue("Was not expecting negative value", response.getTopGrossingCards().get(0).getSales() >= 0);
    }
}
