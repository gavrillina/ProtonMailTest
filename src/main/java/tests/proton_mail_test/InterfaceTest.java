package tests.proton_mail_test;

import buissnes_object.User;
import exeptions.CannotLoginException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import page.*;
import utility.BaseTest;
import utility.RollingLogger;
import utility.WebDriverSingleton;

public class InterfaceTest extends BaseTest{

    WebDriver driver;
    HomePage homePageFactory;
    InboxPage inboxPageFactory;
    SentPage sentPage;
    InterfacePage interfacePage;
    CommonButton commonButton;
    Logger logger = Logger.getLogger(RollingLogger.class);
    private static final String START_URL = "https://protonmail.com/";

    @BeforeClass
    private void openBrowser() throws Exception {

        logger.info("Open browser");
        driver = WebDriverSingleton.getDriver();
        driver.get(START_URL);
        driver.manage().window().maximize();

    }

    @Test(dataProvider = "testDataForLogIn")
    private void logInToBox(User user) throws Exception {

        logger.info("Log In");
        homePageFactory = new HomePage(driver);
        inboxPageFactory = new InboxPage(driver);
        try {
            homePageFactory.clickLoginButton().doLogIn(user);
        } catch (CannotLoginException e) {
            logger.error("Incorrectly data autorization");
        }
    }

    @Test(dependsOnMethods = {"logInToBox"})
    private void switchLoyout() {

        interfacePage = new InterfacePage(driver);
        interfacePage.switchViewOnVertical();
        interfacePage.switchViewOnGprizontal();

    }

    @DataProvider
    public Object[][] testDataForLogIn() {

        return new Object[][]{{User.PROTON_LOGIN}};
    }

    @AfterClass
    private void closeBrowser() {

        driver.quit();
    }
}
