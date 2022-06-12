package com.skyUp.tests.skyUpTests;

import com.skyUp.tests.BaseTest;
import org.testng.annotations.Test;
import data.LoginConstants;


public class SkyUpPersonalDataTest extends BaseTest implements LoginConstants {

    @Test
    public void skyUpPersonalDataCheck() {
        getSkyUpMainPage()
                .logInToCustomerAccount(LOGIN, PASSWORD)
                .openUserProfile()
                .verifyCustomerName(CUSTOMER_NAME)
                .clickOnPersonalDataLinkButton()
                .verifyCustomerPersonalData(CUSTOMER_NAME,LOGIN);
    }
}
