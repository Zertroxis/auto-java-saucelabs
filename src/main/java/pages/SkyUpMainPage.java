package pages;

import data.TextStorege;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static utils.GeneralPageActions.*;


@Getter
public class SkyUpMainPage extends CommonPage implements TextStorege {

    private static final String MAIN_PAGE_LOGIN_BUTTON = ".//button[@id='headerLogin']";
    private static final String LOGIN_BUTTON = ".//button[@class='btn-4 js-show-log-in']";
    private static final String EMAIL_INPUT_FIELD = ".//input[@id='logInEmail']";
    private static final String PASSWORD_INPUT_FIELD = ".//input[@name='password' and @autocomplete='current-password']";
    private static final String LOGIN_TO_CUSTOMER_ACCOUNT_BUTTON = ".//button[@id='logInBtn']";

    private static final String CLOSE_POPUP_SUPPORT_UKRAINE_BUTTON = "//div[@id='open-appeal-modal']" +
            "//div[@class='modal__inner js-modal-inner']//div[@class='modal__content js-modal-content']" +
            "//button[@type='button' and @class='modal__close js-close-modal']";

    private static final String PROFILE_DROP_DOWN_MENU = "//button[@id='headerCabinet']";
    private static final String PROFILE_BUTTON = "//li[@data-page='cabinet-main']";

    private static final String ALL_FLIGHTS_LINK_BUTTON = "//ul[@class='header__unit header__nav']" +
            "//li[@data-page='site-best']";

    private static final String PASSENGERS_DROP_DOWN_MENU = "//ul[@class='header__unit header__nav']" +
            "//div[@id='headerTriggerDropdownPassengers']";
    private static final String PASSENGERS_INFORMATION_LINK_BUTTON = "//*[@id='headerDropdownPassengers']/li[1]/a";

    private static final String LANGUAGE_BUTTON = "//button[@id='showLangsDesktop']";
    private static final String ENG_LANGUAGE_TO_CHANGE_BUTTON = "//button[@data-lang='en']";
    private static final String UKR_LANGUAGE_TO_CHANGE_BUTTON = "//button[@data-lang='uk']";
    private static final String MAINPAGE_HEADER_SUBTEXT = "//div[@class='slider__header stand-for-UA__header']//p";

    private static final String SKYUP_URL = "https://skyup.aero/";

    public SkyUpMainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Login to customer account")
    public SkyUpMainPage logInToCustomerAccount(String login, String password) {
        Allure.step("Open base page");
        open(SKYUP_URL);
        waitForPageToLoad();
        clickOnElement(CLOSE_POPUP_SUPPORT_UKRAINE_BUTTON);
        clickOnElement(MAIN_PAGE_LOGIN_BUTTON);
        waitAndClick(LOGIN_BUTTON);
        sendKeysIntoField(EMAIL_INPUT_FIELD, login);
        sendKeysIntoField(PASSWORD_INPUT_FIELD, password);
        waitAndClick(LOGIN_TO_CUSTOMER_ACCOUNT_BUTTON);
        return this;
    }

    @Step("Open customer profile")
    public SkyUpProfilePage openUserProfile() {
        waitAndClick(PROFILE_DROP_DOWN_MENU);
        clickOnElement(PROFILE_BUTTON);
        return new SkyUpProfilePage(this.driver);
    }

    @Step("Open All Flights page")
    public SkyUpAllFlights openAllFlightsPage() {
        waitAndClick(ALL_FLIGHTS_LINK_BUTTON);
        return new SkyUpAllFlights(this.driver);
    }

    @Step("Open Information for Passengers page")
    public SkyUpInformationForPassengersPage openInformationForPassengersPage() {
        waitAndClick(PASSENGERS_DROP_DOWN_MENU);
        clickElementIfItPresents(PASSENGERS_INFORMATION_LINK_BUTTON);
        return new SkyUpInformationForPassengersPage(this.driver);
    }

    @Step("Click on language button")
    public SkyUpMainPage clickOnLanguageAndChangeLanguageToEnglish() {
        clickOnElement(LANGUAGE_BUTTON);
        clickOnElement(ENG_LANGUAGE_TO_CHANGE_BUTTON);
        waitForPageToLoad();
        return new SkyUpMainPage(driver);
    }

    @Step
    public SkyUpMainPage verifyLanguageChanged() {
        Assertions.assertThat(getTextFromElement(MAINPAGE_HEADER_SUBTEXT)).isIn(getSubtextsFromStorageForLanguageCheck());
        return new SkyUpMainPage(driver);
    }

    @Step("Add languages to check")
    private ArrayList<String> getSubtextsFromStorageForLanguageCheck() {
        ArrayList<String> listOfEngMainPageSubtexts = new ArrayList<>();
        listOfEngMainPageSubtexts.add(MAINPAGE_FIRST_SUBTEXT_ENG);
        listOfEngMainPageSubtexts.add(MAINPAGE_SECOND_SUBTEXT_ENG);
        listOfEngMainPageSubtexts.add(MAINPAGE_FIRST_SUBTEXT_UKR);
        listOfEngMainPageSubtexts.add(MAINPAGE_SECOND_SUBTEXT_UKR);
        return listOfEngMainPageSubtexts;
    }
}
