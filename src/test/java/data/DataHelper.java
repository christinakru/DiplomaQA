package data;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataHelper {
    private static final String approvedCardNumber = "4444 4444 4444 4441";
    private static final String declinedCardNumber = "4444 4444 4444 4442";

    public static CardInformation getValidApprovedCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, month, year, getRandomCardOwner(), getRandomCvvCode());
    }

    public static CardInformation getValidDeclinedCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(declinedCardNumber, month, year, getRandomCardOwner(), getRandomCvvCode());
    }

    public static CardInformation getEmptyCardNumberCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation("", month, year, getRandomCardOwner(), getRandomCvvCode());
    }

    public static CardInformation getInvalidCardNumberWith15SymbolsCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation("4444 4444 4444 444", month, year, getRandomCardOwner(), getRandomCvvCode());
    }

    public static CardInformation getAnotherBankCardNumberCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation("5559 4444 4444 4444", month, year, getRandomCardOwner(), getRandomCvvCode());
    }

    public static CardInformation getEmptyMonthCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, "", year, getRandomCardOwner(), getRandomCvvCode());
    }

    public static CardInformation getInvalidFormatMonthZeroZeroCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, "00", year, getRandomCardOwner(), getRandomCvvCode());
    }

    public static CardInformation getInvalidFormatMonthIrrelevantCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, "13", year, getRandomCardOwner(), getRandomCvvCode());
    }

    public static CardInformation getInvalidFormatMonthOneDigitCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, "8", year, getRandomCardOwner(), getRandomCvvCode());
    }

    public static CardInformation getEmptyYearCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, month, "", getRandomCardOwner(), getRandomCvvCode());
    }

    public static CardInformation getEarlyYearCardInfo() {
        LocalDate pastDate = getRandomPastDate(100);
        String month = getMonthTwoDigits(pastDate);
        String year = getYearTwoDigits(pastDate);

        return new CardInformation(approvedCardNumber, month, year, getRandomCardOwner(), getRandomCvvCode());
    }

    public static CardInformation getFutureYearCardInfo() {
        return new CardInformation(approvedCardNumber, "08", "40", getRandomCardOwner(), getRandomCvvCode());
    }

    public static CardInformation getEmptyOwnerCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, month, year, "", getRandomCvvCode());
    }

    public static CardInformation getInvalidOwnerWithOneNameCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, month, year, "Ivan", getRandomCvvCode());
    }

    public static CardInformation getInvalidOwnerWithThreeWordsCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, month, year, "Ivanov Ivan Ivanovich", getRandomCvvCode());
    }

    public static CardInformation getInvalidOwnerWithLowerCaseCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, month, year, getRandomCardOwner(), getRandomCvvCode());
    }

    public static CardInformation getInvalidOwnerWithUpperCaseCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, month, year, getRandomCardOwner(), getRandomCvvCode());
    }

    public static CardInformation getInvalidOwnerWithCyrillicCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, month, year, "Иванов Иван", getRandomCvvCode());
    }

    public static CardInformation getInvalidOwnerWithDigitsCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, month, year, "12345", getRandomCvvCode());
    }

    public static CardInformation getInvalidOwnerWithSymbolsCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, month, year, "%№%№", getRandomCvvCode());
    }

    public static CardInformation getEmptyCVVCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, month, year, getRandomCardOwner(), "");
    }

    public static CardInformation getInvalidCVVWithOneDigitCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, month, year, getRandomCardOwner(), "9");
    }

    public static CardInformation getInvalidCVVWithTwoDigitsCardInfo() {
        LocalDate futureDate = getRandomFutureDate(100);
        String month = getMonthTwoDigits(futureDate);
        String year = getYearTwoDigits(futureDate);

        return new CardInformation(approvedCardNumber, month, year, getRandomCardOwner(), "91");
    }

    public static LocalDate getRandomPastDate(int daysAgo) {
        Random random = new Random();
        long randomDays = random.nextInt(daysAgo);
        return LocalDate.now().minus(randomDays, ChronoUnit.DAYS);
    }

    private static LocalDate getRandomFutureDate(int maxDays) {
        LocalDate now = LocalDate.now();
        int randomDays = ThreadLocalRandom.current().nextInt(maxDays);
        return now.plus(randomDays, ChronoUnit.DAYS);
    }

    private static String getYearTwoDigits(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
        return date.format(formatter);
    }

    private static String getMonthTwoDigits(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        return date.format(formatter);
    }

    private static String getRandomCardOwner() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }

    private static String getRandomCvvCode() {
        Faker faker = new Faker();
        return faker.number().digits(3);
    }
}
