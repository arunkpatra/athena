package org.athena.api.controllers;

import org.athena.api.exceptions.AthenaException;
import org.athena.api.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/api")
public abstract class AbstractAthenaRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAthenaRestController.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AthenaException.class)
    public ErrorResponse handleAthenaBackendException(AthenaException e) {
        String message = "An error occurred";
        String errorDetail = "Error: " + e.getMessage();
        LOGGER.error("Error occurred: description={}, detail={}", message, errorDetail);
        return new ErrorResponse( message, errorDetail);
    }
}
