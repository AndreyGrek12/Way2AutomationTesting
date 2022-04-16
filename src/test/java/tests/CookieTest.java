package tests;

import helpers.CookieHelper;
import helpers.Data;
import helpers.WindowsUtils;
import io.qameta.allure.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;
import pages.CookieTestingMainPage;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class CookieTest extends BaseTest {

    @Epic("Авторизация")
    @Feature("Вход в профиль")
    @Story("Вход в профиль с использованием Cookie")
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void cookieTesting() throws IOException {
        driver.get(Data.getProperty("siteForCookieTestURL"));
        CookieTestingMainPage cookieTestingMainPage = new CookieTestingMainPage(driver);

        cookieTestingMainPage.enterUsername("Andrusha999")
                .enterPassword("rec!135Hhpf8")
                .pressLoginButton()
                .profileButtonShouldBeVisible();
        CookieHelper.saveCookie(driver, "PHPSESSID");
        driver.manage().deleteAllCookies();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(Data.getProperty("siteForCookieTestURL"));
        WindowsUtils.focusTab(driver, 2);
        CookieHelper.readCookie(driver, "PHPSESSID");
        driver.navigate().refresh();
        cookieTestingMainPage.profileButtonShouldBeVisible();
    }

}
