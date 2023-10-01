package webdriver;
//C: Class
//m: Method
//I: Interface
//E: Enum
//R: Record
//A: Annotation
//f: biến final

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Command_02 {
    WebDriver driver;
/*    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");*/

    @BeforeClass
    public void beforeClass() {
        //Muốn dùng được thì phải khởi tạo
        //Nếu không khởi tạo thì sẽ bị lỗi: NullPointException
        driver = new FirefoxDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));//**

        // FirefoxDriver: firefox on windows (c34887c6-c0e1-4b92-a870-042924b2a871)


    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TC_01_Page_URL() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        // driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        // Sleep để đợi URL hiển thị rồi mới verify (Tốt nhất là dùng wait)
        sleepInSeconds(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_Page_Title() {
        driver.get("http://live.techpanda.org/");
//      driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        Assert.assertEquals(driver.getTitle(),"Customer Login");
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }
    @Test
    public void TC_03_Page_Navigation() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        // driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        sleepInSeconds(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }
    @Test
    public void TC_04_Page_Source() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}