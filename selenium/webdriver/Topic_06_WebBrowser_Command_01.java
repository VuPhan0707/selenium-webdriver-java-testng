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

public class Topic_06_WebBrowser_Command_01 {
    WebDriver driver;
/*    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");*/

    @BeforeClass
    public void beforeClass() {
        //Muốn dùng được thì phải khởi tạo
        //Nếu không khởi tạo thì sẽ bị lỗi: NullPointException
        driver = new FirefoxDriver();
        // driver = new ChromeDriver();
        System.out.println(driver.toString());

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));//**

        // FirefoxDriver: firefox on windows (c34887c6-c0e1-4b92-a870-042924b2a871)


    }

    @Test
    public void TC_01_Run_On_Chrome() throws MalformedURLException {
        driver.get("https://www.facebook.com/");//**

        // Nếu như có 1 tab/window thì tính năng tương tự quit
        // Nếu có nhiều tabs/windows thì đóng tab hiện tại (active)
        driver.close(); //*

        // Đóng browser không quan tâm bao nhiêu tab/window
        driver.quit(); //**
        // 2 hàm này sẽ bị ảnh hưởng bởi timeout của implicitwait
        // Nó sẽ đi tìm vs loại By nào trả về element nếu được tìm thấy
        // Trong trường hợp không tìm thấy: Fail tại step này - throw exception: NoSuchElement
        // Trả về 1 element - nếu như tìm thấy nhiều hơn 1 thì cũng chỉ lấy 1 (thao tác với cái đầu tiên)
        driver.findElement(By.id("email"));//**

        // Nó sẽ đi tìm với loại By nào và trả về 1 danh sách elements
        // Ko tìm thấy - k bị fail - trả về 1 List rỗng (0 element)
        driver.findElements(By.xpath("//input[@id='email']"));//**

        // Tại sao cần phải lấy dữ liệu ra để làm gì?
        // Dùng để lấy ra Url của màn hình / page hiện tại đang dùng
        // Homepage
        driver.getCurrentUrl();//*

        // Dùng để lấy ra page source HTML/CSS/JS của page hiện tại
        // Verify 1 cách tương đối
        driver.getPageSource().contains("");
        Assert.assertTrue(driver.getPageSource().contains(""));

        // Lấy ra title của page hiện tại
        driver.getTitle();

        // Lấy ra ID của tab/window
        driver.getWindowHandle();
        driver.getWindowHandles();

        // Cookies - Framework
        driver.manage().getCookies();

        // Get ra những log ở Dev Tool - Framework
        driver.manage().logs().get(LogType.BROWSER);

        // Apply cho việc tìm element
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Chờ cho page được load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Set trước khi dùng ới thư viện JavascriptExecutor
        // Inject 1 đoạn code JS vào trong Browser/ Element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));


        // Nếu chỉ dùng 1 lần thì không khai báo biến
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");

        // Selenium 4 mới có
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();

        // Chạy full màn hình
        driver.manage().window().fullscreen();
        driver.manage().window().maximize();
        driver.manage().window().minimize();

        // Test GUI
        // Test Responsive (Resolution)
        driver.manage().window().setSize(new Dimension(1366,768));

        driver.manage().window().getSize();

       // driver.manage().window().setPosition(new Point());
        // Điều hướng trang web
        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();

        // Thao tác với history của web page (back / forward)
        driver.navigate().to("");
        driver.navigate().to(new URL(""));

        // Alert/ Window (Tab) / Frame (iFrame
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Text");

        // Lấy ra ID của cửa sổ / tab hiện tại
        // Handle Window / Tab
        String homePageWindowID = driver.getWindowHandle();
        driver.switchTo().window(homePageWindowID);

        // Switch / handle frame (iframe)
        // Index / ID (name) / Element
        driver.switchTo().frame(0);
        driver.switchTo().frame("ann");
        driver.switchTo().frame(driver.findElement(By.id("")));

        // Switch về HTML chưa frame trước đó
        driver.switchTo().defaultContent();

        // Từ frame trong đi ra frame ngoài chưa nó
        driver.switchTo().parentFrame();








    }

    @Test
    public void TC_02_Run_On_Firefox() {

    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}