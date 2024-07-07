package org.test.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Setting extends SettingHelper {

    protected static WebDriver driver;

    @BeforeMethod
    public void visitSettingPage(){
        driver = new ChromeDriver();
        driver.get("https://talent500.co/auth/signin?redirect_to=jobs");
        login("hudhudameed@gmail.com","ameed0595",driver,true);
        driver.get("https://talent500.co/account-settings");
    }

    @Test(dataProvider = "getData",dataProviderClass= DataProvider.class)
    public void VerifyThatTheUserIsUnableToChangeThePasswordWithAOldMismatchedPassword(String email,String password,String confirm,String text) {
        changePassword(email,password,confirm,driver);
        verifyTextExist(text,driver);
    }
    @Test(dataProvider = "getData",dataProviderClass= DataProvider.class)
    public void VerifyThatTheUserIsUnableToChangeThePasswordWithANewMismatchedPassword(String email,String password,String confirm,String text) {
        changePassword(email,password,confirm,driver);
        verifyTextExist(text,driver);
    }
    @Test(dataProvider = "getData",dataProviderClass= DataProvider.class)
    public void VerifyThatTheUserIsUnableToChangeThePasswordToTheSameExistingPassword(String email,String password,String confirm,String text) {
        changePassword(email,password,confirm,driver);
        verifyTextExist(text,driver);
    }
    @Test(dataProvider = "getData",dataProviderClass= DataProvider.class)
    public void VerifyThatTheUserIsUnableToChangeThePasswordWithANewPasswordOfLessThan8Characters(String email,String password,String confirm,String text) {
        changePassword(email,password,confirm,driver);
        verifyTextExist(text,driver);
    }

    @AfterMethod
    public void quit(){
        driver.quit();
    }
}
