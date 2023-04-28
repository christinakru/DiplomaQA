package pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.CardInformation;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private final ElementsCollection fields = $$(".input__control");
    private final SelenideElement numberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement ownerField = fields.get(3);
    private final SelenideElement cvvField = $("[placeholder='999']");

    private final SelenideElement continueButton = $(byText("Продолжить"));
    private final SelenideElement notification = $("div.notification:nth-child(7) > div:nth-child(3)");
    private final SelenideElement successNotification = $(byText("Операция одобрена Банком."));
    private final SelenideElement failNotification = $(byText("Ошибка! Банк отказал в проведении операции."));
    private final SelenideElement wrongFormat = $(byText("Неверный формат"));
    private final SelenideElement wrongTerm = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpired = $(byText("Истёк срок действия карты"));
    private final SelenideElement fieldRequired = $(byText("Поле обязательно для заполнения"));

    public void fillForm(CardInformation info) {
        numberField.setValue(info.getNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        ownerField.setValue(info.getOwner());
        cvvField.setValue(info.getCvv());
        continueButton.click();
    }

    public void successMessage() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void failMessage() {
        failNotification.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void wrongFormatMessage() {
        wrongFormat.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void wrongTermMessage() {
        wrongTerm.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void cardExpiredMessage() {
        cardExpired.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void shouldFillMessage() {
        fieldRequired.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void shouldNotify() {
        notification.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }
}
