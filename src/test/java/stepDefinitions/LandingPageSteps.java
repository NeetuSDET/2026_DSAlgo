package stepDefinitions;

import org.junit.Assert;
//import org.openqa.selenium.WebDriver;

import com.factory.DriverFactory;
import com.pages.LandingPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LandingPageSteps {

	private LandingPage lp = new LandingPage(DriverFactory.getDriver());
	
	@Given("the user has navigates to the DS Algo Portal in their browser.")
	public void the_user_has_navigates_to_the_ds_algo_portal_in_their_browser() {
		DriverFactory.getDriver().get("https://dsportalapp.herokuapp.com/");
	}
	

	@Then("the DS Algo Portal landing page URL should be {string}")
	public void the_DS_Algo_Portal_landing_page_URL_should_be(String expectedURL) {
		String actualURL = lp.getCurrentUrl();
		System.out.println("The actual URL is: " + actualURL);
		Assert.assertTrue(actualURL.contains(expectedURL));
	}

	@Then("the {string} button should be visible on the {string} page")
	public void the_button_should_be_visible_on_the_page(String string, String string2) {
		Boolean flag = lp.isGetStartedButtonVisible();
		System.out.println("The get started buttong visibility: " + flag);
		Assert.assertTrue(flag);
	}

	@Then("the {string} button text should be {string}")
	public void the_button_text_should_be(String string, String expectedText) {
		String actaulTtext = lp.isGetStartedText();
		System.out.println("The text for get started button is: " + actaulTtext);
		Assert.assertTrue(actaulTtext.contains(expectedText));
	}

	@Then("the user should be able to click the {string} button")
	public void the_user_should_be_able_to_click_the_button(String string) {
		Boolean flag = lp.isClickableGetStarted();
		Assert.assertTrue(flag);
	}

	@When("the user clicks the {string} button")
	public void the_user_clicks_the_button(String string) {
		lp.clickGetStarted();
	}

	@Then("the user should be redirected to the {string} page")
	public void the_user_should_be_redirected_to_the_page(String expectedURLstring) {
		String actualURL = lp.getCurrentUrl();
		System.out.println("The page url is: " + actualURL);

		Assert.assertTrue(actualURL.contains(expectedURLstring));
	}

	@Then("the page title should be {string}")
	public void the_page_title_should_be(String expectedTitle) {
		String actualTitle = lp.getPageTitle();
		System.out.println("The title of the page is: " + actualTitle);
		Assert.assertTrue(actualTitle.contains(expectedTitle));
	}

	@Then("the page URL should contain {string}")
	public void the_page_url_should_contain(String expectedURL) {
		String actualURL = lp.getCurrentUrl();
		System.out.println("The url is: " + actualURL);
		Assert.assertTrue(actualURL.contains(expectedURL));

	}

}
















/*
 import org.junit.Assert;
//import org.openqa.selenium.WebDriver;

import com.factory.DriverFactory;
import com.pages.LandingPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LandingPageSteps {

	private LandingPage lp = new LandingPage(DriverFactory.getDriver());
	
	@Given("the user has navigates to the DS Algo Portal in their browser.")
	public void the_user_has_navigates_to_the_ds_algo_portal_in_their_browser() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the DS Algo Portal landing page URL should be {string}")
	public void the_ds_algo_portal_landing_page_url_should_be(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the {string} button should be visible on the {string} page")
	public void the_button_should_be_visible_on_the_page(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the {string} button text should be {string}")
	public void the_button_text_should_be(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the user should be able to click the {string} button")
	public void the_user_should_be_able_to_click_the_button(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("the user clicks the {string} button")
	public void the_user_clicks_the_button(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the user should be redirected to the {string} page")
	public void the_user_should_be_redirected_to_the_page(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the page title should be {string}")
	public void the_page_title_should_be(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the page URL should contain {string}")
	public void the_page_url_should_contain(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
 */


