package org.athena.api.services;

import org.athena.api.model.MerchantSales;
import org.athena.api.model.TopGrossingCard;
import org.athena.api.model.TopSellingCardByQuantity;

import java.util.List;

public interface AthenaBackendService {

    List<TopSellingCardByQuantity> getTopSellingCardsByQuantity();

    List<TopGrossingCard> getTopGrossingCards();

    List<MerchantSales> getTopGrossingMerchants();

    List<MerchantSales> getWorstPerformingMerchants();
}
