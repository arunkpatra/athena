package org.athena.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class HelloResponse {

    private String helloResponse;

    @JsonCreator
    public HelloResponse(String helloResponse) {
        this.helloResponse = helloResponse;
    }

    public String getHelloResponse() {
        return helloResponse;
    }

    public void setHelloResponse(String helloResponse) {
        this.helloResponse = helloResponse;
    }
}
