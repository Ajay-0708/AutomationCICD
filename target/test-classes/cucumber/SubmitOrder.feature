
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file


Background:
Given I landed on Ecommerce Page



  @Regression
  Scenario Outline: Positive Test if Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then I verify the "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples:   
      | name                    |   password         | productName         |
      |upadhyayajay32@gmail.com |  Sephora@123456    | adidas original     |
