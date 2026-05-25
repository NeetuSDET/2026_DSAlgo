package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.factory.DriverFactory;
import com.pages.ArrayPage;
import com.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ArrayPageSteps {
	
	private LoginPage lp = new LoginPage(DriverFactory.getDriver());
	private ArrayPage ap ;

	@Given("the user has already navigated to Array Page after logging into the application {string}")
	public void the_user_has_already_navigated_to_array_page_after_logging_into_the_application(String url,
			io.cucumber.datatable.DataTable dataTable) {
		DriverFactory.getDriver().get(url);
		List<Map<String, String>> credList = dataTable.asMaps();
		String uName = credList.get(0).get("userName");
		String pwd = credList.get(0).get("pwd");
		ap = lp.doLogin_array(uName, pwd);
	}

	@When("the user clicks on the Array page get started button")
	public void the_user_clicks_on_the_array_page_get_started_button() {
		ap.clickGetStartedBtn();
		System.out.println("Clicked Array dropdown toggle");
	}
	@Then("the array page URL should contain {string}")
	public void the_array_page_url_should_contain(String expTitle) {
		String actTitle = ap.getCurrentUrl();
		System.out.println("The expected title: " + expTitle);
		System.out.println("The actual title is: " + actTitle);
		Assert.assertTrue(actTitle.contains(expTitle));

	}


	
}
