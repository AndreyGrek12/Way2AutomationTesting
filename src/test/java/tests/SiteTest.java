package tests;

import helpers.JsHelper;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.PracticeSiteOnePage;

import static helpers.ActionHelpers.mouseover;
import static helpers.WindowsUtils.*;

public class SiteTest extends BaseTest{

    public MainPage mainPage;

    @BeforeMethod
    public void initializingPageObjectAndOpenURL () {
        mainPage = new MainPage(driver);
        driver.get("https://www.way2automation.com/");
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

    @Test
    public void JavascriptTest () {
        PracticeSiteOnePage practiceSiteOnePage = new PracticeSiteOnePage(driver);

        mouseover(driver,mainPage.getResources());
        mainPage.choosePracticeSiteOne()
                .getUsernameField()
                .click();
        JsHelper.blurElement(driver, practiceSiteOnePage.getUsernameField());
        Assert.assertNotEquals(driver.switchTo().activeElement(), practiceSiteOnePage.getUsernameField(),
                "Фокусирование не исчезло");
    }

    @Test
    public void scrollTest () {
        Assert.assertTrue(JsHelper.checkPageScroll(driver), "Странится не скроллится");
    }
}
