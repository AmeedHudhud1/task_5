package org.test.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DiscoverJobs extends DiscoverJobsHelper {

    protected static WebDriver driver;
    @BeforeMethod
    public void visitSettingPage(){
        driver = new ChromeDriver();
        driver.get("https://talent500.co/auth/signin?redirect_to=jobs");
        login("hudhudameed@gmail.com","ameed0595",driver,true);
        driver.get("https://talent500.co/jobs");
        driver.manage().window().maximize();

    }
    @Test(dataProvider = "getData",dataProviderClass= DataProvider.class)
    public void FilterJobsBySelectingASpecificSkill(String skillsFieldLocator, String searchButtonLocator, String firstJobLocator,  String[] data) throws InterruptedException {
        enterFieldValue(skillsFieldLocator,data[0],driver);
        clickButton(searchButtonLocator,driver,true);
        clickButton(firstJobLocator,driver,false);
        verifyJavaPresence(data,driver,true);
    }
    @Test(dataProvider = "getData",dataProviderClass= DataProvider.class)
    public void FilterJobsBySelectingACompanyName(String companiesMenuLocator, String firstJobLocator, String[] data) throws InterruptedException {
        selectFromMenu(companiesMenuLocator,data[0],driver);
        clickButton(firstJobLocator,driver,false);
        verifyJavaPresence(data,driver,true);
    }
    @Test(dataProvider = "getData",dataProviderClass= DataProvider.class)
    public void FilterJobsBySpecifyingARangeOfExperience(String experienceRangeMenuLocator, String firstJobLocator, String range) throws InterruptedException {
        selectFromMenu(experienceRangeMenuLocator,range,driver);
        clickButton(firstJobLocator,driver,false);
        verifyExperience(range);
    }
    @Test(dataProvider = "getData",dataProviderClass= DataProvider.class)
    public void FilterJobsBySelectingAJobLocation(String locationFieldLocator, String searchButtonLocator, String firstJobLocator, String[] data) throws InterruptedException {
        enterFieldValue(locationFieldLocator,data[0],driver);
        clickButton(searchButtonLocator,driver,true);
        clickButton(firstJobLocator,driver,false);
        verifyJavaPresence(data,driver,true);
    }
    @Test(dataProvider = "getData",dataProviderClass= DataProvider.class)
    public void VerifyFilteredJobsBySelectingSkillAndCompanyAndNameAndExperienceRangeAndJobLocation(String skillsField, String companyMenu, String experienceRangeMenu, String location, String searchButton, String firstJob, String[] data, String range) throws InterruptedException {
        enterFieldValue(skillsField,data[0],driver);
        selectFromMenu(companyMenu,data[1],driver);
        selectFromMenu(experienceRangeMenu,range,driver);
        enterFieldValue(location,data[2],driver);
        clickButton(searchButton,driver,true);
        clickButton(firstJob,driver,false);
        verifyJavaPresence(data,driver,true);
        verifyExperience(range);
    }
    @Test(dataProvider = "getData",dataProviderClass= DataProvider.class)
    public void FilterJobsByChoosingRemoteJobOption(String remoteButton, String firstJob, String[] data) throws InterruptedException {
        clickButton(remoteButton,driver,true);
        clickButton(firstJob,driver,false);
        verifyJavaPresence(data,driver,true);
    }
    @Test(dataProvider = "getData",dataProviderClass= DataProvider.class)
    public void FilterCompaniesByEnteringAValidCompanyName(String companiesMenuLocator, String companyNameFieldLocator, String[] data) throws InterruptedException {
        clickButton(companiesMenuLocator,driver,false);
        enterFieldValue(companyNameFieldLocator,data[0],driver);
        verifyJavaPresence(data,driver,true);
    }
    @Test(dataProvider = "getData",dataProviderClass= DataProvider.class)
    public void VerifyNoCompaniesAreReturnedWhenFilteringByAnInvalidCompanyName(String companiesMenuLocator, String companyNameFieldLocator, String[] data) throws InterruptedException {
        clickButton(companiesMenuLocator,driver,false);
        enterFieldValue(companyNameFieldLocator,data[0],driver);
        verifyJavaPresence(data,driver,false);
    }
    @Test(dataProvider = "getData",dataProviderClass= DataProvider.class)
    public void FilterJobsWithACompanyThatHasNoJobs(String companiesMenuLocator, String[] data, String[] message) throws InterruptedException {
    selectFromMenu(companiesMenuLocator,data[0],driver);
    verifyJavaPresence(message,driver,true);
}
    @Test(dataProvider = "getData",dataProviderClass= DataProvider.class)
    public void FilterJobsWithASkillThatHasNoJobs(String skillsFieldLocator, String searchButtonLocator, String[] data, String[] message) throws InterruptedException {
        enterFieldValue(skillsFieldLocator,data[0],driver);
        clickButton(searchButtonLocator,driver,true);
        verifyJavaPresence(message,driver,true);
    }
    @Test(dataProvider = "getData",dataProviderClass= DataProvider.class)
    public void FilterJobsWithAJobLocationThatHasNoJobs(String locationFieldLocator, String searchButtonLocator, String[] data, String[] message) throws InterruptedException {
        enterFieldValue(locationFieldLocator,data[0],driver);
        clickButton(searchButtonLocator,driver,true);
        verifyJavaPresence(message,driver,true);
    }
    @AfterMethod
    public void quit(){
        driver.quit();
    }
}
