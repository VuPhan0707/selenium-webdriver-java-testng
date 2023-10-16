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

import java.time.Duration;
import java.util.Random;

public class Topic_08_TextBox_TextArea_02 {
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
    public String getEmailAddress() {
        Random rand = new Random();
        String emailAddress = "username" + rand.nextInt(99999) + "@gmail.com";
        return emailAddress;
    }
    @Test
    public void TC_01_Login_Success() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        sleepInSeconds(3);

        driver.findElement(By.xpath("//a[@class='oxd-main-menu-item']/span[text()='PIM']")).click();
        sleepInSeconds(3);
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        sleepInSeconds(5);
        String firstName = "Automation", lastName = "FC";
        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstName']"));
        firstNameElement.sendKeys(firstName);
        WebElement lastNameElement = driver.findElement(By.xpath("//input[@name='lastName']"));
        lastNameElement.sendKeys(lastName);
        /*WebElement idElement = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String employeeID = (String) js.executeScript("return arguments[0].value;", idElement);
        System.out.println(employeeID);*/
        String employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");
        System.out.println(employeeID);
        driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
        String userName = "user" + employeeID;
        String passWord = "Abc123!@#";
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(userName);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(passWord);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(passWord);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        sleepInSeconds(8);
        //String firstNameDetails = (String) js.executeScript("return arguments[0].value;", driver.findElement(By.xpath("//input[@name='firstName']")));
        String firstNameDetails = driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value");
        System.out.println(firstNameDetails);
        String lastNameDetails = driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value");
        System.out.println(lastNameDetails);
        String employeeIDDetails = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");
        System.out.println(employeeIDDetails);
        // System.out.println(firstNameDetails);
        //String lastNameDetails = (String) js.executeScript("return arguments[0].value;", driver.findElement(By.xpath("//input[@name='lastName']")));
       // System.out.println(lastNameDetails);
        //String employeeIDDetails = (String) js.executeScript("return arguments[0].value;", driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")));
        // System.out.println(employeeIDDetails);
        Assert.assertEquals(firstNameDetails,firstName);
        Assert.assertEquals(lastNameDetails,lastName);
        Assert.assertEquals(employeeIDDetails,employeeID);
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        sleepInSeconds(3);
        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/parent::div/button")).click();
        String number = "394399", comment = "This is a comment";
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(number);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comment);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.cssSelector(".bi-pencil-fill")).click();
        sleepInSeconds(5);
        //WebElement numberElement = driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input"));
        //WebElement commentElement = driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea"));
        //String numberDetails = (String) js.executeScript("return arguments[0].value;", numberElement);
        //String commentDetails = (String) js.executeScript("return arguments[0].value;", commentElement);
        String numberDetails = driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value");
        String commentDetails = driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value");
        Assert.assertEquals(numberDetails,number);
        Assert.assertEquals(commentDetails,comment);
        driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passWord);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        sleepInSeconds(3);
        driver.findElement(By.xpath("//a[@class='oxd-main-menu-item']/span[text()='My Info']")).click();
        sleepInSeconds(3);
        /*String firstNameDetails2 = (String) js.executeScript("return arguments[0].value;", driver.findElement(By.xpath("//input[@name='firstName']")));
        String lastNameDetails2 = (String) js.executeScript("return arguments[0].value;", driver.findElement(By.xpath("//input[@name='lastName']")));
        String employeeIDDetails2 = (String) js.executeScript("return arguments[0].value;", driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")));*/
        String firstNameDetails2 = driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value");
        String lastNameDetails2 = driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value");
        String employeeIDDetails2 = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");
        Assert.assertEquals(firstNameDetails2,firstNameDetails);
        Assert.assertEquals(lastNameDetails2,lastNameDetails);
        Assert.assertEquals(employeeIDDetails2,employeeIDDetails);
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        sleepInSeconds(3);
        driver.findElement(By.cssSelector(".bi-pencil-fill")).click();
        sleepInSeconds(3);
        /*String numberDetails2 = (String) js.executeScript("return arguments[0].value;", driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")));
        String commentDetails2 = (String) js.executeScript("return arguments[0].value;", driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")));*/
        String numberDetails2 = driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value");
        String commentDetails2 = driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value");
        Assert.assertEquals(numberDetails2,numberDetails);
        Assert.assertEquals(commentDetails2,commentDetails);

        // System.out.println(employeeID);

    }
    @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}