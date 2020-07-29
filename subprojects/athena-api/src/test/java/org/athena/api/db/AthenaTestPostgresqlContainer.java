package org.athena.api.db;

import org.testcontainers.containers.PostgreSQLContainer;

public class AthenaTestPostgresqlContainer extends PostgreSQLContainer<AthenaTestPostgresqlContainer> {
    private static final String IMAGE_VERSION = "postgres:11.1";
    private static AthenaTestPostgresqlContainer container;

    private AthenaTestPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static AthenaTestPostgresqlContainer getInstance() {
        if (container == null) {
            container = new AthenaTestPostgresqlContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}