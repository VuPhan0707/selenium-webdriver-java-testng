package webdriver;
//C: Class
//m: Method
//I: Interface
//E: Enum
//R: Record
//A: Annotation
//f: biến final

import org.openqa.selenium.*;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Topic_14_Action {
    WebDriver driver;
    WebDriverWait explicitWait;
    Actions actions;
    JavascriptExecutor javascriptExecutor;
    String fullname;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        actions = new Actions(driver);

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
    public void TC_01_Hover_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip div")).getText(),"We ask for your age only for statistical purposes.");
    }
    @Test
    public void TC_02_Hover_Myntra() {
        driver.get("http://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//a[text()='Kids' and @class='desktop-main']"))).perform();
        sleepInSeconds(2);
        actions.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.myntra.com/kids-home-bath");
    }
    @Test
    public void TC_03_Hover_Fahasa() {
        driver.get("https://www.fahasa.com/");
        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        sleepInSeconds(2);
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='Bách Hóa Online - Lưu Niệm']"))).perform();
        sleepInSeconds(3);
        driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Thiết Bị Số - Phụ Kiện Số']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(),"THIẾT BỊ SỐ - PHỤ KIỆN SỐ");
    }
    @Test
    public void TC_04_ClickandHold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(),20);
        //Chọn theo block
        actions.clickAndHold(allNumbers.get(0)) // Click lên số 1 và giữ chuột
                .moveToElement(allNumbers.get(14)) // Di chuột trái đế số 15
                .release() // Nhả chuột trái ra
                .perform(); // Execute tất cả các action trên
        List<String> allNumberTextExpected = new ArrayList<String>();

        //Tổng các số đã họn
        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allNumbersSelected.size(),12);
    }

    @Test
    public void TC_05_ClickandHold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(),20);
        //Chọn theo block
        actions.clickAndHold(allNumbers.get(0))
                .moveToElement(allNumbers.get(11))
                .release()
                .perform();
        actions.keyDown(Keys.CONTROL).perform(); // Nhấn phím Ctrl xuống (Chưa thả ra)
        actions.clickAndHold(allNumbers.get(12))
                .moveToElement(allNumbers.get(14))
                .release()
                .perform();
        List<String> allNumberTextExpected = new ArrayList<String>();

        //Tổng các số đã họn
        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allNumbersSelected.size(),15);
    }
    @Test
    public void TC_06_DoubleClick(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
        // Cần scroll tới element rồi mới double click - Chỉ riêng firefox mới scroll
        if(driver.toString().contains("firefox")) {
            //actions.scrollToElement(doubleClickButton).perform();
            //scrollIntoView(true): kéo mép trên của element lên phía trên cùng của viewport
            //scrollIntoView(true): kéo mép trên của element lên phía dưới cùng của viewport
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",doubleClickButton);
        }
        sleepInSeconds(3);
        actions.doubleClick(doubleClickButton).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");
    }
    @Test
    public void TC_07_RightClick(){
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        WebElement rightClickButton = driver.findElement(By.cssSelector("span.context-menu-one"));
        WebElement quit = driver.findElement(By.cssSelector("li.context-menu-icon-quit"));
        actions.contextClick(rightClickButton).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("ul.context-menu-list")).isDisplayed());
        sleepInSeconds(2);
        actions.moveToElement(quit).perform();
        //Assert.assertTrue(quit.getAttribute("class").contains("visble"));
        actions.click(quit).perform();
        sleepInSeconds(2);
        driver.switchTo().alert().accept();
    }
    @Test
    public void TC_08_Drag_Drop_HTML4(){
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
        //actions.dragAndDrop(smallCircle,bigCircle).perform();
        actions.clickAndHold(smallCircle).moveToElement(bigCircle).release().perform();
        Assert.assertEquals(bigCircle.getText(),"You did great!");
        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase(),"#03a9f4");
    }
    @Test
    public void TC_09_Drag_Drop_HTML5_Css(){
        driver.get("https://automationfc.github.io/drag-drop-html5/");

    }
    @Test
    public void TC_10_Drag_Drop_HTML5_Xpath(){
        driver.get("https://automationfc.github.io/drag-drop-html5/");

    }


    @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}