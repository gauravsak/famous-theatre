package com.famousBooking.util;

import com.famousBooking.model.Booking;
import com.famousBooking.model.BookingRequest;
import com.famousBooking.model.Theatre;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by gsakhardande on 4/9/14.
 */
public class SeatNumberAllocatorTest {

    @Test
    public void shouldAllocateSeatNumbersInNumericalOrderForFirstBooking() {

        // Given
        String showDateTime = "21-AUG-2014 11:00";
        int numberOfTickets = 10;
        long mobileNumber = 123;

        List<Integer> expectedSeatNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Set<Booking> bookingRepository = Collections.emptySet();

        Map<String, Integer> bookingStatsRepository = new HashMap<>();
        bookingStatsRepository.put(showDateTime, Theatre.CAPACITY);

        SeatNumberAllocator seatNumberAllocator = new SeatNumberAllocator(bookingRepository, bookingStatsRepository);

        BookingRequest bookingRequest = new BookingRequest(showDateTime, numberOfTickets, mobileNumber);

        // When
        List<Integer> allocatedSeatNumbers = seatNumberAllocator.allocateSeatsFor(bookingRequest);

        // Then
        assertEquals(expectedSeatNumbers, allocatedSeatNumbers);
    }

    @Test
    public void shouldAllocateNextAvailableSeatNumbersInNumericalOrder() {

        // Given
        String showDateTime = "21-AUG-2014 11:00";
        int numberOfTickets = 2;
        long mobileNumber = 123;

        List<Integer> expectedSeatNumbers = Arrays.asList(11, 12);

        Set<Booking> bookingRepository = new HashSet<>();
        bookingRepository.add(new Booking(showDateTime, 10, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), mobileNumber));

        Map<String, Integer> bookingStatsRepository = new HashMap<>();
        bookingStatsRepository.put(showDateTime, Theatre.CAPACITY - 10);

        SeatNumberAllocator seatNumberAllocator = new SeatNumberAllocator(bookingRepository, bookingStatsRepository);

        BookingRequest bookingRequest = new BookingRequest(showDateTime, numberOfTickets, mobileNumber);

        // When
        List<Integer> allocatedSeatNumbers = seatNumberAllocator.allocateSeatsFor(bookingRequest);

        // Then
        assertEquals(expectedSeatNumbers, allocatedSeatNumbers);
    }
}
