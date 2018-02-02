package page;

import buissnes_object.User;
import exeptions.CannotLoginException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.Highlighter;

public class LoginPage extends AbstractPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@id='username']")
    private WebElement loginField;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='login_btn']")
    private WebElement enterButton;

    @FindBy(xpath = "//div[@ng-if='showWelcome']/header")
    private WebElement welcomeMessage;

    public InboxPage doLogIn(User user) throws CannotLoginException {

         waitForVisibilityOfAllElementsLocatedBy(loginField);

        loginField.sendKeys(user.getUSERNAME());
        passwordField.sendKeys(user.getUSERPASSWORD());
        Highlighter.highlightElement(enterButton);
        enterButton.click();

        waitForVisibilityOfAllElementsLocatedBy(welcomeMessage);
        if (welcomeMessage.isDisplayed()) {
            return new InboxPage(getDriver());
        } else
            throw new CannotLoginException("Login failed");

    }

}
