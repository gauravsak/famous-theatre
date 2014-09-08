package com.famousBooking;

import com.famousBooking.model.Booking;
import com.famousBooking.model.SMS;
import com.famousBooking.model.Theatre;
import com.famousBooking.model.BookingSMS;
import com.famousBooking.util.BookingSMSFormatValidator;
import com.famousBooking.util.SeatNumberAllocator;
import com.famousBooking.util.TicketAvailabilityChecker;
import org.junit.Test;

import java.util.*;

/**
 * Created by gsakhardande on 4/9/14.
 */
public class MovieTicketBookingTest {

    @Test
    public void shouldBookTicketsSuccessfullyWithValidSms() {

        String date = "26-AUG-2014";
        String time = "11:00";
        int numberOfTickets = 2;

        long mobileNumber = 123;

        List<Integer> expectedSeatNumbers = Arrays.asList(1, 2);

        // Given
        String message = "DT " + date + " " + time + " TKT " + numberOfTickets;
        SMS sms = new SMS(message, mobileNumber);

        Set<Booking> bookingRepository = Collections.emptySet();
        Map<String, Integer> bookingStatsRepository = Collections.emptyMap();

        BookingSMSFormatValidator validator = new BookingSMSFormatValidator(Theatre.BOOKING_SMS_FORMAT);
        BookingSMS bookingSMS = new BookingSMS(sms, validator);

        TicketAvailabilityChecker ticketAvailabilityChecker = new TicketAvailabilityChecker(bookingStatsRepository);

        SeatNumberAllocator seatNumberAllocator = new SeatNumberAllocator(bookingRepository, bookingStatsRepository);


        Theatre theatre = new Theatre(bookingRepository, bookingStatsRepository);

        // When

        // Then
    }
}
