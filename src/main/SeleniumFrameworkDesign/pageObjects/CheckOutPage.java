package pageObjects;

import abstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponents {

    WebDriver driver;

    @FindBy(css = "[placeholder='Select Country']")
    WebElement countryPlaceHolder;

    @FindBy(css = ".ta-item:nth-of-type(2)")
    WebElement selectCountry;

    @FindBy(css = "a[class*='ng-star-inserted']")
    WebElement placeOrderButton;


    By dropDownItems = By.cssSelector(".ta-item");

    public CheckOutPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void fillCountryDetails(String selectedCountryName){
        Actions a = new Actions(driver);
        a.sendKeys(countryPlaceHolder, selectedCountryName).build().perform();
        waitForElementToAppear(dropDownItems);
        selectCountry.click();
    }

    public ConfirmationPage placeOrder(){
        placeOrderButton.click();

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }
}
