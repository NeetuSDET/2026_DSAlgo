package stepDefinitions;

import java.util.List;
import java.util.Map;

import com.factory.DriverFactory;
import com.pages.ArrayPage;
import com.pages.DataStructPage;
import com.pages.LoginPage;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DataStructPageSteps {

	private LoginPage lp = new LoginPage(DriverFactory.getDriver());
	private DataStructPage dsp;

	@Given("the user has already navigated to Data Structures Page afetr logging into the application {string}")
	public void the_user_has_already_navigated_to_data_structures_page_afetr_logging_into_the_application(String url,
			io.cucumber.datatable.DataTable dataTable) {
		DriverFactory.getDriver().get(url);
		List<Map<String, String>> credList = dataTable.asMaps();
		String uName = credList.get(0).get("userName");
		String pwd = credList.get(0).get("pwd");
		dsp = lp.doLogin_ds(uName, pwd);
	}

	@When("the user clicks on the Data Structures button")
	public void the_user_clicks_on_the_data_structures_button() {
		dsp.clickGetStartedBtn();
		System.out.println("Clicked Data Structures dropdown toggle");
	}

	@Then("the {string} page URL should contain {string}")
	public void the_page_url_should_contain(String page, String expTitle) {
		String actTitle = dsp.getCurrentUrl();
		System.out.println("The expected title: " + expTitle);
		System.out.println("The actual title is: " + actTitle);
		Assert.assertTrue(actTitle.contains(expTitle));

	}
}
