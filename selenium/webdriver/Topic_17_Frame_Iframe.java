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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_17_Frame_Iframe {
    WebDriver driver;
    WebDriverWait explicitWait;
    Actions actions;
    JavascriptExecutor javascriptExecutor;
    String fullname;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
    public void TC_01(){
        // Trang A: domain: formsite.com
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        sleepInSeconds(5);
        // Chứa iframe - trang B
        // Từ A -> B
        WebElement formIframe = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        Assert.assertTrue(formIframe.isDisplayed());

        //Switch vào frame/iframe trước khi thao tác với các element bên trong
        driver.switchTo().frame(formIframe);
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");

        //Switch ra lại trang A
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("nav.header--desktop--floater aa.menu-item-login")).click();


    }
    @Test
    public void TC_02_Shopee() {


    }
    @Test
    public void TC_03_Hdfcbank() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame("login_page");
        driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("automation");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSeconds(5);

        Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());
        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("abc");
    }
        @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}