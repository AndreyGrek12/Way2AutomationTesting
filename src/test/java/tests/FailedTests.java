package tests;


import helpers.TestListeners;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;

import static helpers.ActionHelpers.mouseover;
import static helpers.WindowsUtils.focusTab;

@Listeners(TestListeners.class)
public class FailedTests extends BaseTest {

    @Test
    public void failedTestOne () {
        MainPage mainPage = new MainPage(driver);

        Assert.assertFalse(mainPage.getResources().isDisplayed());
    }

    @Test
    public void failedTestTwo () {
        MainPage mainPage = new MainPage(driver);
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
