package steps;

import com.codeborne.selenide.Condition;
import helpers.PropertiesProvider;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import pages.AlertPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AlertStepDefs {

    AlertPage alertPage = new AlertPage();
    String textToSend = "Andrey";

    @Допустим("браузер запущен")
    public void сайтСАлертомОткрыт() {
        open(PropertiesProvider.getProperty("alertSiteURL"));
    }

    @Если("пользователь нажал INPUT ALERT")
    public void пользовательНажал() {
        alertPage.clickOnInputAlert();
    }
    @Если("нажал на кнопку Click the button to demonstrate the Input box.")
    public void нажалНаКнопку() {
        alertPage.changeFrame()
                .clickOnDemonstrateButton();
    }
    @Если("ввел текст")
    public void ввелТекст() {
        alertPage.sendKeysToAlert(textToSend);
    }
    @Если("нажал кнопку ОК")
    public void нажалКнопкуОК() {
        // Write code here that turns the phrase above into concrete actions
    }
    @Тогда("на вебстранице отображается текст содержащий ранее введенный текст")
    public void наВебстраницеОтображаетсяТекстСодержащийРанееВведенныйТекст() {
        $("#demo").shouldHave(Condition.text("Hello " + textToSend + "! How are you today?"));
    }
}
