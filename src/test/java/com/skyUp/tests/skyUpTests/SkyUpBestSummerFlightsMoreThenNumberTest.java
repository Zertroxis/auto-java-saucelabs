package com.skyUp.tests.skyUpTests;

import com.skyUp.tests.BaseTest;
import org.testng.annotations.Test;
import data.LoginConstants;

public class SkyUpBestSummerFlightsMoreThenNumberTest extends BaseTest implements LoginConstants {

    private static final int ELEMENTS_ON_PAGE_MORE_NUMBER = 1;

    @Test
    public void skyUpBestFlights() {
        getSkyUpMainPage()
                .logInToCustomerAccount(LOGIN, PASSWORD)
                .openAllFlightsPage()
                .verifyCountOfBestSummerFlights(ELEMENTS_ON_PAGE_MORE_NUMBER);
    }
}
