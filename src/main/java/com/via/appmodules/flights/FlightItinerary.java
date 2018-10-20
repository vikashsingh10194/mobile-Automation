package com.via.appmodules.flights;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.via.pageobjects.common.CommonDetails;
import com.via.pageobjects.flights.FlightBookingDetails;
import com.via.pageobjects.flights.FlightDetails;
import com.via.pageobjects.flights.FlightItineraryElements;
import com.via.utils.CommonUtils;
import com.via.utils.Constant;
import com.via.utils.CustomAssert;
import com.via.utils.Log;
import com.via.utils.NumberUtility;
import com.via.utils.PageHandler;
import com.via.utils.RepositoryParser;
import com.via.utils.StringUtilities;

/**
 * 
 * @author Yash Gupta
 *
 */
public class FlightItinerary extends FlightItineraryElements {

    private FlightBookingDetails flightBookingDetails;
    private CommonDetails        commonDetails;

    public FlightItinerary(String testId, WebDriver driver, RepositoryParser repositoryParser,
            FlightBookingDetails flightBookingDetails, CommonDetails commonDetails) {
        super(testId, driver, repositoryParser);
        this.flightBookingDetails = flightBookingDetails;
        this.commonDetails = commonDetails;
    }

    /**
     * Validates the details of all flights and total fare
     */
    public void validateDetails() {
        List<FlightDetails> flights = flightBookingDetails.getFlights();
        int totalFlights = getTotalFlights();

        for (int flightIndex = 1; flightIndex <= totalFlights; flightIndex++) {
            validateFlightDetails(flightIndex, flights.get(flightIndex - 1));
        }

    CustomAssert.assertTrue(
        testId,
        NumberUtility.validAmountDiff(
            testId,
            commonDetails.getCountry(),
            commonDetails.getTotalFare() + commonDetails.getSsrCharges()
                + commonDetails.getPayfee(), getTotalAmount()), "Error in validating total fare");
        Log.info(testId, "::::::::::::::Total Fare varified:::::::::::::::::");
    }

    /**
     * Sets the details of all flights and total fare
     */
    public void setDetails() {

        int totalFlights = getTotalFlights();
        List<FlightDetails> flights = new ArrayList<FlightDetails>();
        PageHandler.setImplicitWaitTime(driver,5);
        for (int flightIndex = 1; flightIndex <= totalFlights; flightIndex++) {
            flights.add(setFlightDetails(flightIndex));          
        }
        flightBookingDetails.setFlights(flights);

        commonDetails.setTotalFare(getTotalFare());
    }

    public void close() {
        CommonUtils.javaScriptExecuterClick(driver, closeItinerary());
    }

    public void proceed() {
        continueButton().click();
    }

    private int getTotalFlights() {
        return flights().size();
    }

    private double getTotalFare() {
      String amountString;
      try {
          CommonUtils.setImplicitWaitTime(driver, 200);
          amountString = totalFare().getAttribute("innerHTML");
      } catch (Exception e) {
          amountString = driver.findElement(By.xpath(".//*[@id='fare-detailsContainer']/div[1]/div[6]/div[2]/span")).getAttribute("innerHTML");
      } finally {
          CommonUtils.setImplicitWaitTime(driver);
      }
      return NumberUtility.getAmountFromString(commonDetails.getCountry(), amountString);
    }
    private double getTotalAmount() {
        String amountString;
        try {
            CommonUtils.setImplicitWaitTime(driver, 200);
            amountString = totalAmount().getAttribute("innerHTML");
        } catch (Exception e) {
            amountString = driver.findElement(By.xpath(".//*[@id='fare-detailsContainer']/div[1]/div[2]/div[2]/span")).getAttribute("innerHTML");
        } finally {
            CommonUtils.setImplicitWaitTime(driver);
        }
        return NumberUtility.getAmountFromString(commonDetails.getCountry(), amountString);
    }

    private FlightDetails setFlightDetails(int flightIndex) {

        Log.divider(testId, "Flight " + flightIndex);
        ItineraryElementGetter itineraryElementGetter = new ItineraryElementGetter(flightIndex);
        String name = itineraryElementGetter.getFlightName();
        String code = itineraryElementGetter.getFlightCode();
        boolean refundable = itineraryElementGetter.isRefundable();
        // String operatingCarrier =
        // itineraryElementGetter.getOperatingCarrier();
        String duration = itineraryElementGetter.getDuration();
        String sourceCity = itineraryElementGetter.getSrcCityName();
        String sourceCityCode = itineraryElementGetter.getSrcArptCode();
        String departureTime = itineraryElementGetter.getDepartureTime();
        String departureDate = itineraryElementGetter.getDepartureDate();
        String destinationCity = itineraryElementGetter.getDestCityName();
        String destinationCityCode = itineraryElementGetter.getDestArptCode();
        String arrivalTime = itineraryElementGetter.getArrivalTime();
        String arrivalDate = itineraryElementGetter.getArrivalDate();

        FlightDetails flightDetails = new FlightDetails();
        flightDetails.setName(name);
        flightDetails.setCode(code);
        flightDetails.setRefundable(refundable);
        // flightDetails.setOperatingCarrier(operatingCarrier);
        flightDetails.setDuration(duration);
        flightDetails.setSourceCity(sourceCity);
        flightDetails.setSourceCityCode(sourceCityCode);
        flightDetails.setDepartureTime(departureTime);
        flightDetails.setDepartureDate(departureDate);
        flightDetails.setDestinationCity(destinationCity);
        flightDetails.setDestinationCityCode(destinationCityCode);
        flightDetails.setArrivalTime(arrivalTime);
        flightDetails.setArrivalDate(arrivalDate);

        Log.info(testId, "Name :: " + name);
        Log.info(testId, "Code :: " + code);
        Log.info(testId, "Refunfable :: " + refundable);
        // Log.info(testId, operatingCarrier);
        Log.info(testId, "Duration :: " + duration);
        Log.info(testId, "SourceCity :: " + sourceCity);
        Log.info(testId, "SourceCityCode :: " + sourceCityCode);
        Log.info(testId, "DepartureTime :: " + departureTime);
        Log.info(testId, "DepartureDate :: " + departureDate);
        Log.info(testId, "DestinationCity :: " + destinationCity);
        Log.info(testId, "DestinationCityCode :: " + destinationCityCode);
        Log.info(testId, "ArrivalTime :: " + arrivalTime);
        Log.info(testId, "ArrivalDate :: " + arrivalDate);

        return flightDetails;
    }

