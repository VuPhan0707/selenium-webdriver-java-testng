package webdriver;
//C: Class
//m: Method
//I: Interface
//E: Enum
//R: Record
//A: Annotation
//f: biến final

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;
import java.util.Random;

public class Topic_11_Button_Radio_Checkbox {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String getEmailAddress() {
        Random rand = new Random();
        String emailAddress = "email" + rand.nextInt(99999) + "@gmail.com";
        return emailAddress;
    }
    public void javascriptExecutorRemoveAttribute(WebElement element, String attributeName) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute(arguments[1])", element, attributeName);
    }
    @Test
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");
        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));
        //Verify button bị disabled khi chưa click vào checkbox
        Assert.assertFalse(registerButton.isEnabled());

        driver.findElement(By.cssSelector("input#chinhSach")).click();
        sleepInSeconds(2);

        //Verify button enabled khi đã click vào checkbox
        Assert.assertTrue(registerButton.isEnabled());

        // Lấy mã màu nền của button
        String registerBackgroundRGB = registerButton.getCssValue("background-color");
        System.out.println("Background color RGB = " + registerBackgroundRGB);

        //Convert từ kiểu String (Mã RGB) qua kiểu Color
        Color registerBackgroundColour = Color.fromString(registerBackgroundRGB);
        System.out.println(registerBackgroundColour);
        //Convert qua kiểu Hexa
        String registerBackgroundHexa = registerBackgroundColour.asHex();
        System.out.println("Background color Hexa = " + registerBackgroundHexa);

        Assert.assertEquals(registerBackgroundHexa.toLowerCase(),"#ef5a00");
    }
    @Test
    public void TC_02_Fahasa_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");

        //Chuyển qua tab Đăng Nhập
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        sleepInSeconds(2);
        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}