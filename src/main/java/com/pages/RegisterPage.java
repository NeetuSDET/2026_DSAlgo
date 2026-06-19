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
    private By brandLogo    = By.xpath("//a[@class='navbar-brand' and text()='NumpyNinja']");
    private By registerLink = By.linkText("Register");
    private By signInLink   = By.linkText("Sign in");

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
        return driver.findElement(registerLink).isDisplayed();
    }

    public boolean isSignInLinkVisible() {
        return driver.findElement(signInLink).isDisplayed();
    }

    public void clickSignInLink() {
        driver.findElement(signInLink).click();
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
}
