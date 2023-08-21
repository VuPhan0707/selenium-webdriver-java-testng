package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_04_Relative_Locator {
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
      //  driver.get("https://demo.nopcommerce.com/register");

    }
    @Test
    public void TC_01_Url() {
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
        //login button
        By loginbuttonBy = By.cssSelector("button.login-button");
        WebElement loginbuttonElement = driver.findElement(By.cssSelector("button.login-button"));

        //Remember Me Checkbox
        By rememberMeCheckboxBy = By.id("RememberMe");

        WebElement rememberMeTextElement = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginbuttonBy).toRightOf(rememberMeCheckboxBy));
        System.out.print(rememberMeTextElement.getText());

        List<WebElement> alllinks = driver.findElements(RelativeLocator.with(By.tagName("a")));
        System.out.print(alllinks.size());
    }
}
