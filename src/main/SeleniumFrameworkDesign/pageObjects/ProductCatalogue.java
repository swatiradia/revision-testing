package pageObjects;

import abstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponents {

    WebDriver driver;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public ProductCatalogue(WebDriver driver){

        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".mb-3")
    List<WebElement> products;

    @FindBy(css=".toast-container")
    WebElement spinner;



    public List<WebElement> getProductList(){
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName){

        WebElement product = getProductList().stream().filter(prod -> prod.findElement(By.cssSelector("b"))
                .getText().equals(productName)).findFirst().orElse(null);
        return product;
    }

    public CartPage addProductToCart(WebElement product){

        product.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);


        CartPage cartPage = new CartPage(driver);
        return cartPage;

    }



}
