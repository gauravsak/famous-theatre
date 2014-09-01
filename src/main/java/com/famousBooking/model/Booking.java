package com.famousBooking.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by gsakhardande on 28/8/14.
 */
public class Booking {
    private final String dateTime;
    private final int numberOfTickets;
    private final List<Integer> seatNumbers;
    private long mobileNumber;

    public Booking(String dateTime, int numberOfTickets, List<Integer> seatNumbers, long mobileNumber) {
        this.dateTime = dateTime;
        this.numberOfTickets = numberOfTickets;
        this.seatNumbers = seatNumbers;
        this.mobileNumber = mobileNumber;
    }

    public Booking(BookingRequest bookingRequest, List<Integer> seatNumbers) {
        this.dateTime = bookingRequest.getDateTime();
        this.numberOfTickets = bookingRequest.getNumberOfTickets();
        this.seatNumbers = seatNumbers;
        this.mobileNumber = bookingRequest.getMobileNumber();
    }

    public static final Booking empty() {
        return new Booking("", 0, Collections.<Integer>emptyList(), 0);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)               return false;
        if(!(obj instanceof Booking)) return false;

        Booking other = (Booking) obj;
        if(! this.dateTime.equals(other.dateTime))                    return false;
        if(this.mobileNumber != other.mobileNumber)       return false;
        if(this.numberOfTickets != other.numberOfTickets) return false;
        if(! (this.seatNumbers.equals(other.seatNumbers)))    return false;

        return true;
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode() *
                numberOfTickets *
                (int) mobileNumber *
                seatNumbers.hashCode();
    }

    public String getDateTime() {
        return dateTime;
    }

    public List<Integer> getSeatNumbers() {
        return seatNumbers;
    }

}
