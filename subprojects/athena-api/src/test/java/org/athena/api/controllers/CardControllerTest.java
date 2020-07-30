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
