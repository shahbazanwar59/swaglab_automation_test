#Author:Shahbaz

Feature: Register to Liskart Seller Page
@LiskartSellerRegistration
  Scenario: Register to Liskart Seller Page
    Given Click on seller register link
    Then  Enter the Basic details of seller
    Then  Enter the Seller OTP for Final Registratation
  