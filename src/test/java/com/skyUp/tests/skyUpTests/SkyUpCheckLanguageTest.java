package com.skyUp.tests.skyUpTests;

import com.skyUp.tests.BaseTest;
import data.LoginConstants;
import org.testng.annotations.Test;

public class SkyUpCheckLanguageTest extends BaseTest implements LoginConstants {

    @Test
    public void skyUpChangeLanguageCheck(){

        getSkyUpMainPage()
                .logInToCustomerAccount(LOGIN, PASSWORD)
                .clickOnLanguageAndChangeLanguageToEnglish()
                .verifyLanguageChanged();
    }
}
