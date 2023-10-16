package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Js_Executor {
    WebDriver driver;
    WebElement idElement = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String employeeID = (String) js.executeScript("return arguments[0].value;", idElement);

}
