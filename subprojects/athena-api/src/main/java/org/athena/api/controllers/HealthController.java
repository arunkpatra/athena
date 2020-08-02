package org.athena.api.controllers;

import org.athena.api.model.HelloResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HealthController {

    @GetMapping({"/"})
    public ResponseEntity<HelloResponse> health() {
        return new ResponseEntity<>(new HelloResponse("UP"), HttpStatus.OK);
    }
}
