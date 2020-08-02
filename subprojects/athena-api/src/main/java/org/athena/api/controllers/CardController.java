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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.athena.api.exceptions.AthenaException;
import org.athena.api.model.CardBreakageResponse;
import org.athena.api.model.ErrorResponse;
import org.athena.api.model.TopGrossingCardsResponse;
import org.athena.api.model.TopSellingCardsByQuantityResponse;
import org.athena.api.services.AthenaBackendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@Api(tags = {"Cards"}, value = "Operations related to Gift Cards")
public class CardController extends AbstractAthenaRestController {

    private final AthenaBackendService athenaBackendService;

    public CardController(AthenaBackendService athenaBackendService) {
        this.athenaBackendService = athenaBackendService;
    }

    @ApiOperation(value = "Get breakage by card",
            notes = "Get breakage by card", response = CardBreakageResponse.class,
            consumes = "application/json",
            produces = "application/json")
    @RequestMapping(value = "/card/breakage", method = GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Card breakages were retrieved",
                    response = CardBreakageResponse.class),
            @ApiResponse(code = 500, message = "An internal error occurred.",
                    response = ErrorResponse.class)
    })
    public ResponseEntity<CardBreakageResponse> cardBreakages() throws AthenaException {
        try {
            return new ResponseEntity<>(new CardBreakageResponse(athenaBackendService.getCardBreakages()), HttpStatus.OK);
        } catch (Throwable t) {
            throw new AthenaException(t);
        }
    }

    @ApiOperation(value = "Get top selling cards by quantity",
            notes = "Get top selling cards by quantity", response = TopSellingCardsByQuantityResponse.class,
            consumes = "application/json",
            produces = "application/json")
    @RequestMapping(value = "/card/topselling", method = GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Top selling cards were retrieved",
                    response = TopSellingCardsByQuantityResponse.class),
            @ApiResponse(code = 500, message = "An internal error occurred.",
                    response = ErrorResponse.class)
    })
    public ResponseEntity<TopSellingCardsByQuantityResponse> topSellingCardsByQuantity() throws AthenaException {
        try {
            return new ResponseEntity<>(new TopSellingCardsByQuantityResponse(athenaBackendService.getTopSellingCardsByQuantity()), HttpStatus.OK);
        } catch (Throwable t) {
            throw new AthenaException(t);
        }
    }

    @ApiOperation(value = "Get top selling cards by volume",
            notes = "Get top selling cards by volume", response = TopGrossingCardsResponse.class,
            consumes = "application/json",
            produces = "application/json")
    @RequestMapping(value = "/card/topgrossing", method = GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Top grossing cards were retrieved",
                    response = TopGrossingCardsResponse.class),
            @ApiResponse(code = 500, message = "An internal error occurred.",
                    response = ErrorResponse.class)
    })
    public ResponseEntity<TopGrossingCardsResponse> topGrossingCards() throws AthenaException {
        try {
            return new ResponseEntity<>(new TopGrossingCardsResponse(athenaBackendService.getTopGrossingCards()), HttpStatus.OK);
        } catch (Throwable t) {
            throw new AthenaException(t);
        }
    }
}
