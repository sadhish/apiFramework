Feature: Login
  @login @Sanity
  Scenario: Login into Homepage-scn_id:s5
    Given navigate to loginpage
    When user logged in using with proper credentials
    Then Homepage should be displayed