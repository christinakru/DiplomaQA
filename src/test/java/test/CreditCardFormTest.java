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
import static data.DataHelper.*;

public class CreditCardFormTest {
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
    void shouldNotSubmitCardWithEmptyNumber() {
        var info = getEmptyCardNumberCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.shouldFillMessage();
    }

    @Test
    void shouldNotSubmitCardWith15DigitsNumber() {
        var info = getInvalidCardNumberWith15SymbolsCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitAnotherBankCard() {
        var info = getAnotherBankCardNumberCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.failMessage();
    }

    @Test
    void shouldNotSubmitEmptyMonth() {
        var info = getEmptyMonthCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.shouldFillMessage();
    }

    @Test
    void shouldNotSubmitMonthIsZero() {
        var info = getInvalidFormatMonthZeroZeroCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.wrongTermMessage();
    }

    @Test
    void shouldNotSubmitMonthOneDigit() {
        var info = getInvalidFormatMonthOneDigitCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitMonthIncorrect() {
        var info = getInvalidFormatMonthIrrelevantCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.wrongTermMessage();
    }

    @Test
    void shouldNotSubmitEmptyYear() {
        var info = getEmptyYearCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.shouldFillMessage();
    }

    @Test
    void shouldNotSubmitEarlyYear() {
        var info = getEarlyYearCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.cardExpiredMessage();
    }

    @Test
    void shouldNotSubmitFutureYear() {
        var info = getFutureYearCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.wrongTermMessage();
    }

    @Test
    void shouldNotSubmitEmptyOwner() {
        var info = getEmptyOwnerCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.shouldFillMessage();
    }

    @Test
    void shouldNotSubmitOneWordOwner() {
        var info = getInvalidOwnerWithOneNameCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitThreeWordsOwner() {
        var info = getInvalidOwnerWithThreeWordsCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitLowerCaseOwner() {
        var info = getInvalidOwnerWithLowerCaseCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitUpperCaseOwner() {
        var info = getInvalidOwnerWithUpperCaseCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitRuOwner() {
        var info = getInvalidOwnerWithCyrillicCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitOwnerWithNumbers() {
        var info = getInvalidOwnerWithDigitsCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitOwnerWithSymbols() {
        var info = getInvalidOwnerWithSymbolsCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitCVVIsEmpty() {
        var info = getEmptyCVVCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.shouldFillMessage();
    }

    @Test
    void shouldNotSubmitCVVOneNumber() {
        var info = getInvalidCVVWithOneDigitCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitCVVTwoNumbers() {
        var info = getInvalidCVVWithTwoDigitsCardInfo();

        PaymentPage paymentPage = new PaymentPage();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }
}
