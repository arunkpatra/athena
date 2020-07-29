package org.athena.api.config;

import org.athena.api.db.AthenaTestPostgresqlContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;

@Profile("intg-test")
@Configuration
public class EmbeddedDBConfig {

//    @Bean
//    public static PostgreSQLContainer postgreSQLContainer() {
//        return AthenaTestPostgresqlContainer.getInstance();
//    }
}
