package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FactoryMethodChrome extends FactoryMethodWebDriver {

    private WebDriver driver;

    @Override
    public WebDriver FactoryMethod() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        return null;
    }
}
