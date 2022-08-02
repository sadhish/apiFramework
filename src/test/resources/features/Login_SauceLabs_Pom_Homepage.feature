Feature: Login
  @login @SANITY
  Scenario: Login into Homepage-scn_id:s5
    Given navigate to loginpage
    When user logged in using with proper credentials
    Then Homepage should be displayed


  @products @SANITY
  Scenario: Login into Homepage and check products -scn_id:s5
    Given navigate to loginpage
    When user logged in using with proper credentials
    Then Homepage should be displayed
    And verify products is displayed
