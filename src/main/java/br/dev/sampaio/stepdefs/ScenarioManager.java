package br.dev.sampaio.stepdefs;

import io.cucumber.java.Scenario;

public class ScenarioManager {
    private static final ThreadLocal<Scenario> _scenario = new ThreadLocal<>();

    public static Scenario getScenario() {
        return _scenario.get();
    }

    public static void setScenario(Scenario scenario) {
        _scenario.set(scenario);
    }
}
