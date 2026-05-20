Feature: NumpyNinja Linked List Page Feature
    As a registered user of the NumpyNinja portal
    I want to log in to my account
    So that I can access Linked List topics and features
  
    # Background navigates to the Linked List page before every scenario
    Background:
      Given the user has already navigated to Linked List Page after logging into the application "https://dsportalapp.herokuapp.com/login"
      |userName|pwd|
      |test1user123|Test@1234|
  
  
  
    Scenario: Verify the Linked List page loads successfully
      When the user clicks on the Linked List page get started button
      Then the "Linked List" page URL should contain "/linked-list/"
  
  //sss
