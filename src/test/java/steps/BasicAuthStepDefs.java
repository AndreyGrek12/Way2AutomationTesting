package steps;

import com.codeborne.selenide.Condition;
import helpers.PropertiesProvider;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Тогда;
import pages.BasicAuthPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BasicAuthStepDefs {

    BasicAuthPage basicAuthPage = new BasicAuthPage();

    @Допустим("сайт с базовой аутентификацией и верными данными открыт")
    public void сайтСБазовойАутентификациейИВернымиДаннымиОткрыт() {
        open(PropertiesProvider.getProperty("basicAuthSiteUrl"),"","httpwatch","httpwatch");
    }

    @Допустим("пользователь нажал DISPLAY IMAGE")
    public void пользовательНажалDisplayImage() {
        basicAuthPage.clickOnDisplayImageButton();
    }

    @Тогда("на вебстранице отображается изображение с данными входа")
    public void наВебстраницеОтображаетсяИзображениеСДаннымиВхода() {
        $("#downloadImg").shouldBe(Condition.visible);
    }

    @Допустим("сайт с базовой аутентификацией и неверными данными открыт")
    public void сайтСБазовойАутентификациейИНевернымиДаннымиОткрыт() {
        open(PropertiesProvider.getProperty("basicAuthSiteUrl"),"","httptch","httpwatch");
    }

    @Тогда("на вебстранице не отображается изображение с данными входа")
    public void наВебстраницеНеОтображаетсяИзображениеСДаннымиВхода() {
        $("#downloadImg").shouldNotBe(Condition.visible);
    }
}
