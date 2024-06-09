package br.dev.sampaio.stepdefs;

import br.dev.sampaio.Page;
import br.dev.sampaio.drivers.DriverManager;
import io.cucumber.java.pt.Então;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

public class CustomSD {
    @Então("Armazeno o número da conta")
    public void variableStorage() throws IOException, ParseException {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
        WebElement element = Page.getElement("id:modalText");
        String[] separarNumeroDaConta = wait.until(ExpectedConditions.visibilityOf(element)).getText().split("-");
        System.out.println(Arrays.toString(separarNumeroDaConta));
        String apenasNumeroDaConta = separarNumeroDaConta[0].replaceAll("[^0-9]", "");
        String digitoDaConta = separarNumeroDaConta[1].replaceAll("[^0-9]", "");
        System.out.println("==================");
        System.out.println(apenasNumeroDaConta);
        System.out.println("==================");
        System.out.println(digitoDaConta);
        System.out.println("==================");
    }
}