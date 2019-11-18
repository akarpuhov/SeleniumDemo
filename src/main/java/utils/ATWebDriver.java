package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ATWebDriver {
    private static final int PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS = 15;
    private static final int COMMAND_DEFAULT_TIMEOUT_SECONDS = 10;
    private static final int SCRIPT_EXECUTION_TIMEOUT_SECONDS = 10;
    private static final int WAIT_ELEMENT_TIMEOUT = 10;
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";
    private static WebDriver driver = null;

    public static WebDriver getDriver() {
        if (driver != null) {
            return driver;
        }
        driver = init();
        return driver;
    }

    private static WebDriver init() {
        //System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
//        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.chrome());
        //driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(COMMAND_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        //driver.manage().timeouts().setScriptTimeout(SCRIPT_EXECUTION_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

//    public void get(String url) {
//        MyLogger.info("Going to URL: " + url);
//        driver.get(url);
//    }
//
//    public void waitForElementPresent(By locator) {
//        new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
//    }
//
//    public void waitForElementEnabled(By locator) {
//        new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
//    }
//
//    private void waitForElementVisible(By locator) {
//        new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
//    }
//
//    private void highlightElement(By locator) {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='5px solid green'", driver.findElement(locator));
//    }
//
//    private void unHighlightElement(By locator) {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
//    }
//
//    public void click(final By locator) {
//        waitForElementVisible(locator);
//        MyLogger.info("Clicking element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")");
//        highlightElement(locator);
//        takeScreenshot();
//        unHighlightElement(locator);
//        driver.findElement(locator).click();
//    }
//
//    public void type(final By locator, String text) {
//        waitForElementVisible(locator);
//        highlightElement(locator);
//        MyLogger.info("Typing text '" + text + "' to input form '" + driver.findElement(locator).getAttribute("name") + "' (Located: " + locator + ")");
//        driver.findElement(locator).sendKeys(text);
//        takeScreenshot();
//        unHighlightElement(locator);
//    }
//
//    public void typeInUploadInput(final By locator, String text) {
//        highlightElement(locator);
//        MyLogger.info("Typing text '" + text + "' to input form '" + driver.findElement(locator).getAttribute("name") + "' (Located: " + locator + ")");
//        driver.findElement(locator).sendKeys(text);
//        takeScreenshot();
//        driver.findElement(locator).sendKeys(text);
//    }
//
//    public String read(final By locator) {
//        waitForElementVisible(locator);
//        highlightElement(locator);
//        MyLogger.info("Reading text: " + driver.findElement(locator).getText());
//        takeScreenshot();
//        unHighlightElement(locator);
//        return driver.findElement(locator).getText();
//    }

//    public void dragAndDrop(final By locator, final By targetLocator) {
//        waitForElementVisible(locator);
//        waitForElementVisible(targetLocator);
//        WebElement element = driver.findElement(locator);
//        WebElement target = driver.findElement(targetLocator);
//        takeScreenshot();
//        MyLogger.info("Dragging element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")" +
//                "to '" + driver.findElement(targetLocator).getText() + "' (Located: " + targetLocator + ")");
//        (new Actions(driver)).dragAndDrop(element, target).perform();
//    }
//
//    public void selectSeveralElements(List<By> locators) {
//        Actions action = new Actions(driver);
//        action.keyDown(Keys.CONTROL);
//        WebElement element;
//        for (By locator : locators) {
//            waitForElementVisible(locator);
//            highlightElement(locator);
//            MyLogger.info("Clicking element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")");
//            element = driver.findElement(locator);
//            action.moveToElement(element).click();
//        }
//        takeScreenshot();
//        action.keyUp(Keys.CONTROL).perform();
//    }

//    public void selectItems(By firstLocator, By lastLocator) {
//        new Actions(driver).clickAndHold(driver.findElement(firstLocator)).moveToElement(driver.findElement(lastLocator)).release().build().perform();
//        takeScreenshot();
//    }
//
//    public void resize(By sizeHandleLocator, int xOffset, int yOffset) {
//        WebElement handle = driver.findElement(sizeHandleLocator);
//        new Actions(driver).clickAndHold(handle).moveByOffset(xOffset, yOffset).release(handle).build().perform();
//        takeScreenshot();
//    }

//    public boolean isDisplayed(By locator) {
//        boolean succeed = driver.findElements(locator).size() > 0;
//        if (succeed) {
//            MyLogger.info("Element " + driver.findElement(locator).getText() + " is present.");
//            highlightElement(locator);
//            takeScreenshot();
//            unHighlightElement(locator);
//        } else MyLogger.error("Element " + driver.findElement(locator).getText() + " is not present.");
//        return succeed;
//    }

//    public void refresh() {
//        driver.navigate().refresh();
//    }

    static void takeScreenshot() {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            String scrPath = screenshotName + ".jpg";
            File copy = new File(scrPath);
            FileUtils.copyFile(screenshot, copy);
            ATLogger.info("Saved screenshot: " + screenshotName);
            //MyLogger.attach(scrPath, "Screenshot");
        } catch (IOException e) {
            ATLogger.error("Failed to make screenshot");
        }
    }

    public static void kill() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }
//
//    public void switchToFrame() {
//        driver.switchTo().frame(0);
//    }
}
