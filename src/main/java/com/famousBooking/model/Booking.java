package com.famousBooking.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by gsakhardande on 28/8/14.
 */
public class Booking {
    private final String date;
    private final String time;
    private final int numberOfTickets;
    private final List<Integer> seatNumbers;
    private long mobileNumber;

    public Booking(String date, String time, int numberOfTickets, List<Integer> seatNumbers, long mobileNumber) {
        this.date = date;
        this.time = time;
        this.numberOfTickets = numberOfTickets;
        this.seatNumbers = seatNumbers;
        this.mobileNumber = mobileNumber;
    }

    public Booking(BookingRequest bookingRequest, List<Integer> seatNumbers) {
        this.date = bookingRequest.getDate();
        this.time = bookingRequest.getTime();
        this.numberOfTickets = bookingRequest.getNumberOfTickets();
        this.seatNumbers = seatNumbers;
        this.mobileNumber = bookingRequest.getMobileNumber();
    }

    public static final Booking empty() {
        return new Booking("", "", 0, Collections.<Integer>emptyList(), 0);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)               return false;
        if(!(obj instanceof Booking)) return false;

        Booking other = (Booking) obj;
        if(! this.date.equals(other.date))                    return false;
        if(! this.time.equals(other.time))                    return false;
        if(this.mobileNumber != other.mobileNumber)       return false;
        if(this.numberOfTickets != other.numberOfTickets) return false;
        if(! (this.seatNumbers.equals(other.seatNumbers)))    return false;

        return true;
    }

    @Override
    public int hashCode() {
        return date.hashCode() *
                time.hashCode() *
                numberOfTickets *
                (int) mobileNumber *
                seatNumbers.hashCode();
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public List<Integer> getSeatNumbers() {
        return seatNumbers;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }
}
