package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BaseTest{
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }
     public String GetOrderStatus(){
        var orderStatus=driver.findElement(By.cssSelector("#last-orders .last-order td:nth-child(4) div"));
        return orderStatus.getText();
     }
     public String GetGameNameInMyOrders(){
        var gameName=driver.findElement(By.cssSelector("#last-orders .last-order table tbody:nth-child(2) tr td table tbody tr td:nth-child(1) a"));
        return gameName.getText();
     }

    public double GetGamePriceInMyOrders(){
        var gamePrice=driver.findElement(By.cssSelector("#last-orders .last-order table tbody:nth-child(2) tr td table tbody tr td:nth-child(3)"));
        return Double.parseDouble(gamePrice.getText().replace("$"," "));
    }
}
