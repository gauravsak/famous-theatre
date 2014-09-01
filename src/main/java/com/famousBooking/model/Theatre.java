package com.famousBooking.model;

import com.famousBooking.exception.SeatsNotAvailableException;

import java.util.*;

/**
 * Created by gsakhardande on 28/8/14.
 */
public class Theatre {

    public static final int MAX_CAPACITY = 50;

    private Map<String, BookingStat> bookingStatsRepository;
    Set<Booking> bookingRepository;

    public Theatre(Set<Booking> bookingRepository, Map<String, BookingStat> bookingStatsRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingStatsRepository = bookingStatsRepository;
    }

    public String bookTickets(Sms sms) {
        Booking booking = null;
        try {
            BookingRequest bookingRequest = processSms(sms);
            booking = createBooking(bookingRequest);
            addBooking(booking);
        } catch (SeatsNotAvailableException e) {
            switch(e.getMessage()) {
                case "Max capacity of the theatre is " + Theatre.MAX_CAPACITY :
                    return "Sorry! Theatre capacity is " + Theatre.MAX_CAPACITY + " at the moment. Of course, We want our audience to feel among the privileged!!";

                case "Seats not available for this show" :
                    return "Oops, bad theatre!! Number of seats you have requested not available for this show.";
            }

        }
        return "See you at " + booking.getDateTime() + ". Your allocated seats are " + booking.getSeatNumbers() + ".";
    }

    private BookingRequest processSms(Sms sms) {
        String[] tokens = sms.getText().split("\\s+");
        String date = tokens[1];
        String time = tokens[2];
        int numberOfTickets = Integer.parseInt(tokens[4]);
        long mobileNumber = sms.getSourceMobileNumber();

        return new BookingRequest(date, time, numberOfTickets, mobileNumber);
    }

    private Booking createBooking(BookingRequest bookingRequest) throws SeatsNotAvailableException {
        if(isAvailable(bookingRequest)) {
            return new Booking(bookingRequest, allocateSeatNumbers(bookingRequest));
        }
        return Booking.empty();
    }

    private boolean isAvailable(BookingRequest bookingRequest) throws SeatsNotAvailableException{
        if(bookingRequest.getNumberOfTickets() >= Theatre.MAX_CAPACITY) {
            throw new SeatsNotAvailableException("Max capacity of the theatre is " + Theatre.MAX_CAPACITY);
        }
        BookingStat bookingStat = bookingStatsRepository.get(bookingRequest.getDateTime());
        if (bookingStat == null) {
            bookingStat = BookingStat.empty();
            bookingStatsRepository.put(bookingRequest.getDateTime(), bookingStat);
        }
        if (bookingStat.getNumOfAvailableSeats() < bookingRequest.getNumberOfTickets()) {
            throw new SeatsNotAvailableException("Seats not available for this show");
        }
        return true;
    }

    private List<Integer> allocateSeatNumbers(BookingRequest bookingRequest) {
        BookingStat bookingStat = bookingStatsRepository.get(bookingRequest.getDateTime());
        int numOfBookedTickets = bookingStat.getNumOfBookedSeats();
        List<Integer> seatNumbers = new ArrayList<>();
        for (int i = 1; i <= bookingRequest.getNumberOfTickets(); i++) {
            seatNumbers.add(++numOfBookedTickets);
        }
        updateBookingStats(bookingRequest.getDateTime(), numOfBookedTickets);
        return seatNumbers;
    }

    private void updateBookingStats(String dateTime, int numOfBookedTickets) {
        BookingStat currentBookingStat = bookingStatsRepository.get(dateTime);
        int newNumberOfAvailableSeats = currentBookingStat.getNumOfAvailableSeats() - numOfBookedTickets;
        bookingStatsRepository.put(dateTime, new BookingStat(newNumberOfAvailableSeats));
    }

    private void addBooking(Booking booking) {
        bookingRepository.add(booking);
    }

}
