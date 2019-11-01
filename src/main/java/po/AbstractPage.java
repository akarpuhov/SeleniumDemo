package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {

    protected WebDriver driver;
    public static final int WAIT_SECONDS = 10;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    protected void waitForVisibility(By locator){
        new WebDriverWait(driver, WAIT_SECONDS).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public String getTitle(){
        return  driver.getTitle();
    }

    protected boolean isElementPresented(By by){
        return !driver.findElements(by).isEmpty();
    }
}
