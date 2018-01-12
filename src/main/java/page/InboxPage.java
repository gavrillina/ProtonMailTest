package page;

import buissnes_object.Mail;
import exeptions.DraftNotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utility.Highlighter;

import java.util.List;


public class InboxPage extends AbstractPage {


    public InboxPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@class='compose pm_button sidebar-btn-compose']")
    private WebElement newMessageButton;

    @FindBy(css = "#autocomplete")
    private WebElement senderMail;

    @FindBy(xpath = "//*[@class = 'senders-name']")
    private WebElement senderName;

    @FindBy(xpath = "//*[@id='uid1']/div[2]/div[5]/input")
    private WebElement mailTopic;

    @FindBy(xpath = "//*[@class = 'subject-text ellipsis']")
    private WebElement subjectText;

    @FindBy(xpath = "//iframe[@class = 'squireIframe']")
    private WebElement frame;

    @FindBy(xpath = "//*[@class='protonmail_signature_block']/preceding-sibling::div[2]")
    private WebElement textContain;

    @FindBy(xpath = "//*[@data-original-title = 'Закрыть']")
    private WebElement closeButton;

    @FindBy(xpath = "//*[@id='uid1']/footer/div/button[2]")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@id='pm_sidebar']/ul[1]/li[2]/a/span")
    private WebElement draft;

    @FindBy(xpath = "//*[@ng-repeat = 'conversation in conversations track by conversation.ID']")
    private List<WebElement> draftList;

    @FindBy(xpath = "//*[@id='uid1']/footer/div/button[3]")
    private WebElement sendButton;

    @FindBy(xpath = "//span[@ng-bind-html = '$message']")
    private WebElement messagePopUp;

    private Highlighter highlighter;


    public void createNewMessage(Mail mail) {

        highlighter.highlightElement(getDriver(), newMessageButton);

        newMessageButton.click();
        waitForElementToBeClickable(senderMail);
        senderMail.sendKeys(mail.getSenderName());
        mailTopic.sendKeys(mail.getTopic());
        driver.switchTo().frame(frame);
        textContain.click();

        Actions make = new Actions(getDriver());
        Action kbEvents = make.sendKeys(mail.getContain()).build();
        kbEvents.perform();

        driver.switchTo().defaultContent();

        highlighter.highlightElement(driver, saveButton);
        saveButton.click();

        waitForVisibilityOfAllElementsLocatedBy(messagePopUp);


    }


    public void veryfySendMessage(Mail mail) throws DraftNotFoundException {

        waitForElementToBeClickable(draft);
        highlighter.highlightElement(driver, draft);
        draft.click();
        waitForListElements(draftList);


        List<WebElement> list = (List<WebElement>) draftList;

        for (WebElement webElement : list) {
            if (senderName.getText().equals(mail.getSenderName()) && subjectText.getText().equals(mail.getTopic())) {

                webElement.click();

                WebElement iFrame = frame;
                getDriver().switchTo().frame(iFrame);


                if (textContain.getText().equals(mail.getContain())) {
                    getDriver().switchTo().defaultContent();

                    highlighter.highlightElement(getDriver(), sendButton);
                    sendButton.click();
                    waitForVisibilityOfAllElementsLocatedBy(messagePopUp);

                } else throw new DraftNotFoundException("The draft has not been found");
            }
            break;
        }


    }


}

