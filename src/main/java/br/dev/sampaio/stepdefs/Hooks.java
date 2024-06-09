package br.dev.sampaio.stepdefs;

import br.dev.sampaio.Page;
import br.dev.sampaio.drivers.DriverFactory;
import br.dev.sampaio.drivers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @Before
    public void beforeTest(Scenario scenario) {
        System.out.println(
                "BEFORE: THREAD ID : " + Thread.currentThread().getId() + ","
                        + "SCENARIO NAME: " + scenario.getName());

        Page.setPage(scenario.getSourceTagNames());
        DriverManager.setDriver(DriverFactory.createInstance());
        ScenarioManager.setScenario(scenario);
    }

    @After
    public void afterTest(Scenario scenario) {
        System.out.println(
                "AFTER: THREAD ID : " + Thread.currentThread().getId() + ","
                        + "SCENARIO NAME: " + scenario.getName());

        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            ScenarioManager.getScenario().attach(screenshot, "image/png", ScenarioManager.getScenario().getName());
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        DriverManager.quit();
    }
}
