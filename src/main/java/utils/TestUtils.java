package utils;

import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class TestUtils {

    public static  void hilightElement(WebDriver driver, WebElement element, String color){
        if(color.isEmpty())
            color = "yellow";
        String bg = element.getCssValue("backgroundColor");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.backgroundColor = '" + color +"'", element);
        //take screenshot here
        //js.executeScript("arguments[0].style.backgroundColor = '" + bg +"'", element);
    }

    public static void hilightElement(WebDriver driver, WebElement element){
        hilightElement(driver, element, "");
    }

}
