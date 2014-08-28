package com.famousBooking.model;

import com.famousBooking.service.SmsService;

/**
 * Created by gsakhardande on 28/8/14.
 */
public class Customer {
    private final int mobileNumber;
    private final SmsService smsService;

    public Customer(int mobileNumber, SmsService smsService) {

        this.mobileNumber = mobileNumber;
        this.smsService = smsService;
    }

    public String sendBookingSms(String text, int bookingVasNumber) {
        Sms responseSms = smsService.sendSms(text, mobileNumber, bookingVasNumber);
        return responseSms.getText();
    }
}
