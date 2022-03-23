package pages;

import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorizationPage {

    private final WebDriver driver;

    @FindBy (css = "#username")
    private WebElement usernameField;

    @FindBy (css = "#password")
    private WebElement passwordField;

    @FindBy (css = "#formly_1_input_username_0")
    private WebElement descriptionField;

    @FindBy (css = ".btn")
    private WebElement loginButton;

    @FindBy (xpath = "//div[@ng-if='Auth.error']")
    private WebElement loginErrorText;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step ("Ввод логина")
    public AuthorizationPage insertUsername(String username) {
        Waiters.waitForVisibility(10,driver,usernameField);
        usernameField.sendKeys(username);
        return this;
    }

    @Step ("Ввод пароля")
    public AuthorizationPage insertPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    @Step ("Ввод описания")
    public AuthorizationPage insertDescription(String description) {
        descriptionField.sendKeys(description);
        return this;
    }

    @Step ("Нажатие кнопки логина")
    public HomePage pressLoginButton() {
        loginButton.click();
        return new HomePage(driver);
    }

    @Step ("Проверка видимости поля ошибки авторизации")
    public AuthorizationPage checkAuthorizationFail () {
        Waiters.waitForVisibility(10,driver,loginErrorText);
        return this;
    }

    public WebElement getLoginButton () {
        return loginButton;
    }

}
