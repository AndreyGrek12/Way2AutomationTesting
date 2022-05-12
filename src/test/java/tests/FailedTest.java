package tests;

import helpers.PropertiesProvider;
import helpers.RetryAnalyzer;
import helpers.TestListeners;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AuthorizationPage;
import pages.MainPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static helpers.ActionHelpers.mouseover;
import static helpers.WindowsUtils.focusTab;

@Listeners(TestListeners.class)

public class FailedTest extends BaseTest {

    public MainPage mainPage;

    @BeforeMethod
    public void setup () throws MalformedURLException {
        driver = new RemoteWebDriver(new URL(PropertiesProvider.getProperty("localhost")), new ChromeOptions());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(PropertiesProvider
                .getLongProperty(PropertiesProvider.getProperty("implicitlyWait"))));
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        driver.get(PropertiesProvider.getProperty("w2aURL"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void failedTestOne () {
        Assert.assertFalse(mainPage.getResources().isDisplayed());
    }

    @Test
    public void failedTestTwo () {
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);

        mouseover(driver,mainPage.getResources());
        mainPage.choosePracticeSiteTwo()
                .openAuthorizationScreen();
        focusTab(driver,2);
        authorizationPage.insertUsername("angular")
                .insertPassword("password")
                .insertDescription("description")
                .pressLoginButton()
                .assertVisibility();
        driver.close();
        focusTab(driver, 1);
    }
}
