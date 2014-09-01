package com.famousBooking.service;

/**
 * Created by gsakhardande on 1/9/14.
 */
public interface SmsService {
    public String sendSms(String text, long sourceMobileNumber);
}
