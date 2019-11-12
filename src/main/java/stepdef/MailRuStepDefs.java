package stepdef;
//import cucumber.api.java.en.Then;

import atobjects.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pf.HomePage;
import pf.LoginPage;

public class MailRuStepDefs {

    private HomePage homePage;
    private LoginPage loginPage;

    @Given("I open mail.ru site")
    public void iOpenMailRuSite(){
        loginPage = new LoginPage();
        loginPage.open();
    }

    @When("I login in my account with {string} and {string}")
    public void iLoginInMyaccount(String loginStr, String password){
        Login login = new Login(loginStr, password);
        loginPage.fillLogin(login.login);
        loginPage.clickEnterPasswordButton();
        loginPage.fillPassword(login.password);
        homePage =  loginPage.clickEnterButton();
        homePage.waitAjaxIsLoaded();


    }

    @Then("Open home page")
    public void openedHomePage(){
        Assert.assertTrue(homePage.isPageOpened(), "Home page was not opened.");
    }

}
