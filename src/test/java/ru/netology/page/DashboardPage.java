package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class DashboardPage {
    private final SelenideElement header = $("h2");
    private final SelenideElement buttonCard = $x("/html/body/div/div/button[1]/span/span");
    private final SelenideElement buttonCredit = $x("//span[contains(text(),'Купить в кредит')]");


    public void DashboardPageStarting() {
        header.shouldBe(Condition.visible);
    }

    public CardPage PressButtonCard() {
        buttonCard.click();
        return new CardPage();
    }
    public CardPage PressButtonCredit() {
        buttonCredit.click();
        return  new CardPage();

    }
}