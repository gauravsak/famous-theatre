package com.famousBooking.util;

import com.famousBooking.exception.BookingSMSFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gsakhardande on 8/9/14.
 */
public class BookingSMSFormatValidator {

    private String smsFormat;

    public BookingSMSFormatValidator(String smsFormat) {
        this.smsFormat = smsFormat;
    }

    public void validate(String smsText) {
        Pattern pattern = Pattern.compile(smsFormat);
        Matcher matcher = pattern.matcher(smsText);
        if (!matcher.matches()) throw new BookingSMSFormatException();
    }
}
