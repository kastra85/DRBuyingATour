package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.BDHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.CardPage;
import ru.netology.page.DashboardPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.BDHelper.cleanDatabase;
import static ru.netology.data.BDHelper.cleanPaymentStatuses;

public class CardTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void tearDown() {
        cleanPaymentStatuses();
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
    @DisplayName("Sending a form with valid values in the fields when paying with a debit card")
    void sendingAFormWithValidValuesInTheFieldsWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formWidgetOperation();
        var paymentInfo = BDHelper.getSuccessfulPayment().getResultPayment();
        Assertions.assertEquals("APPROVED", paymentInfo);
    }

    @Test
    @DisplayName("Sending a form with empty fields when paying with a debit card")
    void sendingAFormWithEmptyFieldsWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.formButton();
        cardPage.formNumberFieldError("Неверный формат");
        cardPage.formMonthFieldError("Неверный формат");
        cardPage.formYearFieldError("Неверный формат");
        cardPage.formOwnerFieldError("Поле обязательно для заполнения");
        cardPage.formCodFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with an empty card number field when paying with a debit card")
    void sendingAFormWithAnEmptyCardNumberFieldWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        dashboardPage.pressButtonCard();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formNumberFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with an empty month field when paying with a debit card")
    void sendingAFormWithAnEmptyMonthFieldWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formMonthFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with an empty year field when paying with a debit card")
    void sendingAFormWithAnEmptyYearFieldWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formYearFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with an empty owner field when paying with a debit card")
    void sendingAFormWithAnEmptyOwnerFieldWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formOwnerFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Sending a form with an empty CVC/CVV field when paying with a debit card")
    void sendingAFormWithAnEmptyCVCCVVFieldWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.formButton();
        cardPage.formCodFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with the details of the rejected card when paying with a debit card")
    void sendingAFormWithTheDetailsOfTheRejectedCardWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getSecondCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formWidgetError();
        var paymentInfo = BDHelper.getSuccessfulPayment().getResultPayment();
        Assertions.assertEquals("DECLINED", paymentInfo);
    }

    @Test
    @DisplayName("Sending a form with an invalid value in the month field when paying with a debit card")
    void sendingAFormWithAnInvalidValueInTheMonthFieldWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatchPlusTwelveMonths());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formMonthFieldError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Sending a form with an invalid value in the owner field when paying with a debit card")
    void sendingAFormWithAnInvalidValueInTheOwnerFieldWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getNotValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formOwnerFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Sending a form with an invalid value in the CVC/CVV field when paying with a debit card")
    void sendingAFormWithAnInvalidValueInTheCVCCVVFieldWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomInvalidSecurityCode());
        cardPage.formButton();
        cardPage.formCodFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with a fifteen-digit number in the card field when paying with a debit card")
    void sendingAFormWithAFifteenDigitNumberInTheCardFieldWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getNotValidCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formNumberFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with a numeric value in the owner field when paying with a debit card")
    void sendingAFormWithANumericValueInTheOwnerFieldWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getNumberOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formOwnerFieldError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Sending a form with a zero value in the year field when paying with a debit card")
    void sendingAFormWithAZeroValueInTheYearFieldWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getZeroDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formYearFieldError("Истёк срок действия карты");
    }

    @Test
    @DisplayName("Sending a form with a zero in the card field when paying with a debit card")
    void sendingAFormWithAZeroInTheCardFieldWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getZeroCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formNumberFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with a zero value in the month field when paying with a debit card")
    void sendingAFormWithAZeroValueInTheMonthFieldWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getZeroDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formMonthFieldError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Sending a form with a zero value in the CVC/CVV field when paying with a debit card")
    void sendingAFormWithAZeroValueInTheCVCCVVFieldWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getZeroSecurityCode());
        cardPage.formButton();
        cardPage.formCodFieldError("Неверный формат");
    }

    @Test
    @DisplayName("Sending a form with expired card data when paying with a debit card")
    void sendingAFormWithExpiredCardDataWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYearMinusSixYears());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formYearFieldError("Истёк срок действия карты");
    }

    @Test
    @DisplayName("Sending a form with an invalid value in the year field when paying with a debit card")
    void sendingAFormWithAnInvalidValueInTheYearFieldWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getFirstCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYearPlusSixYears());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formYearFieldError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Sending a form with the details of the rejected card when paying with a debit card without a message")
    void sendingAFormWithTheDetailsOfTheRejectedCardWhenPayingWithADebitCardWithoutAMessage() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getSecondCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        TimeUnit.SECONDS.sleep(15);
        var paymentInfo = BDHelper.getSuccessfulPayment().getResultPayment();
        Assertions.assertEquals("DECLINED", paymentInfo);
    }

    @Test
    @DisplayName("Sending a form with the details of the missing card when paying with a debit card ")
    void sendingAFormWithTheDetailsOfTheMissingCardWhenPayingWithADebitCard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.dashboardPageStarting();
        CardPage cardPage = dashboardPage.pressButtonCard();
        cardPage.cardPaymentHeader();
        cardPage.fillFormCard(DataHelper.getMissingCardInfo());
        cardPage.fillFormMonth(DataHelper.getDataMatch());
        cardPage.fillFormYear(DataHelper.getDataYear());
        cardPage.fillFormOwner(DataHelper.getValidOwner());
        cardPage.fillFormSecurityCod(DataHelper.getRandomSecurityCode());
        cardPage.formButton();
        cardPage.formWidgetError();
    }
}