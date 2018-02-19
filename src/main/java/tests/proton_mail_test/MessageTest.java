package tests.proton_mail_test;

import buissnes_object.Mail;
import buissnes_object.User;
import exeptions.CannotLoginException;
import exeptions.DraftNotFoundException;
import exeptions.SentMessageNotFound;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import page.*;
import utility.BaseTest;
import utility.RollingLogger;
import utility.WebDriverSingleton;

import javax.jws.soap.SOAPBinding;


public class MessageTest extends BaseTest {

    WebDriver driver;
    HomePage homePageFactory;
    InboxPage inboxPageFactory;
    SentPage sentPage;
    Logger logger = Logger.getLogger(RollingLogger.class);
    private static final String START_URL = "https://protonmail.com/";


    @BeforeTest(groups = {"Smoke test"})
    private void openBrowser() {

        logger.info("Open browser");
        driver = WebDriverSingleton.getDriver();
        driver.get(START_URL);
        driver.manage().window().maximize();
    }

    @Test(groups = {"Smoke test"}, dataProvider = "testDataForLogIn")
    private void logIn(User user) {
        homePageFactory = new HomePage(driver);
        inboxPageFactory = new InboxPage(driver);
        try {
            homePageFactory.clickLoginButton().doLogIn(user);
        } catch (CannotLoginException e) {
            logger.error("Incorrectly data autorization");
        }
    }

    @Test(groups = {"Smoke test"}, dataProvider = "testDataForMail", dependsOnMethods = "logIn")
    private void createNewMail(Mail mail) {

        logger.debug("Create new message");
        inboxPageFactory = new InboxPage(driver);
        inboxPageFactory.createNewMessage(mail);

    }

    @Test(groups = {"Smoke test"}, dataProvider = "testDataForMail", dependsOnMethods = {"createNewMail"})
    private void checkingDraftPresence(Mail mail) throws Exception {

        try {
            inboxPageFactory.veryfySendMessage(mail);
        } catch (DraftNotFoundException e) {
            logger.error("Draft not found");
        }

    }

    @Test(groups = {"Smoke test"}, dataProvider = "testDataForMail", dependsOnMethods = {"checkingDraftPresence"})
    private void verifySendMessage(Mail mail) throws Exception {

        sentPage = new SentPage(driver);
        try {
            sentPage.checkSentMesage(mail);
        } catch (SentMessageNotFound e) {
            logger.error("Sent message not found");
        }
    }


    @DataProvider
    public Object[][] testDataForMail() {
        Mail mail = new Mail("tani455@mail.ru", "Tatyana", "Some text");
        return new Object[][]{{mail}};
    }

    @DataProvider
    public Object[][] testDataForLogIn() {

        return new Object[][]{{User.PROTON_LOGIN}};
    }

    @AfterTest(groups = {"Smoke test"})
    private void closeBrowser() {

        inboxPageFactory.logOut();
        driver.quit();
    }

}