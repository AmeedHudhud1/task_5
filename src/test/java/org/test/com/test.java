package org.test.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class test extends LoginHelper {

    protected WebDriver driver;

    @BeforeMethod
    public void visitLoginPage() {
        driver = new ChromeDriver();
        driver.get("https://talent500.co/auth/signin?redirect_to=jobs");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"hudhudameed@gmail.com", "ameed0595", true},
                {"hudhudameed@gmail.com", "12345", false},
                {"hudhudameed0@gmail.com", "ameed0595", false},
                {"hudhudameed0@gmail.com", "", false},
                {"", "ameed0595", false},
                {"", "", false}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String email, String password, boolean validCred) {
        login(email, password, driver, validCred);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
