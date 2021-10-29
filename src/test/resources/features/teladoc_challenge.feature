Feature: Add, Delete Users
  Verify user is able to add and Delete Users in the Table

  Background: Access the Application
    Given User should open the Application

  Scenario: Add a user and validate the user has been added to the table
    When Click on AddUser Button
    And Add User with information UserName:test FirstName:qaFirstName LastName:qaLastName Password:test123 Customer:Company AAA Role:Customer CellPhone:1234567890
    Then Verify user is listed in table

  Scenario: Delete user User Name: novak and validate user has been delete
    When Search UserName:novak in table
    And  Click on Delete Option in Table for UserName:novak
    Then Verify user:novak is not listed in table
