package tests;

import helpers.DriverFactory;
import helpers.PropertiesProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.time.Duration;


public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void generalSetup() throws MalformedURLException {
        driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(PropertiesProvider
                .getLongProperty(PropertiesProvider.getProperty("implicitlyWait"))));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

    public WebDriver getDriver () {
        return driver;
    }

}
