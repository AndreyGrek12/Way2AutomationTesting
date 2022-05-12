package tests;

import helpers.PropertiesProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void generalSetup() throws MalformedURLException {
        driver = chooseDriver();
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

    public WebDriver chooseDriver () throws MalformedURLException {
        Capabilities capabilities = null;
        boolean remote = PropertiesProvider.getBooleanProperty(PropertiesProvider.getProperty("remote"));
        String browser = PropertiesProvider.getProperty("browser");
        if (!remote) {
            switch (browser) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
                    return new ChromeDriver();
                case "firefox":
                    System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver.exe");
                    return new FirefoxDriver();
                case "internet explorer":
                    System.setProperty("webdriver.ie.driver","src/test/resources/IEDriverServer.exe");
                    return new InternetExplorerDriver(new InternetExplorerOptions().ignoreZoomSettings()
                            .introduceFlakinessByIgnoringSecurityDomains());
                default:
                    throw new RuntimeException("Неверное имя браузера");
            }
        } else {
             switch(browser) {
                case "chrome":
                    capabilities = new ChromeOptions();
                case "firefox":
                    capabilities = new FirefoxOptions();
                case "internet explorer":
                    capabilities = new InternetExplorerOptions()
                            .ignoreZoomSettings()
                            .introduceFlakinessByIgnoringSecurityDomains();
            }
            return new RemoteWebDriver(new URL(PropertiesProvider.getProperty("localhost")), capabilities);
        }
    }
}
