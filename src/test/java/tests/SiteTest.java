package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.HomePage;
import pages.MainPage;

import static helpers.ActionHelpers.mouseover;
import static helpers.WindowsUtils.*;


class SiteTest extends BaseTest {

    @Epic("Главная страница")
    @Feature("Загрузка главной страницы")
    @Test
    @Severity(SeverityLevel.BLOCKER)
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

    @Epic("Главная страница")
    @Feature("Хедер остается вверху про скроллинге")
    @Test
    @Severity(SeverityLevel.MINOR)
    public void headerAfterScrollingTest() {
        MainPage mainPage = new MainPage(driver);

        mouseover(driver,mainPage.getNextCourseButton());
        mainPage.horizontalMenuShouldBeVisible();
    }

    @Epic("Главная страница")
    @Feature("Кнопки в меню работают")
    @Test
    @Severity(SeverityLevel.BLOCKER)
    public void menuButtonWorkingTest(){
        MainPage mainPage = new MainPage(driver);

        mouseover(driver,mainPage.getResources());
        mainPage.choosePracticeSiteOne()
                .registrationFormShouldBeVisible();
    }

    @Epic("Авторизация")
    @Feature("Проверка работы авторизации")
    @Story("Переход к экрану авторизации с главной страницы")
    @Test
    @Severity(SeverityLevel.BLOCKER)
    public void authorizationTest() {
        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        HomePage homePage = new HomePage(driver);

        mouseover(driver,mainPage.getResources());
        mainPage.choosePracticeSiteTwo()
                .openAuthorizationScreen();
        focusTab(driver,2);
        authorizationPage.insertUsername("angular")
                .insertPassword("password")
                .insertDescription("description")
                .pressLoginButton();
        Assert.assertEquals(homePage.getAuthorizationText(),"You're logged in!!");
        driver.close();
        focusTab(driver, 1);
    }
}
