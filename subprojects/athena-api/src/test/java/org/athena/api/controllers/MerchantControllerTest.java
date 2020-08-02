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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.athena.api.AbstractTest;
import org.athena.api.exceptions.AthenaException;
import org.athena.api.model.*;
import org.athena.api.queries.NativeQueries;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
    public void getMerchantBreakageTest() throws Exception {
        MerchantBreakageResponse response = mockHttpExchange(
                get("/api/merchant/breakage"),
                status().isOk(),
                Optional.empty(),
                Optional.empty(),
                MerchantBreakageResponse.class);
        Assert.assertTrue("Empty response is unexpected", response.getMerchantBreakages().size() != 0);
        Assert.assertNotNull("Expected nun null value", response.getMerchantBreakages().get(0).getMerchantName());
        Assert.assertTrue("Was not expecting negative value", response.getMerchantBreakages().get(0).getMerchantBreakage() >= 0);
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

    @Test
    public void getMerchantBreakageForecastByCardCategory() throws Exception {
        MerchantBreakageByCardCategoryResponse response = mockHttpExchange(
                get("/api/merchant/M-0001/breakage/category"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.empty(),
                MerchantBreakageByCardCategoryResponse.class);
        // TODO: Add assertions when API is implemented
    }

    @Test
    public void getMerchantBreakageForecastByCardMedium() throws Exception {
        MerchantBreakageByCardMediumResponse response = mockHttpExchange(
                get("/api/merchant/M-0001/breakage/cardmedium/Physical"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.empty(),
                MerchantBreakageByCardMediumResponse.class);
        // TODO: Add assertions when API is implemented
    }

    @Test
    public void getMerchantBreakageForecastByBusinessModel() throws Exception {
        MerchantBreakageByBusinessModelResponse response = mockHttpExchange(
                get("/api/merchant/M-0001/breakage/businessmodel"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.empty(),
                MerchantBreakageByBusinessModelResponse.class);
        // TODO: Add assertions when API is implemented
    }

    @Test
    public void getMerchantBreakageForecastByCustomerSegment() throws Exception {
        MerchantBreakageByCustomerSegmentResponse response = mockHttpExchange(
                get("/api/merchant/M-0001/breakage/customersegment/Adult"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.empty(),
                MerchantBreakageByCustomerSegmentResponse.class);
        // TODO: Add assertions when API is implemented
    }
}
