package page;

import exeptions.MessageNotMakeUnread;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utility.Highlighter;

import java.util.List;

public class CommonButton extends AbstractPage {

    public CommonButton(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//*[@id='pm_main']/nav/span[1]/a[2]")
    private WebElement unreadButton;

    @FindBy(xpath = "//*[@id='pm_main']/nav/span[1]/a[1]")
    private WebElement readButton;

    @FindBy(xpath = "//*[@class='elements-selector-check']")
    private WebElement checkAllButtons;

    @FindBy(xpath = "//*[@class='ptSelectConversation-label']")
    private List<WebElement> checkMessageButton;

    @FindBy(xpath = "//*[@ng-repeat = 'conversation in conversations track by conversation.ID']")
    private List<WebElement> messageList;

    @FindBy(xpath = ".//*[@ng-attr-title='{{ conversation.Subject | unescape }}']")
    private WebElement messageTitle;

    public void checkAllMessage() {

        waitForElementToBeClickable(checkAllButtons);
        Highlighter.highlightElement(driver, checkAllButtons);
        checkAllButtons.click();
    }

    public void checkMessage() {

        waitForListElements(checkMessageButton);
        List<WebElement> list = (List<WebElement>) checkMessageButton;
        for (WebElement webElement : list) {

            Highlighter.highlightElement(driver,webElement);
            webElement.click();
            break;
        }
    }

    public void makeFirstMessageUnread() {

        checkMessage();

        waitForElementToBeClickable(unreadButton);
        Highlighter.highlightElement(driver, unreadButton);
        unreadButton.click();
        Assert.assertEquals("h4", messageTitle.getTagName());

        if (messageTitle.isDisplayed()) {

        } else new MessageNotMakeUnread("Any messages aren't here");

    }

    public void makeMessageUnread() {

        checkAllMessage();

        waitForElementToBeClickable(unreadButton);
        Highlighter.highlightElement(driver, unreadButton);
        unreadButton.click();
        Assert.assertEquals("h4", messageTitle.getTagName());

        if (messageTitle.isDisplayed()) {

        } else new MessageNotMakeUnread("Any messages aren't here");
    }

}
