package com.teladoc.pages;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class WebTablesPage {

    WebDriver driver;

    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//*[@class=\"smart-table-global-search-row\"]//input")
    WebElement searchTextField;

    @FindBy(xpath="//button[text()=' Add User']")
    WebElement addUserButton;

    @FindBy(xpath="//*[text()='Confirmation Dialog']//parent::div/parent::div//button[text()='OK']")
    WebElement DeleteConfirmationDialogOkButton;

    public void enterUserInfoInSearchField(String searchStr){

        try{
            WebDriverWait wait = new WebDriverWait(driver,60);
            wait.until(ExpectedConditions.elementToBeClickable(searchTextField));
            searchTextField.clear();
            searchTextField.sendKeys(searchStr);
        }catch(Exception e){
            Assert.assertTrue(e.getMessage(),false);
        }
    }

    public void clickAddUserButton(){

        try{
            WebDriverWait wait = new WebDriverWait(driver,60);
            wait.until(ExpectedConditions.elementToBeClickable(addUserButton));
            addUserButton.click();
        }catch(Exception e){
            Assert.assertTrue(e.getMessage(),false);
        }

    }

    public void clickDeleteConfirmationPageOKButton(){

        try{
            WebDriverWait wait = new WebDriverWait(driver,60);
            wait.until(ExpectedConditions.elementToBeClickable(DeleteConfirmationDialogOkButton));
            DeleteConfirmationDialogOkButton.click();
        }catch(Exception e){
            Assert.assertTrue(e.getMessage(),false);
        }

    }

    public HashMap<String,Object> getUserInformationFromWebTable(){

        List<WebElement> rows  = null;
        HashMap<String,Object> actualDataMap = new HashMap<>();

        try{

            rows  = driver.findElements(By.xpath("//*[contains(@class,\"smart-table\")]/tbody/tr"));
            if(rows != null && rows.size() !=0){
                for(int i=1;i<(rows.size()+1);i++){
                    HashMap<String,String> rowDatas = new HashMap<>();
                    String actualFirstName = driver.findElement(By.xpath("//*[contains(@class,\"smart-table\")]/tbody/tr["+i+"]/td[1]")).getText();
                    String actualLastName = driver.findElement(By.xpath("//*[contains(@class,\"smart-table\")]/tbody/tr["+i+"]/td[2]")).getText();
                    String actualUserName = driver.findElement(By.xpath("//*[contains(@class,\"smart-table\")]/tbody/tr["+i+"]/td[3]")).getText();
                    String actualCustomer = driver.findElement(By.xpath("//*[contains(@class,\"smart-table\")]/tbody/tr["+i+"]/td[5]")).getText();
                    String actualRole = driver.findElement(By.xpath("//*[contains(@class,\"smart-table\")]/tbody/tr["+i+"]/td[6]")).getText();
                    String actualEmail = driver.findElement(By.xpath("//*[contains(@class,\"smart-table\")]/tbody/tr["+i+"]/td[7]")).getText();
                    String actualCellPhone = driver.findElement(By.xpath("//*[contains(@class,\"smart-table\")]/tbody/tr["+i+"]/td[8]")).getText();

                    rowDatas.put("firstName",actualFirstName);
                    rowDatas.put("lastName",actualLastName);
                    rowDatas.put("userName",actualUserName);
                    rowDatas.put("customer",actualCustomer);
                    rowDatas.put("role",actualRole);
                    rowDatas.put("email",actualEmail);
                    rowDatas.put("cellPhone",actualCellPhone);
                    actualDataMap.put(String.valueOf(i),rowDatas);
                }
            }

        }catch(Exception e){
            Assert.assertTrue(e.getMessage(),false);
        }



        return actualDataMap;

    }

    public void clickDeleteUserButtonInTable(String userName){

        List<WebElement> rows  = null;
        WebElement deleteColumn = null;

        try{
            rows  = driver.findElements(By.xpath("//*[contains(@class,\"smart-table\")]/tbody/tr"));

            if(rows != null && rows.size() !=0){
                for(int i=1;i<(rows.size()+1);i++){
                    String actualUserName = driver.findElement(By.xpath("//*[contains(@class,\"smart-table\")]/tbody/tr["+i+"]/td[3]")).getText();

                    if(StringUtils.equals(userName.toLowerCase(Locale.ROOT).trim(),
                            actualUserName.toLowerCase(Locale.ROOT).trim())){
                        deleteColumn = driver.findElement(By.xpath("//*[contains(@class,\"smart-table\")]/tbody/tr["+i+"]/td[11]/button"));
                    }
                }
            }else{
                Assert.assertTrue("UserName:"+userName+" not in webtable",false);
            }
            deleteColumn.click();
            clickDeleteConfirmationPageOKButton();

        }catch(Exception e){
            Assert.assertTrue(e.getMessage(),false);
        }

    }



}
