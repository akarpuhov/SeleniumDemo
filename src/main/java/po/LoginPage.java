package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LoginPage extends AbstractPage {

    private static final By LOGIN_INPUT_SELECTOR = By.id("mailbox:login");
    private static final By PASSWORD_INPUT_SELECTOR = By.id("mailbox:password");
    private static final By SUBMIT_BUTTON_SELECTOR =By.xpath("//input[@value='Ввести пароль']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open(){
        driver.get("https://mail.ru");
    }

    public void filLogin(String login){
        waitForVisibility(LOGIN_INPUT_SELECTOR);
        driver.findElement(LOGIN_INPUT_SELECTOR).sendKeys(login);
        Select domainSelect = new Select(driver.findElement(By.id("mailbox:domain"))); // ???
    }

    public void clickEnterPasswordButton() {
        //waitForVisibility(SUBMIT_BUTTON_SELECTOR);
        driver.findElement(SUBMIT_BUTTON_SELECTOR).click();
    }

    public HomePage clickEnterButton() {
        clickEnterPasswordButton();
        return new HomePage(driver);
    }


    public void fillPassword(String password){
        waitForVisibility(PASSWORD_INPUT_SELECTOR);
        driver.findElement(PASSWORD_INPUT_SELECTOR).sendKeys(password);
    }

}
