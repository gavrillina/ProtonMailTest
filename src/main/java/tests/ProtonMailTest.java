package tests;

import buissnes_object.Mail;
import buissnes_object.User;
import exeptions.CannotLoginException;
import exeptions.DraftNotFoundException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.InboxPage;
import utility.RollingLogger;
import utility.ScreenShot;
import utility.WebDriverSingleton;


public class ProtonMailTest {
    WebDriver driver;
    HomePage homePageFactory;
    InboxPage inboxPageFactory;
    Logger logger = Logger.getLogger(RollingLogger.class);
    ScreenShot screenShot = new ScreenShot();

    @BeforeTest
    private void openBrowser() throws Exception {

        logger.info("Open browser");
        driver = WebDriverSingleton.getDriver();
        driver.get("https://protonmail.com/");
        driver.manage().window().maximize();

    }


    @Test(dataProvider = "testDataForLogIn")
    private void logInToBox(User user) throws Exception {

        homePageFactory = new HomePage(driver);
        inboxPageFactory = new InboxPage(driver);
        try {
            homePageFactory.clickLoginButton().doLogIn(user);
        } catch (CannotLoginException e) {
           screenShot.takeScreenShot(driver,"Log In to inbox failed");
            logger.error("Incorrectly data autorization");
        }

    }

    @Test(dataProvider = "testDataForMail", dependsOnMethods = {"logInToBox"})
    private void createNewMail(Mail mail) throws Exception {

        logger.debug("Create new message");
        inboxPageFactory.createNewMessage(mail);

    }

    @Test(dataProvider = "testDataForMail", dependsOnMethods = {"createNewMail"})
    private void checkingDraftPresence(Mail mail) throws Exception {

        try {
            inboxPageFactory.veryfySendMessage(mail);
        } catch (DraftNotFoundException e) {
            logger.error("Draft not found");
            screenShot.takeScreenShot(driver,"verify send message not found");
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
        driver.quit();
    }

}