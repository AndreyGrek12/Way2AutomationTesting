package tests;

import helpers.Data;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AuthorizationPage;
import pages.HomePage;


public class AuthorizationTest extends BaseTest{

    @DataProvider(name = "authorization")
    public static Object[][] authorizationData () {
        return new Object[][] {
                {"angular", "password", "description"},
                {"","password","description"},
                {"angular","","description"},
                {"angular","password",""},
                {"ang","pass","des"}};
    }

    @Epic("Авторизация")
    @Feature("Тест авторизации")
    @Story("Авторизация с разными данными")
    @Test(dataProvider = "authorization")
    @Severity(SeverityLevel.BLOCKER)
    public void authorizationTest (String username, String password, String description) {
        driver.get(Data.getProperty("loginURL"));
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        HomePage homePage = new HomePage(driver);

        authorizationPage.insertUsername(username)
                .insertPassword(password)
                .insertDescription(description);
        if (!username.isEmpty() && !password.isEmpty() && !description.isEmpty()) {
            authorizationPage.pressLoginButton();
            if (username.equals("angular") && password.equals("password") && description.length()>2) {
                homePage.homePageShouldBeVisible();
            } else {
                    authorizationPage.failedLoginElementShouldBeVisible();
            }
        } else {
            Assert.assertFalse(authorizationPage.getLoginButton().isEnabled());
        }
    }
}
