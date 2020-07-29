package org.athena.api.controllers;

import org.athena.api.AbstractTest;
import org.athena.api.model.*;
import org.athena.api.queries.NativeQueries;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BreakageControllerTest extends AbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BreakageControllerTest.class);

    @Test
    public void getCardBreakageTest() throws Exception {
        CardBreakageResponse response = mockHttpExchange(
                get("/api/breakage/card"),
                status().isOk(),
                Optional.empty(),
                Optional.empty(),
                CardBreakageResponse.class);
        Assert.assertTrue("Empty response is unexpected", response.getCardBreakages().size() != 0);
        Assert.assertNotNull("Expected nun null value", response.getCardBreakages().get(0).getCardName());
        Assert.assertTrue("Was not expecting negative value", response.getCardBreakages().get(0).getCardBreakage() >= 0);
    }

    @Test
    public void getMerchantBreakageTest() throws Exception {
        MerchantBreakageResponse response = mockHttpExchange(
                get("/api/breakage/merchant"),
                status().isOk(),
                Optional.empty(),
                Optional.empty(),
                MerchantBreakageResponse.class);
        Assert.assertTrue("Empty response is unexpected", response.getMerchantBreakages().size() != 0);
        Assert.assertNotNull("Expected nun null value", response.getMerchantBreakages().get(0).getMerchantName());
        Assert.assertTrue("Was not expecting negative value", response.getMerchantBreakages().get(0).getMerchantBreakage() >= 0);
    }

    @Test
    public void getCardBreakageForecastTest() throws Exception {
        CardBreakageForecastResponse response = mockHttpExchange(
                get("/api/breakage/forecast/card/G-0001"),
                status().isOk(),
                Optional.empty(),
                Optional.empty(),
                CardBreakageForecastResponse.class);
        LOGGER.info("Forecast: {}", response);
        Assert.assertNotNull("Empty response is unexpected", response.getForecast());
        Assert.assertNotNull("Expected nun null value", response.getForecast().getCardCode());
        Assert.assertTrue("Was not expecting negative value", response.getForecast().getBreakageForecast() >= 0);
    }
}
