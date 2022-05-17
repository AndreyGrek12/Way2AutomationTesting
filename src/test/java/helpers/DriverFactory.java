package helpers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    public static WebDriver getDriver() throws MalformedURLException {
        boolean remote = PropertiesProvider.getBooleanProperty(PropertiesProvider.getProperty("remote"));
        String browser = PropertiesProvider.getProperty("browser");
        if (!remote) {
            return getDriver(browser);
        } else {
            return getRemoteDriver(browser);
        }
    }

    private static WebDriver getDriver (String browserName) {
        switch(browserName) {
            case "chrome" :
                System.setProperty(PropertiesProvider.getProperty("chromeProperty"), PropertiesProvider.getProperty("chromeDriverPath"));
                return new ChromeDriver();
            case "firefox":
                System.setProperty(PropertiesProvider.getProperty("firefoxProperty"), PropertiesProvider.getProperty("firefoxDriverPath"));
                return new FirefoxDriver();
            case "internet explorer":
                System.setProperty(PropertiesProvider.getProperty("iEProperty"), PropertiesProvider.getProperty("iEDriverPath"));
                return new InternetExplorerDriver(new InternetExplorerOptions().ignoreZoomSettings()
                        .introduceFlakinessByIgnoringSecurityDomains());
            default:
                return null;
        }
    }

    private static WebDriver getRemoteDriver (String browserName) throws MalformedURLException {
        Capabilities capabilities = null;
        switch(browserName) {
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
