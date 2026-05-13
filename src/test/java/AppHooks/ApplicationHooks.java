package AppHooks;


import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.util.Assert;
import com.factory.DriverFactory;
import com.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

	    private DriverFactory driverFactory;
	    private WebDriver driver;
	    private ConfigReader configReader;
	    Properties prop;

	    // Extent Reports setup
	    private static ExtentReports extent;
	    private static ExtentTest test;

	    @Before(order = 0)
	    public void getProperty() {
	        configReader = new ConfigReader();
	        prop = configReader.init_prop();

	        // Initialize Extent Reports once
	        if (extent == null) {
	            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
	            spark.config().setDocumentTitle("DSAlgo Test Report");
	            spark.config().setReportName("Automation Results");
	            extent = new ExtentReports();
	            extent.attachReporter(spark);
	        }
	    }

	    @Before(order = 1)
	    public void launchBrowser(Scenario scenario) {
	        test = extent.createTest(scenario.getName());
	        String browserName = prop.getProperty("browser");
	        driverFactory = new DriverFactory();
	        driver = driverFactory.init_driver(browserName);
	    }

	    @After(order = 1)
	    public void tearDown(Scenario scenario) {
	        if (scenario.isFailed()) {
	            // Attach to Cucumber built-in report
	            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	            scenario.attach(screenshot, "image/png", scenario.getName().replaceAll(" ", "_"));

	            // Attach to Extent Report
	            String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	            test.fail("Scenario failed")
	                .addScreenCaptureFromBase64String(base64, "Failed Screenshot");
	        } else {
	            test.pass("Scenario passed");
	        }
	    }

	    @After(order = 0)
	    public void quitBrowser() {
	        driver.quit();
	        extent.flush(); // ← saves the report to disk
	    }
	}