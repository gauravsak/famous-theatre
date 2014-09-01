package com.famousBooking;

import com.famousBooking.model.Booking;
import com.famousBooking.model.Customer;
import com.famousBooking.model.Theatre;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by gsakhardande on 26/8/14.
 */
public class CustomerInteractionTest {

    @Test
    public void booksTicketForValidSms() {

        String date = "26-AUG-2014";
        String time = "11:00";
        int numberOfTickets = 2;

        long mobileNumber = 123;

        List<Integer> seatNumbers = Arrays.asList(1, 2);

        //Given
        String message = "DT " + date + " " + time + " TKT " + numberOfTickets;
        Customer customer = new Customer(mobileNumber);

        //When
        String responseText = customer.sendSms(message);

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
        Customer customer = new Customer(mobileNumber);

        //When
        String responseText = customer.sendSms(message);

        //Then
        assertEquals("Sorry! Theatre capacity is " + Theatre.MAX_CAPACITY + " at the moment. Of course, We want our audience to feel among the privileged!!", responseText);
    }

    @Test
    @Ignore
    public void seatsMoreThanAvailableCantBeBooked() {

        String date = "26-AUG-2014";
        String time = "11:00";
        int numberOfTickets = 10;

        long mobileNumber = 123;

        List<Integer> seatNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        String message = "DT " + date + " " + time + " TKT " + numberOfTickets;
        Customer customer = new Customer(mobileNumber);

        String responseText = customer.sendSms(message);

        assertEquals("See you at " + date + " " + time + ". Your allocated seats are " + seatNumbers + ".", responseText);

        //Given
        numberOfTickets = Theatre.MAX_CAPACITY - 9; // 10 seats have already been booked. So, next booking request can have a max. of (Theatre.MAX_CAPACITY - 10) tickets
        message = "DT " + date + " " + time + " TKT " + numberOfTickets;

        //When
        responseText = customer.sendSms(message);

        //Then
        assertEquals("Oops, bad theatre!! Number of seats you have requested not available for this show.", responseText);
    }
}
