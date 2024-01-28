package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private final SelenideElement header = $("h2");
    private final SelenideElement buttonCard = $x("//span[text()='Купить']");
    private final SelenideElement buttonCredit = $x("//span[text()='Купить в кредит']");

    public void dashboardPageStarting() {
        header.shouldBe(Condition.visible);
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