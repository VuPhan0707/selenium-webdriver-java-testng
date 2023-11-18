package webdriver;
//C: Class
//m: Method
//I: Interface
//E: Enum
//R: Record
//A: Annotation
//f: biến final

import org.openqa.selenium.*;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Topic_13_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    By resultText = By.cssSelector("p#result");
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        // Chờ cho alert present
        // Nếu trong thời gian chờ mà xuất hiện thì tự switch vào
        // Nếu trong thời gian cờ không xuất hiện mới fail
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        sleepInSeconds(3);
        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        alert.accept();
        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked an alert successfully");
           }
    @Test
    public void TC_02_Confirm_Alert() {
    }
    @Test
    public void TC_03_Prompt_Alert() {

    }
    @Test
    public void TC_04_Authen_Alert() {
        // Thư viện Alert không sử dụng cho Authentication Alert được
        // Chrome DevTool Protocal (CDP)
        String username = "admin";
        String password = "admin";

        // Cách 1: truyền thẳng user/pass vào URL
        // Trick - ByPass
        //driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
        //Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

        // Cách 2: Từ page A thao tác lên 1 element nó sẽ qua page B (cần phải thao tác vs Authen Alert trước)

        driver.get("http://the-internet.herokuapp.com/");

        String authenLinkUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

        String[] authenLinkArray = authenLinkUrl.split("//");

        driver.get(authenLinkArray[0] + "//" + username + ":" + password + "@" + authenLinkArray[1]);
        Assert.assertTrue(driver.findElement(By.xpath(
                "//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

    }
    @Test
    public void TC_05_Authen_AutoIT() {

    }
    @Test
    public void TC_06_Authen_Seleninum_4x() {
        // Cách 3:
        // Thư viện Alert không sử dụng cho AUthentication Alert được
        // Chrome DevTool Protocol (CDP) - Chrome / Edge(Chromium)

        DevTools devTools = ((HasDevTools)driver).getDevTools();
        
    }
    @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}