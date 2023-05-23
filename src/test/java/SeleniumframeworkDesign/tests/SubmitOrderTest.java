package SeleniumframeworkDesign.tests;
import TestComponents.BaseTest;
import TestComponents.Retry;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class SubmitOrderTest extends BaseTest {

    @Test(dataProvider = "getData", groups = {"Purchase"}, retryAnalyzer = Retry.class)
    public void submitOrder(HashMap<String,String> input) throws IOException {
        String selectedCountryName = "india";
        String expectedConfirmationText = "Thankyou for the order.";
        ProductCatalogue productCatalogue = landingPage.loginMethod(input.get("email"),input.get("password"));
        productCatalogue.getProductList();
        WebElement product = productCatalogue.getProductByName(input.get("productName"));
        productCatalogue.addProductToCart(product);
        CartPage cartPage = productCatalogue.goToCart();
        cartPage.getCartItems();
        boolean resultOfMatch = cartPage.matchTheCartItems(input.get("productName"));
        Assert.assertTrue(resultOfMatch);
        CheckOutPage checkOutPage = cartPage.goToCheckOutPage();
        checkOutPage.fillCountryDetails(selectedCountryName);
        ConfirmationPage confirmationPage = checkOutPage.placeOrder();
        String getConfirmationTest = confirmationPage.checkConfirmationMessage();
        Assert.assertTrue(getConfirmationTest.equalsIgnoreCase(expectedConfirmationText));
    }


    @Test(dataProvider = "getData", dependsOnMethods = "submitOrder")
    public void orderHistoryTest(HashMap<String, String> input) throws IOException {
        ProductCatalogue productCatalogue = landingPage.loginMethod(input.get("email"),input.get("password"));
        OrderPage orderPage = productCatalogue.gotoMyOrder();
        Assert.assertTrue(orderPage.matchProductNames(input.get("productName")));

    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/main/SeleniumFrameworkDesign/dataPackage/PurchaseOrder.json");
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }

//        HashMap<String,String> dataset1 = new HashMap<String,String>();
//        dataset1.put("email","swati@radia.com");
//        dataset1.put("password","Swati@radia1");
//        dataset1.put("productName","ZARA COAT 3");
//
//        HashMap<String,String> dataset2 = new HashMap<String,String>();
//        dataset2.put("email","mihir@radia.com");
//        dataset2.put("password","Mihir@radia6");
//        dataset2.put("productName","ADIDAS ORIGINAL");

}