    private void validateFlightDetails(int flightIndex, FlightDetails flightDetails) {

        ItineraryElementGetter itineraryElementGetter = new ItineraryElementGetter(flightIndex);
        PageHandler.sleep(testId,5 * 1000l);
        CustomAssert.assertEquals(testId, itineraryElementGetter.getFlightName(), flightDetails.getName(),
                "Error in validating flight name");
        CustomAssert.assertEquals(testId, itineraryElementGetter.getFlightCode(), flightDetails.getCode(),
                "Error in validating flight code");
        CustomAssert.assertEquals(testId, itineraryElementGetter.isRefundable(), flightDetails.isRefundable(),
                "Error in validating refundable status");
        // CustomAssert.assertEquals(testId,
        // itineraryElementGetter.getOperatingCarrier(),
        // flightDetails.getOperatingCarrier(),
        // "Error in validating flight code");
        CustomAssert.assertEquals(testId, itineraryElementGetter.getDuration(), flightDetails.getDuration(),
                "Error in validating flighr duration");
        CustomAssert.assertEquals(testId, itineraryElementGetter.getSrcCityName(), flightDetails.getSourceCity(),
                "Error in validating src city");
        CustomAssert.assertEquals(testId, itineraryElementGetter.getSrcArptCode(), flightDetails.getSourceCityCode(),
                "Error in validating src arpt code");
        CustomAssert.assertEquals(testId, itineraryElementGetter.getDepartureTime(), flightDetails.getDepartureTime(),
                "Error in validating departure time");
        CustomAssert.assertEquals(testId, itineraryElementGetter.getDepartureDate(), flightDetails.getDepartureDate(),
                "Error in validating departure date");
        CustomAssert.assertEquals(testId, itineraryElementGetter.getDestCityName(), flightDetails.getDestinationCity(),
                "Error in validating dest city");
        CustomAssert.assertEquals(testId, itineraryElementGetter.getDestArptCode(),
                flightDetails.getDestinationCityCode(), "Error in validating dest arpt code");
        CustomAssert.assertEquals(testId, itineraryElementGetter.getArrivalTime(), flightDetails.getArrivalTime(),
                "Error in validating arrival time");
        CustomAssert.assertEquals(testId, itineraryElementGetter.getArrivalDate(), flightDetails.getArrivalDate(),
                "Error in validating arrival date");
    }

    /**
     * Gets all the details of each flight element
     */
    private class ItineraryElementGetter {

        private String flightIndex;

        private ItineraryElementGetter(int flightIndex) {
            this.flightIndex = Integer.toString(flightIndex);
        }

        private String getFlightName() {
            String flightNameText = flightName(flightIndex).getAttribute("innerHTML");
            return StringUtilities.split(flightNameText, Constant.NEW_LINE).get(0);
        }

        private boolean isRefundable() {
           // String refundableText = StringUtilities.split(flightName(flightIndex).getText(), Constant.NEW_LINE).get(1);
            String refundableText=checkRefundable(flightIndex).getAttribute("innerHTML");
            if (StringUtils.equalsIgnoreCase(refundableText, "Refundable")) {
                return true;
            }
            return false;
        }

        private String getFlightCode() {
            return flightCode(flightIndex).getAttribute("innerHTML");
        }

        @SuppressWarnings("unused")
        private String getOperatingCarrier() {
            return operatingCarrier(flightIndex).getText();
        }

        private String getDuration() {
            return flightDuration(flightIndex).getAttribute("innerHTML");
        }

        private String getSrcArptCode() {
            return srcArptCode(flightIndex).getAttribute("innerHTML");
        }

        private String getSrcCityName() {
            return srcArptName(flightIndex).getAttribute("innerHTML");
        }

        private String getDepartureTime() {
            return departureTime(flightIndex).getText();
        }

        private String getDepartureDate() {
            return departureDate(flightIndex).getAttribute("innerHTML");
        }

        private String getDestArptCode() {
            return destArptCode(flightIndex).getAttribute("innerHTML");
        }

        private String getDestCityName() {
            return destArptName(flightIndex).getAttribute("innerHTML");
        }

        private String getArrivalTime() {
            return arrivalTime(flightIndex).getText();
        }

        private String getArrivalDate() {
            return arrivalDate(flightIndex).getAttribute("innerHTML");
        }
    }
}
