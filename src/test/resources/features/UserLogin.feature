Feature: Login to the TradingView site and navigate to the GBPJPY exchange page

  @validLogin
  Scenario: Login to the TradingView site and navigate to the GBPJPY exchange page
    Given I launch the TradingView site
    When I fill the valid credentials for login into the application
    Then I select Asia tab in the application
    And I search for the currency pair and click see overview
    And I verify whether this is correct page
