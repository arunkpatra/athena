package org.athena.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.athena.api.db.AthenaTestPostgresqlContainer;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles({"intg-test"})
public abstract class AbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(org.athena.api.AbstractTest.class);
    protected static String SUCCESS_CHAR = "✓ ";
    protected static String FAILURE_CHAR = "✕ ";
    protected static String RUNNING_CHAR = "\uD83C\uDFC3 ";

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = AthenaTestPostgresqlContainer.getInstance();

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
