package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private final SelenideElement header = $("h2");
    private final SelenideElement buttonCard = $x("/html/body/div/div/button[1]/span/span");
    private final SelenideElement buttonCredit = $x("//span[contains(text(),'Купить в кредит')]");
    private final SelenideElement headerCard = $(withText("Оплата по карте"));
    private final SelenideElement headerCredit = $(withText("Кредит по данным карты"));

    public void dashboardPageStarting() {
        header.shouldBe(Condition.visible);
    }

    public void cardPaymentHeader() {
        headerCard.shouldBe(visible);
    }

    public void creditPaymentHeader() {
        headerCredit.shouldBe(visible);
    }

    public CardPage pressButtonCard() {
        buttonCard.click();
        return new CardPage();
    }

    public CardPage pressButtonCredit() {
        buttonCredit.click();
        return new CardPage();
    }
}