package tests.proton_mail_test;

import exeptions.MessageListIsVoid;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import page.*;
import utility.BaseTest;
import utility.RollingLogger;
import org.testng.annotations.Test;

public class SelectTest extends BaseTest {


    WebDriver driver;
    CommonButton commonButton;
    Logger logger = Logger.getLogger(RollingLogger.class);


    @Test
    private void makeAllMessageUnread() throws InterruptedException, MessageListIsVoid {

        logger.info("Select message and make it unread");
        commonButton = new CommonButton(driver);
        try {
            commonButton.makeMessageUnread();
        } catch (MessageListIsVoid e) {
            logger.error("There aren't any message");
        }
        Thread.sleep(3000);
    }

    @Test
    private void makeFirstMessageUnread() throws InterruptedException {

        logger.info("Select all message and male them unread");
        commonButton = new CommonButton(driver);

        try {
            commonButton.makeFirstMessageUnread();
        } catch (MessageListIsVoid e) {
            logger.error("There aren't any message");
        }
        Thread.sleep(3000);
    }


}
