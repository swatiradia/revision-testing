@tag
Feature: Purchase the order from Ecommerce Website
  and check them in Order history page.

#  @Background:
#    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Select an item from the Ecommerce Website and place an order.
    Given I landed on Ecommerce Page
    And I logged in using <username> and <password>
    When I add the <productName> to cart
    And I checkout with the <productName> and submit the order
    Then The confirmation message "Thankyou For The Order" is displayed

    Examples:
      | username        | password     | productName |
      | swati@radia.com | Swati@radia1 | ZARA COAT 3 |

