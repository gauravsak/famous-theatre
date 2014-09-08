package com.famousBooking.util;

import com.famousBooking.model.Booking;
import com.famousBooking.model.BookingRequest;
import com.famousBooking.model.Theatre;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by gsakhardande on 4/9/14.
 */
public class SeatNumberAllocator {
    private final Set<Booking> bookingRepository;
    private final Map<String, Integer> bookingStatsRepository;

    public SeatNumberAllocator(Set<Booking> bookingRepository, Map<String, Integer> bookingStatsRepository) {

        this.bookingRepository = bookingRepository;
        this.bookingStatsRepository = bookingStatsRepository;
    }

    public List<Integer> allocateSeatsFor(BookingRequest bookingRequest) {
        int numberOfAvailableSeats = bookingStatsRepository.get(bookingRequest.getDateTime());
        int numberOfBookedSeats = Theatre.CAPACITY - numberOfAvailableSeats;
        int seatNumber = numberOfBookedSeats + 1;
        List<Integer> allocatedSeatNumbers = new ArrayList<>();
        while(seatNumber <= (numberOfBookedSeats + bookingRequest.getNumberOfTickets())) {
            allocatedSeatNumbers.add(seatNumber ++);
        }
        return allocatedSeatNumbers;
    }
}
