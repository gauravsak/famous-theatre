package com.famousBooking.exception;

/**
 * Created by gsakhardande on 1/9/14.
 */
public class SeatsNotAvailableException extends Throwable {
    public SeatsNotAvailableException(String message) {
        super(message);
    }
}
