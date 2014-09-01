package com.famousBooking.model;

import com.famousBooking.exception.SeatsNotAvailableException;

import java.util.*;

/**
 * Created by gsakhardande on 28/8/14.
 */
public class Theatre {

    public static final int MAX_CAPACITY = 50;

//    private static Theatre instance;
    private Map<String, BookingStat> bookingStatsRepository;
    Set<Booking> bookingRepository;

    public Theatre(Set<Booking> bookingRepository, Map<String, BookingStat> bookingStatsRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingStatsRepository = bookingStatsRepository;
    }

//    private Theatre() {
//        bookingStatsRepository = new HashMap<String, BookingStat>() {
//            @Override
//            public BookingStat get(Object key) {
//                return super.containsKey(key) ? super.get(key) : BookingStat.empty();
//            }
//        };
//        bookingRepository = new ArrayList<>();
//    }

//    public static synchronized Theatre getInstance() {
//        if (instance == null) {
//            instance = new Theatre();
//        }
//        return instance;
//    }
//
//    public Object clone() throws CloneNotSupportedException {
//        throw new CloneNotSupportedException();
//    }

    public boolean hasBooking(Booking booking) {
        return bookingRepository.contains(booking);
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
        return "See you at " + booking.getDate() + " " + booking.getTime() + ". Your allocated seats are " + booking.getSeatNumbers() + ".";
    }

    private void addBooking(Booking booking) {
        bookingRepository.add(booking);
    }

    private void updateBookingStats(String time, int numOfBookedTickets) {
        BookingStat currentBookingStat = bookingStatsRepository.get(time);
        int newNumberOfAvailableSeats = currentBookingStat.getNumOfAvailableSeats() - numOfBookedTickets;
        bookingStatsRepository.put(time, new BookingStat(newNumberOfAvailableSeats));
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
        BookingStat bookingStat = bookingStatsRepository.get(bookingRequest.getTime());
        if (bookingStat == null) {
            bookingStat = BookingStat.empty();
            bookingStatsRepository.put(bookingRequest.getTime(), bookingStat);
        }
        if (bookingStat.getNumOfAvailableSeats() < bookingRequest.getNumberOfTickets()) {
            throw new SeatsNotAvailableException("Seats not available for this show");
        }
        return true;
    }

    private BookingRequest processSms(Sms sms) {
        String[] tokens = sms.getText().split("\\s+");
        String date = tokens[1];
        String time = tokens[2];

//        String[] dateTokens = date.split("-");
//        int dd = Integer.parseInt(dateTokens[0]);
//        String MMM = dateTokens[1];
//        String yyyy = dateTokens[2];
//        String[] timeTokens = time.split(":");
//        String HH = timeTokens[0];
//        String mm = timeTokens[1];
//        Calendar dateTime = Calendar.getInstance();
//        dateTime.set(Calendar.DAY_OF_MONTH, dd);
//        dateTime.set(Calendar.MONTH, MMM);
//        dateTime.set(Calendar.YEAR, dd);

        int numberOfTickets = Integer.parseInt(tokens[4]);
        long mobileNumber = sms.getSourceMobileNumber();

        return new BookingRequest(date, time, numberOfTickets, mobileNumber);
    }

    private List<Integer> allocateSeatNumbers(BookingRequest bookingRequest) {
        BookingStat bookingStat = bookingStatsRepository.get(bookingRequest.getTime());
        int numOfBookedTickets = bookingStat.getNumOfBookedSeats();
        List<Integer> seatNumbers = new ArrayList<>();
        for (int i = 1; i <= bookingRequest.getNumberOfTickets(); i++) {
            seatNumbers.add(++numOfBookedTickets);
        }
        updateBookingStats(bookingRequest.getTime(), numOfBookedTickets);
        return seatNumbers;
    }

}
