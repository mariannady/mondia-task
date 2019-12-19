package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginActionPage extends BaseTest{
    public LoginActionPage(WebDriver driver) {
        super( driver);
    }

    public DeliveryDetailsPage ClickingonCheckOutAsGuestButton(){
        var checkoutAsGuestButton=driver.findElement(By.xpath("//a[@href='CustomerRegistration.action']"));
        Actions.click(checkoutAsGuestButton).perform();
        return new DeliveryDetailsPage(driver);
    }
}
