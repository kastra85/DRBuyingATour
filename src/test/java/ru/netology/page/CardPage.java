package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class CardPage {
    private final SelenideElement numberField = $x("//input[@placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $x("//input[@placeholder='08']");
    private final SelenideElement yearField = $x("//input[@placeholder='22']");
    private final SelenideElement ownerField = $x("/html/body/div/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    private final SelenideElement codField = $x("//input[@placeholder='999']");
    private final SelenideElement sendingButton = $(withText("Продолжить"));
    private final SelenideElement operationNotification = $(withText("Операция одобрена Банком"));
    private final SelenideElement errorNotification = $("Ошибка! Банк отказал в проведении операции");
    private final SelenideElement errorNumberField = $x("/html/body/div/div/form/fieldset/div[1]/span/span/span[3]");
    private final SelenideElement errorMonthField = $x("/html/body/div/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]");
    private final SelenideElement errorYearField = $x("/html/body/div/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]");
    private final SelenideElement errorOwnerField = $x("/html/body/div/div/form/fieldset/div[3]/span/span[1]/span/span/span[3]");
    private final SelenideElement errorCodField = $x("/html/body/div/div/form/fieldset/div[3]/span/span[2]/span/span/span[3]");
    private final SelenideElement errorFieldText = $(withText("Неверный формат"));
    private final SelenideElement errorOwnerFieldText = $(withText("Поле обязательно для заполнения"));
    private final SelenideElement errorYearFieldText = $("Истекший срок действия карты");
    private final SelenideElement errorMonthFieldText = $(withText("Неверно указан срок действия карты"));


    public void fillFormCard(DataHelper.CardInfo cardInfo) {
        numberField.setValue(cardInfo.getCardNumber());
    }

    public void fillFormMonth(DataHelper.MonthInfo monthInfo) {
        monthField.setValue(String.valueOf(monthInfo.getMonthNumber()));
    }

    public void fillFormYear(DataHelper.YearInfo yearInfo) {
        yearField.setValue(yearInfo.getYearNumber());
    }

    public void fillFormOwner(DataHelper.Owner owner) {
        ownerField.setValue(owner.getOwnerDetails());
    }

    public void fillFormSecurityCod(DataHelper.SecurityCode securityCode) {
        codField.setValue(securityCode.getNumberString());
    }

    public void formButton() {
        sendingButton.click();
    }

    public void formWidgetOperation() {
        operationNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void formWidgetError() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void formNumberFieldError() {
        errorNumberField.shouldBe(visible);
    }

    public void formMonthFieldError() {
        errorMonthField.shouldBe(visible);
    }

    public void formYearFieldError() {
        errorYearField.shouldBe(visible);
    }

    public void formOwnerFieldError() {
        errorOwnerField.shouldBe(visible);
    }

    public void formCodFieldError() {
        errorCodField.shouldBe(visible);
    }

    public void formFieldError() {
        errorFieldText.shouldBe(visible);
    }

    public void formOwnerFieldErrorText() {
        errorOwnerFieldText.shouldBe(visible);
    }

    public void formYearFieldErrorText() {
        errorYearFieldText.shouldBe(visible);
    }

    public void formMonthFieldErrorText() {
        errorMonthFieldText.shouldBe(visible);
    }
}
