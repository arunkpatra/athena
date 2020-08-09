package org.athena.api;

import org.athena.api.services.AthenaBackendService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("mock-athena-service")
@Configuration
public class MockConfig {

    @Bean
    @Primary
    public AthenaBackendService athenaBackendServiceMock() {
        return Mockito.mock(AthenaBackendService.class);
    }
}
