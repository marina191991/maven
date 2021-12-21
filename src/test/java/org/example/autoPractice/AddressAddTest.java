package org.example.autoPractice;

import com.github.dockerjava.core.dockerfile.DockerfileStatement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddressAdd extends AbstractTest {


    @Test
    void addressAdd () {
        new MainPage(getWebDriver()).submitSignInClick();
        new LoginPage(getWebDriver()).emailSend("martiniz1901@gmail.com").passwordSend("Test124Test").submitLoginClick();
        new AccountPage(getWebDriver()).addressClick().addressAddClick();
        //String a = new AddressAddPage(getWebDriver()).firstnameNotEmpty();
        Assertions.assertEquals(new AddressAddPage(getWebDriver()).firstnameNotEmpty(),"Марина","Форма First name пустая");
        Assertions.assertEquals(new AddressAddPage(getWebDriver()).lastnameNotEmpty(),"Зубкова","Форма Last name пустая");

        new AddressAddPage(getWebDriver()).valueEnter("Moscow","Moscow","11145","79034864555","79034864555");

        new AddressAddPage(getWebDriver()).aliasEnter("My next address");
        new AddressAddPage(getWebDriver()).selectState("Alabama");
        Assertions.assertEquals(new AddressAddPage(getWebDriver()).selectCountryNotEmpty(),"United States", "Форма Country имеет неверное значение");
        Assertions.assertTrue(new AddressAddPage(getWebDriver()).companyIsEmpty().isEmpty(),"Форма Company должна быть пустой");
        Assertions.assertTrue(new AddressAddPage(getWebDriver()).addressSecondIsEmpty().isEmpty(),"Форма AddressSecond должна быть пустой");
        Assertions.assertTrue(new AddressAddPage(getWebDriver()).additionalInformationIsEmpty().isEmpty(),"Форма Additional Information должна быть пустой");


    }

}
   /* // проверка формы Имя на наличие исходного текста в форме
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
//***Дописать метод отдельно
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
//***Дописать метод отдельно
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
        driver.quit();*/