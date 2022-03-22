package pages;

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

    @FindBy (css = ".btn")
    private WebElement loginButton;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AuthorizationPage insertUsername(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    public AuthorizationPage insertPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public AuthorizationPage pressLoginButton() {
        loginButton.click();
        return this;
    }

}
