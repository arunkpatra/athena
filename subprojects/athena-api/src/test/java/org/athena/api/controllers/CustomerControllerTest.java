package org.athena.api.controllers;

import org.athena.api.AbstractTest;
import org.athena.api.model.MyCardDetailsResponse;
import org.athena.api.model.TopGrossingMerchantsResponse;
import org.athena.api.queries.NativeQueries;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantControllerTest.class);

    @Test
    public void getTopGrossingMerchantsTest() throws Exception {
        LOGGER.info("Query: " + NativeQueries.TOP_GROSSING_MERCHANTS);
        MyCardDetailsResponse response = mockHttpExchange(
                get("/api/customer/76809bcc-0e1f-4b44-8119-8a795b103678/cards"),
                status().isOk(),
                Optional.empty(),
                Optional.empty(),
                MyCardDetailsResponse.class);
        Assert.assertTrue("Empty response is unexpected", response.getCustomerCardDetails().size() != 0);
        Assert.assertNotNull("Expected nun null value", response.getCustomerCardDetails().get(0).getCardName());
        Assert.assertNotNull("Expected nun null value", response.getCustomerCardDetails().get(0).getCardID());
        Assert.assertNotNull("Expected nun null value", response.getCustomerCardDetails().get(0).getExpiryDate());
        Assert.assertTrue("Was not expecting negative value", response.getCustomerCardDetails().get(0).getUnredeemedValue() >= 0);
        Assert.assertTrue("Was not expecting negative value", response.getCustomerCardDetails().get(0).getCardValue() >= 0);
    }
}
