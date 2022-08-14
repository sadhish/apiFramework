Feature: Login in Swag Labs Android App-APP

@app @SANITY
  Scenario Outline: Login into Homepage-scn_id:s6
    Given launch the app and check login page is displayed:"<platform>"
    When user enters login credentials
    Then Homepage should be displayed in app
    Examples:
    |platform|
    |Android |