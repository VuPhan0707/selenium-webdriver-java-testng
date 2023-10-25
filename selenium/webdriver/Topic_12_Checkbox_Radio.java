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
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_12_Checkbox_Radio {
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
        String emailAddress = "email" + rand.nextInt(99999) + "@gmail.com";
        return emailAddress;
    }
    public void javascriptExecutorRemoveAttribute(WebElement element, String attributeName) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute(arguments[1])", element, attributeName);
    }
    @Test
    public void TC_01_Default_Telerik_Checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        //WebElement checkBox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
        By dualZoneCheckBox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
        By rearSideAirbagscheckBox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
        if(!driver.findElement(dualZoneCheckBox).isSelected()){
            driver.findElement(dualZoneCheckBox).click();
            sleepInSeconds(2);
        }
        if(!driver.findElement(rearSideAirbagscheckBox).isSelected()){
            driver.findElement(rearSideAirbagscheckBox).click();
            sleepInSeconds(2);
        }
        Assert.assertTrue(driver.findElement(rearSideAirbagscheckBox).isSelected());
        Assert.assertTrue(driver.findElement(dualZoneCheckBox).isSelected());


           }
    @Test
    public void TC_02_Default_Telerik_Radio() {
            }
    @Test
    public void TC_03_Select_All_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        //Chọn hết tất cả
        for (WebElement checkbox : allCheckboxes){
            if(!checkbox.isSelected()){
                checkbox.click();
            }
        }
        for (WebElement checkbox : allCheckboxes){
            Assert.assertTrue(checkbox.isSelected());
        }
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));
        //Chọn 1 checkbox/radio nào đó trong tất cả các checkbox
        for (WebElement checkbox : allCheckboxes){
            if(checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()){
                checkbox.click();
            }
        }
        for (WebElement checkbox : allCheckboxes){
            if(checkbox.getAttribute("value").equals("Heart Attack")){
                Assert.assertTrue(checkbox.isSelected());
            }else{
                Assert.assertFalse(checkbox.isSelected());
            }
        }

    }
    @Test
    public void TC_04_Custom_Checkbox() {
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        //Case 4:
        //Chỉ cần dùng 1 locator
        //Dùng thẻ input để click => JS (JavascriptExecutor)
        /*JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("");*/

        //Dùng thẻ input để verify => isSelected: only applies to input elements
        // driver = interface => Không có new
        // Ép kiểu interface này qua kiểu interface khác
        By registerRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
        ((JavascriptExecutor)driver).executeScript("arguments[0].click()",driver.findElement(registerRadio));



    }
/*    public void checkToElement(By byXpath){
       if(!driver.findElement(By ))
            }*/
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}