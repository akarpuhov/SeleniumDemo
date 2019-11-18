package com.epam.testing;

import atobjects.Email;
import atobjects.Login;
import org.testng.Assert;
import org.testng.annotations.Test;
import pf.HomePage;
import pf.LoginPage;
import utils.BaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class PFTest extends BaseTest {

//    String login = "at.test@inbox.ru";
//    String password = "saratov01";

    //@Test(description = "Check creating draft email and send it.")
    public void MailRuTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        Login login = new Login();
        loginPage.fillLogin(login.login);
        loginPage.clickEnterPasswordButton();
        loginPage.fillPassword(login.password);
        HomePage homePage =  loginPage.clickEnterButton();
        homePage.waitAjaxIsLoaded();

        Assert.assertTrue(homePage.isPageOpened(), "Home page was not opened.");
        Email email = new Email(true);
        homePage.fillEmail(email);
        homePage.clickSaveDraftButton();
        homePage.clickCloseEmailForm();
        homePage.clickDraftsLink();
        homePage.openEmail(email.Subject);
        // Check opened email

        Assert.assertEquals(homePage.getEmailTo(), email.To, "To field has incorrect value.");
        Assert.assertEquals(homePage.getEmailSubject(), email.Subject, "Subject field has incorrect value.");
        assertThat(homePage.getEmailBody(), containsString(email.Body));

        homePage.clickSendButton();

        homePage.clickClosePopupButton();
        homePage.clickSendEmailsButton();

        homePage.waitAjaxIsLoaded();
        homePage.openEmail(email.Subject);

        Assert.assertEquals(homePage.getToEmailLabel(), email.To, "To field has incorrect value.");
        Assert.assertEquals(homePage.getSubjectEmailLabel(), email.Subject, "Subject field field has incorrect value.");
        assertThat(homePage.getBodyEmailLabel(), containsString(email.Body));

        //homePage.handleToolTip();
    }

    @Test(description = "Check creating draft email and send it.")
    public void BrokenTest() throws Exception {
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        Login login = new Login();
        loginPage.fillLogin(login.login);

        throw new Exception("Custom Error");
//        loginPage.clickEnterPasswordButton();
//        loginPage.fillPassword(login.password);
//        HomePage homePage = loginPage.clickEnterButton();
//
//        Assert.assertTrue(homePage.isPageOpened(), "Home page was not opened.");

    }
}
