package org.athena.api.services;

import org.athena.api.model.MerchantSales;
import org.athena.api.model.TopGrossingCard;
import org.athena.api.model.TopSellingCardByQuantity;
import org.athena.api.queries.NativeQueries;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Interacts with the backend warehouse and returns results.
 *
 * @author Arun Patra
 */
public class AthenaBackendServiceImpl implements AthenaBackendService{

    private final JdbcTemplate jdbcTemplate;

    public AthenaBackendServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TopSellingCardByQuantity> getTopSellingCardsByQuantity() {
        return jdbcTemplate.query(NativeQueries.TOP_SELLING_CARDS_QUANTITY,
                (rs, rowNum) -> new TopSellingCardByQuantity(rs.getString(1).trim(), rs.getLong(2))
        );
    }

    @Override
    public List<TopGrossingCard> getTopGrossingCards() {
        return jdbcTemplate.query(NativeQueries.TOP_GROSSING_CARDS,
                (rs, rowNum) -> new TopGrossingCard(rs.getString(1).trim(), rs.getFloat(2))
        );
    }

    public List<MerchantSales> getTopGrossingMerchants() {
        return jdbcTemplate.query(NativeQueries.TOP_GROSSING_MERCHANTS,
                (rs, rowNum) -> new MerchantSales(rs.getString(1).trim(), rs.getDouble(2))
        );
    }

    @Override
    public List<MerchantSales> getWorstPerformingMerchants() {
        throw new UnsupportedOperationException();
    }
}
