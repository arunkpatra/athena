package org.athena.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.athena.api.model.ErrorResponse;
import org.athena.api.model.HealthStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@Api(tags = {"API Health"}, value = "Health", hidden = true)
public class ApiHealthController {

    @ApiOperation(value = "Health",
            notes = "Get top grossing merchants by volume", response = HealthStatus.class,
            consumes = "application/json",
            produces = "application/json")
    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Health was retrieved",
                    response = HealthStatus.class),
            @ApiResponse(code = 500, message = "An internal error occurred.",
                    response = ErrorResponse.class)
    })
    @GetMapping({"/"})
    public ResponseEntity<HealthStatus> health() {
        return new ResponseEntity<>(new HealthStatus("UP"), HttpStatus.OK);
    }
}
