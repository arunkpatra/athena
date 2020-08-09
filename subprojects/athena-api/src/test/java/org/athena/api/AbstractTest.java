/*
 * MIT License
 *
 * Copyright (c) 2020 Arun Patra
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.athena.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.athena.api.db.AthenaTestPostgresqlContainer;
import org.athena.api.model.ErrorResponse;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
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
    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = AthenaTestPostgresqlContainer.getInstance();
    protected static String SUCCESS_CHAR = "✓ ";
    protected static String FAILURE_CHAR = "✕ ";
    protected static String GENERIC_ERROR_MESSAGE = "An error occurred";
    protected static String RUNNING_CHAR = "\uD83C\uDFC3 ";
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
    protected <T, M> T httpExchange(
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
            if (response instanceof ErrorResponse) {
                Assert.assertEquals(FAILURE_CHAR + "Did not get expected error description",
                        m, ((ErrorResponse) response).getErrorDescription());
                LOGGER.info(SUCCESS_CHAR + "Receive expected error message: {}", expectedErrorMessage.get());
            }
        });
        return response;
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    protected <T, S> T mockHttpExchange(MockHttpServletRequestBuilder requestBuilder,
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
            if (response instanceof ErrorResponse) {
                Assert.assertTrue(FAILURE_CHAR + "Did not get expected error description",
                        ((ErrorResponse) response).getErrorDescription().contains(m));
                LOGGER.info(SUCCESS_CHAR + "Receive expected error message: {}", expectedErrorMessage.get());
            }
        });
        return response;
    }
}
