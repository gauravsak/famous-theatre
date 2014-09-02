package com.famousBooking.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by gsakhardande on 1/9/14.
 */
public class HomePage{

    @FindBy(id = "sms")
    private WebElement smsTextBox;

    @FindBy(id = "mobile")
    private WebElement mobileTextBox;

    @FindBy(id = "sendSmsBtn")
    private WebElement sendBtn;


    public void enterBookingDetails(String validBookingSms, String mobileNumber) {
        smsTextBox.sendKeys(validBookingSms);
        mobileTextBox.sendKeys(mobileNumber);
    }

    public void sendSms() {
        sendBtn.click();
    }
}
