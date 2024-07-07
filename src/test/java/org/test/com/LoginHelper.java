package org.test.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class LoginHelper {
    final String emailLocator  = "[placeholder=\"Email\"]";
    final String passwordLocator  = "[placeholder=\"Password\"]";
    final String loginButtonLocator = "[data-id=\"submit-login-btn\"]";

    public void login(String email, String password, WebDriver driver, Boolean validCred){
        WebElement emailField = driver.findElement(By.cssSelector(emailLocator));
        WebElement passwordField = driver.findElement(By.cssSelector(passwordLocator));
        WebElement loginButton = driver.findElement(By.cssSelector(loginButtonLocator));
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();

        if (validCred) {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Upload resume']")));
        } else {
            if (email.equals("") || password.equals("")) {
                Assert.assertTrue(Boolean.parseBoolean(passwordField.getAttribute("required")), "Please fill out this field");
            } else {
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Unable to login with the provided credentialsUnable to login with the provided credentials']")));
            }
        }
    }
}
