package stepDefinitions;

import org.junit.Assert;

import com.factory.DriverFactory;
import com.pages.HomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageSteps {

    private HomePage hp = new HomePage(DriverFactory.getDriver());

    // ─── Navigation ───────────────────────────────────────────────

    @Given("the user navigates to the home page {string}")
    public void the_user_navigates_to_the_home_page(String url) {
        DriverFactory.getDriver().get(url);
    }

    // ─── Page Title & URL ─────────────────────────────────────────

    @Then("the home page title should be {string}")
    public void the_home_page_title_should_be(String expectedTitle) {
        String actualTitle = hp.getPageTitle();
        System.out.println("The title of the home page is: " + actualTitle);
        Assert.assertTrue(actualTitle.contains(expectedTitle));
    }

    @Then("the home page URL should contain {string}")
    public void the_home_page_url_should_contain(String expectedURL) {
        String actualURL = hp.getCurrentUrl();
        System.out.println("The current home page URL is: " + actualURL);
        Assert.assertTrue(actualURL.contains(expectedURL));
    }

    @Then("the home page user should be redirected to {string}")
    public void the_home_page_user_should_be_redirected_to(String expectedURL) {
        String actualURL = hp.getCurrentUrl();
        System.out.println("The redirected URL is: " + actualURL);
        Assert.assertTrue(actualURL.contains(expectedURL));
    }

    // ─── Not Logged In Message ────────────────────────────────────
    // Appears after clicking Get Started OR a dropdown topic item — not on page load.

    @Then("the not logged in message should be displayed")
    public void the_not_logged_in_message_should_be_displayed() {
        boolean flag = hp.isNotLoggedInMessageVisible();
        System.out.println("Not logged in message visibility: " + flag);
        Assert.assertTrue(flag);
    }

    @Then("the not logged in message text should contain {string}")
    public void the_not_logged_in_message_text_should_contain(String expectedText) {
        String actualText = hp.getNotLoggedInMessageText();
        System.out.println("Not logged in message text: " + actualText);
        Assert.assertTrue(actualText.contains(expectedText));
    }

    // ─── Navbar ───────────────────────────────────────────────────

    @Then("the brand logo {string} should be displayed in the navbar")
    public void the_brand_logo_should_be_displayed_in_the_navbar(String expectedText) {
        boolean flag = hp.isBrandLogoVisible();
        System.out.println("Brand logo visibility: " + flag);
        Assert.assertTrue(flag);
        String actualText = hp.getBrandLogoText();
        System.out.println("Brand logo text: " + actualText);
        Assert.assertTrue(actualText.contains(expectedText));
    }

    @When("the user clicks on the brand logo")
    public void the_user_clicks_on_the_brand_logo() {
        hp.clickBrandLogo();
    }

    @Then("the navbar {string} link should be visible")
    public void the_navbar_link_should_be_visible(String linkName) {
        boolean flag;
        if (linkName.equalsIgnoreCase("Register")) {
            flag = hp.isRegisterLinkVisible();
        } else if (linkName.equalsIgnoreCase("Sign in")) {
            flag = hp.isSignInLinkVisible();
        } else {
            flag = hp.isDataStructuresToggleVisible();
        }
        System.out.println(linkName + " navbar link visibility: " + flag);
        Assert.assertTrue(flag);
    }

    @When("the user clicks on the navbar {string} link")
    public void the_user_clicks_on_the_navbar_link(String linkName) {
        if (linkName.equalsIgnoreCase("Register")) {
            hp.clickRegisterLink();
        } else if (linkName.equalsIgnoreCase("Sign in")) {
            hp.clickSignInLink();
        } else {
            System.out.println("Navbar link not recognized: " + linkName);
        }
    }

    @Then("the Register link href should contain {string}")
    public void the_register_link_href_should_contain(String expectedHref) {
        String actualHref = hp.getRegisterLinkHref();
        System.out.println("Register link href: " + actualHref);
        Assert.assertTrue(actualHref.contains(expectedHref));
    }

    @Then("the Sign In link href should contain {string}")
    public void the_sign_in_link_href_should_contain(String expectedHref) {
        String actualHref = hp.getSignInLinkHref();
        System.out.println("Sign In link href: " + actualHref);
        Assert.assertTrue(actualHref.contains(expectedHref));
    }

    // ─── Data Structures Dropdown ─────────────────────────────────

    @When("the user clicks on the Data Structures dropdown")
    public void the_user_clicks_on_the_data_structures_dropdown() {
        hp.clickDataStructuresToggle();
        System.out.println("Clicked Data Structures dropdown toggle");
    }

    @When("the user selects {string} from the Data Structures dropdown")
    public void the_user_selects_from_the_data_structures_dropdown(String topic) {
        switch (topic) {
            case "Arrays":      hp.clickDropdownArrays();     break;
            case "Linked List": hp.clickDropdownLinkedList(); break;
            case "Stack":       hp.clickDropdownStack();      break;
            case "Queue":       hp.clickDropdownQueue();      break;
            case "Tree":        hp.clickDropdownTree();       break;
            case "Graph":       hp.clickDropdownGraph();      break;
            default: System.out.println("Dropdown topic not recognized: " + topic);
        }
        System.out.println("Selected from dropdown: " + topic);
    }

    // ─── Topic Cards Visibility ───────────────────────────────────

    @Then("the {string} topic card should be visible on the home page")
    public void the_topic_card_should_be_visible_on_the_home_page(String topic) {
        boolean flag;
        switch (topic) {
            case "Data Structures-Introduction": flag = hp.isIntroductionCardVisible(); break;
            case "Array":                        flag = hp.isArrayCardVisible();        break;
            case "Linked List":                  flag = hp.isLinkedListCardVisible();   break;
            case "Stack":                        flag = hp.isStackCardVisible();        break;
            case "Queue":                        flag = hp.isQueueCardVisible();        break;
            case "Tree":                         flag = hp.isTreeCardVisible();         break;
            case "Graph":                        flag = hp.isGraphCardVisible();        break;
            default: throw new IllegalArgumentException("Unknown topic card: " + topic);
        }
        System.out.println(topic + " card visibility: " + flag);
        Assert.assertTrue(flag);
    }

    // ─── Get Started Buttons ──────────────────────────────────────

    @Then("the {string} Get Started button should be visible on the home page")
    public void the_get_started_button_should_be_visible_on_the_home_page(String topic) {
        boolean flag;
        switch (topic) {
            case "Data Structures-Introduction": flag = hp.isGetStartedIntroductionVisible(); break;
            case "Array":                        flag = hp.isGetStartedArrayVisible();        break;
            case "Linked List":                  flag = hp.isGetStartedLinkedListVisible();   break;
            case "Stack":                        flag = hp.isGetStartedStackVisible();        break;
            case "Queue":                        flag = hp.isGetStartedQueueVisible();        break;
            case "Tree":                         flag = hp.isGetStartedTreeVisible();         break;
            case "Graph":                        flag = hp.isGetStartedGraphVisible();        break;
            default: throw new IllegalArgumentException("Unknown topic: " + topic);
        }
        System.out.println(topic + " Get Started button visibility: " + flag);
        Assert.assertTrue(flag);
    }

    @Then("the {string} Get Started button should be clickable on the home page")
    public void the_get_started_button_should_be_clickable_on_the_home_page(String topic) {
        boolean flag;
        switch (topic) {
            case "Data Structures-Introduction": flag = hp.isGetStartedIntroductionClickable(); break;
            case "Array":                        flag = hp.isGetStartedArrayClickable();        break;
            default: throw new IllegalArgumentException("Clickable check not defined for: " + topic);
        }
        System.out.println(topic + " Get Started button clickable: " + flag);
        Assert.assertTrue(flag);
    }

    @Then("the {string} Get Started button text should be {string} on the home page")
    public void the_get_started_button_text_should_be_on_the_home_page(String topic, String expectedText) {
        String actualText;
        switch (topic) {
            case "Data Structures-Introduction": actualText = hp.getGetStartedIntroductionText(); break;
            case "Array":                        actualText = hp.getGetStartedArrayText();        break;
            default: throw new IllegalArgumentException("getText not defined for: " + topic);
        }
        System.out.println(topic + " Get Started button text: " + actualText);
        Assert.assertTrue(actualText.contains(expectedText));
    }

    @Then("the {string} Get Started button href should contain {string}")
    public void the_get_started_button_href_should_contain(String topic, String expectedHref) {
        String actualHref;
        switch (topic) {
            case "Data Structures-Introduction": actualHref = hp.getGetStartedIntroductionHref(); break;
            case "Array":                        actualHref = hp.getGetStartedArrayHref();        break;
            case "Linked List":                  actualHref = hp.getGetStartedLinkedListHref();   break;
            case "Stack":                        actualHref = hp.getGetStartedStackHref();        break;
            case "Queue":                        actualHref = hp.getGetStartedQueueHref();        break;
            case "Tree":                         actualHref = hp.getGetStartedTreeHref();         break;
            case "Graph":                        actualHref = hp.getGetStartedGraphHref();        break;
            default: throw new IllegalArgumentException("Unknown topic: " + topic);
        }
        System.out.println(topic + " Get Started href: " + actualHref);
        Assert.assertTrue(actualHref.contains(expectedHref));
    }

    @When("the user clicks the {string} Get Started button on the home page")
    public void the_user_clicks_the_get_started_button_on_the_home_page(String topic) {
        switch (topic) {
            case "Data Structures-Introduction": hp.clickGetStartedIntroduction(); break;
            case "Array":                        hp.clickGetStartedArray();        break;
            case "Linked List":                  hp.clickGetStartedLinkedList();   break;
            case "Stack":                        hp.clickGetStartedStack();        break;
            case "Queue":                        hp.clickGetStartedQueue();        break;
            case "Tree":                         hp.clickGetStartedTree();         break;
            case "Graph":                        hp.clickGetStartedGraph();        break;
            default: throw new IllegalArgumentException("Unknown topic: " + topic);
        }
        System.out.println("Clicked Get Started for: " + topic);
    }
}
