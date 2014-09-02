import com.famousBooking.model.Theatre;
import com.famousBooking.page.HomePage;
import com.famousBooking.page.ResultPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gsakhardande on 1/9/14.
 */
public class MovieTicketBookingSteps {

    WebDriver driver = new FirefoxDriver();
    HomePage homePage = PageFactory.initElements(driver, HomePage.class);

    String date = "26-AUG-2014";
    String time = "11:00";
    int numberOfTickets = 2;

    String validBookingSms = "DT " + date + " " + time + " TKT " + numberOfTickets;
    String smsWithMaxCapacityPlus1Seats = "DT " + date + " " + time + " TKT " + (Theatre.MAX_CAPACITY + 1);
    String smsWithAvailablePlus1SeatsAfterBooking2 = "DT " + date + " " + time + " TKT " + 49;
    String mobileNumber = "9552027729";
    List<Integer> seatNumbers12 = Arrays.asList(1, 2);

    @Given("^User is on the home page$")
    public void User_is_on_the_home_page() {
        driver.get("http://localhost:8080/famous-booking");
    }

    @And("^User enters a valid booking SMS and a mobile number$")
    public void User_enters_a_valid_booking_SMS_and_a_mobile_number() {
        homePage.enterBookingDetails(validBookingSms, mobileNumber);
    }

    @And("^User enters sms with number of seats greater than theatre's max capacity and mobile number$")
    public void User_enters_sms_with_number_of_seats_greater_than_theatre_s_max_capacity_and_mobile_number() {
        homePage.enterBookingDetails(smsWithMaxCapacityPlus1Seats, mobileNumber);
    }

    @When("^User clicks on send button$")
    public void User_clicks_on_send_button() {
        homePage.sendSms();
    }

    @Then("^User should see ticket booking successful$")
    public void User_should_see_ticket_booking_successful() {
        driver.getPageSource().contains("See you at " + date + " " + time + ". Your allocated seats are " + seatNumbers12 + ".");
    }

    @Then("^User should see ticket booking unsuccessful with max capacity exceed error$")
    public void User_should_see_ticket_booking_unsuccessful_with_max_capacity_exceed_error() {
        driver.getPageSource().contains("Sorry! Theatre capacity is " + Theatre.MAX_CAPACITY + " at the moment. Of course, We want our audience to feel among the privileged!!");
    }

    @Then("^User should see ticket booking unsuccessful with available seats exceed error$")
    public void User_should_see_ticket_booking_unsuccessful_with_available_seats_exceed_error() {
        driver.getPageSource().contains("Oops, bad theatre!! Number of seats you have requested not available for this show.");
    }

    @And("^User enters valid booking sms with number of seats more than available and mobile number$")
    public void User_enters_valid_booking_sms_with_number_of_seats_more_than_available_and_mobile_number() {
        homePage.enterBookingDetails(smsWithAvailablePlus1SeatsAfterBooking2, mobileNumber);
    }

    @And("^User clicks on Book more button$")
    public void User_clicks_on_Book_more_button() {
        driver.findElement(By.linkText("Book more...")).click();
    }

    @And("^User quits the browser$")
    public void User_quits_the_browser() {
        driver.quit();
    }
}
