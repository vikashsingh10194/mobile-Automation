package com.via.testcases.common;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.via.appmodules.common.HomePage;
import com.via.appmodules.common.Payments;
import com.via.appmodules.flights.FlightResults;
import com.via.appmodules.flights.FlightsSearch;
import com.via.appmodules.flights.TravellersDetails;
import com.via.pageobjects.common.CommonDetails;
import com.via.pageobjects.flights.FlightBookingDetails;
import com.via.utils.CommonUtils;
import com.via.utils.Constant.Booking_Media;
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

            CommonDetails commonDetails = new HomePage(testId, driver, repositoryParser, country, testData).execute();

            FlightBookingDetails flightBookingDetails = new FlightsSearch(testId, driver, repositoryParser, testData,
                    flight).execute();

            new FlightResults(testId, driver, repositoryParser, flightBookingDetails, commonDetails).execute();

            new TravellersDetails(testId, driver, repositoryParser, flightBookingDetails, commonDetails).execute();

            new Payments(testId, driver, repositoryParser, commonDetails).execute(flightBookingDetails,Booking_Media.B2C);

        } catch (Exception e) {
            CustomAssert.assertFail(testId, e.toString());
        } finally {
            CommonUtils.closeDriver(driver);
            Log.endTestCase(testId);
        }
    }
}
