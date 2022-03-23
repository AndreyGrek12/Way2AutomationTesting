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

    public PracticeSiteOnePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Проверка видимости регистрационной формы")
    public PracticeSiteOnePage registrationFormShouldBeVisible() {
        Waiters.waitForVisibility(10,driver, registrationForm);
        return this;
    }
}
