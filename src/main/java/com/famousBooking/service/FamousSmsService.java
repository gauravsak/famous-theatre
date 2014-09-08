package com.famousBooking.service;

import com.famousBooking.model.Theatre;
import org.springframework.stereotype.Service;

/**
 * Created by gsakhardande on 28/8/14.
 */
@Service
public class FamousSmsService implements SmsService {

//    @Autowired
    private Theatre theatre;

    @Override
    public String sendSms(String text, long sourceMobileNumber) {
//        return theatre.bookTickets(new SMS(text, sourceMobileNumber));
        return null;
    }
}
