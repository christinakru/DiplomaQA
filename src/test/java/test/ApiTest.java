package test;

import org.junit.jupiter.api.Test;

import static data.DataHelper.getValidApprovedCardInfo;
import static data.DataHelper.getValidDeclinedCardInfo;
import static data.RestApiHelper.fillPaymentFormWithCreditCardData;
import static data.RestApiHelper.fillPaymentFormWithDebitCardData;
import static data.SQLHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {
    @Test
    void shouldGiveResponseForValidApprovedDebitCard() {
        var validApprovedCardForApi = getValidApprovedCardInfo();
        var response = fillPaymentFormWithDebitCardData(validApprovedCardForApi);
        assertTrue(response.contains("APPROVED"));

        var paymentId = getOrderId();
        String actStatus = getStatusForPaymentWithDebitCard(paymentId);
        String expStatus = "APPROVED";
        assertEquals(expStatus, actStatus);
    }

    @Test
    void shouldWriteCorrectAmountForValidApprovedDebitCard() {
        var validApprovedCardForApi = getValidApprovedCardInfo();
        fillPaymentFormWithDebitCardData(validApprovedCardForApi);

        var paymentId = getOrderId();
        String actualAmount = getPaymentAmount(paymentId);
        String expectedAmount = "45000";

        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void shouldGiveResponseForValidDeclinedDebitCard() {
        var validDeclinedCardForApi = getValidDeclinedCardInfo();
        var response = fillPaymentFormWithDebitCardData(validDeclinedCardForApi);
        assertTrue(response.contains("DECLINED"));

        var paymentId = getOrderId();
        String actStatus = getStatusForPaymentWithDebitCard(paymentId);
        String expStatus = "DECLINED";
        assertEquals(expStatus, actStatus);
    }

    @Test
    void shouldGiveResponseForValidApprovedCreditCard() {
        var validApprovedCardForApi = getValidApprovedCardInfo();
        var response = fillPaymentFormWithCreditCardData(validApprovedCardForApi);
        assertTrue(response.contains("APPROVED"));

        var paymentId = getOrderId();
        String actStatus = getStatusForPaymentWithCreditCard(paymentId);
        String expStatus = "APPROVED";
        assertEquals(expStatus, actStatus);
    }

    @Test
    void shouldGiveResponseForValidDeclinedCreditCard() {
        var validDeclinedCardForApi = getValidDeclinedCardInfo();
        var response = fillPaymentFormWithCreditCardData(validDeclinedCardForApi);
        assertTrue(response.contains("DECLINED"));

        var paymentId = getOrderId();
        String actStatus = getStatusForPaymentWithCreditCard(paymentId);
        String expStatus = "DECLINED";
        assertEquals(expStatus, actStatus);
    }
}
