package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DragNDropPage {

    private final WebDriver driver;

    @FindBy(css = "#example-1-tab-1 iframe")
    private WebElement iFrame;

    @FindBy (css = "#draggable")
    private WebElement draggableElement;

    @FindBy (css = "#droppable")
    private WebElement placeToDrop;

    public DragNDropPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public DragNDropPage changeFrame () {
        driver.switchTo().frame(iFrame);
        return this;
    }

    public DragNDropPage dragNDrop () {
        new Actions(driver).dragAndDrop(draggableElement,placeToDrop).build().perform();
        return this;
    }

    public String getText () {
        return placeToDrop.getText();
    }
}
