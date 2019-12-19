package Tests;
import Pages.*;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Test
public class FirstScenario extends PageBase {

    public void CheckFirstScenario() {
        var gamesPage = homePage.ClickonGamesCategory();
        gamesPage.MovePriceRightSlider(80);
        var gamePriceInLandingPage=gamesPage.GetGamePriceFromLandingPage();
        //gamesPage.AddLastItemToCart();
        //gamesPage.CheckShoppingCartValue(1);
        var gamesDetailsPage=gamesPage.ClickonFirstGame();

        String gameNameInLandingPage= gamesPage.GetGameNameInLandingPage();
        String gameNameInGameDetailsPage=gamesDetailsPage.GetGameNameInGameDetailsPage();
        assertEquals("Incorrect game name Displayed in details page",gameNameInLandingPage,gameNameInGameDetailsPage);

        int actualScreenShotsCount=gamesDetailsPage.GetScreenShotsCountInGameDetailsPage();
        int expectedNumberofScreenShots=4;
        assertEquals("Screen shots number isn't as expected",actualScreenShotsCount,expectedNumberofScreenShots);

        int quantity= 2;
        gamesDetailsPage.SelectQuantity(Integer.toString(quantity));

        gamesDetailsPage.ClickonAddToCartButton();

        /*String gameTitleInCart=gamesDetailsPage1.GetTitleOfAddedItemToCart();
        String gameQuantityInCart=gamesDetailsPage1.GetQuantityOfAddedItemToCart();
        assertEquals("Incorrect game name displays",gameTitleInCart,gameNameInLandingPage);
        assertTrue("Incorrect quantity displays",gameQuantityInCart.contains("Quantity:" + quantity));*/

        gamesDetailsPage.ClickonShoppingCart();

        ShoppingCartPage shoppingCart=new ShoppingCartPage(driver);
        var GameNameInCartPage=shoppingCart.GetgameNameInCartPage();
        var GameQuantityInCartPage=shoppingCart.GetgameQuantityInCartPage();
        assertEquals("Incorrect game name displays",GameNameInCartPage,gameNameInGameDetailsPage);
        assertEquals("Incorrect game quantity displays",GameQuantityInCartPage,quantity);

        var subtotalInCartPage=shoppingCart.GetSubTotalValueInCartPage();
        var totalInCartPage=shoppingCart.GetTotalValueInCartPage();
        assertEquals("subtotal != total",subtotalInCartPage,totalInCartPage);

        shoppingCart.ClickingonCheckOutButton();
        LoginActionPage loginActionPage=new LoginActionPage(driver);
        loginActionPage.ClickingonCheckOutAsGuestButton();

        DeliveryDetailsPage deliveryDetailsPage=new DeliveryDetailsPage(driver);
        deliveryDetailsPage.FillGenderInfo("female");
        String firstName="Marian";
        deliveryDetailsPage.FillFirstNameField(firstName);
        String lastName="Nady";
        deliveryDetailsPage.FillLastNameField(lastName);
        deliveryDetailsPage.FillDateOfBirthField("25/02/1994");
        deliveryDetailsPage.FillEmailAddressField("mariannady78@gmail.com");
        String street="street";
        deliveryDetailsPage.FillStreetAddressField(street);
        String postCode="12345";
        deliveryDetailsPage.FillPostalCodeField(postCode);
        String cityName="asd";
        deliveryDetailsPage.FillCityField(cityName);
        String countryName="Egypt";
        deliveryDetailsPage.FillCountryField(countryName);
        String stateName="Cairo";
        deliveryDetailsPage.FillStateField(stateName);
        deliveryDetailsPage.FillPrimaryTelephoneNumber("1234567");
        deliveryDetailsPage.ClickonConfirmButton();

        ConfirmationPage confirmationPage=new ConfirmationPage(driver);
        String address=confirmationPage.GetAddress();
        String expectedCommand=firstName+" "+lastName+"\n"+street+"\n"+cityName+", "+postCode+"\n"+stateName+", "+countryName;
        assertEquals("data isn't displaying as expected",expectedCommand,address);

        String currentShippingMethodPrice=confirmationPage.GetCurrentPrice();
        confirmationPage.ChangeShippingMethod("Table Rate");
        String newShippingMethodPrice=confirmationPage.GetCurrentPrice();
        assertNotEquals("Old price = New price",newShippingMethodPrice,currentShippingMethodPrice);
        confirmationPage.ConfirmOrder();

        SucceededOrderPage succeededOrderPage=new SucceededOrderPage(driver);
        String processedItemtitle=succeededOrderPage.CheckOrderStatus();
        assertEquals("Item hasn't been processed successfully","Your Order Has Been Processed",processedItemtitle);

        var myAccountPage=confirmationPage.GotoMyAccount();

        String actualStatus= myAccountPage.GetOrderStatus();
        assertEquals("Status isn't pending","Pending",actualStatus);

        String gameNameInMyAccountPage=myAccountPage.GetGameNameInMyOrders();
        double gamePriceInMyAccountPage=myAccountPage.GetGamePriceInMyOrders();
        assertEquals("Game name isn't as in landing page",
                gameNameInLandingPage,
                gameNameInMyAccountPage);
        assertEquals("Game price isn't as in landing page",
                gamePriceInLandingPage*quantity,
                gamePriceInMyAccountPage, 0);
    }
}