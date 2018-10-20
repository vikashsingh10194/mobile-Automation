package com.via.appmodules.flights;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.via.pageobjects.flights.FlightBookingDetails;
import com.via.pageobjects.flights.FlightSearchElements;
import com.via.testcases.common.TestCaseExcelConstant;
import com.via.utils.CalendarUtils;
import com.via.utils.CommonUtils;
import com.via.utils.Constant;
import com.via.utils.Constant.Flight;
import com.via.utils.Constant.JourneyType;
import com.via.utils.EntityMap;
import com.via.utils.Log;
import com.via.utils.PageHandler;
import com.via.utils.RepositoryParser;
import com.via.utils.StringUtilities;

/**
 * 
 * @author Yash Gupta
 *
 */
public class FlightsSearch extends FlightSearchElements {

    private FlightBookingDetails flightBookingDetails;

    public FlightsSearch(String testId, WebDriver driver, RepositoryParser repositoryParser,
            Map<Integer, String> flightTestCase, Flight flight) {
        super(testId, driver, repositoryParser);
        setFlightBookingDetailsObject(flightTestCase, flight);
    }

    public FlightBookingDetails execute() throws ParseException {
        Log.openPage(testId, "Flights Search Page");
        
        CommonUtils.click(driver, getFlightLink());
        
        JourneyType journeyType = flightBookingDetails.getJourneyType();

        //Not available in mobile site
//        if (journeyType == JourneyType.ONE_WAY) {
//            CommonUtils.click(driver, oneWay());
//        } else {
//            CommonUtils.click(driver, roundTrip());
//        }
        setSource();
        setDestination();
        setOnwardDate();
        if(journeyType==JourneyType.ONE_WAY) {
          try{
            if(!addReturnDate().isDisplayed()) {        
              CommonUtils.click(driver, unCheckReturnDate());
            }}
            catch(Exception e){        
            }
        }
//      if (journeyType == JourneyType.ROUND_TRIP)         
        else {
         setReturnDate();          
        }
        setPassengers();
        CommonUtils.javaScriptExecuterClick(driver, searchFlightButton());
        return flightBookingDetails;
    }
    private void setFlightBookingDetailsObject(Map<Integer, String> flightTestCase, Flight flight) {

        flightBookingDetails = new FlightBookingDetails();
        flightBookingDetails.setFlight(flight);

        flightBookingDetails.setSourceCityCode(flightTestCase.get(TestCaseExcelConstant.COL_FLIGHT_SOURCE));
        flightBookingDetails.setDestinationCityCode(flightTestCase.get(TestCaseExcelConstant.COL_FLIGHT_DESTINATION));

        flightBookingDetails
                .setAdultsCount(Integer.parseInt(flightTestCase.get(TestCaseExcelConstant.COL_FLIGHT_ADULT)));
        flightBookingDetails.setChildrenCount(Integer.parseInt(flightTestCase
                .get(TestCaseExcelConstant.COL_FLIGHT_CHILD)));
        flightBookingDetails.setInfantsCount(Integer.parseInt(flightTestCase
                .get(TestCaseExcelConstant.COL_FLIGHT_INFANT)));

        int onwardIncrement = Integer.parseInt(flightTestCase.get(TestCaseExcelConstant.COL_FLIGHT_ONWARD_DATE_ADD));
        Calendar onwardDate = CalendarUtils.getCalendarIncrementedByDays(onwardIncrement);
        flightBookingDetails.setOnwardDate(onwardDate);

        String returnAdd = flightTestCase.get(TestCaseExcelConstant.COL_FLIGHT_RETURN_DATE_ADD);
        if (returnAdd != null) {
            flightBookingDetails.setJourneyType(JourneyType.ROUND_TRIP);
            int returnIncrement = onwardIncrement + Integer.parseInt(returnAdd);
            Calendar returnDate = CalendarUtils.getCalendarIncrementedByDays(returnIncrement);
            flightBookingDetails.setReturnDate(returnDate);
        }

        flightBookingDetails.setOnwardFlightCode(EntityMap.getAirlineCode(flightTestCase
                .get(TestCaseExcelConstant.COL_FLIGHT_ONWARD_FLIGHT)));
        if (flightBookingDetails.getJourneyType() == JourneyType.ROUND_TRIP) {
            flightBookingDetails.setReturnFlightCode(EntityMap.getAirlineCode(flightTestCase
                    .get(TestCaseExcelConstant.COL_FLIGHT_RETURN_FLIGHT)));
        }

        String ssrString = flightTestCase.get(TestCaseExcelConstant.COL_FLIGHT_SSR);
        if (StringUtils.isNotBlank(ssrString)) {
            List<String> addonsFlag = StringUtilities.split(ssrString, Constant.PIPE);
            flightBookingDetails.setMealsFlag(StringUtils.equalsIgnoreCase(addonsFlag.get(0), "Y"));
            flightBookingDetails.setBaggageFlag(StringUtils.equalsIgnoreCase(addonsFlag.get(1), "Y"));
            flightBookingDetails.setSeatsFlag(StringUtils.equalsIgnoreCase(addonsFlag.get(2), "Y"));
        }
    }

