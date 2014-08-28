package com.famousBooking;

import com.famousBooking.model.Customer;
import com.famousBooking.model.Sms;
import com.famousBooking.service.SmsService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by gsakhardande on 26/8/14.
 */
public class CustomerInteractionTest {

    @Test
    public void customerGetsSomeSmsInResponseToBookingSms() {

        String date = "26-AUG-2014";
        String time = "11";
        int numOfTickets = 2;
        String customerName = "James Shore";

        //Given
        String message = "DT " + date + " " + time + " TKT " + numOfTickets + " NAME '" + customerName + "'";
        int mobileNumber = 123;
        int bookingVasNumber = 5555;

        SmsService smsService = mock(SmsService.class);
        when(smsService.sendSms(message, mobileNumber, bookingVasNumber)).thenReturn(new Sms("sample text", bookingVasNumber, mobileNumber));

        Customer customer = new Customer(mobileNumber, smsService);

        //When
        String response = customer.sendBookingSms(message, bookingVasNumber);

        //Then
        assertTrue(!response.isEmpty());
    }

}
