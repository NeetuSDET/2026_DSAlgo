Feature: NumpyNinja Array Page Feature
    As a registered user of the NumpyNinja portal
    I want to log in to my account
    So that I can access Array topics and features
  
    # Background navigates to the Array page before every scenario
    Background:
      Given the user has already navigated to Array Page after logging into the application "https://dsportalapp.herokuapp.com/login"
      |userName|pwd|
      |test1user123|Test@1234|
  
  
  
    Scenario: Verify the Array page loads successfully
      When the user clicks on the Array page get started button
      Then the array page URL should contain "/array/"
