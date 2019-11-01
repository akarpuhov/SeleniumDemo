package com.epam.testing;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import po.HomePage;
import po.LoginPage;
import utils.BaseTest;

import java.lang.Thread;
import java.util.concurrent.TimeUnit;

public class POTest extends BaseTest {

    WebDriver driver;

    String login = "at.test@inbox.ru";
    String password = "saratov01";

    //@BeforeClass
    public void BeforeTest(){
        //System.setProperty("webdriver.chrome.driver", "C:\\ProgramData\\ChromeWebDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    //@Test(description = "Open mail.ru test")
    public void Test1() throws InterruptedException{
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.filLogin(login);
        loginPage.clickEnterPasswordButton();
        loginPage.fillPassword(password);
        HomePage homePage =  loginPage.clickEnterButton();

        Thread.sleep(1000);
        homePage.clickExitButton();
        System.out.println(homePage.getTitle());
    }

    @Test
    public void Test2() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        throw  new Exception("Error");
    }

    //@AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
