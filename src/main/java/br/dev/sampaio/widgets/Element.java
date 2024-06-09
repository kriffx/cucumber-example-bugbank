package br.dev.sampaio.widgets;

import br.dev.sampaio.Page;
import br.dev.sampaio.drivers.DriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class Element {
    private WebDriver driver = null;
    private By by = null;
    private WebElement locator = null;
    private final WebDriverWait wait;

    public Element(String locator) throws IOException, ParseException {
        this.driver = DriverManager.getDriver();
        this.by = Page.selector(Page.getSelector(locator));
        this.locator = Page.getElement(locator);
        this.wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
    }

    public void click() {
        try {
            this.wait.until(ExpectedConditions.elementToBeClickable(this.by)).click();
        } catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            JavascriptExecutor executor = (JavascriptExecutor)this.driver;
            executor.executeScript("arguments[0].click();", this.locator);
        }
    }

    public void text(String text) throws Exception {
        try {
            WebElement element = this.wait.until(ExpectedConditions.elementToBeClickable(this.by));
            element.clear();
            element.sendKeys(text);
        } catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            String message = "O elemento " + this.by.toString() + " existente no DOM e tem um conjunto de recursos como oculto.";
            throw new Exception(message);
        }
    }
}