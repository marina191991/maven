package org.example;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

/*Создание дополнительного адреса в аккаунте пользователя
*/

public class App
    {
    public static void main( String[] args )
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments ("start-maximized"); // открытие полноэкранного формата
        options.addArguments ("--incognito");// открытие страницы в режиме инкогнито
        options.addArguments ("version"); //версия браузера
        options.addArguments ("disable-popup-blocking"); //блокировка всплывающих окон

        WebDriver driver=new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver,8);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php"); //открыли страницу
       //аутентификация
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
        //ждем пока загрузится элемент на странице аккаунта. Проверяем тайтл страницы
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("my-account")));
                if (driver.getTitle().equals("My account - My Store")) {
            System.out.println("Page title Account:"+" '"+ driver.getTitle()+"' ");
        }
        else  { System.out.println("Page title Account changed:"+" '"+ driver.getTitle()+"' ");}

     //Добавление нового адреса
     driver.findElement(By.xpath("//a[@title='Addresses']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[@id='addresses']")));
        System.out.println("Page title Address :"+" '"+ driver.getTitle()+"' ");
        driver.findElement(By.xpath("//a[@title='Add an address']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='center_column']/ul/li/a")));

        // проверка формы Имя на наличие исходного текста в форме
        WebElement firstname= driver.findElement(By.xpath("//input[@id='firstname']"));
        assertEquals("Марина",firstname.getAttribute("value")); //сравниваем значение
        System.out.println("Значение совпадает: "+ "'"+ firstname.getAttribute("value")+"'");

        //проверка формы Фамилия на наличие исходного текста в форме
        WebElement lastname= driver.findElement(By.xpath("//input[@id='lastname']"));
        assertEquals("Зубкова",lastname.getAttribute("value")); //сравниваем значение
        System.out.println("Значение совпадает: "+ "'"+ lastname.getAttribute("value")+"'");
        //заполнение обязательных форм
        WebElement address1 =  driver.findElement(By.xpath("//input[@id='address1']"));
        address1.click();
        address1.sendKeys("Moscow");

        WebElement city = driver.findElement(By.xpath("//input[@id='city']"));
        city.click();
        city.sendKeys("Moscow");

        WebElement selectState = driver.findElement(By.xpath("//select[@id='id_state']"));
        Select sel = new Select(selectState);
        WebElement option = sel.getFirstSelectedOption(); //выбираем первый элемент в списке, что бы убедиться что у нас верное значение по умолчанию
        assertEquals("-",option.getText()); //сравниваем значение
        System.out.println("Значение по умолчанию в списке State: "+ "'"+ option.getText()+"'");
        selectState.click() ; //кликаем по списку
        sel.selectByVisibleText("Alabama");

        WebElement postcode =  driver.findElement(By.xpath("//input[@id='postcode']"));
        postcode.click();
        postcode.sendKeys("11145");

        WebElement selectCountry = driver.findElement(By.xpath("//select[@id='id_country']"));
        assertEquals("United States",selectCountry.getText()); //сравниваем значение
        System.out.println("Значение по умолчанию в списке Country: "+ "'"+ selectCountry.getText()+"'");

        WebElement phone =  driver.findElement(By.xpath("//input[@id='phone']"));
        phone.click();
        phone.sendKeys("79034864555");

        WebElement phoneMobile =  driver.findElement(By.xpath("//input[@id='phone_mobile']"));
        phoneMobile.click();
        phoneMobile.sendKeys("79034864555");

        WebElement alias =  driver.findElement(By.xpath("//input[@id='alias']"));
        alias.click();
        alias.clear();
        alias.sendKeys("My next address");

        //проверка 3-х форм на пустоту
        WebElement company = driver.findElement(By.xpath("//input[@id='company']"));
        if (company.getAttribute("value").isEmpty()) {
            System.out.println("Форма 'Компания' пустая" );
        } else System.out.println("Форма 'Компания' не пустая" + company.getAttribute("value"));

        WebElement address2 = driver.findElement(By.xpath("//input[@id='address2']"));
        if (address2.getAttribute("value").isEmpty()) {
            System.out.println("Форма 'Адрес' пустая" );
        } else System.out.println("Форма 'Адрес' не пустая" + address2.getAttribute("value"));

        WebElement other= driver.findElement(By.xpath("//input[@id='company']"));
        if (other.getAttribute("value").isEmpty()) {
            System.out.println("Форма 'Additional information' пустая" );
        } else System.out.println("Форма 'Additional information' не пустая" + other.getAttribute("value"));

       //отправка формы
        WebElement submitSentAddress = driver.findElement(By.xpath("//button[@id='submitAddress']"));
        submitSentAddress.click();
      //ждем загрузки новой страницы, находим форму с новым адресом.
       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3 [text()='My next address']")));
       System.out.println("Новый адрес создан");
       driver.quit();
    }
}
