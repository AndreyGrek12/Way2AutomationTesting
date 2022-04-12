package tests;


import helpers.TestListeners;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;

@Listeners(TestListeners.class)

public class FailedTest extends BaseTest {

    @Test
    public void failedTestOne () {
        MainPage mainPage = new MainPage(driver);

        Assert.assertFalse(mainPage.getResources().isDisplayed());
    }

    @Test
    public void failedTestTwo () {
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);

        driver.get("https://www.way2automation.com/angularjs-protractor/registeration/#/login");
        authorizationPage.pressLoginButton();
    }
}
