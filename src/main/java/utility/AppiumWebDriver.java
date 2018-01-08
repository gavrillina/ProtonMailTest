package utility;

import buissnes_object.MobileCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;



public class AppiumWebDriver {

    public WebDriver driver;

    public WebDriver initRemoteBrowser(String url, MobileCapabilities mobileCapabilities) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, mobileCapabilities.getPlatformName());
        capabilities.setCapability(CapabilityType.VERSION, mobileCapabilities.getVersion());
        capabilities.setCapability("deviceName", mobileCapabilities.getDeviceName());
        capabilities.setCapability("appPackage", mobileCapabilities.getAppPackage());
        capabilities.setCapability("appActivity", mobileCapabilities.getAppActivity());
        try {
            driver = new RemoteWebDriver(new URL(url), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }
}