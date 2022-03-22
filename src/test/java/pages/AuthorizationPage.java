package pages;

import helpers.Waiters;
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

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AuthorizationPage insertUsername(String username) {
        Waiters.waitForVisibility(10,driver,usernameField);
        usernameField.sendKeys(username);
        return this;
    }

    public AuthorizationPage insertPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public AuthorizationPage insertDescription(String description) {
        descriptionField.sendKeys(description);
        return this;
    }

    public HomePage pressLoginButton() {
        loginButton.click();
        return new HomePage(driver);
    }

}
