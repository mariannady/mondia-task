package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DeliveryDetailsPage extends BaseTest{
    public DeliveryDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void FillGenderInfo(String gender){
        if (gender=="male"){
            var maleGender=driver.findElement(By.cssSelector(".radio-buttons span:nth-child(2) input"));
            maleGender.click();
        }else {
            var femaleGender=driver.findElement(By.cssSelector(".radio-buttons span:nth-child(3) input"));
            femaleGender.click();
        }
    }

    public void FillFirstNameField(String firstName){
        var firstNameLabel=driver.findElement(By.id("firstName"));
        firstNameLabel.sendKeys(firstName);
    }

    public void FillLastNameField(String lastName){
        var lastNameLabel=driver.findElement(By.id("lastName"));
        lastNameLabel.sendKeys(lastName);
    }

    public void FillDateOfBirthField(String dob){
        var dobField=driver.findElement(By.id("datepicker"));
        dobField.sendKeys(dob);
    }

    public void FillEmailAddressField(String emailAddress){
        var emailAddressLabel=driver.findElement(By.id("emailAddr"));
        emailAddressLabel.sendKeys(emailAddress);
    }

    public void FillStreetAddressField(String streetAddress){
        var streetAddressLabel=driver.findElement(By.id("streetAddress"));
        streetAddressLabel.sendKeys(streetAddress);
    }

    public void FillPostalCodeField(String postalCode){
        var postalCodeLabel=driver.findElement(By.id("postcode"));
        postalCodeLabel.sendKeys(postalCode);
    }

    public void FillCityField(String cityName){
        var cityNameLabel=driver.findElement(By.id("city"));
        cityNameLabel.sendKeys(cityName);
    }

    public void FillStateField(String stateName){
        var stateLabel=driver.findElement(By.id("state"));
        stateLabel.sendKeys(stateName);
    }

    public void FillCountryField(String countryName){
        var countryFieldDropDownList=driver.findElement(By.id("countryId"));
        var countrydropdownListSelector= new Select(countryFieldDropDownList);
        Actions.click(countryFieldDropDownList).perform();
        countrydropdownListSelector.selectByVisibleText(countryName);
    }

    public void FillPrimaryTelephoneNumber(String telephoneNumber){
        var telephoneNumLabel=driver.findElement(By.id("telephoneNumber"));
        telephoneNumLabel.sendKeys(telephoneNumber);
    }
    public ConfirmationPage ClickonConfirmButton(){
        var confirmButton=driver.findElement(By.cssSelector("#continue-button span"));
        Actions.click(confirmButton).perform();
        return new ConfirmationPage(driver);
    }


}
