package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewTabPage {

    private final WebDriver driver;

    @FindBy (css = "[href='#']")
    private WebElement newTabButton;

    public NewTabPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Клик на кнопку открытия новой вкладки")
    public void openNewTab() {
        newTabButton.click();
    }
}
