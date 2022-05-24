package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class AlertPage {

    @Step("Переключение на вкладку Input Alert")
    public AlertPage clickOnInputAlert() {
        $("[href='#example-1-tab-2']").click();
        return this;
    }

    @Step("Переключение фрейма")
    public AlertPage changeFrame() {
        Selenide.switchTo().frame($("[src='alert/input-alert.html']"));
        return this;
    }

    @Step("Нажатие кнопки вызова алерта")
    public AlertPage clickOnDemonstrateButton () {
        $("[onclick='myFunction()']").click();
        return this;
    }

    @Step("Отправка текста в алерт")
    public AlertPage sendKeysToAlert(String text) {
        Selenide.switchTo().alert().sendKeys(text);
        Selenide.switchTo().alert().accept();
        return this;
    }
}
