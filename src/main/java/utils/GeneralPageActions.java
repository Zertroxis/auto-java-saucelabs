package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class GeneralPageActions {

    private static final String ACTION_CLICK = "arguments[0].click();";
    private static final String SCROLL_TO = "arguments[0].scrollIntoView(false)";
    public static final Duration TIMEOUT = Duration.ofSeconds(5);
    public static final Duration DURATION = Duration.ofMillis(200);

    public static void waitForPageToLoad() {
        Selenide.Wait().until(webDriver -> {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            return Objects.equals("complete", js.executeScript("return document.readyState"));
        });
    }

    /**
     * @param elementXpath
     * @param value
     */
    public static void fillInputField(String elementXpath, String value) {
        $(By.xpath(elementXpath)).shouldBe(visible).setValue(value);
        pressTabOnElement(elementXpath);
    }

    public static void pressTabOnElement(String elementXpath) {
        $(By.xpath(elementXpath)).pressTab();
    }

    /**
     * @param selectElementXpath
     * @param text
     */
    public static void selectOptionByText(String selectElementXpath, String text) {
        $(By.xpath(selectElementXpath)).findElement(
                By.xpath("//*[contains(text(),'" + text + "')]")).click();
        waitForPageToLoad();
    }

    public static void selectOptionByValue(String selectElementXpath, String value) {
        $(By.xpath(selectElementXpath)).selectOptionByValue(value);
        waitForPageToLoad();
    }

    public static void selectOptionByIndex(String selectElementXpath, int index) {
        $(By.xpath(selectElementXpath)).selectOption(index);
        waitForPageToLoad();
    }

    /**
     * @param elementXpath
     */
    public static void clickOnElement(String elementXpath) {
        //click
        $(By.xpath(elementXpath)).shouldBe(visible).click();
        //waiting for page loading after click
        waitForPageToLoad();
    }

    public static void clickOnElement(String elementXpath, Object... parameter) {
        //click
        $(By.xpath(format(elementXpath, parameter))).shouldBe(visible).click();
        //waiting for page loading after click
        waitForPageToLoad();
    }

    /**
     * @param elementXpath
     */
    public static String getTextFromElement(String elementXpath) {
        waitForPageToLoad();
        return StringUtils.normalizeSpace($(By.xpath(elementXpath)).shouldBe(visible).getText());
    }

    public static String getTextFromElement(String elementXpath, Object... parameter) {
        waitForPageToLoad();
        return StringUtils.normalizeSpace($(By.xpath(format(elementXpath, parameter))).shouldBe(visible).getText());
    }

    public static void clickOnElementJs(SelenideElement element) {
        waitForPageToLoad();
        JavascriptExecutor jse = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        jse.executeScript(ACTION_CLICK, element);
    }

    public static void scrollToElementIntoView(String xpath) {
        JavascriptExecutor jse = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        jse.executeScript(SCROLL_TO, $(By.xpath(xpath)));
    }

    public static void dragDrop(String fromXpath, String toXpath) {
        $x(fromXpath).dragAndDropTo($x(toXpath));
    }

    public static void selectFromDropDown(String xpath, String optionValue) {
        Select groupActionSelect = new Select($(By.xpath(xpath)));
        groupActionSelect.selectByValue(optionValue);
    }

    public static void selectFromDropDownByVisibleText(String xpath, String optionValue) {
        Select groupActionSelect = new Select($(By.xpath(xpath)));
        groupActionSelect.selectByVisibleText(optionValue);
    }


    public static boolean isElementDisplayed(String xpath) {
        return $(By.xpath(xpath)).shouldHave(visible, TIMEOUT).isDisplayed();
    }

    public static void clearInputFiled(String xpath) {
        $(By.xpath(xpath)).shouldHave(visible, TIMEOUT).clear();
    }

    public static void waitAndClick(String xpath) {
        $(By.xpath(xpath)).shouldHave(visible, TIMEOUT).click();
    }

    public static void clickElementIfItPresents(String xpath) {
        if (isElementDisplayedWithoutWait(xpath)) {
            waitAndClick(xpath);
        }
    }

    public static String getElementAttribute(String xpath, String attribute) {
        return $(By.xpath(xpath)).getAttribute(attribute);
    }

    public static String getElementAttributeFromList(String xpath, String attribute) {
        return $$(By.xpath(xpath)).first().getAttribute(attribute);
    }

    public static void waitUntilEnabledAndClick(String xpath) {
        $(By.xpath(xpath)).shouldHave(Condition.enabled, TIMEOUT).click();
    }

    public static void clickScrollUntilNotVisible(String scrollXpath, String expressionXpath) {
        int count = 10;
        do {
            waitAndClick(scrollXpath);
            count--;
        } while (!isElementDisplayedWithoutWait(expressionXpath) && count > 0);

    }

    public static void waitAndSetValue(String xpath, String ean) {
        $(By.xpath(xpath)).shouldHave(visible, TIMEOUT).setValue(ean);
    }

    public static String getTextForElement(String xpath) {
        return $(By.xpath(xpath)).getText();
    }

    public static String getValueForElement(String xpath) {
        return $(By.xpath(xpath)).getValue();
    }

    public static boolean isElementDisplayedWithoutWait(String xpath) {
        return $(By.xpath(xpath)).isDisplayed();
    }

    public static List<SelenideElement> getListOfElementsByXpath(String xpath) {
        return $$x(xpath);
    }

    public static void clickOnFirstElement(String elementXpath) {
        $$x(elementXpath).first().shouldBe(visible).click();
    }

    public static String getTextFromFirstElement(String elementXpath) {
        return $$x(elementXpath).first().shouldBe(visible).getText();
    }

    /**
     * Return false if element is not visible Return true if element is visible
     */
    public static boolean isElementNotDisplayed(String xpath) {
        return $(By.xpath(xpath)).isDisplayed();
    }

    public static void findAnElementAndClickWithJSByXpath(String xPath) {
        String command = "document.evaluate(\"" + xPath
                + "\", document, null, XPathResult.ANY_TYPE, null).iterateNext().click()";
        JavascriptExecutor jse = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        jse.executeScript(command);
    }

    public static void sendKeysIntoField(String xpath, String value) {
        $(By.xpath(xpath)).sendKeys(value);
    }

    public static int getCountOfElementsOnPage(String xpath) {
        return $$(By.xpath(xpath)).size();
    }

    public static String moveToElementAndGetText(String xpath) {
        SelenideElement element = $(By.xpath(xpath));
        actions().moveToElement(element).perform();
        return getTextForElement(xpath);
    }
}
