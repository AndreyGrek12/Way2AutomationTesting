package tests.uItests;

import com.codeborne.selenide.Condition;
import helpers.PropertiesProvider;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AlertPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AlertTest {

    @BeforeMethod
    public void setup() {
        open(PropertiesProvider.getProperty("alertSiteURL"));
    }

    @Epic("Страницы для практики")
    @Feature("Проверка работы Alert")
    @Story("Проверка появления корректного текста")
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void inputAlertTest () {
        String textToSend = "Andrey";
        AlertPage alertPage = new AlertPage();

        alertPage.clickOnInputAlert()
                .changeFrame()
                .clickOnDemonstrateButton()
                .sendKeysToAlert(textToSend);
        $("#demo").shouldHave(Condition.text("Hello " + textToSend + "! How are you today?"));
    }
}
