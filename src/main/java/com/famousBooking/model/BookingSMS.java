package com.famousBooking.model;

import com.famousBooking.util.BookingSMSFormatValidator;

/**
 * Created by gsakhardande on 4/9/14.
 */
public class BookingSMS {

    private SMS sms;

    public BookingSMS(SMS sms, BookingSMSFormatValidator validator) {
        validator.validate(sms.getText());
        this.sms = sms;
    }

    public BookingRequest toBookingRequest() {
        String[] tokens = tokenize(sms.getText());
        String date = tokens[1];
        String time = tokens[2];
        int numberOfTickets = Integer.parseInt(tokens[4]);
        long mobileNumber = sms.getSourceMobileNumber();

        return new BookingRequest(date, time, numberOfTickets, mobileNumber);
    }

    private String[] tokenize(String text) {
        return text.split("\\s+");
    }
}
