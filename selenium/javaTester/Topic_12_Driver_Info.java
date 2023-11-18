package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_12_Driver_Info {
    WebDriver driver;
    @Test
    public void testDriverInformation(){
        driver = new FirefoxDriver();
        System.out.println(driver.toString());
        //Ở trên OS nào
        //Browser nào
        //Browser class
        //Id của driver là gì
        //FirefoxDriver: firefox on windows (c42ecc94-595f-4ed1-989d-2bf3c0aec846)
        driver = new ChromeDriver();
        System.out.println(driver.toString());
        if (driver.toString().contains("firefox")){
            //Scroll
        }
        driver.quit();
    }
}
