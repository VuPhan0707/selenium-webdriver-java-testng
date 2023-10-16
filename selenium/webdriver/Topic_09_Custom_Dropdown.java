package webdriver;//C: Class
//m: Method7845120
//I: Interface
//E: Enum
//R: Record
//A: Annotation
//f: biến final

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_09_Custom_Dropdown {
    WebDriver driver;
    // Tường minh: trạng thái cụ thể cho element
    // Visible / Invisible / Presence / Number / Clickable/..
    WebDriverWait explicitWait;

    //FluentWait<WebDriver> fluentWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
        // Ngầm định: k rõ ràng cho 1 trạng thái cụ thể nào của element hết
        // Cho việc tìm element - findElement(s)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
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
        String emailAddress = "username" + rand.nextInt(99999) + "@gmail.com";
        return emailAddress;
    }
    //@Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        /*//1 - Click vào 1 thẻ để cho nó xổ hết các item bên trong dropdown ra
        driver.findElement(By.cssSelector("span#number-button")).click();
        sleepInSeconds(10);
        //2.1 - Nó sẽ xổ ra chứa hết tất cả các item
        //2.2 - Nó sẽ xổ ra nhưng chỉ chứa 1 phần và đang load thêm
        //Chờ cho nó xổ ra hết tất cả các items trong dropdown
        //Xuất hiện đầy đủ trong HTML
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
        List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
        //allItems đang lưu trữ 19 items bên trong

        for (WebElement item: allItems){
            if(item.getText().equals("8")){
                item.click();
                break; // Thoát vòng lặp (for/while/do-while/switch-case)
            }
        } */
        selectItemInDropdown("span#speed-button","ul#speed-menu div","Faster");
        sleepInSeconds(3);
        selectItemInDropdown("span#files-button","ul#files-menu div","ui.jQuery.js");
        //driver.navigate().refresh();
        selectItemInDropdown("span#number-button","ul#number-menu div","10");
        selectItemInDropdown("span#salutation-button","ul#salutation-menu div","Dr.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(),"ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(),"10");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(),"Dr.");

        //3.1 - Nếu như item cần chọn nó hiển thị thì click vào

        //4 - Trước khi click cần kiểm tra nếu như text của item bằng vs item cần chọn thì click vào
    }
    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("i.dropdown.icon","div.item>span","Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Christian");
        selectItemInDropdown("i.dropdown.icon","div.item>span","Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Matt");
        selectItemInDropdown("i.dropdown.icon","div.item>span","Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Stevie Feliciano");

    }
    @Test
    public void TC_03_Vuejs() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu a","Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");
        selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu a","Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");
    }
    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemInEditableDropdown("input.search","div.item span","Algeria");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Algeria");
        selectItemInEditableDropdown("input.search","div.item span","Belarus");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Belarus");
    }
    @Test
    public void TC_05_NopEcommerce() {
        driver.get("https://demo.nopcommerce.com/register");
        selectItemInDropdown("select[name='DateOfBirthDay']","select[name='DateOfBirthDay']>option","18");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']>option[value='18']")).isSelected());
        selectItemInDropdown("select[name='DateOfBirthMonth']","select[name='DateOfBirthMonth']>option","September");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']>option[value='9']")).isSelected());
        selectItemInDropdown("select[name='DateOfBirthYear']","select[name='DateOfBirthYear']>option","1995");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']>option[value='1995']")).isSelected());

    }
    public void selectItemInDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).click();
        sleepInSeconds(1);
        // Vừa wait + vừa tìm element
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
       //List<WebElement> allItems = driver.findElements(By.cssSelector(childItemCss));
        for (WebElement item : allItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }
    public void selectItemInEditableDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected);
        sleepInSeconds(1);
        // Vừa wait + vừa tìm element
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
        //List<WebElement> allItems = driver.findElements(By.cssSelector(childItemCss));
        for (WebElement item : allItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }
    @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}