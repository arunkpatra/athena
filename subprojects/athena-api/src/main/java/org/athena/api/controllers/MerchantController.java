package org.athena.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.athena.api.exceptions.AthenaException;
import org.athena.api.model.ErrorResponse;
import org.athena.api.model.TopGrossingMerchantsResponse;
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
            throw new AthenaException(t);
        }
    }

    @ApiOperation(value = "Worst performing merchants by volume",
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
            throw new AthenaException(t);
        }
    }
}
