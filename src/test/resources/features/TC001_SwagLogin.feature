Feature: Login to the swag application
  As a registered user
  I want to log into the application
  So that I can access my account details

  @SwagLogin
  Scenario: Successful login with valid credentials
    Given user enters valid username and password
    When clicks on the login button
    And user should be navigated to the home page
   
    