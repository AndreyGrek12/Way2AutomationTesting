package pages;

import helpers.Waiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private final WebDriver driver;

    @FindBy (xpath = "//p[text()=\"You're logged in!!\"]")
    private WebElement successAuthorizationText;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getAuthorizationText() {
        Waiters.waitForVisibility(10,driver, successAuthorizationText);
        return successAuthorizationText.getText();
    }
}
