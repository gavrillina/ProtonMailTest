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



public class MessageTest extends BaseTest {

    WebDriver driver;
    HomePage homePageFactory;
    InboxPage inboxPageFactory;
    SentPage sentPage;
    Logger logger = Logger.getLogger(RollingLogger.class);
    private static final String START_URL = "https://protonmail.com/";


    @BeforeClass
    private void openBrowser(User user) {

        logger.info("Open browser");
        driver = WebDriverSingleton.getDriver();
        driver.get(START_URL);
        driver.manage().window().maximize();
        homePageFactory = new HomePage(driver);
        inboxPageFactory = new InboxPage(driver);
        try {
            homePageFactory.clickLoginButton().doLogIn(user);
            driver.get(properties.getProperty("login","password"));
        } catch (CannotLoginException e) {
            logger.error("Incorrectly data autorization");
        }

    }


    @Test(dataProvider = "testDataForMail")
    private void createNewMail(Mail mail) {

        logger.debug("Create new message");
        inboxPageFactory.createNewMessage(mail);

    }

    @Test(dataProvider = "testDataForMail", dependsOnMethods = {"createNewMail"})
    private void checkingDraftPresence(Mail mail) throws Exception {

        try {
            inboxPageFactory.veryfySendMessage(mail);
        } catch (DraftNotFoundException e) {
            logger.error("Draft not found");
        }

    }

    @Test(dataProvider = "testDataForMail", dependsOnMethods = {"checkingDraftPresence"})
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

    @AfterTest
    private void closeBrowser() {

        inboxPageFactory.logOut();
        driver.quit();
    }

}