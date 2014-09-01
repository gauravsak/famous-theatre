package com.famousBooking.model;

/**
 * Created by gsakhardande on 31/8/14.
 */
public class BookingStat {
    private int numOfAvailableSeats = Theatre.MAX_CAPACITY;

    public BookingStat(int numOfAvailableSeats) {
        this.numOfAvailableSeats = numOfAvailableSeats;
    }

    public int getNumOfAvailableSeats() {
        return numOfAvailableSeats;
    }

    public int getNumOfBookedSeats() {
        return Theatre.MAX_CAPACITY - numOfAvailableSeats;
    }

    public static final BookingStat empty() {
        return new BookingStat(Theatre.MAX_CAPACITY);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)               return false;
        if(!(obj instanceof BookingStat)) return false;

        BookingStat other = (BookingStat) obj;
        if(this.numOfAvailableSeats != other.numOfAvailableSeats)       return false;

        return true;
    }

    @Override
    public int hashCode() {
        return numOfAvailableSeats;
    }
}
