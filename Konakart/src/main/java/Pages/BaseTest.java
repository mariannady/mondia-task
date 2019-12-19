package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class BaseTest {
    protected WebDriver driver;
    protected Actions Actions;

    protected BaseTest(WebDriver driver) {
        this.driver = driver;
        Actions = new Actions(driver);
    }

}

