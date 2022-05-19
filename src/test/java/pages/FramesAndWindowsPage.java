package pages;

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

    public FramesAndWindowsPage changeFrame () {
        driver.switchTo().frame(iFrame);
        return this;
    }

    public NewTabPage openNewTab() {
        newTabButton.click();
        return new NewTabPage(driver);
    }
}
