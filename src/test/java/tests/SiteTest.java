package tests;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.HomePage;
import pages.MainPage;

import static helpers.ActionHelpers.mouseover;
import static helpers.WindowsUtils.*;


class SiteTest extends BaseTest {

    public MainPage mainPage;


    @BeforeMethod
    public void initializingMainPage () {
        mainPage = new MainPage(driver);
    }


    @Epic("Главная страница")
    @Feature("Основные элементы главной страницы")
    @Story("Основные элементы главной страницы загружаются")
    @Test
    @Severity(SeverityLevel.BLOCKER)
    public void pageLoadTest(){
        mainPage.headShouldBeVisible()
                .horizontalMenuShouldBeVisible()
                .certificationsShouldBeVisible()
                .coursesShouldBeVisible();
        mouseover(driver,mainPage.getNextCourseButton());
        mainPage.courseBannerShouldBeVisible()
                .footerShouldBeVisible();
    }

    @Epic("Главная страница")
    @Feature("Основные элементы главной страницы")
    @Story("Хедер остается вверху про скроллинге")
    @Test
    @Severity(SeverityLevel.MINOR)
    public void headerAfterScrollingTest() {
        mouseover(driver,mainPage.getNextCourseButton());
        mainPage.horizontalMenuShouldBeVisible();
    }

    @Epic("Главная страница")
    @Feature("Основные элементы главной страницы")
    @Story("Кнопки в меню работают")
    @Test
    @Severity(SeverityLevel.BLOCKER)
    public void menuButtonWorkingTest(){
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
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);

        mouseover(driver,mainPage.getResources());
        mainPage.choosePracticeSiteTwo()
                .openAuthorizationScreen();
        focusTab(driver,2);
        authorizationPage.insertUsername("angular")
                .insertPassword("password")
                .insertDescription("description")
                .pressLoginButton()
                .homePageShouldBeVisible();
        driver.close();
        focusTab(driver, 1);
    }
}
