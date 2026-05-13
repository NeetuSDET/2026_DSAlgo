package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)

@CucumberOptions(
        features = {"src/test/resources/com/features"},
        glue = {"stepDefinitions", "AppHooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",  // ← add this
                "json:target/cucumber-reports/report.json"   // ← add this too
        }
)



public class MyTestRunner {
	
	
	
	
	

}