package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static utils.GeneralPageActions.*;

public class SkyUpInformationForPassengersPage extends CommonPage{

    private static final String RULES_OF_CONDUCT_BUTTON = "//a[@class='airport-info js-airport-info airport-info--rules-of-conduct']//div[@class='airport-info__text']";

    public SkyUpInformationForPassengersPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open rules of conduct page")
    public SkyUpRulesOfConductPage clickOnFlightConductButton(){
        waitForPageToLoad();
        waitAndClick(RULES_OF_CONDUCT_BUTTON);
        return new SkyUpRulesOfConductPage(this.driver);
    }
}
