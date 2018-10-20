package com.via.appmodules.flights;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.via.pageobjects.common.CommonDetails;
import com.via.pageobjects.flights.FlightBookingDetails;
import com.via.pageobjects.flights.FlightResultElements;
import com.via.utils.CalendarUtils;
import com.via.utils.CommonUtils;
import com.via.utils.Constant;
import com.via.utils.Constant.Flight;
import com.via.utils.Constant.JourneyType;
import com.via.utils.CustomAssert;
import com.via.utils.Log;
import com.via.utils.PageHandler;
import com.via.utils.RepositoryParser;
import com.via.utils.StringUtilities;

/**
 * 
 * @author Yash Gupta
 *
 */
public class FlightResults extends FlightResultElements {

  private FlightBookingDetails flightBookingDetails;
  private CommonDetails commonDetails;

  public FlightResults(String testId, WebDriver driver, RepositoryParser repositoryParser,
      FlightBookingDetails flightBookingDetails, CommonDetails commonDetails) {
    super(testId, driver, repositoryParser);
    this.flightBookingDetails = flightBookingDetails;
    this.commonDetails = commonDetails;
  }

  public void execute() throws ParseException {
    Log.openPage(testId, "Flight Results Page");
    PageHandler.sleep(testId, 5 * 1000l);
    validateHeader();
    Log.info(testId, "Header Validated");
    PageHandler.sleep(testId, 5 * 1000l);
    selectFlight();
    Log.info(testId, "Flight Selected");

    FlightItinerary flightItinerary =
        new FlightItinerary(testId, driver, repositoryParser, flightBookingDetails, commonDetails);
    flightItinerary.setDetails();
    flightItinerary.proceed();
  }

  /**
   * Validates the journey source, destination and date
   */
  private void validateHeader() throws ParseException {
    String headerDateFormat = "dd MMM yyyy";

    // CGK - SUB
    // 29 Nov 2016 - 03 Des 2016
    String headerText = header().getText();
    List<String> headerTextList = StringUtilities.split(headerText, Constant.NEW_LINE);
    List<String> srcDestList;
    // if(commonDetails.getCountry()==Constant.Country.INDONESIA){
    // srcDestList = StringUtilities.split(headerTextList.get(0), Constant.WHITESPACE);
    // }else{
    // srcDestList = StringUtilities.split(headerTextList.get(0), Constant.HYPHEN);
    // }
    srcDestList = StringUtilities.split(headerTextList.get(0), Constant.WHITESPACE);
    CustomAssert.assertEquals(testId, flightBookingDetails.getSourceCityCode(), srcDestList.get(0),
        "Error in validating src city");
    CustomAssert.assertEquals(testId, flightBookingDetails.getDestinationCityCode(),
        srcDestList.get(1), "Error in validating dest city");

    List<String> journeyDateList = StringUtilities.split(headerTextList.get(1), Constant.HYPHEN);
    Calendar onwardDate =
        CalendarUtils.dateStringToCalendarDate(journeyDateList.get(0), headerDateFormat);
    CustomAssert.assertTrue(testId, CalendarUtils.compareDateMonthYear(testId,
        flightBookingDetails.getOnwardDate(), onwardDate), "Error in validating onward date");
    if (flightBookingDetails.getJourneyType() == JourneyType.ROUND_TRIP) {
      Calendar returnDate =
          CalendarUtils.dateStringToCalendarDate(journeyDateList.get(1), headerDateFormat);
      CustomAssert.assertTrue(testId, CalendarUtils.compareDateMonthYear(testId,
          flightBookingDetails.getReturnDate(), returnDate), "Error in validating return date");
    }

  }

  private void selectFlight() {

    Flight flight = flightBookingDetails.getFlight();
    JourneyType journeyType = flightBookingDetails.getJourneyType();
    FlightElementGetter flightElementGetter = new FlightElementGetter();

    String onwardFlightCode = flightBookingDetails.getOnwardFlightCode();
    int onwardFlightIndex = flightBookingDetails.getOnwardFlightIndex();
    String returnFlightCode = flightBookingDetails.getReturnFlightCode();
    int returnFlightIndex = flightBookingDetails.getReturnFlightIndex();

    if (flight == Flight.DOMESTIC) {
      CommonUtils.javaScriptExecuterClick(driver,
          flightElementGetter.getOnwardFlight(onwardFlightCode, onwardFlightIndex));

      PageHandler.sleep(testId, 1000l);
      if (journeyType == JourneyType.ROUND_TRIP) {
        CommonUtils.javaScriptExecuterClick(driver,
            flightElementGetter.getReturnFlight(returnFlightCode, returnFlightIndex));
        bookButton().click();
      }
    } else {
      if (journeyType == JourneyType.ONE_WAY) {
        CommonUtils.javaScriptExecuterClick(driver,
            flightElementGetter.getOnwardFlight(onwardFlightCode, onwardFlightIndex));
      } else {
        CommonUtils.javaScriptExecuterClick(driver, flightElementGetter.getRoundTripIntlFlight(
            onwardFlightCode, returnFlightCode, returnFlightIndex));
      }
    }
  }

  private class FlightElementGetter {

    private WebElement getOnwardFlight(String flightCode, int flightIndex) {
      return onwardFlight(flightCode, Integer.toString(flightIndex));
    }

    private WebElement getReturnFlight(String flightCode, int flightIndex) {
      return returnFlight(flightCode, Integer.toString(flightIndex));
    }

    private WebElement getRoundTripIntlFlight(String onwardFlightCode, String returnFlightCode,
        int flightIndex) {
      return intlRoundTrip(onwardFlightCode, returnFlightCode, Integer.toString(flightIndex));
    }
  }
}
