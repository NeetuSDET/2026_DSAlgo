package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LinkedListPage {
	
	private WebDriver driver;
//	private By getStartedBtn = By.xpath("(//a[contains(@href,'linked-list')])[2]");
	private By getStartedBtn = By.xpath("//h5[text()='Linked List']/following-sibling::a[text()='Get Started']");
	public LinkedListPage(WebDriver driver) {
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
