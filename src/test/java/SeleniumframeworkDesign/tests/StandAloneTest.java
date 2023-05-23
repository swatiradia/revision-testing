package SeleniumframeworkDesign.tests;

import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;
import java.util.List;


public class StandAloneTest extends BaseTest {

    @Test
    public void submitOrder() throws IOException {
        String productName = "ZARA COAT 3";
        String selectedCountryName = "india";
        String expectedConfirmationText = "Thankyou for the order.";
//        Logging in into the site - LandingPage
        ProductCatalogue productCatalogue = landingPage.loginMethod("swati@radia.com","Swati@radia1");
//        Selecting the products - ProductCatalogue
        List<WebElement> products = productCatalogue.getProductList();
        WebElement product = productCatalogue.getProductByName(productName);
        CartPage cartPage = productCatalogue.addProductToCart(product);
//        Get the list of items names added to the cart - CartPage
        cartPage.getCartItems();
        boolean resultOfMatch = cartPage.matchTheCartItems(productName);
        Assert.assertTrue(resultOfMatch);
//        Clicking on checkout - CartPage
       CheckOutPage checkOutPage = cartPage.goToCheckOutPage();
//        Filling the country details - CheckOutPage
        checkOutPage.fillCountryDetails(selectedCountryName);
        ConfirmationPage confirmationPage = checkOutPage.placeOrder();
//        Final order confirmation message - ConfirmationPage
        String getConfirmationTest = confirmationPage.checkConfirmationMessage();
        Assert.assertTrue(getConfirmationTest.equalsIgnoreCase(expectedConfirmationText));
//        Finally close the driver window

    }
}
