package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GamesDetailsPage extends BaseTest{
    public GamesDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String GetGameNameInGameDetailsPage() {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("page-title")));
        var gameNameInDetailsPageSelector = driver.findElement(By.id("page-title"));
        var gameName = gameNameInDetailsPageSelector.getText();
        return gameName;
    }

    public int GetScreenShotsCountInGameDetailsPage(){
        var screenShots= driver.findElements(By.cssSelector("#gallery_nav a"));
        return screenShots.size();
    }

    public void SelectQuantity(String quantity){
        var dropdownList=driver.findElement(By.cssSelector("#AddToCartForm #prodQuantityId"));
        Select drpQuantity=new Select(dropdownList);
        drpQuantity.selectByVisibleText(quantity);
    }

    public void ClickonAddToCartButton(){
        var addToCartButton=driver.findElement(By.cssSelector("#AddToCartForm a.add-to-cart-button-big.button.small-rounded-corners"));
        Actions.click(addToCartButton).perform();
    }

    public String GetTitleOfAddedItemToCart(){
        Actions.moveToElement(driver.findElement(By.id("shopping-cart"))).perform();
        var selector = By.cssSelector("#shopping-cart-container #shopping-cart-items > .shopping-cart-item:nth-child(1) > .shopping-cart-item-title");
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        var gameTitle= driver.findElement(selector);
        return gameTitle.getText();
    }

    public int GetQuantityOfAddedItemToCart(){
        Actions.moveToElement(driver.findElement(By.id("shopping-cart"))).perform();
        var selector = By.cssSelector("#shopping-cart-container #shopping-cart-items > .shopping-cart-item:nth-child(1) > .shopping-cart-item-price");
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));


        var gameQuantity=driver.findElement(By.id("shopping-cart-container")).findElement(selector);
        var quantityText = gameQuantity.getText();
        return Integer.parseInt(quantityText.substring(quantityText.indexOf("Quantity: ") + 10));
    }

    public ShoppingCartPage ClickonShoppingCart(){
        var shoppingCartButton=driver.findElement(By.className("shopping-cart-title"));
        Actions.click(shoppingCartButton).perform();
        return new ShoppingCartPage(driver);
    }
}
