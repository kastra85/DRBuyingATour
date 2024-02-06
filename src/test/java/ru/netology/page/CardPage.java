package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class CardPage {
    private final SelenideElement headerCard = $x("//h3[text()='Оплата по карте']");
    private final SelenideElement headerCredit = $x("//h3[text()='Кредит по данным карты']");
    private final SelenideElement numberField = $x("//input[@placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $x("//input[@placeholder='08']");
    private final SelenideElement yearField = $x("//input[@placeholder='22']");
    private final SelenideElement ownerField = $x("//span[text()='Владелец']/parent::*//input[@class='input__control']");
    private final SelenideElement codField = $x("//input[@placeholder='999']");
    private final SelenideElement sendingButton = $(withText("Продолжить"));
    private final SelenideElement operationNotification = $x("//div[text()='Операция одобрена Банком.']");
    private final SelenideElement errorNotification = $x("//div[text()='Ошибка! Банк отказал в проведении операции.']");
    private final SelenideElement errorNumberField = $x("//span[text()='Номер карты']/parent::*/span[@class='input__sub']");
    private final SelenideElement errorMonthField = $x("//span[text()='Месяц']/parent::*/span[@class='input__sub']");
    private final SelenideElement errorYearField = $x("//span[text()='Год']/parent::*/span[@class='input__sub']");
    private final SelenideElement errorOwnerField = $x("//span[text()='Владелец']/parent::*/span[@class='input__sub']");
    private final SelenideElement errorCodField = $x("//span[text()='CVC/CVV']/parent::*/span[@class='input__sub']");


    public void cardPaymentHeader() {
        headerCard.shouldBe(visible);
    }

    public void creditPaymentHeader() {
        headerCredit.shouldBe(visible);
    }

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

    public void formNumberFieldError(String expectedText) {
        errorNumberField.shouldHave(exactText(expectedText)).shouldBe(visible);
    }

    public void formMonthFieldError(String expectedText) {
        errorMonthField.shouldHave(exactText(expectedText)).shouldBe(visible);
    }

    public void formYearFieldError(String expectedText) {
        errorYearField.shouldHave(exactText(expectedText)).shouldBe(visible);
    }

    public void formOwnerFieldError(String expectedText) {
        errorOwnerField.shouldHave(exactText(expectedText)).shouldBe(visible);
    }

    public void formCodFieldError(String expectedText) {
        errorCodField.shouldHave(exactText(expectedText)).shouldBe(visible);
    }
}
