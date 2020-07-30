/*
 * MIT License
 *
 * Copyright (c) 2020 Arun Patra
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
