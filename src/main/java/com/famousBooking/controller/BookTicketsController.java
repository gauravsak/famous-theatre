package com.famousBooking.controller;

import com.famousBooking.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by gsakhardande on 1/9/14.
 */
@Controller
@RequestMapping("bookTickets")
public class BookTicketsController {

    @Autowired
    private SmsService smsService;

    @RequestMapping(method = RequestMethod.GET)
    public String book(ModelMap model, @RequestParam("sms") String text, @RequestParam("mobile") String mobileNumber) {
        model.addAttribute("responseText", smsService.sendSms(text, Long.parseLong(mobileNumber)));
        return "result";
    }

}
