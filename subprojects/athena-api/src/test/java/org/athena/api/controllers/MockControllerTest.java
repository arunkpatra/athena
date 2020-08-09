package org.athena.api.controllers;

import org.athena.api.AbstractTest;
import org.athena.api.model.ErrorResponse;
import org.athena.api.model.MerchantBreakageByCardMediumResponse;
import org.athena.api.model.MerchantBreakageByCustomerSegmentResponse;
import org.athena.api.services.AthenaBackendService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({"intg-test", "mock-athena-service"})
public class MockControllerTest extends AbstractTest {

    @Autowired
    private AthenaBackendService mockAthenaBackendService;

    @Test
    public void merchantBreakageByCardMediumTest() throws Exception {
        // mock request; unimplemented service method. Remove mock wen implemented.
        Mockito.when(mockAthenaBackendService.getMerchantBreakageByCardMedium("M-0001", "Digital")).thenReturn(new ArrayList<>());
        mockHttpExchange(
                get("/api/merchant/M-0001/breakage/cardmedium/Digital"),
                status().isOk(),
                Optional.empty(),
                Optional.empty(),
                MerchantBreakageByCardMediumResponse.class);
    }

    @Test
    public void merchantBreakageByCustomerSegmentTest() throws Exception {
        // mock request; unimplemented service method. Remove mock wen implemented.
        Mockito.when(mockAthenaBackendService.getMerchantBreakageByCustomerSegment("M-0001", "Kid")).thenReturn(new ArrayList<>());
        mockHttpExchange(
                get("/api/merchant/M-0001/breakage/customersegment/Kid"),
                status().isOk(),
                Optional.empty(),
                Optional.empty(),
                MerchantBreakageByCustomerSegmentResponse.class);
    }

    @Test
    public void getCustomerCardsBadRequestTest() throws Exception {
        // bad request
        Mockito.doThrow(new RuntimeException(GENERIC_ERROR_MESSAGE))
                .when(mockAthenaBackendService).getCustomerCardDetails(any());
        mockHttpExchange(
                get("/api/customer/junk/cards"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.of(GENERIC_ERROR_MESSAGE),
                ErrorResponse.class);
    }

    @Test
    public void cardBreakageForecastBadRequestTest() throws Exception {
        // bad request
        Mockito.doThrow(new RuntimeException(GENERIC_ERROR_MESSAGE))
                .when(mockAthenaBackendService).getBreakageForecastForCard(any());
        mockHttpExchange(
                get("/api/breakage/forecast/card/G-0001"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.of(GENERIC_ERROR_MESSAGE),
                ErrorResponse.class);
    }

    @Test
    public void cardBreakagesBadRequestTest() throws Exception {
        // bad request
        Mockito.doThrow(new RuntimeException(GENERIC_ERROR_MESSAGE))
                .when(mockAthenaBackendService).getCardBreakages();
        mockHttpExchange(
                get("/api/card/breakage"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.of(GENERIC_ERROR_MESSAGE),
                ErrorResponse.class);
    }

    @Test
    public void topSellingCardsByQuantityBadRequestTest() throws Exception {
        // bad request
        Mockito.doThrow(new RuntimeException(GENERIC_ERROR_MESSAGE))
                .when(mockAthenaBackendService).getTopSellingCardsByQuantity();
        mockHttpExchange(
                get("/api/card/topselling"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.of(GENERIC_ERROR_MESSAGE),
                ErrorResponse.class);
    }

    @Test
    public void topGrossingCardsBadRequestTest() throws Exception {
        // bad request
        Mockito.doThrow(new RuntimeException(GENERIC_ERROR_MESSAGE))
                .when(mockAthenaBackendService).getTopGrossingCards();
        mockHttpExchange(
                get("/api/card/topgrossing"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.of(GENERIC_ERROR_MESSAGE),
                ErrorResponse.class);
    }

    // Merchants

    @Test
    public void topGrossingMerchantsBadRequestTest() throws Exception {
        // bad request
        Mockito.doThrow(new RuntimeException(GENERIC_ERROR_MESSAGE))
                .when(mockAthenaBackendService).getTopGrossingMerchants();
        mockHttpExchange(
                get("/api/merchant/topgrossing"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.of(GENERIC_ERROR_MESSAGE),
                ErrorResponse.class);
    }

    @Test
    public void merchantBreakagesBadRequestTest() throws Exception {
        // bad request
        Mockito.doThrow(new RuntimeException(GENERIC_ERROR_MESSAGE))
                .when(mockAthenaBackendService).getMerchantBreakages();
        mockHttpExchange(
                get("/api/merchant/breakage"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.of(GENERIC_ERROR_MESSAGE),
                ErrorResponse.class);
    }

    @Test
    public void merchantBreakageByCardCategoryBadRequestTest() throws Exception {
        // bad request
        Mockito.doThrow(new RuntimeException(GENERIC_ERROR_MESSAGE))
                .when(mockAthenaBackendService).getMerchantBreakageByCardCategory(any());
        mockHttpExchange(
                get("/api/merchant/M-0001/breakage/category"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.of(GENERIC_ERROR_MESSAGE),
                ErrorResponse.class);
    }

    @Test
    public void merchantBreakageByBusinessModelBadRequestTest() throws Exception {
        // bad request
        Mockito.doThrow(new RuntimeException(GENERIC_ERROR_MESSAGE))
                .when(mockAthenaBackendService).getMerchantBreakageByBusinessModel(any());
        mockHttpExchange(
                get("/api/merchant/M-0001/breakage/businessmodel"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.of(GENERIC_ERROR_MESSAGE),
                ErrorResponse.class);
    }

    @Test
    public void merchantBreakageByCardMediumBadRequestTest() throws Exception {
        // bad request
        Mockito.doThrow(new RuntimeException(GENERIC_ERROR_MESSAGE))
                .when(mockAthenaBackendService).getMerchantBreakageByCardMedium("M-JUNK", "Junk");
        mockHttpExchange(
                get("/api/merchant/M-JUNK/breakage/cardmedium/Junk"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.of(GENERIC_ERROR_MESSAGE),
                ErrorResponse.class);
    }

    @Test
    public void merchantBreakageByCustomerSegmentBadRequestTest() throws Exception {
        // bad request
        Mockito.doThrow(new RuntimeException(GENERIC_ERROR_MESSAGE))
                .when(mockAthenaBackendService).getMerchantBreakageByCustomerSegment("M-JUNK", "Junk");
        mockHttpExchange(
                get("/api/merchant/M-JUNK/breakage/customersegment/Junk"),
                status().isInternalServerError(),
                Optional.empty(),
                Optional.of(GENERIC_ERROR_MESSAGE),
                ErrorResponse.class);
    }
}
