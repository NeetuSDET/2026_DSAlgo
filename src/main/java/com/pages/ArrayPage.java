package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArrayPage {

	private WebDriver driver;
	private By getStartedBtn = By.xpath("(//a[contains(@href,'array')])[2]");
	
	public ArrayPage(WebDriver driver) {
		this.driver = driver;

	}
	
	// ─── Navigation ───────────────────────────────────────────────

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    public void clickGetStartedBtn()
    {
    	driver.findElement(getStartedBtn).click();
    }
}
