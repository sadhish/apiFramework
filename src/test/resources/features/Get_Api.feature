
Feature: GetID-API

@api  @SANITY
Scenario:Get id-scn_id:s3
Given api
When get method is called
Then check response