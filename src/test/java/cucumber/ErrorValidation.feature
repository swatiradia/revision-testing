@tag
Feature: Error validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Select an item from the Ecommerce Website and place an order.
    Given I landed on Ecommerce Page
    And I logged in using <username> and <password>
    Then "Incorrect email or password." error is displayed

    Examples:
      | username        | password     |
      | swati@radia.com | wati@radia1 |