package org.athena.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.athena.api.db.AthenaTestPostgresqlContainer;
import org.athena.api.model.ErrorResponse;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    protected <T, M> T mockHttpExchange(
                                                     MockHttpServletRequestBuilder requestBuilder,
                                                     ResultMatcher resultMatcher,
                                                     Optional<Object> content,
                                                     Optional<String> expectedErrorMessage,
                                                     Class<T> type) throws Exception {

        content.ifPresent(c -> requestBuilder.content(asJsonString(c)));
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(resultMatcher)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        T response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), type);
        expectedErrorMessage.ifPresent(m -> {
            if (response instanceof ErrorResponse  ) {
                Assert.assertEquals(FAILURE_CHAR + "Did not get expected error description",
                        m, ((ErrorResponse) response).getErrorDescription());
                LOGGER.info(SUCCESS_CHAR + "Receive expected error message: {}", expectedErrorMessage.get());
            }
        });
        return response;
    }

}
