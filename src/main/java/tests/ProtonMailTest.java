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
  
    @BeforeTest
    private void openBrowser() throws Exception {

        logger.info("Open browser");
        driver = WebDriverSingleton.getDriver();
        driver.get("https://protonmail.com/");
        driver.manage().window().maximize();
        ScreenShot.takeSnapShot(driver, "src\\main\\java\\screenshots\\ProtonMailTestScreenshots\\open_browser.png");
    }


    @Test(dataProvider = "testDataForLogIn")
    private void logInToBox(User user) throws Exception {

        homePageFactory = new HomePage(driver);
        inboxPageFactory = new InboxPage(driver);
        try {
            homePageFactory.clickLoginButton().doLogIn(user);
        } catch (CannotLoginException e) {
            logger.error("Incorrectly data autorization");
            ScreenShot.takeSnapShot(driver, "src\\main\\java\\screenshots\\ProtonMailTestScreenshots\\error_log_in.png");
        }
        ScreenShot.takeSnapShot(driver, "src\\main\\java\\screenshots\\ProtonMailTestScreenshots\\log_in.png");
    }

    @Test(dataProvider = "testDataForMail", dependsOnMethods = {"logInToBox"})
    private void createNewMail(Mail mail) throws Exception {

        logger.debug("Create new message");
        inboxPageFactory.createNewMessage(mail);
        ScreenShot.takeSnapShot(driver, "src\\main\\java\\screenshots\\ProtonMailTestScreenshots\\create_message.png");
    }

    @Test(dataProvider = "testDataForMail", dependsOnMethods = {"createNewMail"})
    private void checkingDraftPresence(Mail mail) throws Exception {

        try {
            inboxPageFactory.veryfySendMessage(mail);
        } catch (DraftNotFoundException e) {
            logger.error("Draft not found");
            ScreenShot.takeSnapShot(driver, "src\\main\\java\\screenshots\\ProtonMailTestScreenshots\\error_send.png");
        }
        ScreenShot.takeSnapShot(driver, "src\\main\\java\\screenshots\\ProtonMailTestScreenshots\\send_message.png");
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