package org.example.autoPractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
//import java.util.concurrent.TimeUnit;


public class AutopracticeTest {


static WebDriver driver;
static WebDriverWait wait;


    @BeforeAll
    static void SetDriver() {
WebDriverManager.chromedriver().setup();
    ChromeOptions options=new ChromeOptions();
            options.addArguments ("start-maximized"); // открытие полноэкранного формата
            options.addArguments ("--incognito");// открытие страницы в режиме инкогнито
            options.addArguments ("version"); //
            options.addArguments ("disable-popup-blocking"); //блокировка всплывающих окон
            options.setPageLoadTimeout(Duration.ofSeconds(10));//явное ожидание
           driver =new ChromeDriver(options);
    wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); //не явное ожидание появления элемента на странице


            }
       @BeforeEach
       void initMainPage () {


          Assertions.assertDoesNotThrow(()->driver.navigate().to("http://automationpractice.com/index.php"),"Страница не найдена"); //открыли страницу

       }

        @Test
        void testAuthentication() {
            .emailSend();


            driver.findElement(By.xpath("//div[@class='header_user_info']/a[@class='login']")).click();
            Actions actions = new Actions(driver);
            actions.sendKeys(driver.findElement(By.id("email")),"martiniz1901@gmail.com").sendKeys(driver.findElement(By.id("passwd")),"Test124Test");

            WebElement submitLogin = driver.findElement(By.id("SubmitLogin"));
            submitLogin.click();

            wait.until(ExpectedConditions.stalenessOf(submitLogin)); //ждем пока исчезнет элемент
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("my-account"))); //ждем пока загрузится элемент на странице аккаунта

            String title= driver.getTitle();
            Assertions.assertEquals("My account - My Store",title);

        }

        @AfterEach
    void exit() {
        driver.close();
           }

}
