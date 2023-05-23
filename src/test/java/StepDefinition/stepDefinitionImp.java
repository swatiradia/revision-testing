package StepDefinition;

import TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.*;

import java.io.IOException;


public class stepDefinitionImp extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;
    public CartPage cartPage;


    @Given("I landed on Ecommerce Page")
    public void i_landed_on_ecommerce_page() throws IOException {
       landingPage = launchApplication();
    }

    @And("^I logged in using (.+) and (.+)$")
    public void I_logged_in_using_username_and_password(String username, String password){
        productCatalogue = landingPage.loginMethod(username,password);
    }

    @When("^I add the (.+) to cart$")
    public void I_add_the_productName_to_cart(String productName) throws InterruptedException{
        productCatalogue.getProductList();
        WebElement product = productCatalogue.getProductByName(productName);
        productCatalogue.addProductToCart(product);
    }

    @And("^I checkout with the (.+) and submit the order$")
    public void I_checkout_with_the_productName_and_submit_the_order(String productName){
        cartPage = productCatalogue.goToCart();
        cartPage.getCartItems();
        boolean resultOfMatch = cartPage.matchTheCartItems(productName);
        Assert.assertTrue(resultOfMatch);
        CheckOutPage checkOutPage = cartPage.goToCheckOutPage();
        checkOutPage.fillCountryDetails("india");
        confirmationPage = checkOutPage.placeOrder();
    }

    @Then("The confirmation message {string} is displayed")
    public void The_confirmation_message_is_displayed(String confirmationString){
        String getConfirmationTest = confirmationPage.checkConfirmationMessage();
        Assert.assertTrue(getConfirmationTest.equalsIgnoreCase(confirmationString));

    }

    @Then("{string} error is displayed")
    public void Incorrect_email_or_password_error_is_displayed(String errorString){
        Assert.assertEquals(errorString, landingPage.getErrorMessage());
        driver.close();

    }
}
