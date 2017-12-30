package steps;

import buissnes_object.Mail;
import buissnes_object.User;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import exeptions.CannotLoginException;
import exeptions.DraftNotFoundException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.HomePage;
import page.InboxPage;
import page.LoginPage;
import utility.WebDriverSingleton;

import java.util.concurrent.TimeUnit;

public class ProtonMailSteps {

    private WebDriver driver = new WebDriverSingleton().getDriver();
    private static final String START_URL = "https://protonmail.com/";




    @Given("^user navigates to ProtonMail home page$")
    public void user_navigates_to_ProtonMail_home_page_and() {
        driver.get(START_URL);
    }

    @When("^click Login button$")
    public void click_Login_button_and() throws CannotLoginException {
        // Login via user-defined method
        new HomePage(driver).clickLoginButton();
        new LoginPage(driver).doLogIn(User.PROTON_LOGIN);

    }

    @And("^user creates new draft \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_creates_new_draft(String sender, String subject, String body) throws DraftNotFoundException {

        Mail mail = new Mail(sender, subject, body);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        new InboxPage(driver).createNewMessage(mail);
    }


    @Then("^user searches sent \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_sends_draft(String sender, String subject, String body) throws DraftNotFoundException {
        Mail mail = new Mail(sender, subject, body);
        new InboxPage(driver).veryfySendMessage(mail);
        driver.quit();

    }
}

