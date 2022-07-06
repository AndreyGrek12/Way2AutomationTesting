package tests.UItests;

import helpers.PropertiesProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DragNDropPage;

public class DragNDropTest extends BaseTest {

    @BeforeMethod
    public void setup() {
        driver.get(PropertiesProvider.getProperty("drugNDropSiteURL"));
    }

    @Epic("Страницы для практики")
    @Feature("Проверка работы Drag n Drop")
    @Story("Прроверка изменения текста при переносе элемента в нужное место")
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void dragNDropTest () {
        DragNDropPage dragNDropPage = new DragNDropPage(driver);

        dragNDropPage.changeFrame();
        String textBeforeDragNDrop = dragNDropPage.getText();
        dragNDropPage.dragNDrop();
        Assert.assertNotEquals(dragNDropPage.getText(), textBeforeDragNDrop, "Текст не изменился");
    }
}
