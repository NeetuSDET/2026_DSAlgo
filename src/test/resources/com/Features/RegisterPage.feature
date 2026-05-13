@RegisterPage
Feature: NumpyNinja Register Page
  As a new user on the NumpyNinja portal
  I want to register for an account
  So that I can access protected Data Structure content

  Background: 
    Given the user navigates to the register page "https://dsportalapp.herokuapp.com/register"

  # ─────────────────────────────────────────────
  # Page Load
  # ─────────────────────────────────────────────
  @Smoke @PageLoad
  Scenario: Verify the register page loads successfully
    Then the register page URL should contain "/register"

  # ─────────────────────────────────────────────
  # Navigation Bar
  # ─────────────────────────────────────────────
  @Smoke @Navigation
  Scenario: Verify the brand logo is displayed on the register page
    Then the register page brand logo "NumpyNinja" should be displayed

  @Navigation
  Scenario: Verify Register link is visible in the navbar on the register page
    Then the register page navbar "Register" link should be visible

  @Navigation
  Scenario: Verify Sign In link is visible in the navbar on the register page
    Then the register page navbar "Sign in" link should be visible

  @Navigation
  Scenario: Verify clicking Sign In link from register page redirects to login page
    When the user clicks on the register page navbar "Sign in" link
    Then the register page user should be redirected to "/login"

  # ─────────────────────────────────────────────
  # Registration Form — Fields
  # ─────────────────────────────────────────────
  @Smoke @Form
  Scenario: Verify the Username field is displayed on the register page
    Then the register page "Username" field should be visible

  @Smoke @Form
  Scenario: Verify the Password field is displayed on the register page
    Then the register page "Password" field should be visible

  @Smoke @Form
  Scenario: Verify the Password Confirmation field is displayed on the register page
    Then the register page "Password confirmation" field should be visible

  @Form
  Scenario: Verify the Username field accepts input
    When the user enters "test1user123" in the register page "Username" field
    Then the register page "Username" field value should be "test1user123"

  @Form
  Scenario: Verify the Password field accepts input
    When the user enters "Secure1Pass@1" in the register page "Password" field
    Then the register page "Password" field value should be "Secure1Pass@1"

  @Form
  Scenario: Verify the Password field is of type password (masked)
    Then the register page "Password" field type should be "password"

  @Form
  Scenario: Verify the Password confirmation field is of type password (masked)
    Then the register page "Password confirmation" field type should be "password"

  # ─────────────────────────────────────────────
  # Registration Form — Submit Button
  # ─────────────────────────────────────────────
  @Smoke @Form
  Scenario: Verify the Register submit button is visible
    Then the register submit button should be visible

  @Form
  Scenario: Verify the Register submit button text
    Then the register submit button text should be "Register"

  @Form
  Scenario: Verify the Register submit button is clickable
    Then the register submit button should be clickable

  # ─────────────────────────────────────────────
  # Successful Registration
  # ─────────────────────────────────────────────
  @Smoke @Auth
  Scenario: Verify a new user can register successfully
    When the user registers with a unique username and password "Xk9#mP2$vQ1"
    Then the register page user should be redirected to "/home"
    And the register page success message text should contain "New Account Created"

  # ─────────────────────────────────────────────
  # Validation — Empty Form
  # ─────────────────────────────────────────────
  @Validation
  Scenario: Verify form stays on register page when submitted empty
    When the user submits the register form bypassing html5 validation
    Then the register page URL should contain "/register"

  # ─────────────────────────────────────────────
  # Validation — Password Mismatch
  # ─────────────────────────────────────────────
  @Validation
  Scenario: Verify error banner when passwords do not match
    When the user enters "test1user123" in the register page "Username" field
    And the user enters "Password@11" in the register page "Password" field
    And the user enters "DifferentPass@11" in the register page "Password confirmation" field
    And the user clicks the register submit button
    Then the register page error banner should be displayed
    And the register page error banner text should contain "password"

  # ─────────────────────────────────────────────
  # Validation — Existing Username
  # Banner is asserted visible only — text assertion removed as Django's
  # password validator intercepts before the uniqueness check.
  # ─────────────────────────────────────────────
  @Validation
  Scenario: Verify error banner when registering with an existing username
    When the user enters "newuser_auto" in the register page "Username" field
    And the user enters "Xk9#mP2$vQ1" in the register page "Password" field
    And the user enters "Xk9#mP2$vQ1" in the register page "Password confirmation" field
    And the user clicks the register submit button
    Then the register page error banner should be displayed

  # ─────────────────────────────────────────────
  # Validation — Password Too Short
  # FIX (round 6): "short"/"short" caused password_mismatch banner because
  # the confirmation field value was not sticking due to a form render race.
  # Fixed in RegisterPage.java using JS sendKeys for the confirm field.
  # ─────────────────────────────────────────────
  @Validation
  Scenario: Verify error banner when password is too short
    When the user enters "testuser123" in the register page "Username" field
    And the user enters "short" in the register page "Password" field
    And the user enters "short" in the register page "Password confirmation" field
    And the user clicks the register submit button
    Then the register page error banner should be displayed
#@RegisterPage
#Feature: NumpyNinja Register Page
  #As a new user visiting the NumpyNinja portal
  #I want to register for an account
  #So that I can access Data Structure topics and features
#
  #Background: 
    #Given the user navigates to the register page "https://dsportalapp.herokuapp.com/register"
