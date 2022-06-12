package pages;

import io.qameta.allure.Step;

import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.GeneralPageActions.*;

public class SkyUpAllFlights extends CommonPage {
    private static final String ALL_BEST_SUMMER_FLIGHTS_LIST = "//div[@class='best-card__items']" +
            "//div[@data-type='best summer']";

    public SkyUpAllFlights(WebDriver driver) {
        super(driver);
    }

    @Step("Check all best summer flights")
    public SkyUpAllFlights verifyCountOfBestSummerFlights(int count) {
        waitForPageToLoad();
        assertThat(getCountOfElementsOnPage(ALL_BEST_SUMMER_FLIGHTS_LIST))
                .isGreaterThan(count);
        return new SkyUpAllFlights(driver);
    }
}
