package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utility.WebDriverSingleton;

import java.util.concurrent.TimeUnit;


@CucumberOptions(strict = true, tags = "@all", features = "src/main/resources/cucumber.feature", glue = {
        "steps"})

public class ProtonMailCucumberTest extends AbstractTestNGCucumberTests {
    private static WebDriver driver = WebDriverSingleton.getDriver();

    @BeforeClass(description = "Start browser, add implicit wait and maximize window")
    public void startBrowser() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        driver.quit();
    }

}