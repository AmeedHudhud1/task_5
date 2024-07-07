package org.test.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DiscoverJobsHelper extends LoginHelper {

    public void clickButton(String locator, WebDriver driver, boolean xpath) throws InterruptedException {
        Thread.sleep(1000);
        if(xpath){
            String buttonLocator = "//*[text()='" + locator + "']";
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buttonLocator)));
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(buttonLocator)));
            driver.switchTo().activeElement();
            driver.findElement(By.xpath(buttonLocator)).click();
        }else{
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("["+locator+"]"))).click();
        }
    }

    public void enterFieldValue(String locator, String text, WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator))).sendKeys(text);
    }

    public void selectFromMenu(String locator, String data,WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        clickButton(locator,driver,false);
        clickButton(data,driver,true);
    }


    public void verifyJavaPresence(String[] information, WebDriver driver, boolean exist) throws InterruptedException {
        Thread.sleep(1000);
        if(exist){
            String pageSource = driver.getPageSource();
            for (String data : information) {
                if (!pageSource.contains(data)) {
                    Assert.fail(data);
                }
            }
        }else{
            String pageSource = driver.getPageSource();
            for (String data : information) {
                if (pageSource.contains(data)) {
                    Assert.fail(data);
                }
            }
        }

    }

    public void verifyExperience(String range) throws InterruptedException {
//        Thread.sleep(100);
        String[] splitRange = range.split(" - ");
        String minString = splitRange[0];
        String maxString = splitRange[1];
        int min = Integer.parseInt(minString.replaceAll("\\D", ""));
        int max = Integer.parseInt(maxString.replaceAll("\\D", ""));
        if (min >= 1 && max <= 4) {
            Assert.fail("error");
        }
    }

}
