package Tests;
import Pages.*;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Test
public class SubmitOrderScenario extends PageBase {
    private double _gamePriceInLandingPage;
    private String _gameNameInLandingPage;
    private String _gameNameInGameDetailsPage;
    private int _gameQuantityInGameDetailsPage;

    public void Run() {
        var gamesPage = homePage.ClickonGamesCategory();

        var gamesDetailsPage = GamesPageChecks(gamesPage);

        var shoppingCart = GamesDetailsPageChecks(gamesDetailsPage);
        /*String gameTitleInCart=gamesDetailsPage1.GetTitleOfAddedItemToCart();
        String gameQuantityInCart=gamesDetailsPage1.GetQuantityOfAddedItemToCart();
        assertEquals("Incorrect game name displays",gameTitleInCart,gameNameInLandingPage);
        assertTrue("Incorrect quantity displays",gameQuantityInCart.contains("Quantity:" + quantity));*/

        var loginActionPage = ShoppingCartPageCheckss(shoppingCart);

        var deliveryDetailsPage=LoginActionPageChecks(loginActionPage);

        String firstName="Marian";
        String lastName="Nady";
        String street="street";
        String postCode="12345";
        String cityName="asd";
        String countryName="Egypt";
        String stateName="Cairo";
        var confirmationPage=DeliveryDetailsPageChecks(deliveryDetailsPage, firstName, lastName, street, postCode, cityName, countryName, stateName);

        var succeededOrderPage=ConfirmationPageChecks(confirmationPage, firstName, lastName, street, postCode, cityName, countryName, stateName);

        MyAccountPage myAccountPage = SucceededOrderPageChecks(succeededOrderPage);

        MyAccountPageChecks(myAccountPage);
    }

    private void MyAccountPageChecks(MyAccountPage myAccountPage) {
        String actualStatus= myAccountPage.GetOrderStatus();
        assertEquals("Status isn't pending","Pending",actualStatus);

        String gameNameInMyAccountPage=myAccountPage.GetGameNameInMyOrders();
        double gamePriceInMyAccountPage=myAccountPage.GetGamePriceInMyOrders();
        assertEquals("Game name isn't as in landing page",
                _gameNameInLandingPage,
                gameNameInMyAccountPage);
        assertEquals("Game price isn't as in landing page",
                _gamePriceInLandingPage*_gameQuantityInGameDetailsPage,
                gamePriceInMyAccountPage, 0);
    }

    private MyAccountPage SucceededOrderPageChecks(SucceededOrderPage succeededOrderPage) {
        String processedItemtitle=succeededOrderPage.CheckOrderStatus();
        assertEquals("Item hasn't been processed successfully","Your Order Has Been Processed",processedItemtitle);

        return succeededOrderPage.GotoMyAccount();
    }

    private SucceededOrderPage ConfirmationPageChecks(ConfirmationPage confirmationPage, String firstName, String lastName, String street, String postCode, String cityName, String countryName, String stateName) {
        String address=confirmationPage.GetAddress();
        String expectedCommand=firstName+" "+lastName+"\n"+street+"\n"+cityName+", "+postCode+"\n"+stateName+", "+countryName;
        assertEquals("data isn't displaying as expected",expectedCommand,address);

        String currentShippingMethodPrice=confirmationPage.GetCurrentPrice();
        confirmationPage.ChangeShippingMethod("Table Rate");
        String newShippingMethodPrice=confirmationPage.GetCurrentPrice();
        assertNotEquals("Old price = New price",newShippingMethodPrice,currentShippingMethodPrice);
        return confirmationPage.ConfirmOrder();
    }

    private ConfirmationPage DeliveryDetailsPageChecks(DeliveryDetailsPage deliveryDetailsPage, String firstName, String lastName, String street, String postCode, String cityName, String countryName, String stateName) {
        deliveryDetailsPage.FillGenderInfo("female");
        deliveryDetailsPage.FillFirstNameField(firstName);
        deliveryDetailsPage.FillLastNameField(lastName);
        deliveryDetailsPage.FillDateOfBirthField("25/02/1994");
        deliveryDetailsPage.FillEmailAddressField("mariannady78@gmail.com");
        deliveryDetailsPage.FillStreetAddressField(street);
        deliveryDetailsPage.FillPostalCodeField(postCode);
        deliveryDetailsPage.FillCityField(cityName);
        deliveryDetailsPage.FillCountryField(countryName);
        deliveryDetailsPage.FillStateField(stateName);
        deliveryDetailsPage.FillPrimaryTelephoneNumber("1234567");
        return deliveryDetailsPage.ClickonConfirmButton();
    }

    private DeliveryDetailsPage LoginActionPageChecks(LoginActionPage loginActionPage) {
        return loginActionPage.ClickingonCheckOutAsGuestButton();
    }

    private LoginActionPage ShoppingCartPageCheckss(ShoppingCartPage shoppingCart) {
        var GameNameInCartPage=shoppingCart.GetgameNameInCartPage();
        var GameQuantityInCartPage=shoppingCart.GetgameQuantityInCartPage();
        assertEquals("Incorrect game name displays",GameNameInCartPage,_gameNameInGameDetailsPage);
        assertEquals("Incorrect game quantity displays",GameQuantityInCartPage,_gameQuantityInGameDetailsPage);

        var subtotalInCartPage=shoppingCart.GetSubTotalValueInCartPage();
        var totalInCartPage=shoppingCart.GetTotalValueInCartPage();
        assertEquals("subtotal != total",subtotalInCartPage,totalInCartPage);

        return shoppingCart.ClickingonCheckOutButton();
    }

    private GamesDetailsPage GamesPageChecks(GamesPage gamesPage){
        gamesPage.MovePriceRightSlider(80);

        _gamePriceInLandingPage=gamesPage.GetGamePriceFromLandingPage();
        _gameNameInLandingPage= gamesPage.GetGameNameInLandingPage();

        return gamesPage.ClickonFirstGame();
    }

    private ShoppingCartPage GamesDetailsPageChecks(GamesDetailsPage gamesDetailsPage){

        _gameNameInGameDetailsPage=gamesDetailsPage.GetGameNameInGameDetailsPage();
        assertEquals("Incorrect game name Displayed in details page",_gameNameInLandingPage,_gameNameInGameDetailsPage);

        int actualScreenShotsCount=gamesDetailsPage.GetScreenShotsCountInGameDetailsPage();
        int expectedNumberofScreenShots=4;
        assertEquals("Screen shots number isn't as expected",actualScreenShotsCount,expectedNumberofScreenShots);

        _gameQuantityInGameDetailsPage= 2;
        gamesDetailsPage.SelectQuantity(Integer.toString(_gameQuantityInGameDetailsPage));

         gamesDetailsPage.ClickonAddToCartButton();

         return gamesDetailsPage.ClickonShoppingCart();

    }
}