package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class ActionHelpers {

    public static Action mouseover(WebDriver driver, WebElement element) {
        Actions builder = new Actions(driver);
        builder.moveToElement(element);
        return builder.build();
    }
}
