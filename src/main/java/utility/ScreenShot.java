package utility;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;

public class ScreenShot implements ITestListener{

    WebDriver driver = WebDriverSingleton.getDriver();
    static Logger logger = LogManager.getLogger(Listeners.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
//        logger.error("'{}' test has failed", iTestResult.getName());
//        String methodName = iTestResult.getName().toString().trim();
//        takeScreenShot(driver, methodName);

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    public  void takeScreenShot(WebDriver driver, String methodName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        try {
            FileUtils.copyFile(takesScreenshot.getScreenshotAs(OutputType.FILE),
                    new File("src/main/java/screenshots/ProtonMailTestScreenshots" + methodName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Screenshot for {} taken", methodName);

    }
}

