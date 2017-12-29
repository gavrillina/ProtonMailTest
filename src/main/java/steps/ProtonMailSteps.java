package steps;

import buissnes_object.Mail;
import buissnes_object.User;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import exeptions.CannotLoginException;
import exeptions.DraftNotFoundException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import page.HomePage;
import page.InboxPage;
import page.LoginPage;
import utility.WebDriverManager;
import utility.WebDriverSingleton;

public class ProtonMailSteps {

    private static WebDriver driver = new WebDriverSingleton().getDriver();

    private static final String START_URL = "https://protonmail.com/";


    @Given("^user navigates to ProtonMail home page$")
    public void user_navigates_to_ProtonMail_home_page_and() {
        driver.get(START_URL);
    }

    @When("^click Login button$")
    public void click_Login_button_and() {
        // Login via user-defined method
        new HomePage(driver).clickLoginButton();

    }

    @And("^creates new draft "<sender>" "<subject>" "<body>"$")
    public void enter_user_credentials() throws CannotLoginException {
        new LoginPage(driver).doLogIn(User.PROTON_LOGIN);

    }

  @And("^creates new draft \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void create_new_mwssage_and_save(String sender, String subject, String body){
       Mail mail = new Mail(sender,subject,body);
        new InboxPage(driver).createNewMessage(mail);

    }

    @Then("^searches sent \"([^\"]*)\" \"([^\"]*)\"$")
    public void send_message_from_draft(Mail mail) throws DraftNotFoundException {
        new InboxPage(driver).veryfySendMessage(mail);
        }

    }
