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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_08_TextBox_TextArea_03 {
    WebDriver driver;
    String customerName = "AutomationFC", gender = "female", dateofBirth = "01011993", address = "123 abc" ,
            city = "New York", state = "North America", pin = "123456", mobileNumber = "0905123456", email = getEmailAddress(), password = "Abc123!@#";
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
    @Test
    public void TC_01_Login_Guru() {
        //Step 1: Access to page
        driver.get("https://demo.guru99.com/v4/");
        //Step 2: Login with username/password
        String userName = "mngr532021", passWord = "subysAj";
        driver.findElement(By.name("uid")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(passWord);
        driver.findElement(By.name("btnLogin")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),"Welcome To Manager's Page of Guru99 Bank");
        Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText(),"Manger Id : " + userName);
        //Step 3: Click New Customer link
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();
        //Step 4: Input valid data
        driver.findElement(By.name("name")).sendKeys(customerName);
        driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td/input[@value='f']")).click();
        //driver.findElement(By.name("dob")).click();
        sleepInSeconds(2);
        driver.findElement(By.id("dob")).sendKeys("11/11/2021");
        sleepInSeconds(2);
        driver.findElement(By.name("addr")).sendKeys(address);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("state")).sendKeys(state);
        driver.findElement(By.name("pinno")).sendKeys(pin);
        driver.findElement(By.name("telephoneno")).sendKeys(mobileNumber);
        driver.findElement(By.name("emailid")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        //driver.findElement(By.xpath("//input[@type='submit']")).click();










    }
    @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}