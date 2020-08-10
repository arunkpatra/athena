package org.athena.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class HealthStatus {

    private String apiHealthStatus;

    @JsonCreator
    public HealthStatus(String apiHealthStatus) {
        this.apiHealthStatus = apiHealthStatus;
    }

    public String getApiHealthStatus() {
        return apiHealthStatus;
    }
}
