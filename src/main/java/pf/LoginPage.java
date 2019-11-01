package pf;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.TestUtils;

public class LoginPage extends AbstractPage {

//    private static final By LOGIN_INPUT_SELECTOR = By.id("mailbox:login");
//    private static final By PASSWORD_INPUT_SELECTOR = By.id("mailbox:password");
//    private static final By SUBMIT_BUTTON_SELECTOR =By.xpath("//input[@value='Ввести пароль']");

    @FindBy(id = "mailbox:login")
    private WebElement loginInput;

    @FindBy(id = "mailbox:password")
    private  WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Ввести пароль']")
    private  WebElement submitButton;

    @FindBy(id = "mailbox:domain")
    private WebElement domainSelect;

//    public LoginPage(WebDriver driver) {
//        super(driver);
//    }

    public void open(){
        driver.get("https://mail.ru");
    }

    public void fillLogin(String login){
        waitForVisibility(loginInput);
        TestUtils.hilightElement(driver, loginInput);
        loginInput.sendKeys(login);
    }

    public void clickEnterPasswordButton() {
        //waitForVisibility(submitButton);
        //submitButton.click();
        //Example with using Actions
        new Actions(driver).sendKeys(submitButton, Keys.ENTER).build().perform();
    }

    public HomePage clickEnterButton() {
        clickEnterPasswordButton();
        return new HomePage();
    }

    public void fillPassword(String password) {
        TestUtils.hilightElement(driver, passwordInput);
        waitForVisibility(passwordInput);
        passwordInput.sendKeys(password);
    }

}
