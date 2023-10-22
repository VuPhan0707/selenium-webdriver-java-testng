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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class Topic_08_TextBox_TextArea_03 {
    WebDriver driver;
    String customerName = "AutomationFC", gender = "female", dateofBirth = "01/01/1993", address = "123 abc" ,
            city = "New York", state = "North America", pin = "123456", mobileNumber = "0905123456", email = getEmailAddress(), password = "Abc123!@#";
    String editPhone = "0905654321";
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
    public void javascriptExecutorRemoveAttribute(WebElement element, String attributeName) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute(arguments[1])", element, attributeName);
    }
    @Test
    public void TC_01_Login_Guru() throws ParseException {
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
        javascriptExecutorRemoveAttribute(driver.findElement(By.id("dob")),"type");
        driver.findElement(By.id("dob")).sendKeys(dateofBirth);
        driver.findElement(By.name("addr")).sendKeys(address);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("state")).sendKeys(state);
        driver.findElement(By.name("pinno")).sendKeys(pin);
        driver.findElement(By.name("telephoneno")).sendKeys(mobileNumber);
        driver.findElement(By.name("emailid")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        sleepInSeconds(2);

        //Step 5: Get Customer Id
        String customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
        System.out.println(customerID);
        //Step 6: Verify input data
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),customerName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),gender);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
        Date data = sdf.parse(dateofBirth);
        String newDate = output.format(data);
        System.out.println(newDate);

        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),newDate);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),mobileNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),email);

        //Step 7: Click Edit Customer
        driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
        sleepInSeconds(2);

        //Step 8: Input CustomerId and Submit
        driver.findElement(By.name("cusid")).sendKeys(customerID);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        sleepInSeconds(2);

        //Step 9: Verify customer data
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td/input")).getAttribute("value"),customerName);
        Assert.assertEquals(driver.findElement(By.name("addr")).getText(),address);

        //Step 10: Edit data
        driver.findElement(By.name("addr")).clear();
        driver.findElement(By.name("addr")).sendKeys("Edit " + address);

        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("Edit " + city);

        driver.findElement(By.name("state")).clear();
        driver.findElement(By.name("state")).sendKeys("Edit " + state);
        driver.findElement(By.name("pinno")).clear();
        driver.findElement(By.name("pinno")).sendKeys(editPhone);
        driver.findElement(By.name("telephoneno")).clear();
        driver.findElement(By.name("telephoneno")).sendKeys(editPhone);


        driver.findElement(By.name("emailid")).clear();
        String editEmail = getEmailAddress();
        driver.findElement(By.name("emailid")).sendKeys(editEmail);
        driver.findElement(By.xpath("//input[@type='submit']")).click();


    }
    @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}