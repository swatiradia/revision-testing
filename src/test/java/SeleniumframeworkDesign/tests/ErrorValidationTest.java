package SeleniumframeworkDesign.tests;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

    @Test(groups = {"ErrorHandling"})
    public void ProductErrorValidation() {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginMethod("swati@radia.com","Swati@radia1");
        productCatalogue.getProductList();
        WebElement product = productCatalogue.getProductByName(productName);
        CartPage cartPage = productCatalogue.addProductToCart(product);
        cartPage.getCartItems();
        boolean resultOfMatch = cartPage.matchTheCartItems("ZARA COAT 33");
        Assert.assertFalse(resultOfMatch);
    }

    @Test(retryAnalyzer = Retry.class)
    public void LoginErrorValidation(){
        landingPage.loginMethod("swatinotradia@gmail.com", "hekeo123");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

}
