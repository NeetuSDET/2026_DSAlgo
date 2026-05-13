package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ─── By Locators ──────────────────────────────────────────────

    // Navbar
    // Live page: <a class="navbar-brand" href="/">NumpyNinja</a>
    private By brandLogo         = By.xpath("//a[@class='navbar-brand' and text()='NumpyNinja']");
    private By registerNavLink   = By.linkText("Register");
    private By signInNavLink     = By.linkText("Sign in");

    // Page Heading
    // Live page renders heading with text "Login" — checking h1 through h4
    private By pageHeading = By.xpath(
        "//*[self::h1 or self::h2 or self::h3 or self::h4]" +
        "[contains(text(),'Login') or contains(text(),'Sign')]"
    );

    // Form Fields
    // Django AuthenticationForm field IDs: id_username, id_password
    private By usernameField = By.id("id_username");
    private By passwordField = By.id("id_password");

    // Submit Button
    // <input type="submit" value="Login"> or <button type="submit">
    private By submitButton = By.xpath(
        "//input[@type='submit'] | //button[@type='submit']"
    );

    // Register link below the login form
    // Live page: "Register!" text → href="/register", outside navbar
    private By registerFormLink = By.xpath(
        "//a[contains(@href,'/register') and not(ancestor::nav)]"
    );

    // Error / Validation Messages
    // Django renders auth errors inside <ul class="errorlist"> or
    // non-field errors in a <ul class="nonfield errorlist">
    private By errorMessage = By.xpath(
        "//ul[contains(@class,'errorlist')] | " +
        "//*[contains(@class,'alert-danger')] | " +
        "//*[contains(@class,'invalid-feedback')]"
    );

    // ─── Constructor ──────────────────────────────────────────────

    public LoginPage(WebDriver driver) {
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

    public void clickBrandLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(brandLogo));
        driver.findElement(brandLogo).click();
    }

    public String getBrandLogoText() {
        return driver.findElement(brandLogo).getText().trim();
    }

    public boolean isBrandLogoVisible() {
        return driver.findElement(brandLogo).isDisplayed();
    }

    // ─── Navbar — Register & Sign In Links ───────────────────────

    public boolean isRegisterNavLinkVisible() {
        return driver.findElement(registerNavLink).isDisplayed();
    }

    public boolean isSignInNavLinkVisible() {
        return driver.findElement(signInNavLink).isDisplayed();
    }

    public String getRegisterNavLinkHref() {
        return driver.findElement(registerNavLink).getAttribute("href");
    }

    public String getSignInNavLinkHref() {
        return driver.findElement(signInNavLink).getAttribute("href");
    }

    public void clickRegisterNavLink() {
        driver.findElement(registerNavLink).click();
    }

    public void clickSignInNavLink() {
        driver.findElement(signInNavLink).click();
    }

    // ─── Page Heading ─────────────────────────────────────────────

    public boolean isPageHeadingVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeading));
        return driver.findElement(pageHeading).isDisplayed();
    }

    public String getPageHeadingText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeading));
        return driver.findElement(pageHeading).getText().trim();
    }

    // ─── Form Fields — Visibility ─────────────────────────────────

    public boolean isUsernameFieldVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        return driver.findElement(usernameField).isDisplayed();
    }

    public boolean isPasswordFieldVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        return driver.findElement(passwordField).isDisplayed();
    }

    // ─── Form Fields — Input ──────────────────────────────────────

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    // ─── Submit Button ────────────────────────────────────────────

    public boolean isSubmitButtonVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
        return driver.findElement(submitButton).isDisplayed();
    }

    public boolean isSubmitButtonClickable() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        return driver.findElement(submitButton).isEnabled();
    }

    public String getSubmitButtonText() {
        // <input type="submit"> exposes label via "value" attribute
        String val = driver.findElement(submitButton).getAttribute("value");
        return (val != null && !val.isEmpty())
            ? val.trim()
            : driver.findElement(submitButton).getText().trim();
    }

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        driver.findElement(submitButton).click();
    }

    // ─── Register Link (below form) ───────────────────────────────

    public boolean isRegisterFormLinkVisible() {
        return driver.findElement(registerFormLink).isDisplayed();
    }

    public String getRegisterFormLinkHref() {
        return driver.findElement(registerFormLink).getAttribute("href");
    }

    public void clickRegisterFormLink() {
        driver.findElement(registerFormLink).click();
    }

    // ─── Error / Validation Message ───────────────────────────────

    public boolean isErrorMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return driver.findElement(errorMessage).isDisplayed();
    }

    public String getErrorMessageText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return driver.findElement(errorMessage).getText().trim();
    }
}