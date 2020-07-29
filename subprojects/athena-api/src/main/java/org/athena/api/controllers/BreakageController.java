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
@Api(tags = {"Breakage"}, value = "Operations related to breakage")
public class BreakageController extends AbstractAthenaRestController {

    private final AthenaBackendService athenaBackendService;

    public BreakageController(AthenaBackendService athenaBackendService) {
        this.athenaBackendService = athenaBackendService;
    }

    @ApiOperation(value = "Get breakage by merchant",
            notes = "Get breakage by merchant", response = MerchantBreakageResponse.class,
            consumes = "application/json",
            produces = "application/json")
    @RequestMapping(value = "/breakage/merchant", method = GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Merchant breakages were retrieved",
                    response = MerchantBreakageResponse.class),
            @ApiResponse(code = 500, message = "An internal error occurred.",
                    response = ErrorResponse.class)
    })
    public ResponseEntity<MerchantBreakageResponse> merchantBreakages() throws AthenaException {
        try {
            return new ResponseEntity<>(new MerchantBreakageResponse(athenaBackendService.getMerchantBreakages()), HttpStatus.OK);
        } catch (Throwable t) {
            throw new AthenaException(t);
        }
    }

    @ApiOperation(value = "Get breakage by card",
            notes = "Get breakage by card", response = CardBreakageResponse.class,
            consumes = "application/json",
            produces = "application/json")
    @RequestMapping(value = "/breakage/card", method = GET)
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
            @ApiParam(name = "cardTypeCode", required = true)
            @PathVariable(name = "cardTypeCode") String cardTypeCode) throws AthenaException {
        try {
            return new ResponseEntity<>(new CardBreakageForecastResponse(
                    athenaBackendService.getBreakageForecastForCard(cardTypeCode)), HttpStatus.OK);
        } catch (Throwable t) {
            throw new AthenaException(t);
        }
    }
}
