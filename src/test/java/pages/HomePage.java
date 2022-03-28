package pages;

import helpers.Waiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private final WebDriver driver;

    @FindBy (css = ".jumbotron")
    private WebElement homePage;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage homePageShouldBeVisible() {
        Waiters.waitForVisibility(10,driver, homePage);
        return this;
    }
}