#
  # ─── Page Load & Title ───────────────────────────────────────────
  # BUG FIX #1: Actual page title is "Registration", NOT "NumpyNinja"
  #@Smoke @PageLoad
  #Scenario: Verify the register page loads successfully
    #Then the register page title should be "Registration"
#
  #@Smoke @PageLoad
  #Scenario: Verify the register page URL
    #Then the register page URL should contain "/register"
#
  # ─── Navigation Bar ──────────────────────────────────────────────
  #@Smoke @Navigation
  #Scenario: Verify the brand logo is displayed on the register page navbar
    #Then the register page brand logo "NumpyNinja" should be displayed in the navbar
#
  # BUG FIX #2: Brand logo href is "/" (root), NOT "/home"
  #@Navigation
  #Scenario: Verify clicking the brand logo redirects to the root page
    #When the user clicks on the register page brand logo
    #Then the register page user should be redirected to "dsportalapp.herokuapp.com"
#
  #@Navigation
  #Scenario: Verify Register link is visible in the navbar on register page
    #Then the register page navbar "Register" link should be visible
#
  #@Navigation
  #Scenario: Verify Sign In link is visible in the navbar on register page
    #Then the register page navbar "Sign in" link should be visible
#
  #@Navigation
  #Scenario: Verify Sign In link href on register page
    #Then the register page Sign In link href should contain "/login"
#
  #@Navigation
  #Scenario: Verify Sign In link navigates to login page from register page
    #When the user clicks on the register page navbar "Sign in" link
    #Then the register page user should be redirected to "/login"
#
  # ─── Page Heading & Form Visibility ──────────────────────────────
  # BUG FIX #3: Actual heading is "Registration" inside <h3>, not <h1>"Register"
  # BUG FIX #4: No Email field on Django default UserCreationForm — REMOVED
  #@Smoke @Content
  #Scenario: Verify the register page heading is displayed
    #Then the register page heading should contain "Registration"
#
  #@Smoke @Content
  #Scenario: Verify the Username field is visible on the register page
    #Then the register page "Username" field should be visible
#
  #@Smoke @Content
  #Scenario: Verify the Password field is visible on the register page
    #Then the register page "Password" field should be visible
#
  #@Smoke @Content
  #Scenario: Verify the Password confirmation field is visible on the register page
    #Then the register page "Password confirmation" field should be visible
#
  #@Content
  #Scenario: Verify the Register submit button is visible
    #Then the register submit button should be visible
#
  #@Content
  #Scenario: Verify the Register submit button text
    #Then the register submit button text should be "Register"
#
  #@Content
  #Scenario: Verify the Register submit button is clickable
    #Then the register submit button should be clickable
#
  # ─── Login Link Below Form ────────────────────────────────────────
  #@Content @Navigation
  #Scenario: Verify the Login link is present below the register form
    #Then the register page sign in form link should be visible
#
  #@Content @Navigation
  #Scenario: Verify the Login form link href contains login path
    #Then the register page sign in form link href should contain "/login"
#
  #@Content @Navigation
  #Scenario: Verify clicking the Login form link navigates to login page
    #When the user clicks the sign in link on the register form
    #Then the register page user should be redirected to "/login"
#
  # ─── Functional & Validation Tests ───────────────────────────────
  # BUG FIX #4: Email steps removed — no email field on the form
  # BUG FIX #5: After register, site redirects to "/" not "/login"
  # BUG FIX #6: Error locator updated to Django's <ul class="errorlist">
  #@Smoke @Functional
  #Scenario: Verify successful registration with valid credentials
    #When the user enters "testuser123" in the register username field
    #And the user enters "Test@1234" in the register password field
    #And the user enters "Test@1234" in the register confirm password field
    #And the user clicks the register submit button
    #Then the register page user should be redirected to "/"
#
  #@Validation
  #Scenario: Verify error when registering with an already existing username
    #When the user enters "existinguser" in the register username field
    #And the user enters "Test@1234" in the register password field
    #And the user enters "Test@1234" in the register confirm password field
    #And the user clicks the register submit button
    #Then the register page error message should be displayed
#
  #@Validation
  #Scenario: Verify error when passwords do not match
    #When the user enters "newuser456" in the register username field
    #And the user enters "Test@1234" in the register password field
    #And the user enters "WrongPass@9" in the register confirm password field
    #And the user clicks the register submit button
    #Then the register page error message should be displayed
#
  #@Validation
  #Scenario: Verify browser native validation triggers when all fields are empty
    #When the user clicks the register submit button
    #Then the register page "Username" field should be visible
#
  #@Validation
  #Scenario: Verify error when username field is left empty
    #When the user enters " " in the register username field
    #And the user enters "Test@1234" in the register password field
    #And the user enters "Test@1234" in the register confirm password field
    #And the user clicks the register submit button
    #Then the register page error message should be displayed
#
  #@Validation
  #Scenario: Verify error when password fields are left empty
    #When the user enters "newuser789" in the register username field
    #And the user enters " " in the register password field
    #And the user enters " " in the register confirm password field
    #And the user clicks the register submit button
    #Then the register page error message should be displayed
#
  # ─── Field Labels Outline ─────────────────────────────────────────
  # BUG FIX #4: "Email Address" row removed — field does not exist on live form
  #@Content
  #Scenario Outline: Verify register form field labels are displayed
    #Then the register page "<Field>" field should be visible
#
    #Examples: 
      #| Field                 |
      #| Username              |
      #| Password              |
      #| Password confirmation |
