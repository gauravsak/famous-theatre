package com.famousBooking.util;

import com.famousBooking.exception.SeatsNotAvailableException;
import com.famousBooking.exception.ShowNotFoundException;
import com.famousBooking.model.BookingRequest;
import com.famousBooking.model.Theatre;

import java.util.Map;

/**
 * Created by gsakhardande on 4/9/14.
 */
public class TicketAvailabilityChecker {
    private Map<String, Integer> bookingStatsRepository;

    public TicketAvailabilityChecker(Map<String, Integer> bookingStatsRepository) {
        this.bookingStatsRepository = bookingStatsRepository;
    }

    public boolean ticketAvailable(BookingRequest bookingRequest) throws SeatsNotAvailableException, ShowNotFoundException {
        if(requestedMoreThanCapacity(bookingRequest)) {
            throw new SeatsNotAvailableException("Sorry! Theatre capacity is " + Theatre.CAPACITY + " at the moment. Of course, we want our audience to feel among the privileged!!");
        }
        Integer numberOfAvailableTickets = bookingStatsRepository.get(bookingRequest.getDateTime());
        if(isValidShow(numberOfAvailableTickets)) {
            throw new ShowNotFoundException();
        } else if (requestedAreAvailable(bookingRequest, numberOfAvailableTickets)) {
            return true;
        }
        return false;
    }

    private boolean requestedMoreThanCapacity(BookingRequest bookingRequest) {
        return bookingRequest.getNumberOfTickets() >= Theatre.CAPACITY;
    }

    private boolean requestedAreAvailable(BookingRequest bookingRequest, Integer numberOfAvailableTickets) {
        return numberOfAvailableTickets >= bookingRequest.getNumberOfTickets();
    }

    private boolean isValidShow(Integer numberOfAvailableTickets) {
        return numberOfAvailableTickets == null;
    }
}
