package test;

import Pages.MainPage;
import Pages.PaymentPage;
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

public class DebitCardTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        mainPage.payWithDebitCard();
    }

    @AfterAll
    static void cleanDataBases() {
        SQLHelper.cleanDb();
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldSubmitApprovedCard() {
        var info = getValidApprovedCardInfo();
        paymentPage.fillForm(info);
        paymentPage.successMessage();
    }

    @Test
    void shouldNotSubmitDeclinedCard() {
        var info = getValidDeclinedCardInfo();
        paymentPage.fillForm(info);
        paymentPage.failMessage();
    }
}
