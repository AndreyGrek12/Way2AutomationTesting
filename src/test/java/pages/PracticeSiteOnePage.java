package pages;

import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracticeSiteOnePage {

    private final WebDriver driver;

    @FindBy (xpath = "//div[@id='load_box']")
    private WebElement registrationForm;

    @FindBy (xpath = "//a[@href='#login']")
    private WebElement signInButton;

    @FindBy (css = "[name='name']")
    private WebElement usernameField;

    @FindBy (css = "#login [name='password']")
    private WebElement passwordField;

    @FindBy (css = "#login [value='Submit']")
    private WebElement submitButton;

    @FindBy (css = "#login #alert1")
    private WebElement successLoginText;

    public PracticeSiteOnePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Проверка видимости регистрационной формы")
    public PracticeSiteOnePage registrationFormShouldBeVisible() {
        Waiters.waitForVisibility(10,driver, registrationForm);
        return this;
    }

    @Step("Переход на форму авторизации")
    public PracticeSiteOnePage openLoginForm() {
        signInButton.click();
        return this;
    }

    @Step("Ввод логина")
    public PracticeSiteOnePage enterUsername(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    @Step("Ввод пароля")
    public PracticeSiteOnePage enterPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Нажатие кнопки логина")
    public PracticeSiteOnePage pressLogin() {
        submitButton.click();
        return this;
    }

    @Step("Проверка видимости текста успешной авторизации")
    public PracticeSiteOnePage successLoginTextShouldBeVisible () {
        Waiters.waitForVisibility(10, driver, successLoginText);
        return this;
    }

    public WebElement getUsernameField () {
        return usernameField;
    }
}
