package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }
@Value
public static class YearInfo{
        private String yearNumber;
}
    public static YearInfo getDataYear() {
        return new YearInfo(LocalDate.now().format(DateTimeFormatter.ofPattern("uu")));
    }

    public static YearInfo getDataYearPlusSixYears() {
        return new YearInfo(LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("uu")));
    }

    @Value
    public static class Owner {
        private String ownerDetails;
    }

    public static Owner getValidOwner() {
        return new Owner("IVAN IVANOV");
    }

    public static Owner getNotValidOwner() {
        return new Owner("ИВАН ИВАНОВ");
    }

    @Value
    public static class MonthInfo {
        private int monthNumber;
    }

    public static MonthInfo getDataMatch() {
        return new MonthInfo(LocalDate.now().getMonthValue());
    }

    public static MonthInfo getDataMatchPlusTwelveMonths() {
        return new MonthInfo(LocalDate.now().plusMonths(6).getMonthValue());
    }

    @Value
    public static class SecurityCode {
        private String numberString;

        public static SecurityCode getRandomSecurityCode() {
            return new SecurityCode(faker.numerify("###"));
        }
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo("4444 4444 4444 4441");
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("4444 4444 4444 4442");
    }

    public static CardInfo getThirdCardInfo() {
        return new CardInfo("4444 4444 4444 4443");
    }

    public static CardInfo getZeroCardInfo() {
        return new CardInfo("0000 0000 0000 0000");
    }

    public static CardInfo getNotValidCardInfo() {
        return new CardInfo("4444 4444 4444 444");
    }

}
