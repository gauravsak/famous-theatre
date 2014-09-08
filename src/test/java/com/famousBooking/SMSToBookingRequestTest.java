package com.famousBooking;

import com.famousBooking.exception.BookingSMSFormatException;
import com.famousBooking.model.BookingRequest;
import com.famousBooking.model.SMS;
import com.famousBooking.model.Theatre;
import com.famousBooking.model.BookingSMS;
import com.famousBooking.util.BookingSMSFormatValidator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by gsakhardande on 4/9/14.
 */
public class SMSToBookingRequestTest {

    @Test
    public void shouldCreateBookingRequestFromBookingSMS() {
        String date = "26-AUG-2014";
        String time = "11:00";
        int numberOfTickets = 2;

        long mobileNumber = 123;

        // Given
        String message = "DT " + date + " " + time + " TKT " + numberOfTickets;
        SMS sms = new SMS(message, mobileNumber);
        BookingSMSFormatValidator validator = new BookingSMSFormatValidator(Theatre.BOOKING_SMS_FORMAT);

        BookingSMS bookingSMS = new BookingSMS(sms, validator);

        // When
        BookingRequest bookingRequest = bookingSMS.toBookingRequest();

        // Then
        assertEquals(date + " " + time, bookingRequest.getDateTime());
        assertEquals(mobileNumber, bookingRequest.getMobileNumber());
        assertEquals(numberOfTickets, bookingRequest.getNumberOfTickets());
    }

    @Test(expected = BookingSMSFormatException.class)
    public void throwsBookingSMSFormatExceptionForInvalidSMSAndDoesntCreateBookingSMS() {
        long mobileNumber = 123;

        // Given
        String message = "qwerty";
        SMS sms = new SMS(message, mobileNumber);
        BookingSMSFormatValidator validator = new BookingSMSFormatValidator(Theatre.BOOKING_SMS_FORMAT);


        // When
        new BookingSMS(sms, validator);

        // Then
    }
}
