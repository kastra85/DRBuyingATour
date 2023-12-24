package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.commands.Press;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.page.CardPage;
import ru.netology.page.DashboardPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class CardTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("Successful purchase by card")
    void successfulPurchaseByCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.DashboardPageStarting();
        dashboardPage.PressButtonCard();
        CardPage cardPage = new CardPage();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.SecurityCode.getRandomSecurityCode());
        cardPage.FormButton();
        cardPage.FormWidgetOperation();

    }
    @Test
    @DisplayName("Unsuccessful purchase with a rejected card")
    void UnsuccessfulPurchaseWithRejectedCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.DashboardPageStarting();
        dashboardPage.PressButtonCard();
        CardPage cardPage = new CardPage();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getSecondCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.SecurityCode.getRandomSecurityCode());
        cardPage.FormButton();
        cardPage.FormWidgetError();

    }
}