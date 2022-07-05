
Feature: Meetsky application login feature verification


  Scenario: User can see valid placeholders on Username and Password input boxes
    Given User is on the login page
    Then  User sees the Username or email and Password as placeholders

  Scenario: User can login with valid credentials
    Given User is on the login page
    When  User enters "Employee11" and "Employee123" as valid credentials
    When  User clicks on login button
    Then  User should be able to login and see the dashboard


  Scenario Outline:User can not login with any invalid credentials
  - Credentials should contain at least 2, at most 15 characters.
  -"Wrong username or password." should be displayed for invalid credentials
    Given User is on the login page
    When  User enters invalid "<username>" and "<password>" as username and password
    And   User clicks on login button
    Then  User shouldn't be able to login and should see error message

    Examples:
      | username   | password         |
      | Sati11     | Employee123      |
      | Employee11 | 123456SA         |
      | Sati11     | 123456SA         |
      | S          | 12345678901234SA |


  Scenario Outline:User can not login with any invalid credentials
  "Please fill out this field." message should be displayed if the password or username is empty
    Given User is on the login page
    When  User does not enter any "<username>" as username
    When  User does not enter any "<password>" as password
    And   User clicks on login button
    Then  User shouldn't be able to login and should see required field error message

    Examples:
      | username   | password         |
      |            |                  |
      | Employee11 |                  |
      |            | Employee123      |


  Scenario Outline: User can see the password in a form of dots by default
    Given User is on the login page
    When  User enters "<password>" as password
    Then  User should see the password in a form of dots
    Examples:
      | password     |
      | Employee123  |
      | Cydeo123     |
      | Student***   |


  Scenario Outline: User can see the password explicitly if needed
    Given User is on the login page
    When  User enters "<password>" password
    When  User clicks on eye icon
    Then  User should see the characters of password explicitly
    Examples:
      | password     |
      | Employee123  |
      | Cydeo123     |
      | Student***   |


  Scenario: User can see the "Forgot password?" link on the login page and
            can see the "Reset Password" button on the next page after clicking on forget password link
    Given User is on the login page
    When  User clicks on Forgot password? link
    Then  User should see the Reset password button




