package pf;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CustomConditions;
import utils.MyWebDriver;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AbstractPage {

    protected WebDriver driver;
    private static final int WAIT_SECONDS = 5;

    AbstractPage() {
        this.driver = MyWebDriver.getDriver();
        PageFactory.initElements(driver, this);
    }

    void waitForVisibility(WebElement element){
        new WebDriverWait(driver, WAIT_SECONDS).until(ExpectedConditions.visibilityOfAllElements(element));
    }

    WebElement waitForElementLocatedBy(By by){
        return new WebDriverWait(driver, WAIT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    void waitForElementToBeClickable(WebElement element){
        new WebDriverWait(driver, WAIT_SECONDS).until(ExpectedConditions.elementToBeClickable(element));
    }

    List<WebElement> waitForAllElementsLocatedBy(By by){
        return new WebDriverWait(driver, WAIT_SECONDS).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    void ActionClick(WebElement element){
        new Actions(driver).click(element).build().perform();
    }

    public void waitAjaxIsLoaded(){
        new WebDriverWait(driver, WAIT_SECONDS).until(CustomConditions.jQueryAJAXCompleted());
    }

    protected List<WebElement> waitForAllElementsLocatedFluentBy(By by){
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .withMessage("Timeout for waiting search list of elements was exceeded!");

        return wait.until(driver1 -> driver1.findElements(by));
    }

}
