package pageObjects;

import abstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponents {

    WebDriver driver;

    By confirmationMessage = By.className("hero-primary");

    public ConfirmationPage(WebDriver driver){

        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public String checkConfirmationMessage(){
        waitForElementToAppear(confirmationMessage);
        String confirmationMessageText = driver.findElement(confirmationMessage).getText();
        return confirmationMessageText;
    }

}
