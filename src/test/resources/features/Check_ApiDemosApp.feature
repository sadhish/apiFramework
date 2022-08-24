Feature:Launch ApiDemos
  @app1 @SANITY
  Scenario Outline: Launch ApiDemo App-scn_id:s8
    Given Launchapp:"<platform>"
    And Verify Random Elements
    Examples:
    |platform|
    |Android |

  Scenario Outline: Launch ApiDemo and Verify App-scn_id:s8
    Given Launchapp:"<platform>"
    And Verify Random Elements
    Examples:
      |platform|
      |Android |
