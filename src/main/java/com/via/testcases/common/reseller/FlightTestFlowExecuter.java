package com.via.testcases.common.reseller;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.via.appmodules.common.Payments;
import com.via.appmodules.common.reseller.AgentLoginPage;
import com.via.appmodules.flights.FlightResults;
import com.via.appmodules.flights.FlightsSearch;
import com.via.appmodules.flights.TravellersDetails;
import com.via.pageobjects.common.CommonDetails;
import com.via.pageobjects.flights.FlightBookingDetails;
import com.via.utils.CommonUtils;
import com.via.utils.Constant.Country;
import com.via.utils.Constant.Flight;
import com.via.utils.CustomAssert;
import com.via.utils.Log;
import com.via.utils.RepositoryParser;

public class FlightTestFlowExecuter {

    public void execute(String testId, WebDriver driver, RepositoryParser repositoryParser, Country country,
            Flight flight, Map<Integer, String> testData) {

        try {
            Log.startTestCase(testId);

            CommonDetails commonDetails = new AgentLoginPage(testId, driver, repositoryParser, country, testData)
                    .execute();
            FlightBookingDetails flightBookingDetails = new FlightsSearch(testId, driver, repositoryParser, testData,
                    flight).execute();
            new FlightResults(testId, driver, repositoryParser, flightBookingDetails, commonDetails).execute();

            new TravellersDetails(testId, driver, repositoryParser, flightBookingDetails, commonDetails).execute();

            new Payments(testId, driver, repositoryParser, commonDetails).execute(flightBookingDetails);

        } catch (Exception e) {
            CustomAssert.assertFail(testId, e.toString());
        } finally {
            CommonUtils.closeDriver(driver);
            Log.endTestCase(testId);
        }
    }
}
