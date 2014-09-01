package com.famousBooking.model;

/**
 * Created by gsakhardande on 28/8/14.
 */
public class BookingRequest {
    private final String dateTime;
    private final int numberOfTickets;
    private final long mobileNumber;

    public BookingRequest(String date, String time, int numberOfTickets, long mobileNumber) {
        this.dateTime = date + " " + time;
        this.numberOfTickets = numberOfTickets;
        this.mobileNumber = mobileNumber;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }
}
