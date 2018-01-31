package tests.proton_mail_test;

import buissnes_object.User;
import exeptions.CannotLoginException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.*;
import utility.RollingLogger;
import utility.WebDriverSingleton;

public class SelectTest {


    WebDriver driver;
    HomePage homePageFactory;
    InboxPage inboxPageFactory;
    SentPage sentPage;
    InterfacePage interfacePage;
    CommonButton commonButton;
    Logger logger = Logger.getLogger(RollingLogger.class);
    private static final String START_URL = "https://protonmail.com/";


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
    private void makeAllMessageUnread() throws InterruptedException {

        commonButton = new CommonButton(driver);
        commonButton.makeMessageUnread();
        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = {"logInToBox"})
    private void makeFirstMessageUnread() throws InterruptedException {

        commonButton = new CommonButton(driver);
        commonButton.makeFirstMessageUnread();
        Thread.sleep(3000);
    }

    @DataProvider
    public Object[][] testDataForLogIn() {

        return new Object[][]{{User.PROTON_LOGIN}};
    }



}
