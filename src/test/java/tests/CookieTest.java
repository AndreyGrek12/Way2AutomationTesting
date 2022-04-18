package tests;

import helpers.CookieHelper;
import helpers.PropertiesProvider;
import helpers.WindowsUtils;
import io.qameta.allure.*;
import org.openqa.selenium.WindowType;
import org.testng.annotations.*;
import pages.CookieTestingMainPage;
import java.io.IOException;

public class CookieTest extends BaseTest {

    @BeforeMethod
    public void openURL() {
        driver.get(PropertiesProvider.getProperty("siteForCookieTestURL"));
    }

    @Epic("Авторизация")
    @Feature("Вход в профиль")
    @Story("Вход в профиль с использованием Cookie")
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void cookieTesting() throws IOException {
        CookieTestingMainPage cookieTestingMainPage = new CookieTestingMainPage(driver);

        cookieTestingMainPage.enterUsername(PropertiesProvider.getProperty("Username"))
                .enterPassword(PropertiesProvider.getProperty("Password"))
                .pressLoginButton()
                .profileButtonShouldBeVisible();
        CookieHelper.saveCookie(driver, "PHPSESSID");
        driver.manage().deleteAllCookies();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(PropertiesProvider.getProperty("siteForCookieTestURL"));
        WindowsUtils.focusTab(driver, 2);
        CookieHelper.readCookie(driver, "PHPSESSID");
        driver.navigate().refresh();
        cookieTestingMainPage.profileButtonShouldBeVisible();
    }
}
