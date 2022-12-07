package tests.uItests;

import com.codeborne.selenide.Selenide;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterMethod;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps"},
        tags = "",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)

public class RunnerTest extends AbstractTestNGCucumberTests {
    @AfterMethod
    public void closeDriver() {
        Selenide.closeWebDriver();
    }
}
