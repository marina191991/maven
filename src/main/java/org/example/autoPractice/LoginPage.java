package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy (id = "email")
    private WebElement email;

    @FindBy (id = "passwd")
    private WebElement password;

    @FindBy (id = "SubmitLogin")
    private WebElement submitLogin;

    public LoginPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
            }
    public LoginPage emailSend() {
        email.click();
        email.sendKeys("martiniz1901@gmail.com");
        return this; }

    public LoginPage passwordSend() {
        password.click();
        password.sendKeys("Test124Test");
        return this; }



    public LoginPage submitLoginClick() {
        submitLogin.click();
        return this; }

    }
