package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FramesAndWindowsPage {

    private final WebDriver driver;

    @FindBy(css = "#example-1-tab-1 iframe")
    private WebElement iFrame;

    @FindBy(css = "[href='#']")
    private WebElement newTabButton;

    public FramesAndWindowsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Переключение фрейма")
    public FramesAndWindowsPage changeFrame () {
        driver.switchTo().frame(iFrame);
        return this;
    }

    @Step("Клик на кнопку открытия новой вкладки")
    public NewTabPage openNewTab() {
        newTabButton.click();
        return new NewTabPage(driver);
    }
}
