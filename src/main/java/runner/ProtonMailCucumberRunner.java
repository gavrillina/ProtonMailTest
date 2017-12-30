package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utility.WebDriverManager;
import utility.WebDriverSingleton;


import java.util.concurrent.TimeUnit;


@CucumberOptions(strict = true, tags = "@all", features = "src/main/resources/cucumber.feature", glue = {
        "steps"})

public class ProtonMailCucumberRunner extends AbstractTestNGCucumberTests {

    private WebDriver driver;

    @BeforeTest(description = "Start browser, add implicit wait and maximize window")
    public void startBrowser() {

    }


    @AfterTest(description = "Stop Browser")
    public void stopBrowser() {

    }

}
