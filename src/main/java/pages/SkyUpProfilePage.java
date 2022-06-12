package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.GeneralPageActions.clickOnElement;
import static utils.GeneralPageActions.getTextFromElement;


public class SkyUpProfilePage extends CommonPage {

    private static final String CUSTOMER_NAME_FIELD = "//p[@class='page-title' and text()='Vladyslav']";
    private static final String PERSONAL_DATA_LINK_BUTTON = "//a[@class='link-card link-card--settings']";

    public SkyUpProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Check customer name on page")
    public SkyUpProfilePage verifyCustomerName(String name) {
        assertThat(getTextFromElement(CUSTOMER_NAME_FIELD)).isEqualTo(name);
        return SkyUpProfilePage.this;
    }

    @Step("Open personal data page")
    public SkyUpPersonalDataPage clickOnPersonalDataLinkButton() {
        clickOnElement(PERSONAL_DATA_LINK_BUTTON);
        return new SkyUpPersonalDataPage(this.driver);
    }

}
