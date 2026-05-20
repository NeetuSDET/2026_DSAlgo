package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DataStructPage {
	private WebDriver driver;

	// ─── By Locators ──────────────────────────────────────────────

//	private By usernameField = By.id("id_username");
//    private By passwordField = By.id("id_password");
//
//    // Submit Button
//    // <input type="submit" value="Login"> or <button type="submit">
//    private By submitButton = By.xpath(
//        "//input[@type='submit'] | //button[@type='submit']");
//    
	private By getStartedBtn = By.xpath("//a[contains(@href, 'data-structures')]");

	// ─── Constructor ──────────────────────────────────────────────

	public DataStructPage(WebDriver driver) {
		this.driver = driver;

	}

	// ─── Navigation ───────────────────────────────────────────────

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void clickGetStartedBtn() {
		driver.findElement(getStartedBtn).click();
	}

}
