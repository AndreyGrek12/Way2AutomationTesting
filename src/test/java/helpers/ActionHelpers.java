package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionHelpers {

    public static void mouseover(WebDriver driver, WebElement element) {
        new Actions(driver).moveToElement(element).build().perform();
    }
}
