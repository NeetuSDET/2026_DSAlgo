package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ─── By Locators ──────────────────────────────────────────────

    // Navbar
    private By brandLogo            = By.xpath("//a[@class='navbar-brand' and text()='NumpyNinja']");
    private By dataStructuresToggle = By.xpath("//a[contains(@class,'nav-link dropdown-toggle')]");
    private By registerLink         = By.linkText("Register");
    private By signInLink           = By.linkText("Sign in");

    // Data Structures dropdown CHILD items (only visible after toggle is clicked)
    private By ddArrays     = By.xpath("//div[contains(@class,'dropdown-menu')]//a[@href='/array']");
    private By ddLinkedList = By.xpath("//div[contains(@class,'dropdown-menu')]//a[@href='/linked-list']");
    private By ddStack      = By.xpath("//div[contains(@class,'dropdown-menu')]//a[@href='/stack']");
    private By ddQueue      = By.xpath("//div[contains(@class,'dropdown-menu')]//a[@href='/queue']");
    private By ddTree       = By.xpath("//div[contains(@class,'dropdown-menu')]//a[@href='/tree']");
    private By ddGraph      = By.xpath("//div[contains(@class,'dropdown-menu')]//a[@href='/graph']");

    // "You are not logged in" alert banner
    // NOTE: This banner only appears AFTER clicking a Get Started button, NOT on page load
    private By notLoggedInMessage = By.xpath("//*[contains(text(),'You are not logged in')]");

    // Topic card headings
    private By headingIntroduction = By.xpath("//h5[contains(text(),'Data Structures-Introduction')]");
    private By headingArray        = By.xpath("//h5[contains(text(),'Array')]");
    private By headingLinkedList   = By.xpath("//h5[contains(text(),'Linked List')]");
    private By headingStack        = By.xpath("//h5[contains(text(),'Stack')]");
    private By headingQueue        = By.xpath("//h5[contains(text(),'Queue')]");
    private By headingTree         = By.xpath("//h5[contains(text(),'Tree')]");
    private By headingGraph        = By.xpath("//h5[contains(text(),'Graph')]");

    // Get Started buttons — scoped per card via following:: axis
    private By getStartedIntroduction = By.xpath("//h5[contains(text(),'Data Structures-Introduction')]/following::a[contains(text(),'Get Started')][1]");
    private By getStartedArray        = By.xpath("//h5[contains(text(),'Array')]/following::a[contains(text(),'Get Started')][1]");
    private By getStartedLinkedList   = By.xpath("//h5[contains(text(),'Linked List')]/following::a[contains(text(),'Get Started')][1]");
    private By getStartedStack        = By.xpath("//h5[contains(text(),'Stack')]/following::a[contains(text(),'Get Started')][1]");
    private By getStartedQueue        = By.xpath("//h5[contains(text(),'Queue')]/following::a[contains(text(),'Get Started')][1]");
    private By getStartedTree         = By.xpath("//h5[contains(text(),'Tree')]/following::a[contains(text(),'Get Started')][1]");
    private By getStartedGraph        = By.xpath("//h5[contains(text(),'Graph')]/following::a[contains(text(),'Get Started')][1]");

    // ─── Constructor ──────────────────────────────────────────────

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // ─── Navigation ───────────────────────────────────────────────

    public void navigateTo(String url) {
        driver.get(url);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // ─── Not Logged In Message ────────────────────────────────────
    // IMPORTANT: This message appears AFTER clicking Get Started, not on page load.
    // The test must click Get Started first, then verify the message.

    public boolean isNotLoggedInMessageVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(notLoggedInMessage));
        return driver.findElement(notLoggedInMessage).isDisplayed();
    }

    public String getNotLoggedInMessageText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(notLoggedInMessage));
        return driver.findElement(notLoggedInMessage).getText().trim();
    }

    // ─── Navbar — Brand Logo ──────────────────────────────────────

    public void clickBrandLogo() {
        driver.findElement(brandLogo).click();
    }

    public String getBrandLogoText() {
        return driver.findElement(brandLogo).getText().trim();
    }

    public boolean isBrandLogoVisible() {
        return driver.findElement(brandLogo).isDisplayed();
    }

    // ─── Navbar — Data Structures Dropdown ───────────────────────

    public void clickDataStructuresToggle() {
        wait.until(ExpectedConditions.elementToBeClickable(dataStructuresToggle));
        driver.findElement(dataStructuresToggle).click();
    }

    public boolean isDataStructuresToggleVisible() {
        return driver.findElement(dataStructuresToggle).isDisplayed();
    }

    // ─── Navbar — Register & Sign In ─────────────────────────────

    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    public boolean isRegisterLinkVisible() {
        return driver.findElement(registerLink).isDisplayed();
    }

    public String getRegisterLinkHref() {
        return driver.findElement(registerLink).getAttribute("href");
    }

    public void clickSignInLink() {
        driver.findElement(signInLink).click();
    }

    public boolean isSignInLinkVisible() {
        return driver.findElement(signInLink).isDisplayed();
    }

    public String getSignInLinkHref() {
        return driver.findElement(signInLink).getAttribute("href");
    }

    // ─── Dropdown Child Item Actions ──────────────────────────────

    public void clickDropdownArrays() {
        wait.until(ExpectedConditions.elementToBeClickable(ddArrays));
        driver.findElement(ddArrays).click();
    }

    public void clickDropdownLinkedList() {
        wait.until(ExpectedConditions.elementToBeClickable(ddLinkedList));
        driver.findElement(ddLinkedList).click();
    }

    public void clickDropdownStack() {
        wait.until(ExpectedConditions.elementToBeClickable(ddStack));
        driver.findElement(ddStack).click();
    }

    public void clickDropdownQueue() {
        wait.until(ExpectedConditions.elementToBeClickable(ddQueue));
        driver.findElement(ddQueue).click();
    }

    public void clickDropdownTree() {
        wait.until(ExpectedConditions.elementToBeClickable(ddTree));
        driver.findElement(ddTree).click();
    }

    public void clickDropdownGraph() {
        wait.until(ExpectedConditions.elementToBeClickable(ddGraph));
        driver.findElement(ddGraph).click();
    }

    // ─── Topic Card Visibility ────────────────────────────────────

    public boolean isIntroductionCardVisible() {
        return driver.findElement(headingIntroduction).isDisplayed();
    }

    public boolean isArrayCardVisible() {
        return driver.findElement(headingArray).isDisplayed();
    }

    public boolean isLinkedListCardVisible() {
        return driver.findElement(headingLinkedList).isDisplayed();
    }

    public boolean isStackCardVisible() {
        return driver.findElement(headingStack).isDisplayed();
    }

    public boolean isQueueCardVisible() {
        return driver.findElement(headingQueue).isDisplayed();
    }

    public boolean isTreeCardVisible() {
        return driver.findElement(headingTree).isDisplayed();
    }

    public boolean isGraphCardVisible() {
        return driver.findElement(headingGraph).isDisplayed();
    }

    // ─── Get Started — Visibility ─────────────────────────────────

    public boolean isGetStartedIntroductionVisible() {
        return driver.findElement(getStartedIntroduction).isDisplayed();
    }

    public boolean isGetStartedArrayVisible() {
        return driver.findElement(getStartedArray).isDisplayed();
    }

    public boolean isGetStartedLinkedListVisible() {
        return driver.findElement(getStartedLinkedList).isDisplayed();
    }

    public boolean isGetStartedStackVisible() {
        return driver.findElement(getStartedStack).isDisplayed();
    }

    public boolean isGetStartedQueueVisible() {
        return driver.findElement(getStartedQueue).isDisplayed();
    }

    public boolean isGetStartedTreeVisible() {
        return driver.findElement(getStartedTree).isDisplayed();
    }

    public boolean isGetStartedGraphVisible() {
        return driver.findElement(getStartedGraph).isDisplayed();
    }

    // ─── Get Started — Clickable ──────────────────────────────────

    public boolean isGetStartedIntroductionClickable() {
        return driver.findElement(getStartedIntroduction).isEnabled();
    }

    public boolean isGetStartedArrayClickable() {
        return driver.findElement(getStartedArray).isEnabled();
    }

    // ─── Get Started — Text ───────────────────────────────────────

    public String getGetStartedIntroductionText() {
        return driver.findElement(getStartedIntroduction).getText().trim();
    }

    public String getGetStartedArrayText() {
        return driver.findElement(getStartedArray).getText().trim();
    }

    // ─── Get Started — Href ───────────────────────────────────────

    public String getGetStartedIntroductionHref() {
        return driver.findElement(getStartedIntroduction).getAttribute("href");
    }

    public String getGetStartedArrayHref() {
        return driver.findElement(getStartedArray).getAttribute("href");
    }

    public String getGetStartedLinkedListHref() {
        return driver.findElement(getStartedLinkedList).getAttribute("href");
    }

    public String getGetStartedStackHref() {
        return driver.findElement(getStartedStack).getAttribute("href");
    }

    public String getGetStartedQueueHref() {
        return driver.findElement(getStartedQueue).getAttribute("href");
    }

    public String getGetStartedTreeHref() {
        return driver.findElement(getStartedTree).getAttribute("href");
    }

    public String getGetStartedGraphHref() {
        return driver.findElement(getStartedGraph).getAttribute("href");
    }

    // ─── Get Started — Click ──────────────────────────────────────

    public void clickGetStartedIntroduction() {
        driver.findElement(getStartedIntroduction).click();
    }

    public void clickGetStartedArray() {
        driver.findElement(getStartedArray).click();
    }

    public void clickGetStartedLinkedList() {
        driver.findElement(getStartedLinkedList).click();
    }

    public void clickGetStartedStack() {
        driver.findElement(getStartedStack).click();
    }

    public void clickGetStartedQueue() {
        driver.findElement(getStartedQueue).click();
    }

    public void clickGetStartedTree() {
        driver.findElement(getStartedTree).click();
    }

    public void clickGetStartedGraph() {
        driver.findElement(getStartedGraph).click();
    }
}