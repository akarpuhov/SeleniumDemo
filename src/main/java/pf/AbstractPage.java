package pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyWebDriver;

public class AbstractPage {

    protected WebDriver driver;
    private static final int WAIT_SECONDS = 10;

    protected AbstractPage() {
        this.driver = MyWebDriver.getDriver();
        PageFactory.initElements(driver, this);
    }

    protected void waitForVisibility(WebElement element){
        new WebDriverWait(driver, WAIT_SECONDS).until(ExpectedConditions.visibilityOfAllElements(element));
    }

}
