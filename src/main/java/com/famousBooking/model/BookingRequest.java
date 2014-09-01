package com.famousBooking.model;

import java.util.List;

/**
 * Created by gsakhardande on 28/8/14.
 */
public class BookingRequest {
    private final String date;
    private final String time;
    private final int numberOfTickets;
    private final long mobileNumber;

    public BookingRequest(String date, String time, int numberOfTickets, long mobileNumber) {
        this.date = date;
        this.time = time;
        this.numberOfTickets = numberOfTickets;
        this.mobileNumber = mobileNumber;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }
}
