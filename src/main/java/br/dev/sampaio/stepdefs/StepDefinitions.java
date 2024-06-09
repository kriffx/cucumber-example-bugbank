package br.dev.sampaio.stepdefs;

import br.dev.sampaio.drivers.DriverManager;
import br.dev.sampaio.widgets.Element;
import io.cucumber.java.pt.Então;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class StepDefinitions {
    @Então("Navego para {string}")
    public void eu_vou_para(String url) {
        DriverManager.getDriver().get(url);
        DriverManager.getDriver().manage().window().maximize();
    }

    @Então("Clico no botão {string}")
    @Então("Clico no elemento {string}")
    public void eu_clico(String locator) throws IOException, ParseException {
        new Element(locator).click();
    }

    @Então("Preencho {string} no campo de texto {string}")
    public void eu_preencho(String text, String locator) throws Exception {
        new Element(locator).text(text);
    }
}