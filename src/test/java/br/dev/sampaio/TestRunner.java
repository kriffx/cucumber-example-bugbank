package br.dev.sampaio;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    plugin = {
        "summary",
        "html:reports/report.html",
        "json:reports/report.json",
        "br.dev.sampaio.MyTestListener",
    },
    glue = { "br.dev.sampaio.steps" },
    features = "src/test/resources/features"
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}