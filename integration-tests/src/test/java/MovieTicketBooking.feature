Feature: Movie Ticket Booking

  @Implemented
  Scenario: User should be able to book tickets
    Given User is on the home page
    And User enters a valid booking SMS and a mobile number
    When User clicks on send button
    Then User should see ticket booking successful
    And User quits the browser

   @Implemented
  Scenario: User is not allowed to book ticket more than theatre's max capacity
    Given User is on the home page
    And User enters sms with number of seats greater than theatre's max capacity and mobile number
    When User clicks on send button
    Then User should see ticket booking unsuccessful with max capacity exceed error
    And User quits the browser

  @Implemented
  Scenario: User is not allowed to book ticket more than available
    Given User is on the home page
    And User enters a valid booking SMS and a mobile number
    When User clicks on send button
    Then User should see ticket booking successful
    And User clicks on Book more button
    And User enters valid booking sms with number of seats more than available and mobile number
    When User clicks on send button
    Then User should see ticket booking unsuccessful with available seats exceed error
    And User quits the browser