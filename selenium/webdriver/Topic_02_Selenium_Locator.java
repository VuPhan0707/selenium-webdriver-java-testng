package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        /*if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");*/
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_Url() {

    }

    @Test
    public void TC_02_Logo() {

    }

    @Test
    public void TC_03_Form() {

    }
    @Test
    public void TC_04_Form() {

    }
    @Test
    public void TC_05_Form() {

    }
    @Test
    public void TC_06_Form() {

    }
    @Test
    public void TC_07_Css() {
    //Css vs ID
        driver.findElement(By.cssSelector("input[id='FirstName']"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));

    //Css vs Class
        driver.findElement(By.cssSelector("div[class='page-title']"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));
    //Css vs Name
        driver.findElement(By.cssSelector("input[name='FirstName']"));
    //Css vs tagname
        driver.findElement(By.cssSelector("input"));
    //Css vs link
        driver.findElement(By.cssSelector("a[href='/search']"));
    //Css vs partial link
        driver.findElement(By.cssSelector("a[href*='search']"));
    }
    @Test
    public void TC_08_Xpath() {
        //Xpath vs ID
        driver.findElement(By.ByXPath("input[id='FirstName']"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));

        //Xpath vs Class
        driver.findElement(By.cssSelector("div[class='page-title']"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));
        //Xpath vs Name
        driver.findElement(By.cssSelector("input[name='FirstName']"));
        //Xpath vs tagname
        driver.findElement(By.cssSelector("input"));
        //Xpath vs link
        driver.findElement(By.cssSelector("a[href='/search']"));
        //Xpath vs partial link
        driver.findElement(By.cssSelector("a[href*='search']"));
    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}