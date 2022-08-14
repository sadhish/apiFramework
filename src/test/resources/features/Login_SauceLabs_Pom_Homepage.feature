Feature: Login Swag Labs-UI
  @login @SANITY @smokk
  Scenario: Login into Swag Labs Homepage-scn_id:s5
    Given navigate to loginpage
    When user logged in using with proper credentials
    Then Homepage should be displayed


  @products @SANITY
  Scenario: Login into Swag Labs Homepage and check products page -scn_id:s5
    Given navigate to loginpage
    When user logged in using with proper credentials
    Then Homepage should be displayed
    And verify products is displayed
