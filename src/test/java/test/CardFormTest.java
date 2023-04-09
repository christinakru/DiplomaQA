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
import static data.DataHelper.*;

public class CardFormTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
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
        paymentPage.fillForm(info);
        paymentPage.shouldFillMessage();
    }

    @Test
    void shouldNotSubmitCardWith15DigitsNumber() {
        var info = getInvalidCardNumberWith15SymbolsCardInfo();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitAnotherBankCard() {
        var info = getAnotherBankCardNumberCardInfo();
        paymentPage.fillForm(info);
        paymentPage.failMessage();
    }

    @Test
    void shouldNotSubmitEmptyMonth() {
        var info = getEmptyMonthCardInfo();
        paymentPage.fillForm(info);
        paymentPage.shouldFillMessage();
    }

    @Test
    void shouldNotSubmitMonthIsZero() {
        var info = getInvalidFormatMonthZeroZeroCardInfo();
        paymentPage.fillForm(info);
        paymentPage.wrongTermMessage();
    }

    @Test
    void shouldNotSubmitMonthOneDigit() {
        var info = getInvalidFormatMonthOneDigitCardInfo();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitMonthIncorrect() {
        var info = getInvalidFormatMonthIrrelevantCardInfo();
        paymentPage.fillForm(info);
        paymentPage.wrongTermMessage();
    }

    @Test
    void shouldNotSubmitEmptyYear() {
        var info = getEmptyYearCardInfo();
        paymentPage.fillForm(info);
        paymentPage.shouldFillMessage();
    }

    @Test
    void shouldNotSubmitEarlyYear() {
        var info = getEarlyYearCardInfo();
        paymentPage.fillForm(info);
        paymentPage.cardExpiredMessage();
    }

    @Test
    void shouldNotSubmitFutureYear() {
        var info = getFutureYearCardInfo();
        paymentPage.fillForm(info);
        paymentPage.wrongTermMessage();
    }

    @Test
    void shouldNotSubmitEmptyOwner() {
        var info = getEmptyOwnerCardInfo();
        paymentPage.fillForm(info);
        paymentPage.shouldFillMessage();
    }

    @Test
    void shouldNotSubmitOneWordOwner() {
        var info = getInvalidOwnerWithOneNameCardInfo();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitThreeWordsOwner() {
        var info = getInvalidOwnerWithThreeWordsCardInfo();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitLowerCaseOwner() {
        var info = getInvalidOwnerWithLowerCaseCardInfo();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitUpperCaseOwner() {
        var info = getInvalidOwnerWithUpperCaseCardInfo();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitRuOwner() {
        var info = getInvalidOwnerWithCyrillicCardInfo();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitOwnerWithNumbers() {
        var info = getInvalidOwnerWithDigitsCardInfo();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitOwnerWithSymbols() {
        var info = getInvalidOwnerWithSymbolsCardInfo();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitCVVIsEmpty() {
        var info = getEmptyCVVCardInfo();
        paymentPage.fillForm(info);
        paymentPage.shouldFillMessage();
    }

    @Test
    void shouldNotSubmitCVVOneNumber() {
        var info = getInvalidCVVWithOneDigitCardInfo();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }

    @Test
    void shouldNotSubmitCVVTwoNumbers() {
        var info = getInvalidCVVWithTwoDigitsCardInfo();
        paymentPage.fillForm(info);
        paymentPage.wrongFormatMessage();
    }
}
