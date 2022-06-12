package pages;

import data.TextStorege;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.GeneralPageActions.moveToElementAndGetText;
import static utils.GeneralPageActions.waitForPageToLoad;

public class SkyUpRulesOfConductPage extends CommonPage implements TextStorege {

    private static final String TEXT_ON_CONDUCT_PAGE = "//div[@class='faq__wrapper']//p[contains(text(),'Пасажири-порушники')]";

    public SkyUpRulesOfConductPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify text on page")
    public SkyUpRulesOfConductPage verifyTextOnRulesOfConductPage() {
        waitForPageToLoad();
        assertThat(moveToElementAndGetText(TEXT_ON_CONDUCT_PAGE)).contains(RULES_OF_CONDUCT_TEXT_NUMBER_7);
        return new SkyUpRulesOfConductPage(driver);
    }
}
