package test;

import pages.MainPage;
import pages.PaymentPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.getValidApprovedCardInfo;
import static data.DataHelper.getValidDeclinedCardInfo;
import static data.SQLHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentWithCreditCardTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        MainPage mainPage = new MainPage();
        open("http://localhost:8080");
        mainPage.payWithCreditCard();
    }

    @AfterAll
    static void cleanDataBases() {
        SQLHelper.cleanDb();
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldSubmitApprovedCard() {
        var info = getValidApprovedCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.successMessage();

        var paymentId = getOrderId();
        String actStatus = getStatusForPaymentWithCreditCard(paymentId);
        String expStatus = "APPROVED";
        assertEquals(expStatus, actStatus);
    }

    @Test
    void shouldNotSubmitDeclinedCard() {
        var info = getValidDeclinedCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.failMessage();

        var paymentId = getOrderId();
        String actStatus = getStatusForPaymentWithCreditCard(paymentId);
        String expStatus = "DECLINED";
        assertEquals(expStatus, actStatus);
    }
}
