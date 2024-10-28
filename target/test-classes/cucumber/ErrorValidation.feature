
@tag
Feature: Title of your feature
  I want to use this template for my feature file

  

  @ErrorValidation
  Scenario Outline: Negative Scenario of Login Application
    Given I landed on Ecommerce page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      |       name                         | password       |
      |  kalyankumar135999@gmail.com       | password@(12)  |
