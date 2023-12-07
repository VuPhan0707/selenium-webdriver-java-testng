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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_18_Window_Tab {
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
    public void switchToWindowByID(String windowID){
        Set<String> allWindowsID = driver.getWindowHandles();
        for(String id : allWindowsID)
        {
            if(!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }
    public void switchToWindowByTitle(String expectedTitle){
        // Lấy tất cả các ID của window/tab
        Set<String> allWindowsID = driver.getWindowHandles();
        for(String id : allWindowsID)
        {
        driver.switchTo().window(id);
        String currentTitle = driver.getTitle();
            if(currentTitle.equals(expectedTitle)) {
                break;
            }
        }
    }
    @Test
    public void TC_01_Basic_Form(){
       driver.get("https://automationfc.github.io/basic-form/index.html");

       String parentWindow = driver.getWindowHandle();
       driver.findElement(By.xpath("//a[@href='https://google.com.vn']")).click();
       sleepInSeconds(3);
       Set<String> allWindows = driver.getWindowHandles();
       switchToWindowByID(parentWindow);
       System.out.println(driver.getTitle());
       driver.findElement(By.xpath("//textarea[@type='search']")).sendKeys("automationFC");
    }
    @Test
    public void TC_02_Many_Windows(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//a[@href='https://google.com.vn']")).click();
        sleepInSeconds(3);
        switchToWindowByTitle("Google");
        driver.findElement(By.xpath("//textarea[@type='search']")).sendKeys("automationFC");

        switchToWindowByTitle("Selenium WebDriver");
        driver.findElement(By.xpath("//a[@href='https://facebook.com']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Facebook – log in or sign up");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        switchToWindowByTitle("Selenium WebDriver");
    }
    @Test
    public void TC_02_Shopee() {


    }
    @Test
    public void TC_03_Hdfcbank() {

    }

        @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}