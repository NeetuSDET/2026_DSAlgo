Feature: NumpyNinja Data Structure Page Feature
    As a registered user of the NumpyNinja portal
    I want to log in to my account
    So that I can access Data Structure topics and features
  
    # Background navigates to the Data Structure page before every scenario
    Background:
      Given the user has already navigated to Data Structures Page afetr logging into the application "https://dsportalapp.herokuapp.com/login"
      |userName|pwd|
      |test1user123|Test@1234|
  
  
  
    Scenario: Verify the register page loads successfully
      When the user clicks on the Data Structures button
      Then the Data Structure page URL should contain "/data-structures"
