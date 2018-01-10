package tests;

import buissnes_object.Mail;
import buissnes_object.User;
import exeptions.CannotLoginException;
import exeptions.DraftNotFoundException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.InboxPage;
import utility.RollingLogger;
import utility.WebDriverSingleton;

import java.io.File;
import java.io.IOException;


public class ProtonMailTest {
    WebDriver driver;
    HomePage homePageFactory;
    InboxPage inboxPageFactory;
    Logger logger = Logger.getLogger(RollingLogger.class);



    @BeforeTest
    private void openBrowser() throws IOException {

        logger.info("Open browser");
        driver = WebDriverSingleton.getDriver();
        driver.get("https://protonmail.com/");
        driver.manage().window().maximize();

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("src\\main\\java\\screenshots\\ProtonMailTestScreenshots\\open_browser.png"));

    }


    @Test(dataProvider = "testDataForLogIn")
    private void logInToBox(User user) throws CannotLoginException, IOException {

        homePageFactory = new HomePage(driver);
        inboxPageFactory = new InboxPage(driver);
        try {
            homePageFactory.clickLoginButton().doLogIn(user);
        } catch (CannotLoginException e) {
            logger.error("Incorrectly data autorization");
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("src\\main\\java\\screenshots\\ProtonMailTestScreenshots\\error_log_in.png"));
        }
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("src\\main\\java\\screenshots\\ProtonMailTestScreenshots\\log_in.png"));

    }

    @Test(dataProvider = "testDataForMail", dependsOnMethods = {"logInToBox"})
    private void createNewMail(Mail mail) throws IOException {

        logger.debug("Create new message");
        inboxPageFactory.createNewMessage(mail);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("src\\main\\java\\screenshots\\ProtonMailTestScreenshots\\create_message.png"));
    }

    @Test(dataProvider = "testDataForMail", dependsOnMethods = {"createNewMail"})
    private void checkingDraftPresence(Mail mail) throws DraftNotFoundException, IOException {

        try {
            inboxPageFactory.veryfySendMessage(mail);
        } catch (DraftNotFoundException e) {
            logger.error("Draft not found");
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("src\\main\\java\\screenshots\\ProtonMailTestScreenshots\\error_send.png"));
        }
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("src\\main\\java\\screenshots\\ProtonMailTestScreenshots\\send_message.png"));
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