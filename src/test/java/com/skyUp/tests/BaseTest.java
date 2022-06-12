package com.skyUp.tests;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.ScreenShooter;


import com.skyUp.tests.Listeners.AllureListener;
import com.skyUp.tests.loger.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CommonPage;
import pages.SkyUpMainPage;
import pages.SkyUpPersonalDataPage;
import pages.SkyUpProfilePage;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static java.time.Instant.now;

@Listeners({ScreenShooter.class, AllureListener.class})
public class BaseTest {

    protected WebDriver driver;
    private String sessionId;
    private ZonedDateTime testStartTime;
    private ZonedDateTime testEndTime;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yy");
    @BeforeTest
    public void setUp(){
    }

    @BeforeMethod
    public void testsSetUp(Method method) throws MalformedURLException {
        testStartTime = ZonedDateTime.ofInstant(Instant.now(),ZoneId.of("Europe/Kiev"));

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("screenResolution", "1920x1080");
        sauceOptions.put("name", method.getName());
        browserOptions.setCapability("sauce:options", sauceOptions);

        URL url = new URL("https://oauth-v.sanzhara-6cedc:e5fb1988-4473-4426-8dad-6753b8519d90@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
        driver = new RemoteWebDriver(url, browserOptions);

        WebDriverRunner.setWebDriver(driver);
        sessionId = ((RemoteWebDriver)driver).getSessionId().toString();
        System.out.printf("https://app.eu-central-1.saucelabs.com/tests/%s%n", sessionId);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public CommonPage getCommonPage(){
        return new CommonPage(getDriver());
    }

    public SkyUpMainPage getSkyUpMainPage() {
        return new SkyUpMainPage(getDriver());
    }

    public SkyUpProfilePage getSkyUpProfilePage() {
        return new SkyUpProfilePage(getDriver());
    }

    public SkyUpPersonalDataPage getSkyUpPersonalData() {
        return new SkyUpPersonalDataPage(getDriver());
    }

    @AfterMethod
    protected void teardown(ITestResult result) {
        String status = result.isSuccess() ? "passed" : "failed";
        ((JavascriptExecutor) getDriver()).executeScript("sauce:job-result=" + status);
        getDriver().quit();
        testEndTime = ZonedDateTime.ofInstant(now(),ZoneId.of("Europe/Kiev"));
        Log.info(sessionId+" | Status: "+status+" | Start of test at: "+ dateTimeFormatter.format(testStartTime)
                + " | End of test at: "+ dateTimeFormatter.format(testEndTime));
        System.out.printf("%s | Status: %s | Start of test at: %s | End of test at: %s ",
                sessionId,status,dateTimeFormatter.format(testStartTime),dateTimeFormatter.format(testEndTime));
    }

    @AfterTest
    public void teardown(){
    }


}
