package com.famousBooking.service;

/**
 * Created by gsakhardande on 28/8/14.
 */
public class SmsServiceFactory {
    public static SmsService create() {
        return new FamousSmsService();
    }
}
