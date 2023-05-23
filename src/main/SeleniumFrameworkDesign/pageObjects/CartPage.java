package pageObjects;

import abstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {

    WebDriver driver;

    public CartPage(WebDriver driver){

        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = ".infoWrap h3")
    List<WebElement> cartItems;


    By checkOutButton = By.cssSelector(".totalRow button");


    public List<WebElement> getCartItems(){
        waitForElementToAppear(checkOutButton);
        return cartItems;
    }

    public boolean matchTheCartItems(String productName){
        boolean resultOfMatch = getCartItems().stream().anyMatch(items -> items.getText().equalsIgnoreCase(productName));
        return resultOfMatch;
    }

    public CheckOutPage goToCheckOutPage(){
        driver.findElement(checkOutButton).click();

        CheckOutPage checkOutPage = new CheckOutPage(driver);
        return checkOutPage;
    }
}
