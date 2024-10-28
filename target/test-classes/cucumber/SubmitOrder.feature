
@tag
Feature: purchase the order from E-Commerce Website
  I want to use this template for my feature file

  Background:
  Given I landed on Ecommerce page
  
  @Regression
  Scenario Outline: Positive Test of Submitting the order
  
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on conformationPage
    
    Examples: 
      |       name                         | password       | productName  |
      |  kalyankumar135999@gmail.com       | password@(123) | ZARA COAT 3  |