package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {



private WebDriver driver;
//public WebDriverWait wait;

// 1. By Locators
private By mainHeading = By.xpath("//h1");

private By tagline = By.xpath("//*[contains(text(),'You are at the right place')]");
private By getStartedButton = By.linkText("Get Started");
private By copyrightText = By.xpath("//*[contains(text(),'Copyright@NumpyNinja2021')]");


// ─── Constructor ──────────────────────────────────────────────

public LandingPage(WebDriver driver) {
        this.driver = driver;
       
    }

// ─── Actions ──────────────────────────────────────────────────

	/** Navigate to the landing page URL */
	public void navigateTo(String url) {
		driver.get(url);
	}

	/** Click the Get Started button */
	public void clickGetStarted() {
	//	wait.until(ExpectedConditions.elementToBeClickable(getStartedButton));
		driver.findElement(getStartedButton).click();
		
	}

	/** Is Get Started button clickable*/
	public boolean isClickableGetStarted() {
		//wait.until(ExpectedConditions.elementToBeClickable(getStartedButton));
		return driver.findElement(getStartedButton).isEnabled();
		
	}
	

	// ─── Getters / Verifications ──────────────────────────────────

	/** Returns the browser tab title */
	public String getPageTitle() {
		return driver.getTitle();
	}

	/** Returns the current URL */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

//	/** Returns the brand name text (e.g. "Numpy Ninja") */
//	public String getBrandNameText() {
//		wait.until(ExpectedConditions.visibilityOf(brandName));
//		return brandName.getText().trim();
//	}

	/** Returns the main heading text */
	public String getMainHeadingText() {
	//	wait.until(ExpectedConditions.visibilityOf(  driver.findElement(mainHeading)));
		return driver.findElement(mainHeading).getText().trim();                
	}

	/** Returns the tagline text */
	public String getTaglineText() {
		
	//	wait.until(ExpectedConditions.visibilityOf(driver.findElement(tagline)));
		return driver.findElement(tagline).getText().trim();
	}

	/** Returns the copyright text */
	public String getCopyrightText() {
		
	//	wait.until(ExpectedConditions.visibilityOf(driver.findElement(copyrightText)));
		return driver.findElement(copyrightText).getText().trim();
	}

	/** Returns the href attribute of the Get Started button */
	public String getGetStartedHref() {
		
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(getStartedButton)));
		return driver.findElement(getStartedButton).getAttribute("href");
	}

	/** Checks if Get Started button is displayed */
	public boolean isGetStartedButtonVisible() {
		
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(getStartedButton)));
		return driver.findElement(getStartedButton).isDisplayed();
	}
	
	/** Get the text of Get Started button  */
	public String isGetStartedText() {
		
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(getStartedButton)));
		return driver.findElement(getStartedButton).getText();
	}

//	/** Checks if brand name is displayed */
//	public boolean isBrandNameVisible() {
//		wait.until(ExpectedConditions.visibilityOf(brandName));
//		return brandName.isDisplayed();
//	}

	/** Checks if main heading is displayed */
	public boolean isMainHeadingVisible() {
		
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(mainHeading)));
		return driver.findElement(mainHeading).isDisplayed();
	}

	/** Checks if tagline is displayed */
	public boolean isTaglineVisible() {
		
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(tagline)));
		return driver.findElement(tagline).isDisplayed();
	}

	/** Checks if copyright text is displayed */
	public boolean isCopyrightVisible() {
		
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(copyrightText)));
		return driver.findElement(copyrightText).isDisplayed();

}}
