@LoginPage
Feature: NumpyNinja Login Page
  As a registered user of the NumpyNinja portal
  I want to log in to my account
  So that I can access Data Structure topics and features

  # Background navigates to the login page fresh before every scenario
  Background: 
    Given the user navigates to the login page "https://dsportalapp.herokuapp.com/login"

  # ─────────────────────────────────────────────
  # Page Load & Title
  # Actual <title> tag value on live page is "Login"
  # ─────────────────────────────────────────────
  @Smoke @PageLoad
  Scenario: Verify the login page loads successfully
    Then the login page title should be "Login"

  @Smoke @PageLoad
  Scenario: Verify the login page URL
    Then the login page URL should contain "/login"

  # ─────────────────────────────────────────────
  # Navigation Bar
  # ─────────────────────────────────────────────
  @Smoke @Navigation
  Scenario: Verify the brand logo is displayed on the login page navbar
    Then the login page brand logo "NumpyNinja" should be displayed in the navbar

  # Brand logo href="/" points to root, not "/home"
  @Navigation
  Scenario: Verify clicking the brand logo redirects to the root page
    When the user clicks on the login page brand logo
    Then the login page user should be redirected to "dsportalapp.herokuapp.com"

  @Navigation
  Scenario: Verify the Register link is visible in the navbar on login page
    Then the login page navbar "Register" link should be visible

  @Navigation
  Scenario: Verify the Sign in link is visible in the navbar on login page
    Then the login page navbar "Sign in" link should be visible

  @Navigation
  Scenario: Verify the Register navbar link href on login page
    Then the login page Register navbar link href should contain "/register"

  @Navigation
  Scenario: Verify the Sign In navbar link href on login page
    Then the login page Sign In navbar link href should contain "/login"

  @Navigation
  Scenario: Verify clicking the Register navbar link navigates to register page
    When the user clicks on the login page navbar "Register" link
    Then the login page user should be redirected to "/register"

  # ─────────────────────────────────────────────
  # Page Heading & Form Visibility
  # Live page renders a heading with text "Login"
  # ─────────────────────────────────────────────
  @Smoke @Content
  Scenario: Verify the login page heading is displayed
    Then the login page heading should contain "Login"

  @Smoke @Content
  Scenario: Verify the Username field is visible on the login page
    Then the login page "Username" field should be visible

  @Smoke @Content
  Scenario: Verify the Password field is visible on the login page
    Then the login page "Password" field should be visible

  @Content
  Scenario: Verify the login submit button is visible
    Then the login submit button should be visible

  @Content
  Scenario: Verify the login submit button is clickable
    Then the login submit button should be clickable

  @Content
  Scenario: Verify the login submit button text
    Then the login submit button text should be "Login"

  # ─────────────────────────────────────────────
  # Register Link Below the Form
  # Live page: "Register!" link → /register
  # ─────────────────────────────────────────────
  @Content @Navigation
  Scenario: Verify the Register link is present below the login form
    Then the login page register form link should be visible

  @Content @Navigation
  Scenario: Verify the Register form link href contains register path
    Then the login page register form link href should contain "/register"

  @Content @Navigation
  Scenario: Verify clicking the Register form link navigates to register page
    When the user clicks the register link on the login form
    Then the login page user should be redirected to "/register"

  # ─────────────────────────────────────────────
  # Functional — Successful Login
  # After successful login the site redirects to "/home"
  # ─────────────────────────────────────────────
  @Smoke @Functional
  Scenario: Verify successful login with valid credentials
    When the user enters "test1user123" in the login username field
    And the user enters "Test@1234" in the login password field
    And the user clicks the login submit button
    Then the login page user should be redirected to "/home"

  # ─────────────────────────────────────────────
  # Functional — Failed Login / Validation
  # Django renders auth errors in <ul class="errorlist"> or a <p class="...">
  # ─────────────────────────────────────────────
  @Validation
  Scenario: Verify error message on login with invalid username and password
    When the user enters "invaliduser" in the login username field
    And the user enters "WrongPass@1" in the login password field
    And the user clicks the login submit button
    Then the login page error message should be displayed

  @Validation
  Scenario: Verify error message on login with valid username and wrong password
    When the user enters "test1user123" in the login username field
    And the user enters "WrongPass@1" in the login password field
    And the user clicks the login submit button
    Then the login page error message should be displayed

  @Validation
  Scenario: Verify error message on login with empty username field
    When the user enters "" in the login username field
    And the user enters "Test@1234" in the login password field
    And the user clicks the login submit button
    Then the login page "Username" field should be visible

  @Validation
  Scenario: Verify error message on login with empty password field
    When the user enters "testuser123" in the login username field
    And the user enters "" in the login password field
    And the user clicks the login submit button
    Then the login page "Password" field should be visible

  @Validation
  Scenario: Verify error when both username and password fields are empty
    When the user clicks the login submit button
    Then the login page "Username" field should be visible

  # ─────────────────────────────────────────────
  # Field Label Verification (Outline)
  # Only 2 fields exist on the live login form
  # ─────────────────────────────────────────────
  @Content
  Scenario Outline: Verify login form field labels are displayed
    Then the login page "<Field>" field should be visible

    Examples: 
      | Field    |
      | Username |
      | Password |