    private void setSource(){
        String srcCityCode = flightBookingDetails.getSourceCityCode();

        CommonUtils.click(driver, srcCity());
        srcCityInput().sendKeys(srcCityCode);
        WebElement desiredSrcCity = desiredSrcCity(srcCityCode);

        flightBookingDetails.setSourceCity(desiredSrcCity.getAttribute("data-city"));
        CommonUtils.click(driver, desiredSrcCity,10000);

        Log.info(testId, "Source City :: " + flightBookingDetails.getSourceCity());
        Log.info(testId, "Source City Code :: " + flightBookingDetails.getSourceCityCode());
    }

    private void setDestination(){
        String destCityCode = flightBookingDetails.getDestinationCityCode();

        CommonUtils.click(driver, destCity());
        destCityInput().sendKeys(destCityCode);
        WebElement desiredDestCity = desiredDestCity(destCityCode);

        flightBookingDetails.setDestinationCity(desiredDestCity.getAttribute("data-city"));
        CommonUtils.click(driver, desiredDestCity);

        Log.info(testId, "Destination City :: " + flightBookingDetails.getDestinationCity());
        Log.info(testId, "Destination City Code :: " + flightBookingDetails.getDestinationCityCode());
    }

    private void setOnwardDate() throws ParseException {
        Calendar onwardDate = flightBookingDetails.getOnwardDate();
        CommonUtils.click(driver, departureDate());
        PageHandler.sleep(testId,500l);
        CommonUtils.javaScriptExecuterClick(driver, CalendarUtils.selectDate(driver, onwardDate));
        Log.info(testId,
                "Onward Date :: " + CalendarUtils.calendarDateToDateString(onwardDate, Constant.LOG_FILE_DATE_FORMAT));
    }
   private void setReturnDate() throws ParseException {
        Calendar returnDate = flightBookingDetails.getReturnDate();
        PageHandler.sleep(testId,3 * 1000l);
        if(addReturnDate().isDisplayed()){
          CommonUtils.click(driver, addReturnDate()); 
        }else{
        CommonUtils.click(driver, returnDate()); 
        }   
        PageHandler.sleep(testId,500l);
        CommonUtils.javaScriptExecuterClick(driver, CalendarUtils.selectDate(driver, returnDate));
        Log.info(testId,
                "Return Date :: " + CalendarUtils.calendarDateToDateString(returnDate, Constant.LOG_FILE_DATE_FORMAT));
    }

    private void setPassengers(){

        while (!adultsSelectBox().isDisplayed()) {
            PageHandler.sleep(testId,100L);
        }

        int adultsCount = flightBookingDetails.getAdultsCount();
        int childrenCount = flightBookingDetails.getChildrenCount();
        int infantsCount = flightBookingDetails.getInfantsCount();

//        CommonUtils.selectBox(adultsSelectBox(), SelectBy.VALUE, Integer.toString(adultsCount));
//        CommonUtils.selectBox(childrenSelectBox(), SelectBy.VALUE, Integer.toString(childrenCount));
//        CommonUtils.selectBox(infantsSelectBox(), SelectBy.VALUE, Integer.toString(infantsCount));

    CommonUtils.click(driver, adultsSelectBox());
    CommonUtils.click(driver, selectCountBox(Integer.toString(adultsCount)));
    CommonUtils.click(driver, childrenSelectBox());
    CommonUtils.click(driver, selectCountBox(Integer.toString(childrenCount)));
    CommonUtils.click(driver, infantsSelectBox());
    CommonUtils.click(driver, selectCountBox(Integer.toString(infantsCount)));


        Log.info(testId, "Adults :: " + adultsCount);
        Log.info(testId, "Children :: " + childrenCount);
        Log.info(testId, "Infants :: " + infantsCount);
    }
}
