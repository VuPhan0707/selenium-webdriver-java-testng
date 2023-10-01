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

public class Topic_07_WebElement_Command_02 {
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
    @Test
    public void TC_01_Check_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        // Nếu mong đợi 1 element hiển thị thì verify True
        // Nếu mong đợi 1 element không hiển thị thì verify False
        /*Assert.assertTrue(driver.findElement(By.cssSelector("input[id='mail']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[id='under_18']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed());*/

        Boolean emailDisplayed = driver.findElement(By.cssSelector("input[id='mail']")).isDisplayed();
        if(emailDisplayed){
            driver.findElement(By.cssSelector("input[id='mail']")).sendKeys("Automation Testing");
            System.out.println("Email is displayed");
        }
        else {
            System.out.println("Email is not displayed");
        }
        Boolean ageDisplayed = driver.findElement(By.cssSelector("input[id='under_18']")).isDisplayed();
        if(ageDisplayed){
            driver.findElement(By.cssSelector("input[id='under_18']")).click();
            System.out.println("Age is displayed");
        }
        else {
            System.out.println("Age is not displayed");
        }
        if(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()){
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Education is displayed");
        }
        else {
            System.out.println("Education is not displayed");
        }
        if(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()){
                   System.out.println("Element is displayed");
        }
        else {
            System.out.println("Element is not displayed");
        }
    }
    @Test
    public void TC_02_Check_Enable() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if(driver.findElement(By.cssSelector("input[id='mail']")).isEnabled()){
            System.out.println("Email is enabled");
        }
        else {
            System.out.println("Email is not disabled");
        }
        if(driver.findElement(By.cssSelector("input[id='under_18']")).isEnabled()){
            System.out.println("Age is displayed");
        }
        else {
            System.out.println("Age is not disabled");
        }
        if(driver.findElement(By.cssSelector("textarea#edu")).isEnabled()){
            System.out.println("Education is displayed");
        }
        else {
            System.out.println("Education is not disabled");
        }

    }
    @Test
    public void TC_03_Check_Selected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.cssSelector("input[id='under_18']")).click();
        driver.findElement(By.xpath("//input[@id='java']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input[id='under_18']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='java']")).isSelected());
        sleepInSeconds(3);
        driver.findElement(By.xpath("//input[@id='java']")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//input[@id='java']")).isSelected());
    }
    @Test
    public void TC_04_Register() {
        driver.get("https://login.mailchimp.com/signup/");
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("test@gmail.com");
        // Case 1: Nhập số
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("12345");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        // Case 2: Nhập chữ
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("abc");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
    }
    @Test
    public void TC_05_Register() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("input#email")).sendKeys("test@gmail.com");
        driver.findElement(By.id("new_password")).click();
        // driver.findElement(By.id("create-account-enabled")).click();

       //Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}