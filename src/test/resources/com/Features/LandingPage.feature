Feature: Navigate to Landing page

  Background: 
    Given the user has navigates to the DS Algo Portal in their browser.

  Scenario: Verify the DS Algo Portal URL loads successfully
    Then the DS Algo Portal landing page URL should be "https://dsportalapp.herokuapp.com/"

  Scenario: Verify the Get Started button is displayed on the landing page
    Then the "Get Started" button should be visible on the "landing" page

  Scenario: Verify the text on the Get Started button
    Then the "Get Started" button text should be "Get Started"

  Scenario: Verify the Get Started button is clickable
    Then the user should be able to click the "Get Started" button

  Scenario: Verify navigation after clicking the Get Started button
    And the user clicks the "Get Started" button
    Then the user should be redirected to the "home" page
    And the page title should be "NumpyNinja"

  Scenario: Verify the URL after clicking the Get Started button
    And the user clicks the "Get Started" button
    Then the page URL should contain "/home"

    
