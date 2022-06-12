package com.skyUp.tests.skyUpTests;

import com.skyUp.tests.BaseTest;
import org.testng.annotations.Test;
import data.LoginConstants;

public class SkyUpInformationForPassengersTest extends BaseTest implements LoginConstants {

    @Test
    public void skyUpCheckInformationAboutRulesOfConductOnThePlane(){

        getSkyUpMainPage()
                .logInToCustomerAccount(LOGIN, PASSWORD)
                .openInformationForPassengersPage()
                .clickOnFlightConductButton()
                .verifyTextOnRulesOfConductPage();
    }
}
