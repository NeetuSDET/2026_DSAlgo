@HomePage
Feature: NumpyNinja Home Page
  As a user visiting the NumpyNinja portal
  I want to explore the home page
  So that I can navigate to various Data Structure topics and access account features

  # Background re-runs before EVERY scenario/outline row — guaranteeing
  # a fresh home page load and closed dropdown state each time.
  Background: 
    Given the user navigates to the home page "https://dsportalapp.herokuapp.com/home"

  # ─────────────────────────────────────────────
  # Page Load & Title
  # ─────────────────────────────────────────────
  @Smoke @PageLoad
  Scenario: Verify the home page loads successfully
    Then the home page title should be "NumpyNinja"

  @Smoke @PageLoad
  Scenario: Verify the home page URL
    Then the home page URL should contain "/home"

  # ─────────────────────────────────────────────
  # Navigation Bar
  # ─────────────────────────────────────────────
  @Smoke @Navigation
  Scenario: Verify the brand logo is displayed in the navbar
    Then the brand logo "NumpyNinja" should be displayed in the navbar

  @Navigation
  Scenario: Verify clicking the brand logo stays on the home page
    When the user clicks on the brand logo
    Then the home page URL should contain "/home"

  @Navigation
  Scenario: Verify Register link is visible in the navbar
    Then the navbar "Register" link should be visible

  @Navigation
  Scenario: Verify Sign In link is visible in the navbar
    Then the navbar "Sign in" link should be visible

  @Navigation
  Scenario: Verify Register link href
    Then the Register link href should contain "/register"

  @Navigation
  Scenario: Verify Sign In link href
    Then the Sign In link href should contain "/login"

  @Navigation
  Scenario: Verify Register link navigates to registration page
    When the user clicks on the navbar "Register" link
    Then the home page user should be redirected to "/register"

  @Navigation
  Scenario: Verify Sign In link navigates to login page
    When the user clicks on the navbar "Sign in" link
    Then the home page user should be redirected to "/login"

  # ─────────────────────────────────────────────
  # Data Structures Dropdown
  # NOTE: "Data Structures" is a Bootstrap dropdown TOGGLE — NOT a navigable
  # link. Clicking child items (Arrays, Linked List, etc.) while NOT logged in
  # redirects back to home with "You are not logged in" message.
  # Therefore dropdown navigation tests verify the not-logged-in behaviour.
  # ─────────────────────────────────────────────
  @Navigation
  Scenario: Verify Data Structures dropdown toggle is visible in the navbar
    Then the navbar "Data Structures" link should be visible

  @Navigation
  Scenario Outline: Verify Data Structures dropdown items show not logged in message when not logged in
    When the user clicks on the Data Structures dropdown
    And the user selects "<Topic>" from the Data Structures dropdown
    Then the not logged in message should be displayed
    And the not logged in message text should contain "You are not logged in"

    Examples: 
      | Topic       |
      | Arrays      |
      | Linked List |
      | Stack       |
      | Queue       |
      | Tree        |
      | Graph       |

  # ─────────────────────────────────────────────
  # Topic Cards Visibility
  # ─────────────────────────────────────────────
  @Smoke @Content
  Scenario Outline: Verify all topic cards are visible on the home page
    Then the "<Topic>" topic card should be visible on the home page

    Examples: 
      | Topic                        |
      | Data Structures-Introduction |
      | Array                        |
      | Linked List                  |
      | Stack                        |
      | Queue                        |
      | Tree                         |
      | Graph                        |

  # ─────────────────────────────────────────────
  # Get Started Buttons — Visibility & Clickability
  # ─────────────────────────────────────────────
  @Content
  Scenario Outline: Verify Get Started buttons are visible on each topic card
    Then the "<Topic>" Get Started button should be visible on the home page

    Examples: 
      | Topic                        |
      | Data Structures-Introduction |
      | Array                        |
      | Linked List                  |
      | Stack                        |
      | Queue                        |
      | Tree                         |
      | Graph                        |

  @Content
  Scenario: Verify Introduction Get Started button is clickable
    Then the "Data Structures-Introduction" Get Started button should be clickable on the home page

  @Content
  Scenario: Verify Array Get Started button is clickable
    Then the "Array" Get Started button should be clickable on the home page

  # ─────────────────────────────────────────────
  # Get Started Buttons — Text
  # ─────────────────────────────────────────────
  @Content
  Scenario: Verify Introduction Get Started button text
    Then the "Data Structures-Introduction" Get Started button text should be "Get Started" on the home page

  @Content
  Scenario: Verify Array Get Started button text
    Then the "Array" Get Started button text should be "Get Started" on the home page

  # ─────────────────────────────────────────────
  # Get Started Buttons — Href
  # ─────────────────────────────────────────────
  @Content
  Scenario Outline: Verify Get Started button hrefs point to correct pages
    Then the "<Topic>" Get Started button href should contain "<ExpectedHref>"

    Examples: 
      | Topic                        | ExpectedHref                  |
      | Data Structures-Introduction | /data-structures-introduction |
      | Array                        | /array                        |
      | Linked List                  | /linked-list                  |
      | Stack                        | /stack                        |
      | Queue                        | /queue                        |
      | Tree                         | /tree                         |
      | Graph                        | /graph                        |

  # ─────────────────────────────────────────────
  # Not Logged In — Get Started Click Behaviour
  # NOTE: "You are not logged in" message appears AFTER clicking Get Started,
  # not on page load. Background re-navigates before each row so each click
  # starts fresh from the home page.
  # ─────────────────────────────────────────────
  @Smoke @Auth
  Scenario Outline: Verify clicking Get Started when not logged in shows not logged in message
    When the user clicks the "<Topic>" Get Started button on the home page
    Then the not logged in message should be displayed
    And the not logged in message text should contain "You are not logged in"

    Examples: 
      | Topic                        |
      | Data Structures-Introduction |
      | Array                        |
      | Linked List                  |
      | Stack                        |
      | Queue                        |
      | Tree                         |
      | Graph                        |
