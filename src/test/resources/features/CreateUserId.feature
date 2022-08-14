Feature: Create User ID-API

  Background:setUp
    #Given BaseURl
    And testdata

  @SANITY @api @check
    Scenario:Successful creation of UserId-scn_id:s2
    Given valid userid
    When post method is called
    Then response code should be 201
    And verify user id


  @Smo
   Scenario Outline: Title of your scenario outline-scn_id:s1
    Given I want to write a step with :"<name>"
    When I check for the "<value>" in step
    Then I verify the "<status>"in step

    Examples:
      | name  | value | status  |
      | name1 |     5 | success |


