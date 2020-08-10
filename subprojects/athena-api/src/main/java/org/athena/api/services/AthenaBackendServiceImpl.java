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

package org.athena.api.services;

import org.athena.api.model.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.util.List;
import java.util.OptionalDouble;

import static org.athena.api.queries.NativeQueries.*;

/**
 * Interacts with the backend warehouse and returns results.
 *
 * @author Arun Patra
 */
public class AthenaBackendServiceImpl implements AthenaBackendService {

    private final JdbcTemplate jdbcTemplate;

    public AthenaBackendServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TopSellingCardByQuantity> getTopSellingCardsByQuantity() {
        return jdbcTemplate.query(TOP_SELLING_CARDS_QUANTITY,
                (rs, rowNum) -> new TopSellingCardByQuantity(rs.getString(1).trim(), rs.getLong(2))
        );
    }

    @Override
    public List<TopGrossingCard> getTopGrossingCards() {
        return jdbcTemplate.query(TOP_GROSSING_CARDS,
                (rs, rowNum) -> new TopGrossingCard(rs.getString(1).trim(), rs.getFloat(2))
        );
    }

    public List<MerchantSales> getTopGrossingMerchants() {
        return jdbcTemplate.query(TOP_GROSSING_MERCHANTS,
                (rs, rowNum) -> new MerchantSales(rs.getString(1).trim(), rs.getDouble(2))
        );
    }

    @Override
    public List<MerchantBreakage> getMerchantBreakages() {
        return jdbcTemplate.query(BREAKAGE_BY_MERCHANT,
                (rs, rowNum) -> new MerchantBreakage(rs.getString(1).trim(), rs.getDouble(2))
        );
    }

    @Override
    public List<CardBreakage> getCardBreakages() {
        return jdbcTemplate.query(BREAKAGE_BY_CARD,
                (rs, rowNum) -> new CardBreakage(rs.getString(1).trim(), rs.getDouble(4))
        );
    }

    @Override
    public CardBreakageForecast getBreakageForecastForCard(String cardCode) {
        // For given card what was breakages in prior years? Calculate average
        List<YearlyBreakageRate> yearlyBreakageRates =
                jdbcTemplate.query(HISTORICAL_BREAKAGE_RATE_BY_CARD, new Object[]{cardCode},
                        (rs, rowNum) -> new YearlyBreakageRate(rs.getString(1).trim(), rs.getFloat(4))
                );

        OptionalDouble optionalAverageRate = yearlyBreakageRates
                .stream()
                .map(YearlyBreakageRate::getBreakageRate)
                .mapToDouble(a -> a)
                .average();

        double averageYearlyBreakageRate = optionalAverageRate.isPresent() ? optionalAverageRate.getAsDouble() : 0; // average historical

        // What is the total sales volumes in this year for this card?
        List<CardSalesThisYear> cardSalesThisYear = jdbcTemplate.query(SALES_THIS_YEAR_BY_CARD, new Object[]{cardCode},
                (rs, rowNum) -> new CardSalesThisYear(rs.getString(2).trim(), rs.getDouble(3))
        );
        double totalSalesThisYearForCard = cardSalesThisYear.stream().filter(cs -> cs.getCardTypeCode().equalsIgnoreCase(cardCode))
                .map(CardSalesThisYear::getTotalSales).reduce(0.0, Double::sum);

        // extrapolate
        return new CardBreakageForecast(cardCode, String.format("%.2f",averageYearlyBreakageRate * totalSalesThisYearForCard));
    }

    @Override
    public List<CustomerCardDetails> getCustomerCardDetails(String customerID) {
        java.util.Date today = new java.util.Date();
        return jdbcTemplate.query(VALUE_ON_CARDS_BY_CUSTOMER, new Object[]{customerID, customerID},
                (rs, rowNum) -> new CustomerCardDetails(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getFloat(4),
                        rs.getFloat(6),
                        rs.getDate(3).toString(), rs.getDate(3).before(today)
                )
        );
    }

    @Override
    public List<BusinessModelBreakage> getMerchantBreakageByBusinessModel(String merchantID) {
        return jdbcTemplate.query(MERCHANT_BREAKAGE_BY_BUSINESS_MODEL, new Object[]{merchantID, merchantID},
                (rs, rowNum) -> new BusinessModelBreakage(rs.getString(1).trim(), rs.getDouble(2))
        );
    }

    @Override
    public List<CardCategoryBreakage> getMerchantBreakageByCardCategory(String merchantID) {
        return jdbcTemplate.query(MERCHANT_BREAKAGE_BY_CARD_CATEGORY, new Object[]{merchantID, merchantID},
                (rs, rowNum) -> new CardCategoryBreakage(rs.getString(1).trim(), rs.getDouble(2))
        );
    }

    // TODO: Implement me
    @Override
    public List<CardBreakage> getMerchantBreakageByCardMedium(String merchantID, String medium) {
        throw new UnsupportedOperationException("Coming soon. This API has not been implemented yet!");
    }

    // TODO: Implement me
    @Override
    public List<CardBreakage> getMerchantBreakageByCustomerSegment(String merchantID, String segment) {
        throw new UnsupportedOperationException("Coming soon. This API has not been implemented yet!");
    }
}
