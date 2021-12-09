package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

public class Authentication {

        public static void main( String[] args )
        {

            WebDriverManager.chromedriver().setup();
            ChromeOptions options=new ChromeOptions();
            options.addArguments ("start-maximized"); // открытие полноэкранного формата
            options.addArguments ("--incognito");// открытие страницы в режиме инкогнито
            options.addArguments ("version"); //
            options.addArguments ("disable-popup-blocking"); //блокировка всплывающих окон

            WebDriver driver=new ChromeDriver(options);
            WebDriverWait wait = new WebDriverWait(driver,8);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            driver.get("http://automationpractice.com/index.php"); //открыли страницу
            WebElement signIn = driver.findElement(By.xpath("//div[@class='header_user_info']/a[@class='login']"));
            signIn.click();
            WebElement emailF = driver.findElement(By.id("email"));
            emailF.click();
            emailF.sendKeys("martiniz1901@gmail.com");
            WebElement password = driver.findElement(By.id("passwd"));
            password.click();
            password.sendKeys("Test124Test");
            WebElement submitLogin = driver.findElement(By.id("SubmitLogin"));
            submitLogin.click();
            wait.until(ExpectedConditions.stalenessOf(submitLogin)); //ждем пока исчезнет элемент
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("my-account"))); //ждем пока загрузится элемент на странице аккаунта
            if (driver.getTitle().equals("My account - My Store")) {
                System.out.println("Title is :"+" "+ driver.getTitle());
            }
            else  { System.out.println("Title is renamed:"+" "+ driver.getTitle());

        } driver.quit();

} }
