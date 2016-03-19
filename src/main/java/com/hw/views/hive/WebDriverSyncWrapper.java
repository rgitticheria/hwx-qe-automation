package com.hw.views.hive;

import org.openqa.selenium.WebDriver;

/**
 * Created by temp on 3/9/16.
 */
public class WebDriverSyncWrapper {
    private WebDriver selenium;
    public WebDriverSyncWrapper(WebDriver driver){
        this.selenium = driver;
    }
    public static WebDriver obtainActualDriver(WebDriver argument){
        if(argument instanceof WebDriverSyncWrapper){
            return ((WebDriverSyncWrapper) argument).getActualDriver();
        }else
            return argument;
    }
    public WebDriver getActualDriver(){
        return selenium;
    }
}
