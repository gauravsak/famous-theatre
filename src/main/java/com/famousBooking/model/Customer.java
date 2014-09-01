package com.famousBooking.model;

import com.famousBooking.service.FamousSmsService;
import com.famousBooking.service.SmsService;
import com.famousBooking.service.SmsServiceFactory;

/**
 * Created by gsakhardande on 28/8/14.
 */
public class Customer {

    private long mobileNumber;
    private SmsService smsService;

    public Customer(long mobileNumber) {
        this.mobileNumber = mobileNumber;
        this.smsService = SmsServiceFactory.create();
    }

    public String sendSms(String text) {
        return smsService.sendSms(text, mobileNumber);
    }
}
