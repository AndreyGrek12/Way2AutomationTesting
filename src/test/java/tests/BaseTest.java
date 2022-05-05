package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public abstract class BaseTest {

    protected WebDriver driver;

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

    public WebDriver getDriver () {
        return driver;
    }
}
