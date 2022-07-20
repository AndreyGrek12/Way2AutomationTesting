package tests.uItests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps"},
        tags = "",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)

public class RunnerTest extends AbstractTestNGCucumberTests {
}
