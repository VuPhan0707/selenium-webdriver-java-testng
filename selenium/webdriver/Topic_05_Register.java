package webdriver;
//C: Class
//m: Method
//I: Interface
//E: Enum
//R: Record
//A: Annotation
//f: biến final

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_05_Register {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void Register_01_Empty_Data() {

        //Open browser
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
       // driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");

        //Clear all input textbox
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();

        //Click Register button
        driver.findElement(By.xpath("//button[@type='submit' and text()='ĐĂNG KÝ']")).click();

        //Get error messages and verify
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");

    }

    @Test
    public void Register_02_Invalid_Email() {
        //Open browser
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Input all textbox and invalid email/confirm email
        driver.findElement(By.id("txtFirstname")).sendKeys("VuPhan");
        driver.findElement(By.id("txtEmail")).sendKeys("123@456@789");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@456@789");
        driver.findElement(By.id("txtPassword")).sendKeys("12345678");
        driver.findElement(By.id("txtCPassword")).sendKeys("12345678");
        driver.findElement(By.id("txtPhone")).sendKeys("0905123456");

        //Click Register button
        driver.findElement(By.xpath("//button[@type='submit' and text()='ĐĂNG KÝ']")).click();

        //Get error messages and verify
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");

    }

    @Test
    public void Register_03_Incorrect_Confirm_Email() {
        //Open browser
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Input all textbox and invalid email/confirm email
        driver.findElement(By.id("txtFirstname")).sendKeys("VuPhan");
        driver.findElement(By.id("txtEmail")).sendKeys("123@456");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@456@789");
        driver.findElement(By.id("txtPassword")).sendKeys("12345678");
        driver.findElement(By.id("txtCPassword")).sendKeys("12345678");
        driver.findElement(By.id("txtPhone")).sendKeys("0905123456");

        //Click Register button
        driver.findElement(By.xpath("//button[@type='submit' and text()='ĐĂNG KÝ']")).click();

        //Get error messages and verify
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }
    @Test
    public void Register_04_Invalid_Password() {
        //Open browser
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Input all textbox and invalid email/confirm email
        driver.findElement(By.id("txtFirstname")).sendKeys("VuPhan");
        driver.findElement(By.id("txtEmail")).sendKeys("123@456");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@456");
        driver.findElement(By.id("txtPassword")).sendKeys("1234");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234");
        driver.findElement(By.id("txtPhone")).sendKeys("0905123456");

        //Click Register button
        driver.findElement(By.xpath("//button[@type='submit' and text()='ĐĂNG KÝ']")).click();

        //Get error messages and verify
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
    }
    @Test
    public void Register_05_Incorrect_Confirm_Password() {
        //Open browser
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Input all textbox and invalid email/confirm email
        driver.findElement(By.id("txtFirstname")).sendKeys("VuPhan");
        driver.findElement(By.id("txtEmail")).sendKeys("123@456");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@456");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtPhone")).sendKeys("0905123456");

        //Click Register button
        driver.findElement(By.xpath("//button[@type='submit' and text()='ĐĂNG KÝ']")).click();

        //Get error messages and verify
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
    }
    @Test
    public void Register_06_Invalid_Phone_Number() {
        //Open browser
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Input all textbox and invalid email/confirm email
        driver.findElement(By.id("txtFirstname")).sendKeys("VuPhan");
        driver.findElement(By.id("txtEmail")).sendKeys("123@456");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@456");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("090512345698");

        //Click Register button
        driver.findElement(By.xpath("//button[@type='submit' and text()='ĐĂNG KÝ']")).click();

        //Get error messages and verify
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        //Input invalid phone number again
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("123");

        //Get error messages and verify
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }

    @AfterClass
    public void afterClass() {

//        driver.quit();
    }
}