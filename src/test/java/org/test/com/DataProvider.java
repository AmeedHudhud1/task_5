package org.test.com;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

public class DataProvider {

     String[] skillData = {"java"};
     String[] companyData = {"FedEx"};
     String[] experienceData = {"0 - 1 years"};
     String[] locationData = {"Chennai"};
     String[] combinedData = {"java","FedEx","Hyderabad"};
     String[] remoteData = {"Remote"};
     String[] invalidCompany = {"testComapny"};
     String[] validCompany = {"Securonix"};
     String[] skillWithNoJob = {"noJob"};
     String[] locationWithNoJob = {"nablus"};

    JSONParser parser = new JSONParser();
    FileReader reader = new FileReader("src/test/java/org/test/com/data.JSON");
    Object obj = parser.parse(reader);
    JSONObject data = (JSONObject) obj;
    Locators x = new Gson().fromJson(String.valueOf(data), Locators.class);

    public DataProvider() throws IOException, ParseException {

    }


    @org.testng.annotations.DataProvider
    public Object[][] getData(Method method){
        switch (method.getName()) {
            case "logInByValidEmailAndPassword":
                return new Object[][] {
                        {"hudhudameed@gmail.com", "ameed0595", true},
                };
            case "logInByValidEmailAndInvalidPassword":
                return new Object[][] {
                        {"hudhudameed@gmail.com", "12345", false},
                };
            case "logInByInValidEmailAndvalidPassword":
                return new Object[][] {
                        {"hudhudameed0@gmail.com", "ameed0595", false},
                };
            case "LoginWithOnlyTheEmailFieldFilled":
                return new Object[][] {
                        {"hudhudameed0@gmail.com", "", false},
                };
            case "LoginWithOnlyThePasswordFieldFilled":
                return new Object[][] {
                        {"", "ameed0595", false},
                };
            case "LoginWithoutEnteringEmailAndPassword":
                return new Object[][] {
                        {"", "", false}
                };
            case "VerifyThatTheUserIsUnableToChangeThePasswordWithAOldMismatchedPassword":
                return new Object[][] {
                        {"ameed059", "123456789", "123456789", "The password you entered is incorrect. Please try again."}
                };
            case "VerifyThatTheUserIsUnableToChangeThePasswordWithANewMismatchedPassword":
                return new Object[][] {
                        {"ameed0595", "123456789", "12345678", "Password Mismatch"}
                };
            case "VerifyThatTheUserIsUnableToChangeThePasswordToTheSameExistingPassword":
                return new Object[][] {
                        {"ameed0595", "ameed0595", "ameed0595", "Old and new passwords cannot be the same"}
                };
            case "VerifyThatTheUserIsUnableToChangeThePasswordWithANewPasswordOfLessThan8Characters":
                return new Object[][] {
                        {"ameed0595", "12345", "12345", "Ensure this field has at least 8 characters."}
                };
            case "FilterJobsBySelectingASpecificSkill":
                return new Object[][] {
                        {x.getInputFieldSkills(),x.getSearchButton(),x.getFirstJob(),skillData}
                };
            case "FilterJobsBySelectingACompanyName":
                return new Object[][] {
                        {x.getCompaniesMenu(),x.getFirstJob(),companyData}
                };
            case "FilterJobsBySpecifyingARangeOfExperience":
                return new Object[][] {
                        {x.getExperienceRangeMenu(),x.getFirstJob(),experienceData[0]}
                };
            case "FilterJobsBySelectingAJobLocation":
                return new Object[][] {
                        {x.getInputFieldLocation(),x.getSearchButton(),x.getFirstJob(),locationData}
                };
            case "VerifyFilteredJobsBySelectingSkillAndCompanyAndNameAndExperienceRangeAndJobLocation":
                return new Object[][] {
                        {x.getInputFieldSkills(),x.getCompaniesMenu(),x.getExperienceRangeMenu(),x.getInputFieldLocation(),x.getSearchButton(),x.getFirstJob(),combinedData,experienceData[0]}
                };
            case "FilterJobsByChoosingRemoteJobOption":
                return new Object[][] {
                        {x.getRemote(),x.getFirstJob(),remoteData}
                };
            case "FilterCompaniesByEnteringAValidCompanyName":
                return new Object[][] {
                        {x.getCompaniesMenu(),x.getInputFieldCompany(),companyData}
                };
            case "VerifyNoCompaniesAreReturnedWhenFilteringByAnInvalidCompanyName":
                return new Object[][] {
                        {x.getCompaniesMenu(),x.getInputFieldCompany(),invalidCompany}
                };
            case "FilterJobsWithACompanyThatHasNoJobs":
                return new Object[][] {
                        {x.getCompaniesMenu(),validCompany,x.getNoJobMessage()}
                };
            case "FilterJobsWithASkillThatHasNoJobs":
                return new Object[][] {
                        {x.getInputFieldSkills(),x.getSearchButton(),skillWithNoJob,x.getNoJobMessage()}
                };
            case "FilterJobsWithAJobLocationThatHasNoJobs":
                return new Object[][] {
                        {x.getInputFieldLocation(),x.getSearchButton(),locationWithNoJob,x.getNoJobMessage()}
                };
            default:
                return null;
        }

    }
}
