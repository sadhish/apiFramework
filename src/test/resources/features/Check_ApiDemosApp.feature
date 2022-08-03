Feature:Launch ApiDemos
  @app
  Scenario Outline: Launch ApiDemo App-scn_id:s8
    Given Launchapp:"<platform>"
    And Verify Random Elements
    Examples:
    |platform|
    |Android    |
