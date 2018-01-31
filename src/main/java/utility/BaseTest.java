package utility;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public Properties properties;

    public WebDriver initializeDriver() {
        properties = new Properties();

        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String browserType = properties.getProperty("browser");

        driver = WebDriverSingleton.getDriver();                        // <== singleton
        //driver = WebDriverFactory.createDriverByType(browserType);    // <== factory
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

}
