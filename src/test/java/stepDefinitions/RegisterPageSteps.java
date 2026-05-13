package stepDefinitions;

import org.junit.Assert;

import com.factory.DriverFactory;
import com.pages.RegisterPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterPageSteps {

    private RegisterPage rp = new RegisterPage(DriverFactory.getDriver());

    // ─── Navigation ───────────────────────────────────────────────

    @Given("the user navigates to the register page {string}")
    public void the_user_navigates_to_the_register_page(String url) {
        DriverFactory.getDriver().get(url);
    }
    

    // ─── URL & Redirect ───────────────────────────────────────────

    @Then("the register page URL should contain {string}")
    public void the_register_page_url_should_contain(String expectedURL) {
        String actualURL = rp.getCurrentUrl();
        System.out.println("The current register page URL is: " + actualURL);
        Assert.assertTrue(actualURL.contains(expectedURL));
    }

    @Then("the register page user should be redirected to {string}")
    public void the_register_page_user_should_be_redirected_to(String expectedURL) {
        String actualURL = rp.getCurrentUrl();
        System.out.println("The redirected URL is: " + actualURL);
        Assert.assertTrue(actualURL.contains(expectedURL));
    }

    // ─── Navbar ───────────────────────────────────────────────────

    @Then("the register page brand logo {string} should be displayed")
    public void the_register_page_brand_logo_should_be_displayed(String expectedText) {
        boolean flag = rp.isBrandLogoVisible();
        System.out.println("Brand logo visibility: " + flag);
        Assert.assertTrue(flag);
        String actualText = rp.getBrandLogoText();
        System.out.println("Brand logo text: " + actualText);
        Assert.assertTrue(actualText.contains(expectedText));
    }

    @Then("the register page navbar {string} link should be visible")
    public void the_register_page_navbar_link_should_be_visible(String linkName) {
        boolean flag;
        if (linkName.equalsIgnoreCase("Register")) {
            flag = rp.isRegisterLinkVisible();
        } else if (linkName.equalsIgnoreCase("Sign in")) {
            flag = rp.isSignInLinkVisible();
        } else {
            throw new IllegalArgumentException("Navbar link not recognised: " + linkName);
        }
        System.out.println(linkName + " navbar link visibility: " + flag);
        Assert.assertTrue(flag);
    }

    @When("the user clicks on the register page navbar {string} link")
    public void the_user_clicks_on_the_register_page_navbar_link(String linkName) {
        if (linkName.equalsIgnoreCase("Sign in")) {
            rp.clickSignInLink();
        } else {
            System.out.println("Navbar link not recognized: " + linkName);
        }
    }

    // ─── Form Fields — Visibility ─────────────────────────────────

    @Then("the register page {string} field should be visible")
    public void the_register_page_field_should_be_visible(String fieldName) {
        boolean flag;
        switch (fieldName) {
            case "Username":              flag = rp.isUsernameFieldVisible();        break;
            case "Password":              flag = rp.isPasswordFieldVisible();        break;
            case "Password confirmation": flag = rp.isPasswordConfirmFieldVisible(); break;
            default: throw new IllegalArgumentException("Unknown field: " + fieldName);
        }
        System.out.println(fieldName + " field visibility: " + flag);
        Assert.assertTrue(flag);
    }

    // ─── Form Fields — Type ───────────────────────────────────────

    @Then("the register page {string} field type should be {string}")
    public void the_register_page_field_type_should_be(String fieldName, String expectedType) {
        String actualType;
        switch (fieldName) {
            case "Password":              actualType = rp.getPasswordFieldType();        break;
            case "Password confirmation": actualType = rp.getPasswordConfirmFieldType(); break;
            default: throw new IllegalArgumentException("Type check not defined for: " + fieldName);
        }
        System.out.println(fieldName + " field type: " + actualType);
        Assert.assertEquals(expectedType, actualType);
    }

    // ─── Form Fields — Input ──────────────────────────────────────

    @When("the user enters {string} in the register page {string} field")
    public void the_user_enters_in_the_register_page_field(String value, String fieldName) {
        switch (fieldName) {
            case "Username":              rp.enterUsername(value);             break;
            case "Password":              rp.enterPassword(value);             break;
            case "Password confirmation": rp.enterPasswordConfirmation(value); break;
            default: throw new IllegalArgumentException("Input not defined for: " + fieldName);
        }
        System.out.println("Entered '" + value + "' in the " + fieldName + " field");
    }

    // ─── Form Fields — Value ──────────────────────────────────────

    @Then("the register page {string} field value should be {string}")
    public void the_register_page_field_value_should_be(String fieldName, String expectedValue) {
        String actualValue;
        switch (fieldName) {
            case "Username": actualValue = rp.getUsernameFieldValue(); break;
            case "Password": actualValue = rp.getPasswordFieldValue(); break;
            default: throw new IllegalArgumentException("getValue not defined for: " + fieldName);
        }
        System.out.println(fieldName + " field value: " + actualValue);
        Assert.assertEquals(expectedValue, actualValue);
    }

    // ─── Submit Button ────────────────────────────────────────────

    @Then("the register submit button should be visible")
    public void the_register_submit_button_should_be_visible() {
        boolean flag = rp.isRegisterSubmitButtonVisible();
        System.out.println("Register submit button visibility: " + flag);
        Assert.assertTrue(flag);
    }

    @Then("the register submit button text should be {string}")
    public void the_register_submit_button_text_should_be(String expectedText) {
        String actualText = rp.getRegisterSubmitButtonText();
        System.out.println("Register submit button text: " + actualText);
        Assert.assertTrue(actualText.contains(expectedText));
    }

    @Then("the register submit button should be clickable")
    public void the_register_submit_button_should_be_clickable() {
        boolean flag = rp.isRegisterSubmitButtonClickable();
        System.out.println("Register submit button clickable: " + flag);
        Assert.assertTrue(flag);
    }

    @When("the user clicks the register submit button")
    public void the_user_clicks_the_register_submit_button() {
        rp.clickRegisterSubmitButton();
        System.out.println("Clicked the register submit button");
    }

    // ─── Successful Registration ──────────────────────────────────

    @When("the user registers with a unique username and password {string}")
    public void the_user_registers_with_a_unique_username_and_password(String password) {
        String uniqueUsername = "usr" + System.currentTimeMillis();
        System.out.println("Registering with unique username: " + uniqueUsername);
        rp.enterUsername(uniqueUsername);
        rp.enterPassword(password);
        rp.enterPasswordConfirmation(password);
        rp.clickRegisterSubmitButton();
    }

    // ─── Success Banner ───────────────────────────────────────────

    @Then("the register page success message text should contain {string}")
    public void the_register_page_success_message_text_should_contain(String expectedText) {
        String actualText = rp.getSuccessBannerText();
        System.out.println("Success banner text: " + actualText);
        Assert.assertTrue(actualText.contains(expectedText));
    }

    // ─── Validation — Empty Form (JS submit) ─────────────────────

    @When("the user submits the register form bypassing html5 validation")
    public void the_user_submits_the_register_form_bypassing_html5_validation() {
        rp.submitFormViaJS();
        System.out.println("Submitted empty form via JS bypassing HTML5 validation");
    }

    // ─── Validation — Error Banner ────────────────────────────────

    @Then("the register page error banner should be displayed")
    public void the_register_page_error_banner_should_be_displayed() {
        boolean flag = rp.isErrorBannerVisible();
        System.out.println("Error banner visibility: " + flag);
        Assert.assertTrue(flag);
    }

    @Then("the register page error banner text should contain {string}")
    public void the_register_page_error_banner_text_should_contain(String expectedText) {
        String actualText = rp.getErrorBannerText();
        System.out.println("Error banner text: " + actualText);
        Assert.assertTrue(actualText.contains(expectedText.toLowerCase()));
    }
/*
    // ─── Page Title & URL ─────────────────────────────────────────

    // BUG FIX #1: Expected title is "Registration" (actual <title> value)
    @Then("the register page title should be {string}")
    public void the_register_page_title_should_be(String expectedTitle) {
        String actualTitle = rp.getPageTitle();
        System.out.println("Register page title: " + actualTitle);
        Assert.assertTrue(
            "Expected title to contain [" + expectedTitle + "] but was [" + actualTitle + "]",
            actualTitle.contains(expectedTitle)
        );
    }

    @Then("the register page URL should contain {string}")
    public void the_register_page_url_should_contain(String expectedURL) {
        String actualURL = rp.getCurrentUrl();
        System.out.println("Register page URL: " + actualURL);
        Assert.assertTrue(
            "Expected URL to contain [" + expectedURL + "] but was [" + actualURL + "]",
            actualURL.contains(expectedURL)
        );
    }

    // BUG FIX #2: Brand logo redirects to "/" (root), not "/home"
    @Then("the register page user should be redirected to {string}")
    public void the_register_page_user_should_be_redirected_to(String expectedURL) {
        String actualURL = rp.getCurrentUrl();
        System.out.println("Redirected URL: " + actualURL);
        Assert.assertTrue(
            "Expected redirect to contain [" + expectedURL + "] but was [" + actualURL + "]",
            actualURL.contains(expectedURL)
        );
    }

    // ─── Navbar — Brand Logo ──────────────────────────────────────

    @Then("the register page brand logo {string} should be displayed in the navbar")
    public void the_register_page_brand_logo_should_be_displayed_in_the_navbar(String expectedText) {
        boolean flag = rp.isBrandLogoVisible();
        System.out.println("Brand logo visible: " + flag);
        Assert.assertTrue(flag);
        String actualText = rp.getBrandLogoText();
        System.out.println("Brand logo text: " + actualText);
        Assert.assertTrue(actualText.contains(expectedText));
    }

    @When("the user clicks on the register page brand logo")
    public void the_user_clicks_on_the_register_page_brand_logo() {
        rp.clickBrandLogo();
        System.out.println("Clicked brand logo on register page");
    }

    // ─── Navbar — Links ───────────────────────────────────────────

    @Then("the register page navbar {string} link should be visible")
    public void the_register_page_navbar_link_should_be_visible(String linkName) {
        boolean flag;
        if (linkName.equalsIgnoreCase("Register")) {
            flag = rp.isRegisterNavLinkVisible();
        } else if (linkName.equalsIgnoreCase("Sign in")) {
            flag = rp.isSignInNavLinkVisible();
        } else {
            throw new IllegalArgumentException("Navbar link not recognized: " + linkName);
        }
        System.out.println(linkName + " navbar link visible: " + flag);
        Assert.assertTrue(flag);
    }

    @When("the user clicks on the register page navbar {string} link")
    public void the_user_clicks_on_the_register_page_navbar_link(String linkName) {
        if (linkName.equalsIgnoreCase("Sign in")) {
            rp.clickSignInNavLink();
            System.out.println("Clicked Sign in navbar link");
        } else {
            System.out.println("Navbar link not recognized: " + linkName);
        }
    }

    @Then("the register page Sign In link href should contain {string}")
    public void the_register_page_sign_in_link_href_should_contain(String expectedHref) {
        String actualHref = rp.getSignInNavLinkHref();
        System.out.println("Sign In href: " + actualHref);
        Assert.assertTrue(actualHref.contains(expectedHref));
    }

    // ─── Page Heading ─────────────────────────────────────────────

    // BUG FIX #3: Actual heading is "Registration" inside <h3>
    @Then("the register page heading should contain {string}")
    public void the_register_page_heading_should_contain(String expectedHeading) {
        boolean visible = rp.isPageHeadingVisible();
        System.out.println("Heading visible: " + visible);
        Assert.assertTrue(visible);
        String actualHeading = rp.getPageHeadingText();
        System.out.println("Heading text: " + actualHeading);
        Assert.assertTrue(
            "Expected heading to contain [" + expectedHeading + "] but was [" + actualHeading + "]",
            actualHeading.contains(expectedHeading)
        );
    }

    // ─── Form Fields ──────────────────────────────────────────────

    // BUG FIX #4: "Email Address" case removed — field does not exist
    @Then("the register page {string} field should be visible")
    public void the_register_page_field_should_be_visible(String fieldName) {
        boolean flag;
        switch (fieldName) {
            case "Username":
                flag = rp.isUsernameFieldVisible();
                break;
            case "Password":
                flag = rp.isPasswordFieldVisible();
                break;
            case "Password confirmation":
                flag = rp.isConfirmPasswordFieldVisible();
                break;
            default:
                throw new IllegalArgumentException(
                    "Unknown field: [" + fieldName + "]. Email Address does not exist on this form."
                );
        }
        System.out.println(fieldName + " field visible: " + flag);
        Assert.assertTrue(flag);
    }

    @When("the user enters {string} in the register username field")
    public void the_user_enters_in_the_register_username_field(String username) {
        rp.enterUsername(username);
        System.out.println("Entered username: " + username);
    }

    @When("the user enters {string} in the register password field")
    public void the_user_enters_in_the_register_password_field(String password) {
        rp.enterPassword(password);
        System.out.println("Entered password: [HIDDEN]");
    }

    @When("the user enters {string} in the register confirm password field")
    public void the_user_enters_in_the_register_confirm_password_field(String confirmPassword) {
        rp.enterConfirmPassword(confirmPassword);
        System.out.println("Entered confirm password: [HIDDEN]");
    }

    // ─── Submit Button ────────────────────────────────────────────

    @Then("the register submit button should be visible")
    public void the_register_submit_button_should_be_visible() {
        boolean flag = rp.isSubmitButtonVisible();
        System.out.println("Submit button visible: " + flag);
        Assert.assertTrue(flag);
    }

    @Then("the register submit button should be clickable")
    public void the_register_submit_button_should_be_clickable() {
        boolean flag = rp.isSubmitButtonClickable();
        System.out.println("Submit button clickable: " + flag);
        Assert.assertTrue(flag);
    }

    @Then("the register submit button text should be {string}")
    public void the_register_submit_button_text_should_be(String expectedText) {
        String actualText = rp.getSubmitButtonText();
        System.out.println("Submit button text: " + actualText);
        Assert.assertTrue(actualText.contains(expectedText));
    }

    @When("the user clicks the register submit button")
    public void the_user_clicks_the_register_submit_button() {
        rp.clickSubmitButton();
        System.out.println("Clicked the Register submit button");
    }

    // ─── Login Link (below form) ──────────────────────────────────

    @Then("the register page sign in form link should be visible")
    public void the_register_page_sign_in_form_link_should_be_visible() {
        boolean flag = rp.isSignInFormLinkVisible();
        System.out.println("Login form link visible: " + flag);
        Assert.assertTrue(flag);
    }

    @Then("the register page sign in form link href should contain {string}")
    public void the_register_page_sign_in_form_link_href_should_contain(String expectedHref) {
        String actualHref = rp.getSignInFormLinkHref();
        System.out.println("Login form link href: " + actualHref);
        Assert.assertTrue(actualHref.contains(expectedHref));
    }

    @When("the user clicks the sign in link on the register form")
    public void the_user_clicks_the_sign_in_link_on_the_register_form() {
        rp.clickSignInFormLink();
        System.out.println("Clicked the Login link on the register form");
    }

    // ─── Error / Validation Message ───────────────────────────────

    // BUG FIX #6: POM locator now correctly targets Django's <ul class="errorlist">
    @Then("the register page error message should be displayed")
    public void the_register_page_error_message_should_be_displayed() {
        boolean flag = rp.isErrorMessageDisplayed();
        System.out.println("Error message visible: " + flag);
        Assert.assertTrue(flag);
    }

    @Then("the register page error message should contain {string}")
    public void the_register_page_error_message_should_contain(String expectedText) {
        String actualText = rp.getErrorMessageText();
        System.out.println("Error message text: " + actualText);
        Assert.assertTrue(actualText.contains(expectedText));
    }
    */
}



