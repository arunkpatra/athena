package org.athena.api.queries;

import org.athena.api.AbstractTest;
import org.athena.api.model.YearlyBreakageRate;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.athena.api.queries.NativeQueries.HISTORICAL_BREAKAGE_RATE_BY_CARD;


public class HistoricalBreakageRateByCardQueryTest extends AbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoricalBreakageRateByCardQueryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void getCardBreakageForecastTest() {
        List<YearlyBreakageRate> yearlyBreakageRates =
                jdbcTemplate.query(HISTORICAL_BREAKAGE_RATE_BY_CARD , new Object[]{"G-0001"},
                        (rs, rowNum) -> new YearlyBreakageRate(rs.getString(1).trim(), rs.getFloat(4))
                );

        Assert.assertNotNull("Expected nun null value", yearlyBreakageRates.get(0).getCartTypeCode());
    }

    @Test
    public void nativeQueryFileTest() {
        Assert.assertNotNull(HISTORICAL_BREAKAGE_RATE_BY_CARD);
    }
}
