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

import java.io.IOException;


public class ProtonMailTest {
    WebDriver driver;
    HomePage homePageFactory;
    InboxPage inboxPageFactory;
    Logger logger = Logger.getLogger(RollingLogger.class);
    ScreenShot screenShot = new ScreenShot();


    @BeforeTest
    private void openBrowser() throws IOException {

        logger.info("Open browser");
        driver = WebDriverSingleton.getDriver();
        driver.get("https://protonmail.com/");
        driver.manage().window().maximize();
        screenShot.makeSceenShot();
    }


    @Test(dataProvider = "testDataForLogIn")
    private void logInToBox(User user) throws CannotLoginException, IOException {

        homePageFactory = new HomePage(driver);
        inboxPageFactory = new InboxPage(driver);
        try {
            homePageFactory.clickLoginButton().doLogIn(user);
        } catch (CannotLoginException e) {
            logger.error("Incorrectly data autorization");
        }
        screenShot.makeSceenShot();

    }

    @Test(dataProvider = "testDataForMail", dependsOnMethods = {"logInToBox"})
    private void createNewMail(Mail mail) throws IOException {

        logger.info("Create new message");
        inboxPageFactory.createNewMessage(mail);
        screenShot.makeSceenShot();
    }

    @Test(dataProvider = "testDataForMail", dependsOnMethods = {"createNewMail"})
    private void checkingDraftPresence(Mail mail) throws DraftNotFoundException, IOException {

        try {
            inboxPageFactory.veryfySendMessage(mail);
        } catch (DraftNotFoundException e) {
            logger.error("Draft not found");
        }
        screenShot.makeSceenShot();
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