package webdriver;
//C: Class
//m: Method
//I: Interface
//E: Enum
//R: Record
//A: Annotation
//f: biến final

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_16_Shadow_DOM {
    WebDriver driver;
    WebDriverWait explicitWait;
    Actions actions;
    JavascriptExecutor javascriptExecutor;
    String fullname;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
    @Test
    public void TC_01(){

        //driver: Đại diện cho Real DOM (DOM bên ngoài)
        driver.get("https://automationfc.github.io/shadow-dom/");

        //shadowRootContext: Đại diện cho shadow DOM 1 bên trong
        WebElement shadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();
        String someText = shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(someText);
        List<WebElement> allInput = shadowRootContext.findElements(By.cssSelector("input"));
        System.out.println(allInput.size());

        //shadowRootContext2: Đại diện cho shadow DOM 2 bên trong shadow DOM 1
        WebElement shadowHostElement2 = shadowRootContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext shadowRootContext2 = shadowHostElement2.getShadowRoot();
        String someText2 = shadowRootContext2.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        System.out.println(someText2);

    }
    @Test
    public void TC_02_Shopee() {

        driver.get("https://shopee.vn/");
        sleepInSeconds(5);
        WebElement shadowHostElement = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();
        if(shadowRootContext.findElement(By.cssSelector("div.home-popup__content")).isDisplayed()){
            shadowRootContext.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
        }
        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("iphone 15 promax");
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
    }
        @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}