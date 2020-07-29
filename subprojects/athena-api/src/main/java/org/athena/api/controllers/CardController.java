package org.athena.api.controllers;

import io.swagger.annotations.*;
import org.athena.api.exceptions.AthenaException;
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
