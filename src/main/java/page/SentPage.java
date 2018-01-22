package page;

import buissnes_object.Mail;
import exeptions.SentMessageNotFound;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.Highlighter;

import java.util.List;

public class SentPage extends AbstractPage {

    public SentPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='pm_sidebar']/ul[1]/li[3]/a/span")
    private WebElement sent;

    @FindBy(xpath = "//*[@id='body']/header/div/a")
    private WebElement logo;

    @FindBy(xpath = "//*[@ng-repeat = 'conversation in conversations track by conversation.ID']")
    private List<WebElement> sentList;

    @FindBy(xpath = "//*[@class = 'senders-name']")
    private WebElement senderName;

    @FindBy(xpath = "//*[@class = 'subject-text ellipsis']")
    private WebElement subjectText;

    @FindBy(xpath = "//*[@class='protonmail_signature_block']/preceding-sibling::div[2]")
    private WebElement textContain;

    public void checkSentMesage(Mail mail) throws SentMessageNotFound {

        waitForElementToBeClickable(sent);
        Highlighter.highlightElement(driver, sent);
        sent.click();

        waitForListElements(sentList);

        List<WebElement> list = (List<WebElement>) sentList;

        for (WebElement webElement : list) {
            if (senderName.getText().equals(mail.getSenderName()) && subjectText.getText().equals(mail.getTopic())) {

                Highlighter.highlightElement(driver, webElement);
                webElement.click();

            } else throw new SentMessageNotFound("The draft has not been found");

            break;
        }
        waitForElementToBeClickable(logo);
        Highlighter.highlightElement(driver, logo);
        logo.click();
    }
}

