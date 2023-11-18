package webdriver;
//C: Class
//m: Method
//I: Interface
//E: Enum
//R: Record
//A: Annotation
//f: biến final

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Topic_15_Popup {
    WebDriver driver;
    WebDriverWait explicitWait;
    Actions actions;
    JavascriptExecutor javascriptExecutor;
    String fullname;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        actions = new Actions(driver);

    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TC_1_Fixed_Popup_In_DOM_01(){
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_")).click();
        sleepInSeconds(2);
        By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#account-input")).sendKeys("AutomationFC");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#password-input")).sendKeys("AutomationFC");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel")).getText(),"Tài khoản không tồn tại!");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
        sleepInSeconds(2);
        //Kiểm tra popup không còn hiển thị
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
    }
    @Test
    public void TC_2_Fixed_Popup_In_DOM_02(){
        driver.get("https://skills.kynaenglish.vn/");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSeconds(2);
        By loginPopup = By.cssSelector("div#k-popup-account-login-mb div.modal-content");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("div#k-popup-account-login-mb input#user-login")).sendKeys("automationFC@gmail.com");
        driver.findElement(By.cssSelector("div#k-popup-account-login-mb input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
        driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
        sleepInSeconds(2);
        //Kiểm tra popup không còn hiển thị
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
    }
    @Test
    public void TC_3_Fixed_Popup_NOT_In_DOM_01(){
        driver.get("https://tiki.vn/");
        driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
        sleepInSeconds(2);
        By loginPopup = By.cssSelector("div.ReactModal__Content");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        sleepInSeconds(2);
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]")).getText(),"Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span")).getText(),"Mật khẩu không được để trống");
        //Đóng popup
        driver.findElement(By.cssSelector(("img.close-img"))).click();
        sleepInSeconds(2);
        //Khi cái popup đóng lại thì HTML không có trong DOM nữa
        //findElements and assert zero length response instead
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(loginPopup).size(),0);
    }
    @Test
    public void TC_4_Fixed_Popup_NOT_In_DOM_02(){
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        By signupPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
        Assert.assertTrue(driver.findElement(signupPopup).isDisplayed());
        sleepInSeconds(2);
        //Đóng popup
        driver.findElement(By.xpath(("//div[text()='Sign Up']/parent::div/preceding-sibling::img"))).click();
        sleepInSeconds(2);
        //Khi cái popup đóng lại thì HTML không có trong DOM nữa
        //findElements and assert zero length response instead
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(signupPopup).size(),0);
    }


    @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}