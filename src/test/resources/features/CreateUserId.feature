Feature: Create User ID

  Background:setUp
    Given testdata
    And BaseURl

  @SANITY
    Scenario:Successful creation of UserId-scn_id:s2
    Given valid userid
    When post method is called
    Then response code should be 201

  @Smoke
   Scenario Outline: Title of your scenario outline-scn_id:s1
    Given I want to write a step with :"<name>"
    When I check for the "<value>" in step
    Then I verify the "<status>"in step

    Examples:
      | name  | value | status  |
      | name1 |     5 | success |


