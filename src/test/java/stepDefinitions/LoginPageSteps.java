package stepDefinitions;

import org.junit.Assert;
import com.factory.DriverFactory;
import com.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps {

	private LoginPage lp = new LoginPage(DriverFactory.getDriver());

	@Given("the user navigates to the login page {string}")
	public void the_user_navigates_to_the_login_page(String url) {
		DriverFactory.getDriver().get(url);
		System.out.println("Navigated to login page: " + url);
	}

	@Then("the login page title should be {string}")
	public void the_login_page_title_should_be(String expectedTitle) {
		String actualTitle = lp.getPageTitle();
		System.out.println("Login page title: " + actualTitle);
		Assert.assertTrue("Expected title to contain [" + expectedTitle + "] but was [" + actualTitle + "]",
				actualTitle.contains(expectedTitle));
	}

	@Then("the login page URL should contain {string}")
	public void the_login_page_url_should_contain(String expectedURL) {
		String actualURL = lp.getCurrentUrl();
		System.out.println("Login page URL: " + actualURL);
		Assert.assertTrue("Expected URL to contain [" + expectedURL + "] but was [" + actualURL + "]",
				actualURL.contains(expectedURL));

	}

	@Then("the login page brand logo {string} should be displayed in the navbar")
	public void the_login_page_brand_logo_should_be_displayed_in_the_navbar(String expectedText) {
		boolean flag = lp.isBrandLogoVisible();
		System.out.println("Login page brand logo visible: " + flag);
		Assert.assertTrue(flag);
		String actualText = lp.getBrandLogoText();
		System.out.println("Login page brand logo text: " + actualText);
		Assert.assertTrue(actualText.contains(expectedText));
	}

	@When("the user clicks on the login page brand logo")
	public void the_user_clicks_on_the_login_page_brand_logo() {
		lp.clickBrandLogo();
		System.out.println("Clicked brand logo on login page");
	}

	@Then("the login page user should be redirected to {string}")
	public void the_login_page_user_should_be_redirected_to(String string) {

	}

	@Then("the login page navbar {string} link should be visible")
	public void the_login_page_navbar_link_should_be_visible(String linkName) {
		boolean flag;
		if (linkName.equalsIgnoreCase("Register")) {
			flag = lp.isRegisterNavLinkVisible();
		} else if (linkName.equalsIgnoreCase("Sign in")) {
			flag = lp.isSignInNavLinkVisible();
		} else {
			throw new IllegalArgumentException("Navbar link not recognized: " + linkName);
		}
		System.out.println(linkName + " navbar link visible: " + flag);
		Assert.assertTrue(flag);
	}

	@Then("the login page Register navbar link href should contain {string}")
	public void the_login_page_register_navbar_link_href_should_contain(String expectedHref) {
		String actualHref = lp.getRegisterNavLinkHref();
		System.out.println("Register navbar href: " + actualHref);
		Assert.assertTrue(actualHref.contains(expectedHref));
	}

	@Then("the login page Sign In navbar link href should contain {string}")
	public void the_login_page_sign_in_navbar_link_href_should_contain(String expectedHref) {
		String actualHref = lp.getSignInNavLinkHref();
		System.out.println("Sign In navbar href: " + actualHref);
		Assert.assertTrue(actualHref.contains(expectedHref));
	}

	@When("the user clicks on the login page navbar {string} link")
	public void the_user_clicks_on_the_login_page_navbar_link(String linkName) {
		if (linkName.equalsIgnoreCase("Register")) {
			lp.clickRegisterNavLink();
			System.out.println("Clicked Register navbar link on login page");
		} else if (linkName.equalsIgnoreCase("Sign in")) {
			lp.clickSignInNavLink();
			System.out.println("Clicked Sign in navbar link on login page");
		} else {
			System.out.println("Navbar link not recognized: " + linkName);
		}
	}

	@Then("the login page heading should contain {string}")
	public void the_login_page_heading_should_contain(String expectedHeading) {
		boolean visible = lp.isPageHeadingVisible();
		System.out.println("Login page heading visible: " + visible);
		Assert.assertTrue(visible);
		String actualHeading = lp.getPageHeadingText();
		System.out.println("Login page heading text: " + actualHeading);
		Assert.assertTrue("Expected heading to contain [" + expectedHeading + "] but was [" + actualHeading + "]",
				actualHeading.contains(expectedHeading));
	}

	@Then("the login page {string} field should be visible")
	public void the_login_page_field_should_be_visible(String fieldName) {
		boolean flag;
		switch (fieldName) {
		case "Username":
			flag = lp.isUsernameFieldVisible();
			break;
		case "Password":
			flag = lp.isPasswordFieldVisible();
			break;
		default:
			throw new IllegalArgumentException("Unknown login form field: " + fieldName);
		}
		System.out.println(fieldName + " field visible on login page: " + flag);
		Assert.assertTrue(flag);
	}

	@Then("the login submit button should be visible")
	public void the_login_submit_button_should_be_visible() {
		boolean flag = lp.isSubmitButtonVisible();
		System.out.println("Login submit button visible: " + flag);
		Assert.assertTrue(flag);
	}

	@Then("the login submit button should be clickable")
	public void the_login_submit_button_should_be_clickable() {
		boolean flag = lp.isSubmitButtonClickable();
		System.out.println("Login submit button clickable: " + flag);
		Assert.assertTrue(flag);
	}

	@Then("the login submit button text should be {string}")
	public void the_login_submit_button_text_should_be(String expectedText) {
		String actualText = lp.getSubmitButtonText();
		System.out.println("Login submit button text: " + actualText);
		Assert.assertTrue(actualText.contains(expectedText));
	}

	@Then("the login page register form link should be visible")
	public void the_login_page_register_form_link_should_be_visible() {
		boolean flag = lp.isRegisterFormLinkVisible();
		System.out.println("Register form link visible on login page: " + flag);
		Assert.assertTrue(flag);
	}

	@Then("the login page register form link href should contain {string}")
	public void the_login_page_register_form_link_href_should_contain(String expectedHref) {
		String actualHref = lp.getRegisterFormLinkHref();
		System.out.println("Register form link href: " + actualHref);
		Assert.assertTrue(actualHref.contains(expectedHref));
	}

	@When("the user clicks the register link on the login form")
	public void the_user_clicks_the_register_link_on_the_login_form() {
		lp.clickRegisterFormLink();
		System.out.println("Clicked Register link on the login form");
	}

	@When("the user enters {string} in the login username field")
	public void the_user_enters_in_the_login_username_field(String string) {

	}

	@When("the user enters {string} in the login password field")
	public void the_user_enters_in_the_login_password_field(String string) {

	}

	@When("the user clicks the login submit button")
	public void the_user_clicks_the_login_submit_button() {

	}

	@Then("the login page error message should be displayed")
	public void the_login_page_error_message_should_be_displayed() {
		boolean flag = lp.isErrorMessageDisplayed();
		System.out.println("Login page error message visible: " + flag);
		Assert.assertTrue(flag);
	}

}