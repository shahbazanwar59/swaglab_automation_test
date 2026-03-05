#Author: Shahbaz






Feature: Register to Liskart
@LiskartRegistration
  Scenario: Register to Liskart Page
    Given Click on register link
    Then  Enter the Basic details of User
    Then  Enter the OTP for Final Registratation
  