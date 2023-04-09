package data;

import lombok.Value;

public class CardInformation {
    private String number;
    private String month;
    private String year;
    private String owner;
    private String cvv;

    public CardInformation(String number, String month, String year, String owner, String cvv) {
        this.number = number;
        this.month = month;
        this.year = year;
        this.owner = owner;
        this.cvv = cvv;
    }

    public String getNumber() {
        return number;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getOwner() {
        return owner;
    }

    public String getCvv() {
        return cvv;
    }
}
