package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DSTopicsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ─── By Locators ──────────────────────────────────────────────

    // Navbar
    // When logged in, brand logo on /home points to /home
    private By brandLogo            = By.xpath("//a[@class='navbar-brand' and text()='NumpyNinja']");
    private By dataStructuresToggle = By.xpath("//a[contains(@class,'nav-link') and contains(@class,'dropdown-toggle')]");

    // Navbar Dropdown — child topic links (visible only after toggle click)
    private By ddArrays      = By.xpath("//div[contains(@class,'dropdown-menu')]//a[@href='/array']");
    private By ddLinkedList  = By.xpath("//div[contains(@class,'dropdown-menu')]//a[@href='/linked-list']");
    private By ddStack       = By.xpath("//div[contains(@class,'dropdown-menu')]//a[@href='/stack']");
    private By ddQueue       = By.xpath("//div[contains(@class,'dropdown-menu')]//a[@href='/queue']");
    private By ddTree        = By.xpath("//div[contains(@class,'dropdown-menu')]//a[@href='/tree']");
    private By ddGraph       = By.xpath("//div[contains(@class,'dropdown-menu')]//a[@href='/graph']");

    // Topic Card Headings — scoped by <h5> text
    private By cardIntroduction = By.xpath("//h5[contains(text(),'Data Structures-Introduction')]");
    private By cardArray        = By.xpath("//h5[contains(text(),'Array')]");
    private By cardLinkedList   = By.xpath("//h5[contains(text(),'Linked List')]");
    private By cardStack        = By.xpath("//h5[contains(text(),'Stack')]");
    private By cardQueue        = By.xpath("//h5[contains(text(),'Queue')]");
    private By cardTree         = By.xpath("//h5[contains(text(),'Tree')]");
    private By cardGraph        = By.xpath("//h5[contains(text(),'Graph')]");

    // Get Started Buttons — scoped per card via following:: axis
    // This prevents the wrong button being clicked when cards share text
    private By gsIntroduction = By.xpath("//h5[contains(text(),'Data Structures-Introduction')]/following::a[contains(text(),'Get Started')][1]");
    private By gsArray        = By.xpath("//h5[contains(text(),'Array')]/following::a[contains(text(),'Get Started')][1]");
    private By gsLinkedList   = By.xpath("//h5[contains(text(),'Linked List')]/following::a[contains(text(),'Get Started')][1]");
    private By gsStack        = By.xpath("//h5[contains(text(),'Stack')]/following::a[contains(text(),'Get Started')][1]");
    private By gsQueue        = By.xpath("//h5[contains(text(),'Queue')]/following::a[contains(text(),'Get Started')][1]");
    private By gsTree         = By.xpath("//h5[contains(text(),'Tree')]/following::a[contains(text(),'Get Started')][1]");
    private By gsGraph        = By.xpath("//h5[contains(text(),'Graph')]/following::a[contains(text(),'Get Started')][1]");

    // ─── Constructor ──────────────────────────────────────────────

    public DSTopicsPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    // ─── Navbar — Brand Logo ──────────────────────────────────────

    public boolean isBrandLogoVisible() {
        return driver.findElement(brandLogo).isDisplayed();
    }

    public String getBrandLogoText() {
        return driver.findElement(brandLogo).getText().trim();
    }

    public void clickBrandLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(brandLogo));
        driver.findElement(brandLogo).click();
    }

    // ─── Navbar — Data Structures Dropdown Toggle ─────────────────

    public boolean isDataStructuresToggleVisible() {
        return driver.findElement(dataStructuresToggle).isDisplayed();
    }

    public void clickDataStructuresToggle() {
        wait.until(ExpectedConditions.elementToBeClickable(dataStructuresToggle));
        driver.findElement(dataStructuresToggle).click();
    }

    // ─── Navbar — Dropdown Child Links Visibility ─────────────────

    public boolean isDropdownLinkVisible(String topic) {
        By locator = resolveDropdownLocator(topic);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).isDisplayed();
    }

    public void clickDropdownLink(String topic) {
        By locator = resolveDropdownLocator(topic);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    private By resolveDropdownLocator(String topic) {
        switch (topic) {
            case "Arrays":      return ddArrays;
            case "Linked List": return ddLinkedList;
            case "Stack":       return ddStack;
            case "Queue":       return ddQueue;
            case "Tree":        return ddTree;
            case "Graph":       return ddGraph;
            default: throw new IllegalArgumentException("Unknown dropdown topic: " + topic);
        }
    }

    // ─── Topic Card Visibility ────────────────────────────────────

    public boolean isTopicCardVisible(String topic) {
        By locator = resolveCardLocator(topic);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).isDisplayed();
    }

    private By resolveCardLocator(String topic) {
        switch (topic) {
            case "Data Structures-Introduction": return cardIntroduction;
            case "Array":                        return cardArray;
            case "Linked List":                  return cardLinkedList;
            case "Stack":                        return cardStack;
            case "Queue":                        return cardQueue;
            case "Tree":                         return cardTree;
            case "Graph":                        return cardGraph;
            default: throw new IllegalArgumentException("Unknown topic card: " + topic);
        }
    }

    // ─── Get Started — Visibility ─────────────────────────────────

    public boolean isGetStartedVisible(String topic) {
        By locator = resolveGetStartedLocator(topic);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).isDisplayed();
    }

    // ─── Get Started — Text ───────────────────────────────────────

    public String getGetStartedText(String topic) {
        By locator = resolveGetStartedLocator(topic);
        return driver.findElement(locator).getText().trim();
    }

    // ─── Get Started — Href ───────────────────────────────────────

    public String getGetStartedHref(String topic) {
        By locator = resolveGetStartedLocator(topic);
        return driver.findElement(locator).getAttribute("href");
    }

    // ─── Get Started — Click ──────────────────────────────────────

    public void clickGetStarted(String topic) {
        By locator = resolveGetStartedLocator(topic);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    // ─── Private Resolver — Get Started Locators ─────────────────

    private By resolveGetStartedLocator(String topic) {
        switch (topic) {
            case "Data Structures-Introduction": return gsIntroduction;
            case "Array":                        return gsArray;
            case "Linked List":                  return gsLinkedList;
            case "Stack":                        return gsStack;
            case "Queue":                        return gsQueue;
            case "Tree":                         return gsTree;
            case "Graph":                        return gsGraph;
            default: throw new IllegalArgumentException("Unknown Get Started topic: " + topic);
        }
    }
}