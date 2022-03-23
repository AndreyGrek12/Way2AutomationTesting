package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
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
    @Story("")
    @Test(dataProvider = "authorization")
    @Severity(SeverityLevel.BLOCKER)
    public void authorizationTest (String username, String password, String description) {
        driver.get("https://www.way2automation.com/angularjs-protractor/registeration/#/login");
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        HomePage homePage = new HomePage(driver);

        authorizationPage.insertUsername(username)
                .insertPassword(password)
                .insertDescription(description);
        if (!username.isEmpty() && !password.isEmpty() && !description.isEmpty()) {
            authorizationPage.pressLoginButton();
            if (username.equals("angular") && password.equals("password") && description.length()>2) {
                Assert.assertEquals(homePage.getAuthorizationText(),"You're logged in!!");
            } else {
                    authorizationPage.checkAuthorizationFail();
            }
        } else {
            Assert.assertFalse(authorizationPage.getLoginButton().isEnabled());
        }
    }
}
