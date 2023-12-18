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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Wait_1_ElementStatus {
    WebDriver driver;
    WebDriverWait explicitWait;
    By reConfirmEmail = By.cssSelector("input[name='reg_email_confirmation__']");
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get("https://www.facebook.com/");
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
    public void TC_01_Visible(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("automation@gmail.com");
        // Tại đúng thời điểm/step này thì Confirm Email Textbox đang visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reConfirmEmail));
        Assert.assertTrue(driver.findElement(reConfirmEmail).isDisplayed());
    }
    @Test
    public void TC_02_Invisible_In_DOM(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reConfirmEmail));
        Assert.assertFalse(driver.findElement(reConfirmEmail).isDisplayed());

    }
    @Test
    public void TC_03_Invisible_Not_In_DOM(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reConfirmEmail));

    }
    @Test
    public void TC_04_Presence(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("automation@gmail.com");
        sleepInSeconds(2);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reConfirmEmail));
        Assert.assertTrue(driver.findElement(reConfirmEmail).isDisplayed());

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(2);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reConfirmEmail));
        Assert.assertFalse(driver.findElement(reConfirmEmail).isDisplayed());
    }
    @Test
    public void TC_05_Staleness(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        WebElement reconfirmEmail = driver.findElement(reConfirmEmail);
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);
        explicitWait.until(ExpectedConditions.stalenessOf(reconfirmEmail));

    }
            @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}