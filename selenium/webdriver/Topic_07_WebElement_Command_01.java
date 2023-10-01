package webdriver;
//C: Class
//m: Method
//I: Interface
//E: Enum
//R: Record
//A: Annotation
//f: biến final

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_07_WebElement_Command_01 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Element() {

        // HTML Element: Textbox/ TextArea/ Dropdown/ Checkbox/ Link/ Button/...
        // Tìm chưa tương tác lên
        driver.findElement(By.id(""));

        // Tìm và lưu vào 1 biến WebElement
        WebElement fullNameTextbox = driver.findElement(By.id(""));


    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}