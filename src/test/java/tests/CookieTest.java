package tests;

import helpers.CookieHelper;
import helpers.Data;
import org.testng.annotations.Test;
import pages.CookieTestingMainPage;

import java.io.IOException;


public class CookieTest extends BaseTest{

    @Test
    public void cookieTesting () throws IOException {
        driver.get(Data.getProperty("siteForCookieTestURL"));
        CookieTestingMainPage cookieTestingMainPage = new CookieTestingMainPage(driver);

        cookieTestingMainPage.enterUsername("Andrusha999")
                .enterPassword("rec!135Hhpf8")
                .pressLoginButton()
                .profileButtonShouldBeVisible();
        CookieHelper.saveCookie(driver, "PHPSESSID");
        cookieTestingMainPage.pressLogoutButton();
        driver.manage().deleteAllCookies();
        CookieHelper.readCookie(driver, "PHPSESSID");
        driver.navigate().refresh();
        cookieTestingMainPage.pressLoginButton()
                .profileButtonShouldBeVisible();
    }
}
