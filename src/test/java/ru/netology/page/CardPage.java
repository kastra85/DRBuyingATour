package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class CardPage {
    private final SelenideElement headerCard = $(withText("Оплата по карте"));
    private final SelenideElement headerCredit = $(withText("Кредит по данным карты"));
    private final SelenideElement numberField = $x("//input[@placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $x("//input[@placeholder='08']");
    private final SelenideElement yearField = $x("//input[@placeholder='22']");
    private final SelenideElement ownerField = $x("/html/body/div/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    private final SelenideElement codField = $x("//input[@placeholder='999']");
    private final SelenideElement sendingButton = $(withText("Продолжить"));
    private final SelenideElement operationNotification = $(withText("Операция одобрена Банком"));
    private final SelenideElement errorNotification = $("Ошибка! Банк отказал в проведении операции");

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

    public void FormButton() {
        sendingButton.click();

    }

    public void FormWidgetOperation() {
        operationNotification.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void FormWidgetError() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }
}
