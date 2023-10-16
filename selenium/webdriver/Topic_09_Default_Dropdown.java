//C: Class
//m: Method7845120
//I: Interface
//E: Enum
//R: Record
//A: Annotation
//f: biến final

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_09_Default_Dropdown {
    WebDriver driver;
    String firstName = "Tin", lastName = "Ker", password = "Abc123!@#";
    String companyName = "", email = getEmailAddress();
    String day = "1", month = "May", year = "1980";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
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
        String emailAddress = "username" + rand.nextInt(99999) + "@gmail.com";
        return emailAddress;
    }
    @Test
    public void TC_01_Register() {
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);
        Select dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
        dayDropdown.selectByVisibleText(day);
        //new Select(driver.findElement(By.name("DateOffBirthDay"))).selectByVisibleText("1");

        // Verify dropdown này là Single (k phải là Multiple)
        Assert.assertFalse(dayDropdown.isMultiple());
        // Verify số lượng item trong Dropdown này là 32 item
        Assert.assertEquals(dayDropdown.getOptions().size(),32);
        Select monthDropdown = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        monthDropdown.selectByVisibleText(month);
        Assert.assertEquals(monthDropdown.getOptions().size(),13);
        Select yearDropdown = new Select(driver.findElement(By.name("DateOfBirthYear")));
        yearDropdown.selectByVisibleText(year);
        Assert.assertEquals(yearDropdown.getOptions().size(),112);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("button#register-button")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
        // driver.findElement(By.xpath("//a[text()='My account']")).click();
    }
    @Test
    public void TC_02_Login() {

    }

    @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}