package com.famousBooking;

import com.famousBooking.model.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by gsakhardande on 1/9/14.
 */
public class MovieTicketBookingTest {

    @Test
    public void booksTicketForValidSms() {

        String date = "26-AUG-2014";
        String time = "11:00";
        int numberOfTickets = 2;

        long mobileNumber = 123;

        List<Integer> seatNumbers = Arrays.asList(1, 2);

        //Given
        String message = "DT " + date + " " + time + " TKT " + numberOfTickets;
        Sms sms = new Sms(message, mobileNumber);

        Set<Booking> bookingRepository = new HashSet<>();
        Map<String, BookingStat> bookingStatsRepository = mock(HashMap.class);

        when(bookingStatsRepository.get(date + " " + time)).thenReturn(new BookingStat(Theatre.MAX_CAPACITY));

        Theatre theatre = new Theatre(bookingRepository, bookingStatsRepository);

        //When
        String responseText = theatre.bookTickets(sms);

        //Then
        assertEquals("See you at " + date + " " + time + ". Your allocated seats are " + seatNumbers + ".", responseText);
    }

    @Test
    public void seatsMoreThanMaxCapacityCantBeBooked() {

        String date = "26-AUG-2014";
        String time = "11:00";
        int numberOfTickets = Theatre.MAX_CAPACITY + 1;

        long mobileNumber = 123;

        //Given
        String message = "DT " + date + " " + time + " TKT " + numberOfTickets;
        Sms sms = new Sms(message, mobileNumber);

        Set<Booking> bookingRepository = new HashSet<>();
        Map<String, BookingStat> bookingStatsRepository = mock(HashMap.class);

        when(bookingStatsRepository.get(date + " " + time)).thenReturn(new BookingStat(Theatre.MAX_CAPACITY));

        Theatre theatre = new Theatre(bookingRepository, bookingStatsRepository);

        //When
        String responseText = theatre.bookTickets(sms);

        //Then
        assertEquals("Sorry! Theatre capacity is " + Theatre.MAX_CAPACITY + " at the moment. Of course, We want our audience to feel among the privileged!!", responseText);
    }

    @Test
    public void seatsMoreThanAvailableCantBeBooked() {

        String date = "26-AUG-2014";
        String time = "11:00";
        int numberOfTickets = 10;

        long mobileNumber = 123;

        List<Integer> seatNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        String message = "DT " + date + " " + time + " TKT " + numberOfTickets;
        Sms sms = new Sms(message, mobileNumber);

        Set<Booking> bookingRepository = new HashSet<>();
        Map<String, BookingStat> bookingStatsRepository = mock(HashMap.class);

        when(bookingStatsRepository.get(date + " " + time)).thenReturn(BookingStat.empty());

        Theatre theatre = new Theatre(bookingRepository, bookingStatsRepository);

        String responseText = theatre.bookTickets(sms);

        assertEquals("See you at " + date + " " + time + ". Your allocated seats are " + seatNumbers + ".", responseText);

        //Given
        numberOfTickets = Theatre.MAX_CAPACITY - 9; // 10 seats have already been booked. So, next booking request can have a max. of (Theatre.MAX_CAPACITY - 10) tickets
        message = "DT " + date + " " + time + " TKT " + numberOfTickets;
        sms = new Sms(message, mobileNumber);

        when(bookingStatsRepository.get(date + " " + time)).thenReturn(new BookingStat(Theatre.MAX_CAPACITY - 10));

        //When
        responseText = theatre.bookTickets(sms);

        //Then
        assertEquals("Oops, bad theatre!! Number of seats you have requested not available for this show.", responseText);
    }
}
