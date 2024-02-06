package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.BDHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.CardPage;
import ru.netology.page.DashboardPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.BDHelper.*;

public class CreditTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void tearDown() {
        cleanCreditStatuses();
    }

    @AfterAll
    static void tearDownAll() {
        cleanDatabase();
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("Sending a form with valid values in the fields when paying by credit card")
    void sendingAFormWithValidValuesInTheFieldsWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formWidgetOperation();
        var creditInfo = BDHelper.getSuccessfulCredit().getResultCredit();
        Assertions.assertEquals("APPROVED", creditInfo);
    }

    @Test
    @DisplayName("Sending a form with empty fields when paying by credit card")
    void sendingAFormWithEmptyFieldsWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.formButton();
        cardPage.formNumberFieldError("Неверный формат");
        cardPage.formMonthFieldError("Неверный формат");
        cardPage.formYearFieldError("Неверный формат");
        cardPage.formOwnerFieldError("Поле обязательно для заполнения");
        cardPage.formCodFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with an empty card number field when paying by credit card")
    void sendingAFormWithAnEmptyCardNumberFieldWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formNumberFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with an empty month field when paying by credit card")
    void sendingAFormWithAnEmptyMonthFieldWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formMonthFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with an empty year field when paying by credit card")
    void sendingAFormWithAnEmptyYearFieldWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formYearFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with an empty owner field when paying by credit card")
    void sendingAFormWithAnEmptyOwnerFieldWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formOwnerFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Sending a form with an empty CVC/CVV field when paying by credit card")
    void sendingAFormWithAnEmptyCVCCVVFieldWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.formButton();
        cardPage.formCodFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with the details of the rejected card when paying by credit card")
    void sendingAFormWithTheDetailsOfTheRejectedCardWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getSecondCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formWidgetError();
        var creditInfo = BDHelper.getSuccessfulCredit().getResultCredit();
        Assertions.assertEquals("DECLINED", creditInfo);
    }

    @Test
    @DisplayName("Sending a form with an invalid value in the month field when paying by credit card")
    void sendingAFormWithAnInvalidValueInTheMonthFieldWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatchPlusTwelveMonths());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formMonthFieldError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Sending a form with an invalid value in the owner field when paying by credit card")
    void sendingAFormWithAnInvalidValueInTheOwnerFieldWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getNotValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formOwnerFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Sending a form with an invalid value in the CVC/CVV field when paying by credit card")
    void sendingAFormWithAnInvalidValueInTheCVCCVVFieldWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomInvalidSecurityCode());
        cardPage.formButton();
        cardPage.formCodFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with a fifteen-digit number in the card field when paying by credit card")
    void sendingAFormWithAFifteenDigitNumberInTheCardFieldWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getNotValidCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formNumberFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with a numeric value in the owner field when paying by credit card")
    void sendingAFormWithANumericValueInTheOwnerFieldWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getNumberOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formOwnerFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Sending a form with a zero value in the year field when paying by credit card")
    void sendingAFormWithAZeroValueInTheYearFieldWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getZeroDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formYearFieldError("Истёк срок действия карты");
    }

    @Test
    @DisplayName("Sending a form with a zero in the card field when paying by credit card")
    void sendingAFormWithAZeroInTheCardFieldWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getZeroCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formNumberFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with a zero value in the month field when paying by credit card")
    void sendingAFormWithAZeroValueInTheMonthFieldWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getZeroDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formMonthFieldError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Sending a form with a zero value in the CVC/CVV field when paying by credit card")
    void sendingAFormWithAZeroValueInTheCVCCVVFieldWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getZeroSecurityCode());
        cardPage.formButton();
        cardPage.formCodFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with expired card data when paying by credit card")
    void sendingAFormWithExpiredCardDataWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYearMinusSixYears());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formYearFieldError("Истёк срок действия карты");
    }

    @Test
    @DisplayName("Sending a form with an invalid value in the year field when paying by credit card")
    void sendingAFormWithAnInvalidValueInTheYearFieldWhenPayingByCreditCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYearPlusSixYears());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formYearFieldError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Sending a form with the details of the rejected card when paying by credit card without a message")
    void sendingAFormWithTheDetailsOfTheRejectedCardWhenPayingByCreditCardWithoutAMessage() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getSecondCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        TimeUnit.SECONDS.sleep(15);
        var creditInfo = BDHelper.getSuccessfulCredit().getResultCredit();
        Assertions.assertEquals("DECLINED", creditInfo);
    }

    @Test
    @DisplayName("Sending a form with the details of the missing card when paying by credit")
    void sendingAFormWithTheDetailsOfTheMissingCardWhenPayingByCredit() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCredit();
        cardPage.creditPaymentHeader();
        cardPage.fillFormCard(DataHelper.getMissingCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formWidgetError();
    }
}
