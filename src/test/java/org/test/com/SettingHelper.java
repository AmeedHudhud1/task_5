package org.test.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class SettingHelper extends LoginHelper {
    final String changePasswordLocator = "//*[text()='Change Password']";

    public void changePassword(String OldPassword, String NewPassword, String ConfirmPassword ,WebDriver driver)  {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(changePasswordLocator)));
        WebElement changePasswordButton = driver.findElement(By.xpath(changePasswordLocator));
        changePasswordButton.click();

        driver.findElement(By.cssSelector("[placeholder='old password']")).sendKeys(OldPassword);
        driver.findElement(By.cssSelector("[placeholder='new password']")).sendKeys(NewPassword);
        driver.findElement(By.cssSelector("[placeholder='confirm password']")).sendKeys(ConfirmPassword);
        driver.findElement(By.xpath("//*[text()='Save']")).click();
    }
    public void verifyTextExist(String text,WebDriver driver){
            String xpathExpression = "//*[text()='" + text + "']";
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
    }
}
