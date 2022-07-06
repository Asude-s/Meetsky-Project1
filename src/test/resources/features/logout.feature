
Feature: Meetsky application login feature verification

Scenario: User can log out and ends up in login page
  Given User can login with valid credentials
  When  User clicks on user profile menu button on top-right corner
  When  User clicks on log out button
  Then  User should be able to logged out successfully and can see the login page

Scenario: User can not go to home page again by clicking step back button after successfully logged out.
  Given User can login with valid credentials
  When  User clicks on user profile menu button on top-right corner
  When  User clicks on log out button
  Then  User should be able to logged out successfully and can see the login page
  Then  User should not be able to go to home page again by clicking step back button