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

import io.swagger.annotations.*;
import org.athena.api.exceptions.AthenaException;
import org.athena.api.model.CardBreakageForecastResponse;
import org.athena.api.model.CardBreakageResponse;
import org.athena.api.model.ErrorResponse;
import org.athena.api.model.MerchantBreakageResponse;
import org.athena.api.services.AthenaBackendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@Api(tags = {"Breakage Forecast"}, value = "Operations related to breakage")
public class BreakageController extends AbstractAthenaRestController {

    private final AthenaBackendService athenaBackendService;

    public BreakageController(AthenaBackendService athenaBackendService) {
        this.athenaBackendService = athenaBackendService;
    }

    @ApiOperation(value = "Get breakage forecast by card",
            notes = "Get breakage forecast by card", response = CardBreakageForecastResponse.class,
            consumes = "application/json",
            produces = "application/json")
    @RequestMapping(value = "/breakage/forecast/card/{cardTypeCode}", method = GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Card breakage forecast were retrieved",
                    response = CardBreakageForecastResponse.class),
            @ApiResponse(code = 500, message = "An internal error occurred.",
                    response = ErrorResponse.class)
    })
    public ResponseEntity<CardBreakageForecastResponse> cardBreakageForecast(
            @ApiParam(name = "cardTypeCode", required = true, value = "The card type code. Use G-0001 as an example.", defaultValue = "G-0001")
            @PathVariable(name = "cardTypeCode") String cardTypeCode) throws AthenaException {
        try {
            return new ResponseEntity<>(new CardBreakageForecastResponse(
                    athenaBackendService.getBreakageForecastForCard(cardTypeCode)), HttpStatus.OK);
        } catch (Throwable t) {
            throw new AthenaException(t);
        }
    }
}
