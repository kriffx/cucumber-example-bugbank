package br.dev.sampaio;

import br.dev.sampaio.drivers.DriverManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Collection;

public class Page {
    private static final ThreadLocal<Collection<String>> tags = new ThreadLocal<>();

    private Page() {
        super();
    }

    private static JSONObject getPage() throws IOException, ParseException {
        JSONObject jsonObject = new JSONObject();

        for (String selector : Page.tags.get()) {
            if (selector.contains("_page")) {
                JSONParser jsonParser = new JSONParser();
                jsonObject = (JSONObject) jsonParser.parse(
                        new FileReader(Helpers.getCurrentDir() +
                                "/pageobjs/" + selector.replace("@", "") + ".json")
                );
            }
        }

        return jsonObject;
    }

    public static void setPage(Collection<String> tags) {
        Page.tags.set(tags);
    }

    public static String getSelector(String element) throws IOException, ParseException {
        return Page.getPage().get(element).toString();
    }

    public static WebElement getElement(String locator) throws IOException, ParseException {
        if (locator.contains(":")) {
            return Page.elementLocated(locator);
        }

        return Page.elementLocated(Page.getSelector(locator));
    }

    private static WebElement elementLocated(String element) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(Page.selector(element)));
    }

    public static By selector(String element) {
        String locateStrategy = "", selector = "";

        if (element.contains(":")) {
            String[] elementSplit = element.split(":");

            if (elementSplit.length != 2) {
                throw new IllegalStateException(
                        String.format("Locator definition does not had 2 elements for %s locator", element));
            }

            locateStrategy = elementSplit[0].trim();
            selector = elementSplit[1].trim();
        }

        return Page.getLocator(locateStrategy, selector);
    }

    public static By getLocator(String locateStrategy, String selector) {
        switch (locateStrategy.toLowerCase()) {
            case "class_name":
                return By.className(selector);
            case "css_selector":
                return By.cssSelector(selector);
            case "id":
                return By.id(selector);
            case "name":
                return By.name(selector);
            case "link_text":
                return By.linkText(selector);
            case "partial_link_text":
                return By.partialLinkText(selector);
            case "tag_name":
                return By.tagName(selector);
            case "xpath":
                return By.xpath(selector);
            default:
                throw new IllegalStateException(
                        String.format("Cannot define locator for WebElement definition: %s", locateStrategy));
        }
    }
}