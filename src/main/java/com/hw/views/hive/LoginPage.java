package com.hw.views.hive;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by temp on 3/4/16.
 */
public class LoginPage {

    @FindBy(how = How.CSS, using="input.login-user-name")
    private WebElement inpUserName;
    @FindBy(how = How.CSS, using="input.login-user-password")
    private WebElement inpPassword;
    @FindBy(how = How.CSS, using="button.login-btn")
    private WebElement btnSignIn;

    public void loginAmbari(String userName, String password){
        inpUserName.sendKeys(userName);
        inpPassword.sendKeys(password);
        btnSignIn.click();
    }
/*
    public void loginAmbari(){
        inpUserName.sendKeys("admin");
        inpPassword.sendKeys("admin");
        btnSignIn.click();
    }
*/


}
