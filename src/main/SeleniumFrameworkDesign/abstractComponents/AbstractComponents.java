package abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.CartPage;
import pageObjects.OrderPage;

import java.time.Duration;

public class AbstractComponents {

    WebDriver driver;

    @FindBy(css="button[routerlink*='cart']")
    WebElement goToCartButton;

    @FindBy(css="button[routerlink*='myorders']")
    WebElement goToMyOrder;
    public AbstractComponents(WebDriver driver) {

        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    public void waitForElementToAppear(By locate){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locate));

    }

    public void waitForWebElementToAppear(WebElement locate){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(locate));

    }

    public void waitForElementToDisappear(WebElement spinner){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.invisibilityOf(spinner));

    }

    public CartPage goToCart(){
        goToCartButton.click();
        return null;
    }

    public OrderPage gotoMyOrder() {
        goToMyOrder.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;

    }

}
