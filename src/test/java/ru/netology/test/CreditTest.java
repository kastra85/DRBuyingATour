package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.CardPage;
import ru.netology.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;

public class CreditTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("Successful purchase by credit")
    void successfulPurchaseByCredit() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.DashboardPageStarting();
        dashboardPage.PressButtonCredit();
        CardPage cardPage = new CardPage();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.SecurityCode.getRandomSecurityCode());
        cardPage.FormButton();
        cardPage.FormWidgetOperation();
    }
    @Test
    @DisplayName("Unsuccessful purchase with a rejected credit")
    void UnsuccessfulPurchaseWithRejectedCredit() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.DashboardPageStarting();
        dashboardPage.PressButtonCredit();
        CardPage cardPage = new CardPage();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getSecondCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.SecurityCode.getRandomSecurityCode());
        cardPage.FormButton();
        cardPage.FormWidgetError();
    }
}
