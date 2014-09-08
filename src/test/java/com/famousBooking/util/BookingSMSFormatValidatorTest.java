package com.famousBooking.util;

import com.famousBooking.exception.BookingSMSFormatException;
import com.famousBooking.model.Theatre;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by gsakhardande on 8/9/14.
 */
public class BookingSMSFormatValidatorTest {

    @Test
    public void shouldPassForValidSMSAsPerFormat() {

        String date = "26-AUG-2014";
        String time = "11:00";
        int numberOfTickets = 2;

        // Given
        String validSMS = "DT " + date + " " + time + " TKT " + numberOfTickets;

        BookingSMSFormatValidator validator = new BookingSMSFormatValidator(Theatre.BOOKING_SMS_FORMAT);

        validator.validate(validSMS);
    }

    @Test(expected = BookingSMSFormatException.class)
    public void throwsBookingSMSFormatExceptionForMessageNotAsPerFormat() {

        // Given
        String invalidSMS = "qwerty";

        BookingSMSFormatValidator validator = new BookingSMSFormatValidator(Theatre.BOOKING_SMS_FORMAT);

        validator.validate(invalidSMS);
    }
}
