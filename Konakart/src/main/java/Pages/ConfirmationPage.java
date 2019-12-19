package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmationPage extends BaseTest{
    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String GetAddress(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        By contetnSelector=By.id("formattedDeliveryAddr");
        wait.until(ExpectedConditions.visibilityOfElementLocated(contetnSelector));
        var addresscontainer=driver.findElement(contetnSelector);
        String content=addresscontainer.getText();
        return content;
    }

    public String GetCurrentPrice(){
        By currentPriceSelector=By.cssSelector("#ot-table tr:nth-child(3) .cost-overview-amounts");
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentPriceSelector));
        var currentPrice=driver.findElement(currentPriceSelector);
        return currentPrice.getText();
    }

    public void ChangeShippingMethod(String shippingMethod){
        By shippingMethodDropDown=By.id("shippingQuotes");
        var shippingMethodDropDownList=driver.findElement(shippingMethodDropDown);
        Select shippingMethodSelector= new Select(shippingMethodDropDownList);
        shippingMethodSelector.selectByVisibleText(shippingMethod);
    }

    public SucceededOrderPage ConfirmOrder(){
        var confirmOrderButton=driver.findElement(By.cssSelector("#continue-button span"));
        confirmOrderButton.click();
        return new SucceededOrderPage(driver);
    }

    public MyAccountPage GotoMyAccount() {
        var myAccount=driver.findElement(By.id("my-account"));
        myAccount.click();
        return new MyAccountPage(driver);
    }
}
