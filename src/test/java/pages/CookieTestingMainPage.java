package pages;

import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CookieTestingMainPage {

    private final WebDriver driver;

    @FindBy (css = "[colspan='2'] [name='login']")
    private WebElement usernameField;

    @FindBy (css = "[colspan='2'] [type='password']")
    private WebElement passwordField;

    @FindBy (css = "[name='subm1']")
    private WebElement submitButton;

    @FindBy (css = "[class='none'][href='/personal.php']")
    private WebElement profileButton;

    @FindBy (css = "[href='/logout.php']")
    private WebElement logoutButton;

    public CookieTestingMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step ("Ввод имени пользователя")
    public CookieTestingMainPage enterUsername (String username) {
        usernameField.sendKeys(username);
        return this;
    }

    @Step ("Ввод пароля")
    public CookieTestingMainPage enterPassword (String password) {
        passwordField.sendKeys(password);
        return this;
    }

    @Step ("Нажатие кнопки логина")
    public CookieTestingMainPage pressLoginButton () {
        submitButton.click();
        return this;
    }

    @Step ("Проверка видимости кнопки профиля")
    public CookieTestingMainPage profileButtonShouldBeVisible () {
        Waiters.waitForVisibility(3,driver, profileButton);
        return this;
    }

    @Step ("Нажатие кнопки деавторизации")
    public CookieTestingMainPage pressLogoutButton () {
        logoutButton.click();
        return this;
    }
}
