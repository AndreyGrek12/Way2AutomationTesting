package pages;

import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage {

    private final WebDriver driver;


    @FindBy (css = ".elementor-icon-list-items.elementor-inline-items")
    private WebElement head;

    @FindBy (xpath = "//div[@class='main-navigation ast-inline-flex']")
    private WebElement horizontalMenu;

    @FindBy (xpath = "//section[@data-id='5b4952c1']")
    private WebElement certifications;

    @FindBy (xpath = "//div[@data-id='c50f9f0']")
    private WebElement courses;

    @FindBy (xpath = "//div[@data-id='573bc308']")
    private WebElement footer;

    @FindBy (css = ".pp-slider-arrow.swiper-button-next")
    private WebElement nextCourseButton;

    @FindBy (xpath = "//h4[contains(text(),'Appium Mobile Automation Testing for Android and IOS')]")
    private WebElement courseBanner;

    @FindBy (css = "#menu-item-27617")
    private WebElement resources;

    @FindBy (xpath = "//a[@href='https://www.way2automation.com/way2auto_jquery/index.php']")
    private WebElement practiceSiteOneButton;

    @FindBy (xpath = "//a[@href='https://www.way2automation.com/protractor-angularjs-practice-website.html']")
    private WebElement practiceSiteTwoButton;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Проверка видимости хедера")
    public MainPage headShouldBeVisible(){
        Waiters.waitForVisibility(10,driver, head);
        return this;
    }

    @Step ("Проверка видимости горизнотального меню")
    public MainPage horizontalMenuShouldBeVisible() {
        Waiters.waitForVisibility(10,driver, horizontalMenu);
        return this;
    }

    @Step ("Проверка видимости сертификатов")
    public MainPage certificationsShouldBeVisible() {
        Waiters.waitForVisibility(10,driver, certifications);
        return this;
    }

    @Step ("Проверка видимости курсов")
    public MainPage coursesShouldBeVisible() {
        Waiters.waitForVisibility(10,driver, courses);
        return this;
    }

    @Step ("Проверка видимости футера")
    public MainPage footerShouldBeVisible() {
        Waiters.waitForVisibility(10,driver, footer);
        return this;
    }

    public WebElement getResources() {
        Waiters.waitForVisibility(10,driver, resources);
        return resources;
    }

    @Step ("Переход на страницу Practice Site 1")
    public PracticeSiteOnePage choosePracticeSiteOne () {
        practiceSiteOneButton.click();
        return new PracticeSiteOnePage(driver);
    }

    @Step ("Переход на страницу Practice Site 2")
    public PracticeSiteTwoPage choosePracticeSiteTwo () {
        practiceSiteTwoButton.click();
        return new PracticeSiteTwoPage(driver);
    }

    public WebElement getNextCourseButton(){
        return nextCourseButton;
    }

    @Step ("Проверка видимости баннера курса")
    public MainPage courseBannerShouldBeVisible() {
        Waiters.waitForVisibility(10,driver, courseBanner);
        return this;
    }

}
