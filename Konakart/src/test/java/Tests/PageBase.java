package Tests;

import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class PageBase {
    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.konakart.com/konakart/Welcome.action");
        homePage = new HomePage(driver);
    }

    /*@AfterTest
    public void QuitBrowser(){
        driver.quit();
    }*/
}
