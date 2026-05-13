package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ─── By Locators ──────────────────────────────────────────────

    // Navbar
    private By brandLogo       = By.xpath("//a[@class='navbar-brand' and text()='NumpyNinja']");
    private By registerNavLink = By.linkText("Register");
    private By signInNavLink   = By.linkText("Sign in");

 // Form fields — Django renders: id_username, id_password1, id_password2
    private By usernameField        = By.id("id_username");
    private By passwordField        = By.id("id_password1");
    private By passwordConfirmField = By.id("id_password2");

    // Submit button — <input type="submit" value="Register">
    // NOTE: getText() returns empty on <input> — use getAttribute("value")
    private By registerSubmitButton = By.xpath("//input[@type='submit']");

    // ─── FIX (round 6): Simplified error banner locator ──────────
    // Previous compound | XPath was resolving unpredictably. The actual
    // banner on this app is a block-level element rendered below the form
    // whose text always starts with "password_mismatch" or contains
    // "already exists" or password complexity messages.
    // Using a single XPath that matches ANY visible non-empty block below
    // the login link — the first such element IS the banner.
    // Targeting by the known banner text prefix is the most reliable approach.
    private By errorBanner = By.xpath(
        "//*[starts-with(normalize-space(text()),'password_mismatch') " +
        "    or contains(normalize-space(text()),'already exists') " +
        "    or contains(normalize-space(text()),'This password is too short') " +
        "    or contains(normalize-space(text()),'too common') " +
        "    or contains(normalize-space(text()),'entirely numeric')" +
        "    or (contains(@class,'alert') and not(contains(@class,'alert-success')))]"
    );

    // Success banner — shown on /home after successful registration
    private By successBanner = By.xpath(
        "//*[contains(text(),'New Account Created')] " +
        "| //*[contains(@class,'alert-info') or contains(@class,'alert-success')]"
    );

    // ─── Constructor ──────────────────────────────────────────────

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ─── Navigation ───────────────────────────────────────────────

    public void navigateTo(String url) {
        driver.get(url);
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

    // ─── Navbar — Register & Sign In ─────────────────────────────

    public boolean isRegisterLinkVisible() {
        return driver.findElement(registerNavLink).isDisplayed();
    }

    public boolean isSignInLinkVisible() {
        return driver.findElement(signInNavLink).isDisplayed();
    }

    public void clickSignInLink() {
        driver.findElement(signInNavLink).click();
    }

    // ─── Form Fields — Visibility ─────────────────────────────────

    public boolean isUsernameFieldVisible() {
        return driver.findElement(usernameField).isDisplayed();
    }

    public boolean isPasswordFieldVisible() {
        return driver.findElement(passwordField).isDisplayed();
    }

    public boolean isPasswordConfirmFieldVisible() {
        return driver.findElement(passwordConfirmField).isDisplayed();
    }

    // ─── Form Fields — Type ───────────────────────────────────────

    public String getPasswordFieldType() {
        return driver.findElement(passwordField).getAttribute("type");
    }

    public String getPasswordConfirmFieldType() {
        return driver.findElement(passwordConfirmField).getAttribute("type");
    }

    // ─── Form Fields — Input ──────────────────────────────────────

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public String getUsernameFieldValue() {
        return driver.findElement(usernameField).getAttribute("value");
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public String getPasswordFieldValue() {
        return driver.findElement(passwordField).getAttribute("value");
    }

    // ─── FIX (round 6): JS sendKeys for password confirmation field ──
    // Tests #19 and #20 both showed "password_mismatch" even when both
    // password fields contained identical values in the feature. The
    // confirmation field (id_password2) was not retaining its value —
    // caused by a form re-render race after the previous test's error page.
    // Using JavascriptExecutor to set the value directly bypasses the
    // browser event queue and guarantees the field value is set before submit.

    public void enterPasswordConfirmation(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordConfirmField));
        WebElement confirmField = driver.findElement(passwordConfirmField);
        confirmField.clear();
        // Set value via JS to guarantee it sticks regardless of render state
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].value = arguments[1];", confirmField, password
        );
        // Fire the change event so Django's form validation picks up the value
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
            confirmField
        );
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            confirmField
        );
    }

    // ─── Submit Button ────────────────────────────────────────────

    public boolean isRegisterSubmitButtonVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerSubmitButton));
        return driver.findElement(registerSubmitButton).isDisplayed();
    }

    public boolean isRegisterSubmitButtonClickable() {
        wait.until(ExpectedConditions.elementToBeClickable(registerSubmitButton));
        return driver.findElement(registerSubmitButton).isEnabled();
    }

    public String getRegisterSubmitButtonText() {
        // <input type="submit"> exposes label via "value" attribute, not getText()
        return driver.findElement(registerSubmitButton).getAttribute("value").trim();
    }

    public void clickRegisterSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerSubmitButton));
        driver.findElement(registerSubmitButton).click();
    }

    // ─── Submit — JS bypass for empty form ───────────────────────

    public void submitFormViaJS() {
        WebElement form = driver.findElement(By.tagName("form"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].submit();", form);
    }

    // ─── Error Banner ─────────────────────────────────────────────

    public boolean isErrorBannerVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorBanner));
        return driver.findElement(errorBanner).isDisplayed();
    }

    public String getErrorBannerText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorBanner));
        return driver.findElement(errorBanner).getText().trim().toLowerCase();
    }

    // ─── Success Banner ───────────────────────────────────────────

    public boolean isSuccessBannerVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successBanner));
        return driver.findElement(successBanner).isDisplayed();
    }

    public String getSuccessBannerText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successBanner));
        return driver.findElement(successBanner).getText().trim();
    }
    /*
    // Page Heading
    // BUG FIX #3: Live page renders <h3>Registration</h3>, not <h1>Register</h1>
    private By pageHeading = By.xpath(
        "//*[self::h1 or self::h2 or self::h3 or self::h4]" +
        "[contains(text(),'Registration') or contains(text(),'Register')]"
    );

    // Form Fields
    // BUG FIX #4: Django UserCreationForm only has username, password1, password2
    // Email field does NOT exist on the live page — locator removed entirely
    private By usernameField        = By.id("id_username");
    private By passwordField        = By.id("id_password1");
    private By confirmPasswordField = By.id("id_password2");

    // Submit Button
    private By submitButton = By.xpath("//input[@type='submit'] | //button[@type='submit']");

    // Login link below the form
    // Live page: <a href="/login">Login</a> outside navbar
    private By signInFormLink = By.xpath(
        "//a[contains(@href,'/login') and not(ancestor::nav)]"
    );

    // Error / Validation Messages
    // BUG FIX #6: Django renders form errors as <ul class="errorlist">
    // Previous locator targeted Bootstrap .alert-danger which is NOT used here
    private By errorMessage = By.xpath(
        "//ul[contains(@class,'errorlist')] | " +
        "//*[contains(@class,'alert-danger')] | " +
        "//*[contains(@class,'alert') and contains(@class,'error')]"
    );

    // ─── Constructor ──────────────────────────────────────────────

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ─── Navigation ───────────────────────────────────────────────

    public void navigateTo(String url) { driver.get(url); }

    public String getPageTitle()   { return driver.getTitle(); }

    public String getCurrentUrl()  { return driver.getCurrentUrl(); }

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

    // ─── Navbar — Register & Sign In ─────────────────────────────

    public boolean isRegisterNavLinkVisible() {
        return driver.findElement(registerNavLink).isDisplayed();
    }

    public boolean isSignInNavLinkVisible() {
        return driver.findElement(signInNavLink).isDisplayed();
    }

    public String getSignInNavLinkHref() {
        return driver.findElement(signInNavLink).getAttribute("href");
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

    public boolean isConfirmPasswordFieldVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField));
        return driver.findElement(confirmPasswordField).isDisplayed();
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

    public void enterConfirmPassword(String confirmPassword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField));
        driver.findElement(confirmPasswordField).clear();
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
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
        String val = driver.findElement(submitButton).getAttribute("value");
        return (val != null && !val.isEmpty())
            ? val.trim()
            : driver.findElement(submitButton).getText().trim();
    }

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        driver.findElement(submitButton).click();
    }

    // ─── Login Link (below form) ──────────────────────────────────

    public boolean isSignInFormLinkVisible() {
        return driver.findElement(signInFormLink).isDisplayed();
    }

    public String getSignInFormLinkHref() {
        return driver.findElement(signInFormLink).getAttribute("href");
    }

    public void clickSignInFormLink() {
        driver.findElement(signInFormLink).click();
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
    */
}