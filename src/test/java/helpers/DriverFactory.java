package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver getDriver() throws MalformedURLException {
        WebDriver driver;
        boolean remote = PropertiesProvider.getBooleanProperty(PropertiesProvider.getProperty("remote"));
        String browser = PropertiesProvider.getProperty("browser");
        if (remote) {
            driver = getRemoteDriver(browser);
        } else {
            driver = getDriver(browser);
        }
        return driver;
    }

    private static WebDriver getDriver (String browserName) {
        System.setProperty("webdriver." + browserName + ".driver", PropertiesProvider.getProperty("driverPath"));
        switch(browserName) {
            case "chrome" :
                return new ChromeDriver();
            case "gecko":
                return new FirefoxDriver();
            case "ie":
                return new InternetExplorerDriver(new InternetExplorerOptions().ignoreZoomSettings()
                        .introduceFlakinessByIgnoringSecurityDomains());
            default:
                throw new RuntimeException("Неверное имя браузера");
        }
    }

    private static WebDriver getRemoteDriver (String browserName) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browserName);
        if (browserName.equals("ie")) {
            capabilities.setCapability("ignoreZoomSetting", true);
            capabilities.setCapability("introduceFlakinessByIgnoringSecurityDomains", true);
        } else if (browserName.equals("chrome")) {
            Map<String, Boolean> options = new HashMap<>();
            options.put("enableVNC", true);
            capabilities.setCapability("selenoid:options", options);
        }
        return new RemoteWebDriver(new URL(PropertiesProvider.getProperty("localhost")), capabilities);
    }
}
