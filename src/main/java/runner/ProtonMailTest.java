package runner;

import buissnes_object.Mail;
import buissnes_object.User;
import org.testng.annotations.*;
import page.*;
import exeptions.CannotLoginException;
import exeptions.DraftNotFoundException;
import org.openqa.selenium.WebDriver;
import utility.WebDriverSingleton;


public class ProtonMailTest {
    WebDriver driver;
    HomePage homePageFactory;
    InboxPage inboxPageFactory;


    @BeforeTest
    private void openBrowser() {

        //driver=new WebDriverSingleton().getRemotedriver("10.12.12.191");

        driver = new WebDriverSingleton().getDriver();
        // by FactoryMethod
        // driver = new FactoryMethodChrome().FactoryMethod();
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