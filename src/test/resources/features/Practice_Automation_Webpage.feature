Feature: Practice Automation Webpage-UI
  @practice @SANITY
  Scenario: Login and check homepage-scn_id:s10
    Given navigate to loginpage
    And enter practice webpage login credentials
    Then verify practice automation homepage is displayed
  @practicehome @SANITY
    Scenario: Navigate to homepage and logout-scn_id:s10
    Given navigate to loginpage
      And click on home link
      And Verify home page is launched and navigate to main page
      Then click on logout button


