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
import org.athena.api.model.*;
import org.athena.api.services.AthenaBackendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@Api(tags = {"Merchants"}, value = "Operations related to merchants")
public class MerchantController extends AbstractAthenaRestController {

    private final AthenaBackendService athenaBackendService;

    public MerchantController(AthenaBackendService athenaBackendService) {
        this.athenaBackendService = athenaBackendService;
    }

    @ApiOperation(value = "Get top grossing merchants by volume",
            notes = "Get top grossing merchants by volume", response = TopGrossingMerchantsResponse.class,
            consumes = "application/json",
            produces = "application/json")
    @RequestMapping(value = "/merchant/topgrossing", method = GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Top grossing merchants were retrieved",
                    response = TopGrossingMerchantsResponse.class),
            @ApiResponse(code = 500, message = "An internal error occurred.",
                    response = ErrorResponse.class)
    })
    public ResponseEntity<TopGrossingMerchantsResponse> topGrossingMerchants() throws AthenaException {
        try {
            return new ResponseEntity<>(new TopGrossingMerchantsResponse(athenaBackendService.getTopGrossingMerchants()), HttpStatus.OK);
        } catch (Throwable t) {
            throw new AthenaException(t.getMessage(), t);
        }
    }

    @ApiOperation(value = "WIP: Worst performing merchants by volume",
            notes = "Get top grossing merchants by volume", response = TopGrossingMerchantsResponse.class,
            consumes = "application/json",
            produces = "application/json", hidden = true)
    @RequestMapping(value = "/merchant/worstgrossing", method = GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Worst grossing merchants were retrieved",
                    response = TopGrossingMerchantsResponse.class),
            @ApiResponse(code = 500, message = "An internal error occurred.",
                    response = ErrorResponse.class)
    })
    public ResponseEntity<TopGrossingMerchantsResponse> worstPerformingMerchants() throws AthenaException {
        try {
            return new ResponseEntity<>(new TopGrossingMerchantsResponse(athenaBackendService.getWorstPerformingMerchants()), HttpStatus.OK);
        } catch (Throwable t) {
            throw new AthenaException(t.getMessage(), t);
        }
    }


    @ApiOperation(value = "*Get breakage forecast for a merchant by card category",
            notes = "WIP: Get breakage forecast for a merchant by card category", response = MerchantBreakageByCardCategoryResponse.class,
            consumes = "application/json",
            produces = "application/json")
    @RequestMapping(value = "/merchant/{merchantID}/breakage/forecast/category/{category}", method = GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Breakage forecast for merchant was retrieved",
                    response = MerchantBreakageByCardCategoryResponse.class),
            @ApiResponse(code = 500, message = "An internal error occurred.",
                    response = ErrorResponse.class)
    })
    public ResponseEntity<MerchantBreakageByCardCategoryResponse> merchantBreakageByCardCategory(
            @ApiParam(name = "merchantID", required = true)
            @PathVariable(name = "merchantID")String merchantID,
            @ApiParam(name = "category", required = true, defaultValue = "Clothing", allowableValues = "Dining, Entertainment, Fuel, Recreation, Spa/Salon, Clothing")
            @PathVariable(name = "category")String category) throws AthenaException {
        try {
            return new ResponseEntity<>(new MerchantBreakageByCardCategoryResponse(athenaBackendService.getMerchantBreakageByCardCategory(merchantID, category)), HttpStatus.OK);
        } catch (Throwable t) {
            throw new AthenaException(t.getMessage(), t);
        }
    }

    @ApiOperation(value = "*Get breakage forecast for a merchant by business model",
            notes = "WIP: Get breakage forecast for a merchant by business model", response = MerchantBreakageByBusinessModelResponse.class,
            consumes = "application/json",
            produces = "application/json")
    @RequestMapping(value = "/merchant/{merchantID}/breakage/forecast/businessmodel/{businessModel}", method = GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Breakage forecast for merchant was retrieved",
                    response = MerchantBreakageByBusinessModelResponse.class),
            @ApiResponse(code = 500, message = "An internal error occurred.",
                    response = ErrorResponse.class)
    })
    public ResponseEntity<MerchantBreakageByBusinessModelResponse> merchantBreakageByBusinessModel(
            @ApiParam(name = "merchantID", required = true)
            @PathVariable(name = "merchantID") String merchantID,
            @ApiParam(name = "businessModel", required = true, defaultValue = "Closed Loop", allowableValues = "Closed Loop, Semi Closed Loop, Semi Closed Loop")
            @PathVariable(name = "businessModel")String businessModel) throws AthenaException {
        try {
            return new ResponseEntity<>(new MerchantBreakageByBusinessModelResponse(athenaBackendService.getMerchantBreakageByBusinessModel(merchantID, businessModel)), HttpStatus.OK);
        } catch (Throwable t) {
            throw new AthenaException(t.getMessage(), t);
        }
    }

    @ApiOperation(value = "*Get breakage forecast for a merchant by card medium",
            notes = "WIP: Get breakage forecast for a merchant by card medium", response = MerchantBreakageByCardMediumResponse.class,
            consumes = "application/json",
            produces = "application/json")
    @RequestMapping(value = "/merchant/{merchantID}/breakage/forecast/cardmedium/{medium}", method = GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Breakage forecast for merchant was retrieved",
                    response = MerchantBreakageByCardMediumResponse.class),
            @ApiResponse(code = 500, message = "An internal error occurred.",
                    response = ErrorResponse.class)
    })
    public ResponseEntity<MerchantBreakageByCardMediumResponse> merchantBreakageByCardMedium(
            @ApiParam(name = "merchantID", required = true)
            @PathVariable(name = "merchantID") String merchantID,
            @ApiParam(name = "medium", required = true, defaultValue = "Physical", allowableValues = "Physical, Digital")
            @PathVariable(name = "medium") String medium) throws AthenaException {
        try {
            return new ResponseEntity<>(new MerchantBreakageByCardMediumResponse(athenaBackendService.getMerchantBreakageByCardMedium(merchantID, medium)), HttpStatus.OK);
        } catch (Throwable t) {
            throw new AthenaException(t.getMessage(), t);
        }
    }

    @ApiOperation(value = "*Get breakage forecast for a merchant by customer segment",
            notes = "WIP: Get breakage forecast for a merchant by customer segment", response = MerchantBreakageByCustomerSegmentResponse.class,
            consumes = "application/json",
            produces = "application/json")
    @RequestMapping(value = "/merchant/{merchantID}/breakage/forecast/customersegment/{segment}", method = GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Breakage forecast for merchant was retrieved",
                    response = MerchantBreakageByCustomerSegmentResponse.class),
            @ApiResponse(code = 500, message = "An internal error occurred.",
                    response = ErrorResponse.class)
    })
    public ResponseEntity<MerchantBreakageByCustomerSegmentResponse> merchantBreakageByCustomerSegment(
            @ApiParam(name = "merchantID", required = true)
            @PathVariable(name = "merchantID") String merchantID,
            @ApiParam(name = "segment", required = true, defaultValue = "Adult", allowableValues = "Kid, Adult, Senior, Millennial")
            @PathVariable(name = "segment") String segment) throws AthenaException {
        try {
            return new ResponseEntity<>(new MerchantBreakageByCustomerSegmentResponse(athenaBackendService.getMerchantBreakageByCustomerSegment(merchantID, segment)), HttpStatus.OK);
        } catch (Throwable t) {
            throw new AthenaException(t.getMessage(), t);
        }
    }
}
