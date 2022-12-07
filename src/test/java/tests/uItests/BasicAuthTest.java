package tests.uItests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import helpers.PropertiesProvider;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasicAuthPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BasicAuthTest {

    @BeforeMethod
    public void setup() {
        open(PropertiesProvider.getProperty("basicAuthSiteUrl"),"","httpwatch","httpwatch");
    }

    @Epic("Страницы для практики")
    @Feature("Проверка работы Basic Auth")
    @Story("После аутентификации появляется картинка")
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void displayImageOnClickTest() {
        BasicAuthPage basicAuthPage = new BasicAuthPage();

        basicAuthPage.clickOnDisplayImageButton();
        $("#downloadImg").shouldBe(Condition.visible);
    }

    @AfterMethod
    public void closeDriver() {
        Selenide.closeWebDriver();
    }
}
