package com.famousBooking.model;

/**
 * Created by gsakhardande on 28/8/14.
 */
public class SMS {
    private final String text;
    private final long sourceMobileNumber;

    public SMS(String text, long sourceMobileNumber) {
        this.text = text;
        this.sourceMobileNumber = sourceMobileNumber;
    }

    public String getText() {
        return text;
    }

    public long getSourceMobileNumber() {
        return sourceMobileNumber;
    }
}