/*
 
  @Given("the user navigates to the register page {string}")
public void the_user_navigates_to_the_register_page(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the register page title should be {string}")
public void the_register_page_title_should_be(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the register page URL should contain {string}")
public void the_register_page_url_should_contain(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the register page brand logo {string} should be displayed in the navbar")
public void the_register_page_brand_logo_should_be_displayed_in_the_navbar(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("the user clicks on the register page brand logo")
public void the_user_clicks_on_the_register_page_brand_logo() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the register page user should be redirected to {string}")
public void the_register_page_user_should_be_redirected_to(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the register page navbar {string} link should be visible")
public void the_register_page_navbar_link_should_be_visible(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the register page Sign In link href should contain {string}")
public void the_register_page_sign_in_link_href_should_contain(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("the user clicks on the register page navbar {string} link")
public void the_user_clicks_on_the_register_page_navbar_link(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the register page heading should be {string}")
public void the_register_page_heading_should_be(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the register page {string} field should be visible")
public void the_register_page_field_should_be_visible(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the register submit button should be visible")
public void the_register_submit_button_should_be_visible() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the register submit button text should be {string}")
public void the_register_submit_button_text_should_be(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the register submit button should be clickable")
public void the_register_submit_button_should_be_clickable() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the register page sign in form link should be visible")
public void the_register_page_sign_in_form_link_should_be_visible() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the register page sign in form link href should contain {string}")
public void the_register_page_sign_in_form_link_href_should_contain(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("the user clicks the sign in link on the register form")
public void the_user_clicks_the_sign_in_link_on_the_register_form() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("the user enters {string} in the register username field")
public void the_user_enters_in_the_register_username_field(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("the user enters {string} in the register email field")
public void the_user_enters_in_the_register_email_field(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("the user enters {string} in the register password field")
public void the_user_enters_in_the_register_password_field(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("the user enters {string} in the register confirm password field")
public void the_user_enters_in_the_register_confirm_password_field(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("the user clicks the register submit button")
public void the_user_clicks_the_register_submit_button() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the register page error message should be displayed")
public void the_register_page_error_message_should_be_displayed() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

*/

