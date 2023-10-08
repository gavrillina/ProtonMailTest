package tests.proton_mail_test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import page.*;
import utility.BaseTest;
import utility.RollingLogger;


public class InterfaceTest extends BaseTest {

    WebDriver driver;
    InterfacePage interfacePage;
    Logger logger = Logger.getLogger(RollingLogger.class);

    @Test
    private void switchLoyout() {
        logger.info("Change layout");
        interfacePage = new InterfacePage(driver);
        interfacePage.switchViewOnVertical();
        interfacePage.switchViewOnGprizontal();

    }


}
