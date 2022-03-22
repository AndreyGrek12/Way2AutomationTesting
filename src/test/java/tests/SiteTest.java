package tests;

import helpers.Waiters;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.PracticeSiteOnePage;
import pages.PracticeSiteTwoPage;

import static helpers.ActionHelpers.mouseover;
import static helpers.WindowsUtils.*;


class SiteTest extends BaseTest {

    @Test
    public void pageLoadTest(){
        MainPage mainPage = new MainPage(driver);

        Assert.assertTrue(mainPage.getHead().isDisplayed());
        Assert.assertTrue(mainPage.getHorizontalMenu().isDisplayed());
        Assert.assertTrue(mainPage.getCertifications().isDisplayed());
        Assert.assertTrue(mainPage.getCourses().isDisplayed());
        mouseover(driver,mainPage.getNextCourseButton()).perform();
        Waiters.waitForVisibility(10,driver,mainPage.getCourseBanner());
        Assert.assertTrue(mainPage.getCourseBanner().isDisplayed());
        Assert.assertTrue(mainPage.getFooter().isDisplayed());
    }

    @Test
    public void headerAfterScrollingTest() {
        MainPage mainPage = new MainPage(driver);

        mouseover(driver,mainPage.getNextCourseButton()).perform();
        Assert.assertTrue(mainPage.getHorizontalMenu().isDisplayed());
    }

    @Test
    public void menuButtonWorkingTest(){
        MainPage mainPage = new MainPage(driver);
        PracticeSiteOnePage practiceSiteOnePage = new PracticeSiteOnePage(driver);

        mouseover(driver,mainPage.getResources()).perform();
        mainPage.choosePracticeSiteOne();
        Assert.assertTrue(practiceSiteOnePage.getRegistrationForm().isDisplayed());
    }

    @Test
    public void AuthorizationTest() {
        MainPage mainPage = new MainPage(driver);
        PracticeSiteTwoPage practiceSiteTwoPage = new PracticeSiteTwoPage(driver);
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);

        mouseover(driver,mainPage.getResources()).perform();
        mainPage.choosePracticeSiteTwo();
        practiceSiteTwoPage.openAuthorizationScreen();
        focusTab(driver,2);
        authorizationPage.insertUsername("angular")
                .insertPassword("password")
                .pressLoginButton();
        driver.close();
        focusTab(driver, 1);
    }
}
