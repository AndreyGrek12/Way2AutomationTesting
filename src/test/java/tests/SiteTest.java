package tests;

import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;

import static helpers.ActionHelpers.mouseover;
import static helpers.WindowsUtils.*;


class SiteTest extends BaseTest {

    @Test
    public void pageLoadTest(){
        MainPage mainPage = new MainPage(driver);

        mainPage.headShouldBeVisible()
                .horizontalMenuShouldBeVisible()
                .certificationsShouldBeVisible()
                .coursesShouldBeVisible();
        mouseover(driver,mainPage.getNextCourseButton());
        mainPage.courseBannerShouldBeVisible()
                .footerShouldBeVisible();
    }

    @Test
    public void headerAfterScrollingTest() {
        MainPage mainPage = new MainPage(driver);

        mouseover(driver,mainPage.getNextCourseButton());
        mainPage.horizontalMenuShouldBeVisible();
    }

    @Test
    public void menuButtonWorkingTest(){
        MainPage mainPage = new MainPage(driver);

        mouseover(driver,mainPage.getResources());
        mainPage.choosePracticeSiteOne()
                .registrationFormShouldBeVisible();
    }

    @Test
    public void AuthorizationTest() {
        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);

        mouseover(driver,mainPage.getResources());
        mainPage.choosePracticeSiteTwo()
                .openAuthorizationScreen();
        focusTab(driver,2);
        authorizationPage.insertUsername("angular")
                .insertPassword("password")
                .pressLoginButton();
        driver.close();
        focusTab(driver, 1);
    }
}
