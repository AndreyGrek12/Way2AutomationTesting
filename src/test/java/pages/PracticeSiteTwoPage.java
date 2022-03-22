package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracticeSiteTwoPage {

    private final WebDriver driver;

    @FindBy (xpath = "//h2[text()='Registration']")
    private WebElement authorizationBanner;


    public PracticeSiteTwoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AuthorizationPage openAuthorizationScreen () {
        authorizationBanner.click();
        return new AuthorizationPage(driver);
    }
}
