package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class GamesPage extends BaseTest{
    By priceRightSliderSelector=By.cssSelector("#price-range-slider :nth-child(3)");

    public GamesPage(WebDriver driver) {
        super(driver);
    }

    public void MovePriceRightSlider(int maxPrice){
        var priceRightSlider = driver.findElement(priceRightSliderSelector);
        Actions.moveToElement(priceRightSlider).clickAndHold(priceRightSlider).perform();
        var priceSliderRange= driver.findElement(By.id("amount"));
        var priceSliderRangeText= priceSliderRange.getText();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        while (!priceSliderRangeText.equalsIgnoreCase("$39 - $" + maxPrice)){
            Actions.moveByOffset(-3,0).perform();
            priceSliderRangeText= priceSliderRange.getText();
        }
        Actions.release().perform();
    }

    public double GetGamePriceFromLandingPage(){
        By gamePrice=By.cssSelector("#item-overview .items li:first-child .pricing .price");
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(gamePrice));
        var priceString = driver.findElement(gamePrice).getText();
        priceString = priceString.replace ("$","");
        return Double.parseDouble(priceString);
    }

    public void AddLastItemToCart() {
        var lastItem= driver.findElement(By.cssSelector(".items ul li:last-child"));
        var addToCartButton= lastItem.findElement(By.cssSelector(".add-to-cart-button"));
        Actions.moveToElement(lastItem).perform();
        Actions.click(addToCartButton).perform();
    }

    public void CheckShoppingCartValue(int ItemsCount){
        var shoppingCartSelector=driver.findElement(By.cssSelector(".shopping-cart-title #basket-items"));
        var shoppingCartValue= shoppingCartSelector.getText();
        assertEquals(shoppingCartValue, " " + "(" + ItemsCount +")","Item not added");
    }

    public WebElement GetGameNameSelector() {
        WebElement gameNameSelector = driver.findElement(By.xpath("//a[@href='SelectProd.action?prodId=63']"));
        return gameNameSelector;
    }

    public String GetGameNameInLandingPage(){
        var gameName = GetGameNameSelector().getText();
        return gameName;
    }

    public GamesDetailsPage ClickonFirstGame(){
        Actions.click(GetGameNameSelector()).perform();
        return new GamesDetailsPage(driver);
    }
}
