package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class BasicAuthPage {

    @Step("Нажатие кнопки Display image")
    public BasicAuthPage clickOnDisplayImageButton() {
        $("#displayImage").click();
        return this;
    }
}
