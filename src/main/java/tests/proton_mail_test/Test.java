package tests.proton_mail_test;

import buissnes_object.User;
import exeptions.CannotLoginException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import page.*;
import utility.BaseTest;
import utility.RollingLogger;
import utility.WebDriverSingleton;

public class Test extends BaseTest {


    WebDriver driver;
    HomePage homePageFactory;
    InboxPage inboxPageFactory;
    SentPage sentPage;
    Logger logger = Logger.getLogger(RollingLogger.class);
    private static final String START_URL = "https://protonmail.com/";

    @BeforeTest
    private void openBrowser(User user) throws Exception {

        logger.info("Open browser");
        driver = WebDriverSingleton.getDriver();
        driver.get(START_URL);
        driver.manage().window().maximize();
        homePageFactory = new HomePage(driver);
        inboxPageFactory = new InboxPage(driver);
        try {
            homePageFactory.clickLoginButton().doLogIn(user);
            driver.get(properties.getProperty("login", "password"));
        } catch (CannotLoginException e) {
            logger.error("Incorrectly data autorization");
        }

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
