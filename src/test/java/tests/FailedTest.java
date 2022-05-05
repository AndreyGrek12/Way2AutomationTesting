package tests;

import helpers.TestListeners;
import org.openqa.selenium.firefox.FirefoxOptions;
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
        driver = new RemoteWebDriver(new URL("http://localhost:4444/"), new FirefoxOptions());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        driver.get("https://www.way2automation.com/");
    }

    @Test
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
