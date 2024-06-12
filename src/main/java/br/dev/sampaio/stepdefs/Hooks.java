package br.dev.sampaio.stepdefs;

import br.dev.sampaio.Page;
import br.dev.sampaio.ScreenRecoderHelpers;
import br.dev.sampaio.drivers.DriverFactory;
import br.dev.sampaio.drivers.DriverManager;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.awt.*;
import java.io.IOException;

public class Hooks {
    ScreenRecoderHelpers recorder = new ScreenRecoderHelpers();

    public Hooks() throws IOException, AWTException {
    }

    @Before
    public void beforeScenario(Scenario scenario) throws IOException, AWTException {
        System.out.println(
                "BEFORE: THREAD ID : " + Thread.currentThread().getId() + ","
                        + "SCENARIO NAME: " + scenario.getName());

        Page.setPage(scenario.getSourceTagNames());
        DriverManager.setDriver(DriverFactory.createInstance());
        ScenarioManager.setScenario(scenario);

        recorder.startRecording("test");
    }

    @After
    public void afterScenario(Scenario scenario) {
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

        recorder.stopRecording(true);

        DriverManager.quit();
    }

    @BeforeStep
    public void beforeStep() {
    }

    @AfterStep
    public void afterStep() {
    }
}
