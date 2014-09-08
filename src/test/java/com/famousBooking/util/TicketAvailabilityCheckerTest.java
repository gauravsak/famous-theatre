package com.famousBooking.util;

import com.famousBooking.exception.SeatsNotAvailableException;
import com.famousBooking.exception.ShowNotFoundException;
import com.famousBooking.model.BookingRequest;
import com.famousBooking.model.Theatre;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gsakhardande on 4/9/14.
 */
public class TicketAvailabilityCheckerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldShowTicketsAvailabilityForValidBookingRequest() throws SeatsNotAvailableException, ShowNotFoundException {

        // Given
        String showDateTime = "21-AUG-2014 11:00";
        int numberOfTickets = 10;
        long mobileNumber = 123;

        Map<String, Integer> ticketAvailabilityRepository = new HashMap<>();
        ticketAvailabilityRepository.put(showDateTime, Theatre.CAPACITY);

        TicketAvailabilityChecker ticketAvailabilityChecker = new TicketAvailabilityChecker(ticketAvailabilityRepository);

        BookingRequest bookingRequest = new BookingRequest(showDateTime, numberOfTickets, mobileNumber);

        // When
        // Then
        assert ticketAvailabilityChecker.ticketAvailable(bookingRequest);
    }

    @Test
    public void shouldShowTicketUnavailabilityForRequestedSeatsMoreThanAvailable() throws SeatsNotAvailableException, ShowNotFoundException {

        // Given
        String showDateTime = "21-AUG-2014 11:00";
        int numberOfTickets = 10;
        long mobileNumber = 123;

        Map<String, Integer> ticketAvailabilityRepository = new HashMap<>();
        ticketAvailabilityRepository.put(showDateTime, 5);

        TicketAvailabilityChecker ticketAvailabilityChecker = new TicketAvailabilityChecker(ticketAvailabilityRepository);

        BookingRequest bookingRequest = new BookingRequest(showDateTime, numberOfTickets, mobileNumber);

        // When
        // Then
        assert !ticketAvailabilityChecker.ticketAvailable(bookingRequest);
    }

    @Test
    public void shouldThrowSeatsNotAvailableIfRequestedSeatsMoreThanCapacity() throws SeatsNotAvailableException, ShowNotFoundException {

        // Given
        String showDateTime = "21-AUG-2014 11:00";
        int numberOfTickets = Theatre.CAPACITY;
        long mobileNumber = 123;

        Map<String, Integer> ticketAvailabilityRepository = new HashMap<>();
        ticketAvailabilityRepository.put(showDateTime, Theatre.CAPACITY);

        TicketAvailabilityChecker ticketAvailabilityChecker = new TicketAvailabilityChecker(ticketAvailabilityRepository);

        BookingRequest bookingRequest = new BookingRequest(showDateTime, numberOfTickets, mobileNumber);

        // Then
        expectedException.expect(SeatsNotAvailableException.class);
        expectedException.expectMessage("Sorry! Theatre capacity is " + Theatre.CAPACITY + " at the moment. Of course, we want our audience to feel among the privileged!!");

        // When
        ticketAvailabilityChecker.ticketAvailable(bookingRequest);
    }

    @Test
    public void shouldThrowShowNotFoundIfRequestedDateTimeNotForValidShow() throws SeatsNotAvailableException, ShowNotFoundException {

        // Given
        String showDateTime = "21-AUG-2014 12:00";
        int numberOfTickets = 10;
        long mobileNumber = 123;

        Map<String, Integer> ticketAvailabilityRepository = new HashMap<>();
        ticketAvailabilityRepository.put("21-AUG-2014 11:00", Theatre.CAPACITY);

        TicketAvailabilityChecker ticketAvailabilityChecker = new TicketAvailabilityChecker(ticketAvailabilityRepository);

        BookingRequest bookingRequest = new BookingRequest(showDateTime, numberOfTickets, mobileNumber);

        // Then
        expectedException.expect(ShowNotFoundException.class);

        // When
        ticketAvailabilityChecker.ticketAvailable(bookingRequest);
    }
}
