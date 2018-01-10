package page;

import buissnes_object.Mail;
import exeptions.DraftNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//*[@aria-label='Сохранить']")
    private WebElement saveButton;

    @FindBy(xpath = "//span[text() = 'Черновики']")
    private WebElement draft;

    @FindBy(xpath = "//*[@ng-repeat = 'conversation in conversations track by conversation.ID']")
    private List<WebElement> draftList;

    @FindBy(xpath = "//*[text()='Отправить']")
    private WebElement sendButton;

    @FindBy(xpath = "//span[@ng-bind-html = '$message']")
    private WebElement messagePopUp;


    public void createNewMessage(Mail mail) {

        ((JavascriptExecutor) driver).executeScript
                ("arguments[0].style.border='5px solid green'",
                        driver.findElement(By.xpath("//*[@class='compose pm_button sidebar-btn-compose']")));
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
        ((JavascriptExecutor) driver).executeScript
                ("arguments[0].style.border='5px solid green'",
                        driver.findElement(By.xpath("//*[@aria-label='Сохранить']")));
        saveButton.click();

        waitForVisibilityOfAllElementsLocatedBy(messagePopUp);


    }





    public void veryfySendMessage(Mail mail) throws DraftNotFoundException {

        waitForElementToBeClickable(draft);
        ((JavascriptExecutor) driver).executeScript
                ("arguments[0].style.border='5px solid green'",
                        driver.findElement(By.xpath("//span[text() = 'Черновики']")));
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

                    ((JavascriptExecutor) driver).executeScript
                            ("arguments[0].style.border='5px solid green'",
                                    driver.findElement(By.xpath("//*[text()='Отправить']")));
                    sendButton.click();
                    waitForVisibilityOfAllElementsLocatedBy(messagePopUp);

                } else throw new DraftNotFoundException("The draft has not been found");
            }
            break;
        }


    }


}

