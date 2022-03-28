package helpers;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import ru.yandex.qatools.ashot.AShot;

public class TestListeners implements ITestListener {

    private final WebDriver driver;

    public TestListeners(WebDriver driver) {
        this.driver = driver;
    }

    public void onTestFailure(ITestListener Result) {
        new AShot().takeScreenshot(driver);
    }
}
