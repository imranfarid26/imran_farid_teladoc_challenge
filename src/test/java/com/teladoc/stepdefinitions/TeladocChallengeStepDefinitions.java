package com.teladoc.stepdefinitions;

import com.teladoc.base.BaseTest;
import com.teladoc.pages.AddUserPage;
import com.teladoc.pages.WebTablesPage;
import com.teladoc.utils.HelperUtility;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class TeladocChallengeStepDefinitions extends BaseTest {

    WebTablesPage webTablesPage;
    AddUserPage addUserPage;

    HashMap<String,String> expectedUserInformation = null;

    @Before
    public void setUp(){
        setupBrowser();
        webTablesPage = new WebTablesPage(driver);
        addUserPage = new AddUserPage(driver);
    }

    @Given("^User should open the Application$")
    public void user_should_open_the_Application() throws Throwable {
        driver.get(applicationUrl);
    }

    @When("^Click on AddUser Button$")
    public void click_on_AddUser_Button() throws Throwable {
        webTablesPage.clickAddUserButton();
    }


    @When("^Add User with information UserName:(.*) FirstName:(.*) LastName:(.*) Password:(.*) Customer:(.*) Role:(.*) CellPhone:(.*)")
    public void enter_user_information_to_add_new_user(String userName,String firstName,String lastName,
                                                       String password,String customer,String role,String cellphone) throws Throwable {
        //Build email id from first and last name
        String email = firstName+"."+lastName+"@gmail.com";
        userName = userName+HelperUtility.generateUUID();

        //Store input data for verification purpose
        expectedUserInformation = new HashMap<>();
        expectedUserInformation.put("firstName",firstName);
        expectedUserInformation.put("lastName",lastName);
        expectedUserInformation.put("userName",userName);
        expectedUserInformation.put("role",role);
        expectedUserInformation.put("customer",customer);
        expectedUserInformation.put("email",email);
        expectedUserInformation.put("cellPhone",cellphone);

        addUserPage.enterFirstNameTextField(firstName);
        addUserPage.enterLastNameTextField(lastName);
        addUserPage.enterUserNameTextField(userName);
        addUserPage.enterPasswordTextField(password);
        addUserPage.selectCustomerRadioButton(customer);
        addUserPage.selectRoleFromDropDown(role);
        addUserPage.enterEmailTextField(email);
        addUserPage.enterCellPhoneTextField(cellphone);

        addUserPage.clickSaveButton();
    }


    @Then("^Verify user is listed in table$")
    public void verify_user_is_listed_in_table() throws Throwable {
        //Get Unique UserName
        String userName =  expectedUserInformation.get("userName");
        String actualDataToPrint = "";

        //Variable to store the comparison result
        boolean comparsionResultFlag = false;

        webTablesPage.enterUserInfoInSearchField(userName);
        HashMap<String,Object> actualUserInformationFromTable = webTablesPage.getUserInformationFromWebTable();

        if(actualUserInformationFromTable != null && actualUserInformationFromTable.size() > 0){
            for (Map.Entry<String, Object> set :
                    actualUserInformationFromTable.entrySet()) {
                HashMap<String,String> actualData = (HashMap<String, String>) set.getValue();
                actualDataToPrint=actualDataToPrint+" , " +actualData.toString();
                comparsionResultFlag = expectedUserInformation.equals(actualData);

            }
        }else{
            Assert.assertTrue("User is not in the Table",false);
        }

        Assert.assertTrue("New User is in the table ExpectedData:"+expectedUserInformation+", ActualData:"+actualDataToPrint
                ,comparsionResultFlag);


    }

    @When("^Search UserName:(.*) in table$")
    public void search_UserName_novak_in_table(String userName) throws Throwable {
        webTablesPage.enterUserInfoInSearchField(userName);
    }

    @When("^Click on Delete Option in Table for UserName:(.*)$")
    public void click_on_Delete_Option_in_Table_for_UserName_novak(String userName) throws Throwable {
        webTablesPage.clickDeleteUserButtonInTable(userName);
    }

    @Then("^Verify user:(.*) is not listed in table$")
    public void verify_user_is_not_listed_in_table(String userName) throws Throwable {
        String actualDataToPrint = "";
        webTablesPage.enterUserInfoInSearchField(userName);
        HashMap<String,Object> actualUserInformationFromTable = webTablesPage.getUserInformationFromWebTable();

        if(actualUserInformationFromTable != null && actualUserInformationFromTable.size() == 0){
            Assert.assertTrue("Deleted User not in the Table. ActualData:"+ actualDataToPrint,true);
        }else {
            for (Map.Entry<String, Object> set :
                    actualUserInformationFromTable.entrySet()) {
                HashMap<String,String> actualData = (HashMap<String, String>) set.getValue();
                actualDataToPrint=actualDataToPrint+" , " +actualData.toString();
            }
            Assert.assertTrue("Deleted User in the Table. ActualData:"+actualDataToPrint,false);
        }
    }

}
