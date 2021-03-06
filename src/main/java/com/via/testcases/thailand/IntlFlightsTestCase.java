package com.via.testcases.thailand;

import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.via.testcases.common.FlightTestFlowExecuter;
import com.via.utils.CommonUtils;
import com.via.utils.Constant;
import com.via.utils.Constant.Country;
import com.via.utils.Constant.Flight;
import com.via.utils.DataProviderParameters;
import com.via.utils.RepositoryParser;
import com.via.utils.TestCaseDataProvider;

public class IntlFlightsTestCase {

    private WebDriver        driver;
    private RepositoryParser repositoryParser;

    @BeforeClass
    public void dataFetch() {
        repositoryParser = new RepositoryParser();
        repositoryParser.pageElementsLoader(Constant.FLIGHTS_LOCATOR_REPOSITORY);
        repositoryParser.pageElementsLoader(Constant.COMMON_LOCATOR_REPOSITORY);
        repositoryParser.loadConfigProperties();
    }

    @BeforeMethod
    @Parameters({ "url", "browser" })
    public void beforeMethod(String url, String browser) throws Exception {
        DOMConfigurator.configure("log4j.xml");
        driver = CommonUtils.openBrowser(repositoryParser.getPropertyValue(url), browser);
    }

    @Test(dataProvider = "inputTestData", dataProviderClass = TestCaseDataProvider.class)
    @DataProviderParameters(path = Constant.TH_FLIGHTS_TESTDATA, sheetName = Constant.FLIGHTS_INTL_SHEETNAME)
    public void flightsSearchValidation(String testId, Map<Integer, String> testData) {
        FlightTestFlowExecuter flightTestExecuter = new FlightTestFlowExecuter();
        flightTestExecuter.execute(testId, driver, repositoryParser, Country.THAILAND, Flight.INTERNATIONAL, testData);
    }

}
