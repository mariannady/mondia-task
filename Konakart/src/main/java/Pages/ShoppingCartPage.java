package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends BaseTest{
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public String GetgameNameInCartPage(){
        var gameName=driver.findElement(By.className("text-link")).getText();
        return gameName;
    }

    public int GetgameQuantityInCartPage(){
        var gameQuantity=driver.findElement(By.cssSelector(".basket-body .qty-input")).getAttribute("value");
        return Integer.parseInt(gameQuantity);
    }

    public String GetSubTotalValueInCartPage(){
        var subtotal=driver.findElement(By.cssSelector("#costs-and-promotions #cost-overview tr:first-child td:nth-child(2)"));
        return subtotal.getText();
    }

    public String GetTotalValueInCartPage(){
        var total=driver.findElement(By.className("total-price"));
        return total.getText();
    }

    public LoginActionPage ClickingonCheckOutButton(){
        var checkoutButton= driver.findElement(By.cssSelector("#continue-button span"));
        Actions.click(checkoutButton).perform();
        return new LoginActionPage(driver);
    }

}
