package org.example.autoPractice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage {
    @FindBy (xpath = "//a[@title='Addresses']")
    private WebElement myAddresses;
    
}
