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
        checkToElement(dualZoneCheckBox);
        checkToElement(rearSideAirbagscheckBox);
        Assert.assertTrue(driver.findElement(rearSideAirbagscheckBox).isSelected());
        Assert.assertTrue(driver.findElement(dualZoneCheckBox).isSelected());
        uncheckToElement(rearSideAirbagscheckBox);
        uncheckToElement(dualZoneCheckBox);
        Assert.assertFalse(driver.findElement(rearSideAirbagscheckBox).isSelected());
        Assert.assertFalse(driver.findElement(dualZoneCheckBox).isSelected());
           }
    @Test
    public void TC_02_Default_Telerik_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By petrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
        checkToElement(petrolRadio);
        Assert.assertTrue(driver.findElement(petrolRadio).isSelected());
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
    public void TC_04_Custom_Radio() {
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
    @Test
    public void TC_05_Custom_Google_Docs() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        sleepInSeconds(2);
        By canThoRadio = By.xpath("//div[@data-value='Cần Thơ']");
        //By daNangRadio = By.xpath("//div[@data-value ='Đà Nẵng']");
        //Verify radio is not selected
        //Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"false");


        driver.findElement(canThoRadio).click();
        //driver.findElement(daNangRadio).click();
        sleepInSeconds(2);



    }
    public void checkToElement(By byXpath){
        //Nếu chưa chọn thì chọn
        if(!driver.findElement(byXpath).isSelected()){
            driver.findElement(byXpath).click();
            sleepInSeconds(2);
        }
            }
    public void uncheckToElement(By byXpath){
        //Nếu chọn rồi thì bỏ chọn
        if(driver.findElement(byXpath).isSelected()){
            driver.findElement(byXpath).click();
            sleepInSeconds(2);
        }
    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}