package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utility.Highlighter;

public class InterfacePage extends AbstractPage {

    public InterfacePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='tour-layout']/a[2]")
    private WebElement switchViewVertical;

    @FindBy(xpath = "//*[@id='tour-layout']/a[1]")
    private WebElement switchViewGorizontal;

    @FindBy(xpath = "//*[@id='secured-inbox']/div[3]/div[1]/span")
    private WebElement popCapture;


    public void switchViewOnVertical() {

        waitForElementToBeClickable(switchViewVertical);
        Highlighter.highlightElement(driver, switchViewVertical);
        switchViewVertical.click();

        waitForVisibilityOfAllElementsLocatedBy(popCapture);
        Assert.assertEquals("Layout saved", popCapture.getText());
    }

    public void switchViewOnGprizontal() {

        waitForElementToBeClickable(switchViewGorizontal);
        Highlighter.highlightElement(driver, switchViewGorizontal);
        switchViewGorizontal.click();

        waitForVisibilityOfAllElementsLocatedBy(popCapture);
        Assert.assertEquals("Layout saved", popCapture.getText());

    }
}


