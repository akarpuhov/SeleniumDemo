package pf;

import atobjects.Email;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ATLogger;
import utils.TestUtils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class HomePage extends AbstractPage {

    //private static final By LOGOUT_LINK_SELECTOR =  By.id("PH_logoutLink");

    @FindBy(id = "PH_logoutLink")
    private WebElement logoutLink;

    @FindBy(xpath = "//span[@class='compose-button__txt']")
    private WebElement writeEmail;

    @FindBy(xpath = "//div[@data-name='to']//input")
    private  WebElement emailToInput;

    @FindBy(xpath = "//div[@data-name='to']//span")
    private  WebElement emailToLabel;

    @FindBy(xpath = "//input[@name='Subject']")
    private WebElement emailSubjectInput;

    @FindBy(xpath = "//div[@contenteditable='true' and @role='textbox']")
    private WebElement emailBodyDiv;

    @FindBy(xpath = "//span[text()='Сохранить']")
    private WebElement saveDraftButton;

    @FindBy(xpath = "//button[@title='Закрыть']")
    private WebElement closeEmailButton;

    @FindBy(xpath = "//span[@title='Закрыть']")
    private WebElement closePopupButton;

    @FindBy(xpath = "//div[text()='Черновики']")
    private WebElement draftsLink;

    @FindBy(xpath = "//div[text()='Отправленные']")
    private WebElement sendEmailsButton;

    @FindBy(xpath = "//span[text()='Отправить']")
    private WebElement sendButton;

    @FindBy(xpath = "//a[contains(text(), 'Письмо')]")
    private WebElement emailButton;

    @FindBy(xpath = "data-test-id='tooltip-root")
    public WebElement tooltipPopup;

    @FindBy(xpath = "//h2[@class='thread__subject']")
    private WebElement subjectEmailLabel;

    @FindBy (xpath = "//span[@class='letter__contact-item']")
    private WebElement toEmailLabel;

    @FindBy(xpath = "//div[@class='letter-body']")
    private WebElement bodyEmailLabel;

    public String getSubjectEmailLabel(){
        return subjectEmailLabel.getText();
    }

    public String getToEmailLabel(){
        toEmailLabel = waitForElementLocatedBy(By.xpath("//span[@class='letter__contact-item']"));
        return toEmailLabel.getAttribute("title");
    }

    public String getBodyEmailLabel(){
        return bodyEmailLabel.getText();
    }

    public void fillEmail(Email email){
        waitForElementToBeClickable(writeEmail);
        writeEmail.click();
        emailToInput = waitForElementLocatedBy(By.xpath("//div[@data-name='to']//input"));
        emailToInput.sendKeys(email.To);
        emailSubjectInput.sendKeys(email.Subject);
        emailBodyDiv.sendKeys(email.Body);
    }

    public void clickExitButton(){
        waitForVisibility(logoutLink);
        //Using Actions instead of click() method
        new Actions(driver).doubleClick(logoutLink).build().perform();
    }

    public void clickWriteEmailButton(){
        ATLogger.info("Сlick WriteEmailButton.");
        waitForVisibility(logoutLink);
        writeEmail.click();
    }

    public void clickSaveDraftButton(){
        saveDraftButton.click();
    }

    public boolean isPageOpened(){
        waitForVisibility(logoutLink);
        return !driver.findElements( By.id("PH_logoutLink") ).isEmpty();
    }

    public void clickCloseEmailForm() {
        closeEmailButton.click();
    }

    public void clickDraftsLink(){
        TestUtils.hilightElement(driver, draftsLink);
        ATLogger.info("Click DraftsLink.");
        ActionClick(draftsLink);
    }

    public void openEmail(String subject) {
        List<WebElement> elements = waitForAllElementsLocatedBy(By.xpath("//span[@class='llc__subject']"));
        for(WebElement element: elements){
            waitForElementToBeClickable(element);
            if(element.getText().equals(subject)) {
                TestUtils.hilightElement(driver, element, "red");
                element.click();
                return;
            }
        }
        ATLogger.error("Email with subject '" + subject + "' was not found.");
//        var element = elements.stream().filter(elem -> subject.equals(elem.getText())).findAny().orElse(null);
//        element.click();
    }

    public boolean findEmailBySubject(String subject){
        ATLogger.info("Find email by subject.");
        List<WebElement> elements = driver.findElements(By.xpath("//span[@class='llc__subject']"));
        for(WebElement element: elements){
            if(element.getText().equals(subject)) {
                ATLogger.info("Founded element" + element.toString());
                return true;
            }
        }
        ATLogger.error("Element with subject {} was not found...");
        return false;
    }

    public  String getEmailTo(){
        ATLogger.info("Read emailSubjectInput");
        emailToLabel = waitForElementLocatedBy(By.xpath("//div[@data-name='to']//span"));
        return emailToLabel.getText();
    }

    public String getEmailSubject(){
        ATLogger.info("Read emailSubjectInput");
        emailSubjectInput = waitForElementLocatedBy(By.xpath("//input[@name='Subject']"));
        return emailSubjectInput.getAttribute("value");
    }

    public String getEmailBody() {
        ATLogger.info("Read emailBodyDiv");
        emailBodyDiv = waitForElementLocatedBy(By.xpath("//div[@contenteditable='true' and @role='textbox']"));
        return  emailBodyDiv.getText();
    }

    public void clickSendButton() {
        ATLogger.info("Click send button");
        sendButton.click();
    }

    public void clickClosePopupButton(){
        ATLogger.info("Click close popup button");
        waitForElementToBeClickable(closePopupButton);
        closePopupButton.click();
    }

    public void clickSendEmailsButton(){
        ATLogger.info("Click send emails button");
        waitForElementToBeClickable(sendEmailsButton);
        sendEmailsButton.click();
    }

    public void WaitTooltipPopupAndClose()  {
        WebElement dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("dynamicElement_id")));
    }

    public void clickOpenJustSentEmailButton() {
        emailButton.click();
    }

    public void handleToolTip() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        WebElement tooltip = wait.until(driver -> driver.findElement(By.xpath("//div[@data-test-id='tooltip-root']")));
        if(tooltip != null){
            tooltip.findElement(By.xpath("//div[@data-test-id='close']")).click();
        }
    }

    /*public getEmailBody(String body){
        var elements = driver.findElements(by.xpath("//div[@id='toast-container']//*"));
        String html = driver.findElement(By.id("toast-container")).getAttribute("outerHTML");
    }*/

//    public boolean checkEmail(Email email){
//        waitForVisibility(emailToInput);
//        emailToLabel.getText() ;
//        emailSubjectInput.sendKeys(email.Subject);
//        emailBodyDiv.sendKeys(email.Body);
//    }
}
