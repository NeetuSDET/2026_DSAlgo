package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.factory.DriverFactory;
import com.pages.ArrayPage;
import com.pages.LinkedListPage;
import com.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LinkedListPageSteps {

	private LoginPage lp = new LoginPage(DriverFactory.getDriver());
	private LinkedListPage llp;

	@Given("the user has already navigated to Linked List Page after logging into the application {string}")
	public void the_user_has_already_navigated_to_linked_list_page_after_logging_into_the_application(String url,
			io.cucumber.datatable.DataTable dataTable) {
		DriverFactory.getDriver().get(url);
		List<Map<String, String>> credList = dataTable.asMaps();
		String uName = credList.get(0).get("userName");
		String pwd = credList.get(0).get("pwd");
		llp = lp.doLogin_linkedlist(uName, pwd);

	}

	@When("the user clicks on the Linked List page get started button")
	public void the_user_clicks_on_the_Linked_List_page_get_started_button() {
		llp.clickGetStartedBtn();
		System.out.println("Clicked Linked List dropdown toggle");
	}

	@Then("the Linked List page URL should contain {string}")
	public void the_linked_list_page_url_should_contain(String expTitle) {
		String actTitle = llp.getCurrentUrl();
		System.out.println("The expected title: " + expTitle);
		System.out.println("The actual title is: " + actTitle);
		Assert.assertTrue(actTitle.contains(expTitle));
	}

}
