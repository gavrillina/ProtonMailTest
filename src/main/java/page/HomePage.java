package page;

import decorator.WebElementDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.Highlighter;


public class HomePage extends AbstractPage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='bs-example-navbar-collapse-1']/ul/li[7]/a")
    private WebElement loginButton;

    public LoginPage clickLoginButton() {


        waitForElementToBeClickable(loginButton);
        Highlighter.highlightElement(loginButton);
        new WebElementDecorator(loginButton).click();
        return new LoginPage(getDriver());

    }
}