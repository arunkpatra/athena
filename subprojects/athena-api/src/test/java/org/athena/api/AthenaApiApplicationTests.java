/*
 * Copyright (C) 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package org.athena.api;

import org.athena.api.model.Merchant;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AthenaApiApplicationTests extends AbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(org.athena.api.AthenaApiApplicationTests.class);


    @Autowired
    private ApplicationContext context;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoads() {
        validateBeanExistence(JdbcTemplate.class);
    }

    @Test
    public void testDBSanity() {
        LOGGER.info("Querying for customer records where first_name = 'Josh':");
        List<Merchant> merchants = jdbcTemplate.query("SELECT merchant_code, merchant_name FROM merchant;",
                (rs, rowNum) -> new Merchant(rs.getString(1), rs.getString(2))
        );
        Assert.assertTrue(FAILURE_CHAR + "Expected at least one merchant", merchants.size() != 0);

        merchants.forEach(merchant -> LOGGER.info(merchant.toString()));
        LOGGER.info(SUCCESS_CHAR + "Retrieved merchants successfully.");
    }

    private void validateBeanExistence(Class<?>... types) {
        for (Class<?> type : types) {
            if (context.getBeanNamesForType(type).length == 0) {
                Assert.fail(String.format("Bean of type %s was not found", type.getSimpleName()));
            }
        }
    }
}
