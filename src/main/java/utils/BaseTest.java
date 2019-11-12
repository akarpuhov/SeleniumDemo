package utils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void BeforeTest(){
        driver = MyWebDriver.getDriver();
    }

    @AfterMethod
    public void AfterMethod(ITestResult result){
        try
        {
            if(result.getStatus() == ITestResult.SUCCESS)
            {
                ATLogger.info("passed **********");
            }

            else if(result.getStatus() == ITestResult.FAILURE)
            {
                ATLogger.error("\n"+ result.getThrowable().getMessage());
                result.getThrowable().printStackTrace();
                MyWebDriver.takeScreenshot();
                ATLogger.info("Failed ***********");
            }
            else if(result.getStatus() == ITestResult.SKIP ){

                ATLogger.info("Skiped ***********");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @AfterClass
    public void AfterClass(){
        MyWebDriver.kill();
    }

}
