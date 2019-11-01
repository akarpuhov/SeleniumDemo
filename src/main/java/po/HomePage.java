package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {

    private static final By LOGOUT_LINK_SELECTOR =  By.id("PH_logoutLink");

    public HomePage(WebDriver driver) {
        super(driver);
    }

//    //@Override
//    public void waitPageOpened(){
//        waitForVisibility(LOGOUT_LINK_SELECTOR);
//    }

    public void clickExitButton(){
        waitForVisibility(LOGOUT_LINK_SELECTOR);
        driver.findElement(LOGOUT_LINK_SELECTOR).click();
    }
}
