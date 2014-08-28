package com.famousBooking.model;

/**
 * Created by gsakhardande on 28/8/14.
 */
public class Sms {
    private final String text;
    private final int source;
    private final int target;

    public Sms(String text, int source, int target) {

        this.text = text;
        this.source = source;
        this.target = target;
    }

    public String getText() {
        return text;
    }
}
