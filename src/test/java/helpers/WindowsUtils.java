package helpers;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class WindowsUtils {

    public static void focusTab(WebDriver driver,int tabNumber) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabNumber - 1));
    }

}
