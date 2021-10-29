package com.teladoc.base;

import org.openqa.selenium.WebDriver;

public class BaseTest {

    public static WebDriver driver;
    protected static String applicationUrl = "http://www.way2automation.com/angularjs-protractor/webtables/";

    public static void setupBrowser(){
        driver = BrowsersSetup.getBrowserInstance();
    }
}
