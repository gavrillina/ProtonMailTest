package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.Highlighter;

public class InterfacePage extends AbstractPage {

    public InterfacePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='tour-layout']/a[2]")
    private WebElement switchViewVertical;

    @FindBy(xpath = "//*[@id='tour-layout']/a[1]")
    private WebElement switchViewGorizontal;


    public void switchViewOnVertical() {

        waitForElementToBeClickable(switchViewVertical);
        Highlighter.highlightElement(driver, switchViewVertical);
        switchViewVertical.click();
    }

    public void switchViewOnGprizontal() {

        waitForElementToBeClickable(switchViewGorizontal);
        Highlighter.highlightElement(driver, switchViewGorizontal);
        switchViewGorizontal.click();
    }
}


