package org.athena.api.services;

import org.athena.api.model.*;

import java.util.List;

public interface AthenaBackendService {

    List<TopSellingCardByQuantity> getTopSellingCardsByQuantity();

    List<TopGrossingCard> getTopGrossingCards();

    List<MerchantSales> getTopGrossingMerchants();

    List<MerchantSales> getWorstPerformingMerchants();

    List<MerchantBreakage> getMerchantBreakages();

    List<CardBreakage> getCardBreakages();

    CardBreakageForecast getBreakageForecastForCard(String cardCode);

}
