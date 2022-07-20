package tests.uItests;

import helpers.PropertiesProvider;
import helpers.WindowsUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FramesAndWindowsPage;
import pages.NewTabPage;

public class TabTest extends BaseTest{

    @BeforeMethod
    public void setup() {
        driver.get(PropertiesProvider.getProperty("framesAndWindowsSiteURL"));
    }

    @Epic("Страницы для практики")
    @Feature("Проверка работы Frames and Windows")
    @Story("Проверка открытия новых вкладок при нажатии на кнопку")
    @Test
    @Severity(SeverityLevel.NORMAL)
    public void tabTest() {
        FramesAndWindowsPage framesAndWindowsPage = new FramesAndWindowsPage(driver);
        NewTabPage newTabPage = new NewTabPage(driver);

        framesAndWindowsPage.changeFrame()
                .openNewTab();
        WindowsUtils.focusTab(driver, 2);
        newTabPage.openNewTab();
        Assert.assertEquals(WindowsUtils.getTabCount(driver), 3, "Количество вкладок не равно 3");
    }
}
