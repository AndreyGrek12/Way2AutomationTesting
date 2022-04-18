package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void installDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void openChrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void closeChrome() {
        driver.close();
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

    public WebDriver getDriver () {
        return driver;
    }
}
