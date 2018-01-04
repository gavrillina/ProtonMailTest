package tests;

import buissnes_object.Mail;
import buissnes_object.User;
import exeptions.CannotLoginException;
import exeptions.DraftNotFoundException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.InboxPage;
import utility.FactoryMethodChrome;


public class ProtonMailTest {
    WebDriver driver;
    HomePage homePageFactory;
    InboxPage inboxPageFactory;


    @BeforeTest
    private void openBrowser() {

        //driver=new WebDriverSingleton().getRemotedriver("10.12.12.191");


        // by FactoryMethod
        driver = new FactoryMethodChrome().FactoryMethod();
        driver.get("https://protonmail.com/");
        driver.manage().window().maximize();
    }


    @Test(dataProvider = "testDataForLogIn")
    private void logInToBox(User user) throws CannotLoginException {

        homePageFactory = new HomePage(driver);
        inboxPageFactory = new InboxPage(driver);
        homePageFactory.clickLoginButton().doLogIn(user);

    }

    @Test(dataProvider = "testDataForMail", dependsOnMethods = {"logInToBox"})
    private void createNewMail(Mail mail) {

        inboxPageFactory.createNewMessage(mail);
    }

    @Test(dataProvider = "testDataForMail", dependsOnMethods = {"createNewMail"})
    private void checkingDraftPresence(Mail mail) throws DraftNotFoundException {

        inboxPageFactory.veryfySendMessage(mail);
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