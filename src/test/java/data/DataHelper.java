package data;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DataHelper {
    private static final String ApprovedCardNumber = "4444 4444 4444 4441";
    private static final String DeclinedCardNumber = "4444 4444 4444 4442";
    private static final String ValidCardOwner = "Ivanov Ivan";
    private static final String ValidCVVCode = "916";

    public static CardInformation getValidApprovedCardInfo() {
        return new CardInformation(ApprovedCardNumber, "08", "23", ValidCardOwner, ValidCVVCode);
    }

    public static CardInformation getValidDeclinedCardInfo() {
        return new CardInformation(DeclinedCardNumber, "08", "23", ValidCardOwner, ValidCVVCode);
    }

    public static CardInformation getEmptyCardNumberCardInfo() {
        return new CardInformation("", "08", "23", ValidCardOwner, ValidCVVCode);
    }

    public static CardInformation getInvalidCardNumberWith15SymbolsCardInfo() {
        return new CardInformation("4444 4444 4444 444", "08", "23", ValidCardOwner, ValidCVVCode);
    }

    public static CardInformation getAnotherBankCardNumberCardInfo() {
        return new CardInformation("5559 4444 4444 4444", "08", "23", ValidCardOwner, ValidCVVCode);
    }

    public static CardInformation getEmptyMonthCardInfo() {
        return new CardInformation(ApprovedCardNumber, "", "23", ValidCardOwner, ValidCVVCode);
    }

    public static CardInformation getInvalidFormatMonthZeroZeroCardInfo() {
        return new CardInformation(ApprovedCardNumber, "00", "23", ValidCardOwner, ValidCVVCode);
    }

    public static CardInformation getInvalidFormatMonthIrrelevantCardInfo() {
        return new CardInformation(ApprovedCardNumber, "13", "23", ValidCardOwner, ValidCVVCode);
    }

    public static CardInformation getInvalidFormatMonthOneDigitCardInfo() {
        return new CardInformation(ApprovedCardNumber, "8", "23", ValidCardOwner, ValidCVVCode);
    }

    public static CardInformation getEmptyYearCardInfo() {
        return new CardInformation(ApprovedCardNumber, "08", "", ValidCardOwner, ValidCVVCode);
    }

    public static CardInformation getEarlyYearCardInfo() {
        return new CardInformation(ApprovedCardNumber, "05", "22", ValidCardOwner, ValidCVVCode);
    }

    public static CardInformation getFutureYearCardInfo() {
        return new CardInformation(ApprovedCardNumber, "08", "40", ValidCardOwner, ValidCVVCode);
    }

    public static CardInformation getEmptyOwnerCardInfo() {
        return new CardInformation(ApprovedCardNumber, "08", "23", "", ValidCVVCode);
    }

    public static CardInformation getInvalidOwnerWithOneNameCardInfo() {
        return new CardInformation(ApprovedCardNumber, "08", "23", "Ivan", ValidCVVCode);
    }

    public static CardInformation getInvalidOwnerWithThreeWordsCardInfo() {
        return new CardInformation(ApprovedCardNumber, "08", "23", "Ivanov Ivan Ivanovich", ValidCVVCode);
    }

    public static CardInformation getInvalidOwnerWithLowerCaseCardInfo() {
        return new CardInformation(ApprovedCardNumber, "08", "23", ValidCardOwner, ValidCVVCode);
    }

    public static CardInformation getInvalidOwnerWithUpperCaseCardInfo() {
        return new CardInformation(ApprovedCardNumber, "08", "23", ValidCardOwner, ValidCVVCode);
    }

    public static CardInformation getInvalidOwnerWithCyrillicCardInfo() {
        return new CardInformation(ApprovedCardNumber, "08", "23", "Иванов Иван", ValidCVVCode);
    }

    public static CardInformation getInvalidOwnerWithDigitsCardInfo() {
        return new CardInformation(ApprovedCardNumber, "08", "23", "12345", ValidCVVCode);
    }

    public static CardInformation getInvalidOwnerWithSymbolsCardInfo() {
        return new CardInformation(ApprovedCardNumber, "08", "23", "%№%№", ValidCVVCode);
    }

    public static CardInformation getEmptyCVVCardInfo() {
        return new CardInformation(ApprovedCardNumber, "08", "23", ValidCardOwner, "");
    }

    public static CardInformation getInvalidCVVWithOneDigitCardInfo() {
        return new CardInformation(ApprovedCardNumber, "08", "23", ValidCardOwner, "9");
    }

    public static CardInformation getInvalidCVVWithTwoDigitsCardInfo() {
        return new CardInformation(ApprovedCardNumber, "08", "23", ValidCardOwner, "91");
    }
}
