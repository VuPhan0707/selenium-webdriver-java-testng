package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_05_Assert {
    WebDriver driver;
    @Test
    public  void  verifyTestNG(){
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");
        // Trong Java có nhiều thư viện để verify dữ liệu
        // Testing Framework (Unit/ Integration/ UI Automation Test)
        // JUnit 4/ TestNG/ JUnit 5/ Hamcrest/ AssertJ/...

        // Kiểu dữ liệu nhận vào là boolean
        // Khi mong muốn kiểu dữ liệu mong muốn trả về là True thì dùng AssertTrue để verify
        Assert.assertTrue(driver.getPageSource().contains("Facebook helps you connect and share with the people in your life."));

        // Mong muốn điều kiện trả về là sai thì dùng assertFalse
        Assert.assertFalse(driver.getPageSource().contains("Create a new account"));

        // Các hàm trả về dữ liệu kiểu boolean
        // Quy tắc: bắt đầu với tiền tố là isXXX
        driver.findElement(By.id("")).isDisplayed();
        driver.findElement(By.id("")).isEnabled();
        driver.findElement(By.id("")).isSelected();
        new Select((driver.findElement(By.id("")))).isMultiple();

        // Mong đợi 1 điều kiện giống như thực tế (tuyệt đối)
        // Actual = Expected
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");



    }
}
