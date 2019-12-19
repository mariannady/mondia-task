package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SucceededOrderPage extends BaseTest{
    public SucceededOrderPage(WebDriver driver){
        super(driver);
    }
    public String CheckOrderStatus(){
        var currentOrderStatus=driver.findElement(By.id("page-title"));
        return currentOrderStatus.getText();
    }
}
