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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_21_Upload_File {
    WebDriver driver;
    WebDriverWait explicitWait;
    Actions actions;
    JavascriptExecutor jsExecutor;
    String fullname;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TC_01_Upload_Single_File(){
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        //File này nằm ở đâu? => Đưa file vào folder project


    }
    @Test
    public void TC_02_Upload_Multiple_File(){


    }
            @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}