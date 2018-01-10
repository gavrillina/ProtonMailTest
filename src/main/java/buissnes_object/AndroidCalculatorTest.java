package buissnes_object;

import buissnes_object.android_objects.MobileCapabilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utility.AppiumWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;

public class AndroidCalculatorTest {

    private WebDriver driver;

    @BeforeTest()
    public void initDriver() throws MalformedURLException {
        MobileCapabilities mobileCapabilities = new MobileCapabilities
                ("Android", "5.1.1", "emulator-5554", "com.android.calculator2", "com.android.calculator2.Calculator");
        driver = new AppiumWebDriver().initRemoteBrowser("http://127.0.0.1:4723/wd/hub", mobileCapabilities);
    }

    @Test
    public void testCal() {
        WebElement two = driver.findElement(By.xpath("//android.widget.Button[@text='2']"));
        two.click();
        WebElement plus = driver.findElement(By.xpath("//android.widget.Button[@text='+']"));
        plus.click();
        WebElement four = driver.findElement(By.xpath("//android.widget.Button[@text='4']"));
        four.click();
        WebElement equalTo = driver.findElement(By.xpath("//android.widget.Button[@text='=']"));
        equalTo.click();
        WebElement results = driver.findElement(By.className("android.widget.EditText"));
        Assert.assertEquals(results.getText(), "6");
    }

    @AfterTest
    public void closeDriver() throws IOException {
        driver.quit();
    }
}

