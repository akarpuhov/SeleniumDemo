package utils;
import org.apache.tika.sax.TextContentHandler;
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
                MyLogger.info("passed **********");
            }

            else if(result.getStatus() == ITestResult.FAILURE)
            {
                MyWebDriver.takeScreenshot();
                MyLogger.info("Failed ***********");
            }
            else if(result.getStatus() == ITestResult.SKIP ){

                MyLogger.info("Skiped ***********");
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
