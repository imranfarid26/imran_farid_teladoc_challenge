package com.teladoc.pages;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddUserPage {

    WebDriver driver;

    public AddUserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name="FirstName")
    WebElement firstNameTextField;

    @FindBy(name="LastName")
    WebElement lastNameTextField;

    @FindBy(name="UserName")
    WebElement userNameTextField;

    @FindBy(name="Password")
    WebElement passwordTextField;

    @FindBy(xpath="//*[text()='Company AAA']/input")
    WebElement customerCompanyAAARadioButton;

    @FindBy(xpath="//*[text()='Company BBB']/input")
    WebElement customerCompanyBBBRadioButton;

    @FindBy(name="RoleId")
    WebElement roleDropDown;

    @FindBy(name="Email")
    WebElement emailTextField;

    @FindBy(name="Mobilephone")
    WebElement cellPhoneField;

    @FindBy(xpath="//button[text()='Save']")
    WebElement saveButton;

    @FindBy(xpath="///button[text()='Close']")
    WebElement closeButton;

    public void enterFirstNameTextField(String firstNameStr){

        try{
            WebDriverWait wait = new WebDriverWait(driver,60);
            wait.until(ExpectedConditions.elementToBeClickable(firstNameTextField));
            firstNameTextField.sendKeys(firstNameStr);
        }catch(Exception e){
            Assert.assertTrue(e.getMessage(),false);
        }

    }

    public void enterLastNameTextField(String lastNameStr){

        try{
            lastNameTextField.sendKeys(lastNameStr);
        }catch(Exception e){
            Assert.assertTrue(e.getMessage(),false);
        }

    }

    public void enterUserNameTextField(String userNameStr){

        try{
            userNameTextField.sendKeys(userNameStr);
        }catch(Exception e){
            Assert.assertTrue(e.getMessage(),false);
        }
    }

    public void enterPasswordTextField(String passwordStr){

        try{
            passwordTextField.sendKeys(passwordStr);
        }catch(Exception e){
            Assert.assertTrue(e.getMessage(),false);
        }

    }

    public void selectCustomerRadioButton(String radioButtonName){

        try{
            if(StringUtils.equals(radioButtonName,"Company AAA")){
                customerCompanyAAARadioButton.click();
            }else if (StringUtils.equals(radioButtonName,"Company BBB")){
                customerCompanyBBBRadioButton.click();
            }else{
                customerCompanyAAARadioButton.click();
            }
        }catch(Exception e){
            Assert.assertTrue(e.getMessage(),false);
        }

    }

    public void selectRoleFromDropDown(String roleName){
        String value = "0";

        try{
            if(StringUtils.equals(roleName,"Sales Team")){
                value = "0";
            }else if(StringUtils.equals(roleName,"Customer")){
                value = "1";
            }else if(StringUtils.equals(roleName,"Admin")){
                value = "2";
            }

            Select selectRoleDropDown = new Select(roleDropDown);
            selectRoleDropDown.selectByValue(value);
        }catch(Exception e){
            Assert.assertTrue(e.getMessage(),false);
        }

    }

    public void enterEmailTextField(String emailStr){

        try{
            emailTextField.sendKeys(emailStr);
        }catch(Exception e){
            Assert.assertTrue(e.getMessage(),false);
        }
    }

    public void enterCellPhoneTextField(String cellPhoneStr){

        try{
            cellPhoneField.sendKeys(cellPhoneStr);
        }catch(Exception e){
            Assert.assertTrue(e.getMessage(),false);
        }
    }

    public void clickSaveButton(){

        try{
            saveButton.click();
        }catch(Exception e){
            Assert.assertTrue(e.getMessage(),false);
        }
    }
}
