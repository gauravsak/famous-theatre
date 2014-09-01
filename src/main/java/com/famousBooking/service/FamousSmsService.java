package com.famousBooking.service;

import com.famousBooking.model.Booking;
import com.famousBooking.model.BookingStat;
import com.famousBooking.model.Sms;
import com.famousBooking.model.Theatre;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by gsakhardande on 28/8/14.
 */
public class FamousSmsService implements SmsService {

    @Override
    public String sendSms(String text, long sourceMobileNumber) {
        Set<Booking> bookingRepository = new HashSet<>();
        Map<String, BookingStat> bookingStatsRepository = new HashMap<>();
        Theatre theatre = new Theatre(bookingRepository, bookingStatsRepository);
        return theatre.bookTickets(new Sms(text, sourceMobileNumber));
    }
}
