package com.famousBooking.service;

import com.famousBooking.model.Booking;
import com.famousBooking.model.BookingStat;
import com.famousBooking.model.Sms;
import com.famousBooking.model.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by gsakhardande on 28/8/14.
 */
@Service
public class FamousSmsService implements SmsService {

    @Autowired
    private Theatre theatre;

    @Override
    public String sendSms(String text, long sourceMobileNumber) {
        return theatre.bookTickets(new Sms(text, sourceMobileNumber));
    }
}
