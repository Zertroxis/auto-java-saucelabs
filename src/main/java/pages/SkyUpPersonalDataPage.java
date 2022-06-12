package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.GeneralPageActions.*;

public class SkyUpPersonalDataPage extends CommonPage{

    private static final String CUSTOMER_FIRST_NAME_TEXT_IN_VALUE = "//div[@class='field field--first-name']//input";
    private static final String CUSTOMER_FIRST_NAME_EMAIL_IN_VALUE = "//div[@class='fieldset__content']//input[@name='email' and @value]";

    public SkyUpPersonalDataPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check customer personal information")
    public SkyUpPersonalDataPage verifyCustomerPersonalData(String name, String email){
        waitForPageToLoad();
        assertThat(getElementAttribute(CUSTOMER_FIRST_NAME_TEXT_IN_VALUE,"value")).isEqualTo(name);
        assertThat(getElementAttributeFromList(CUSTOMER_FIRST_NAME_EMAIL_IN_VALUE,"value")).isEqualTo(email);
        return new SkyUpPersonalDataPage(driver);
    }
}
