package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BaseTest {
    private By GamesBox= By.cssSelector("#main-menu a:contains('Games')");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public GamesPage ClickonGamesCategory (){
        var gamesAnchor = driver.findElement(By.linkText("Games"));
        gamesAnchor.click();
        return new GamesPage(driver);
    }
}

