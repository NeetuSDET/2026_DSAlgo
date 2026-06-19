package com.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	public WebDriver driver;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public WebDriver init_driver(String browser) {
		System.out.println("Inside browser: " + browser);

		if (browser.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless=new");
			tlDriver.set(new ChromeDriver(options));
//			tlDriver.set(new ChromeDriver());
		} else if (browser.equals("firefox")) {
			tlDriver.set(new FirefoxDriver());
		}

		else {
			System.out.println("Please eneter a valid browser. You enetered: " + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
}
