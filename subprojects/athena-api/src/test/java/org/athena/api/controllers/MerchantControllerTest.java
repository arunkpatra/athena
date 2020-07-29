package org.athena.api.controllers;

import org.athena.api.AbstractTest;
import org.athena.api.model.ErrorResponse;
import org.athena.api.model.TopGrossingMerchantsResponse;
import org.athena.api.queries.NativeQueries;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MerchantControllerTest extends AbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantControllerTest.class);

    @Test
    public void getTopGrossingMerchantsTest() throws Exception {
        LOGGER.info("Query: " + NativeQueries.TOP_GROSSING_MERCHANTS);
        TopGrossingMerchantsResponse response = mockHttpExchange(
                get("/api/merchant/topgrossing"),
                status().isOk(),
                Optional.empty(),
                Optional.empty(),
                TopGrossingMerchantsResponse.class);
        Assert.assertTrue("Empty response is unexpected", response.getMerchantSales().size() != 0);
        Assert.assertNotNull("Expected nun null value", response.getMerchantSales().get(0).getMerchantName());
        Assert.assertTrue("Was not expecting negative value", response.getMerchantSales().get(0).getMerchantSales() >= 0);
    }

    @Test
    public void getWorstGrossingMerchantsTest() throws Exception {
        ErrorResponse response = mockHttpExchange(
                get("/api/merchant/worstgrossing"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.empty(),
                ErrorResponse.class);
    }
}
