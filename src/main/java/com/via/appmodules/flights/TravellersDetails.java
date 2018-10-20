package com.via.appmodules.flights;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.via.appmodules.common.AlertBoxHandler;
import com.via.pageobjects.common.CommonDetails;
import com.via.pageobjects.flights.FlightBookingDetails;
import com.via.pageobjects.flights.TravellersDetailsElements;
import com.via.utils.CommonUtils;
import com.via.utils.Constant;
import com.via.utils.Constant.Country;
import com.via.utils.Constant.CountryCode;
import com.via.utils.Constant.PassengerType;
import com.via.utils.Constant.SelectBy;
import com.via.utils.Log;
import com.via.utils.PageHandler;
import com.via.utils.RandomValues;
import com.via.utils.RepositoryParser;
import com.via.utils.StringUtilities;

/**
 * 
 * @author Yash Gupta
 *
 */
public class TravellersDetails extends TravellersDetailsElements {

    private FlightBookingDetails flightBookingDetails;
    private CommonDetails        commonDetails;

    public TravellersDetails(String testId, WebDriver driver, RepositoryParser repositoryParser,
            FlightBookingDetails flightBookingDetails, CommonDetails commonDetails) {
        super(testId, driver, repositoryParser);
        this.flightBookingDetails = flightBookingDetails;
        this.commonDetails = commonDetails;
    }

    public void execute(){
        Log.openPage(testId, "Travellers Details Page");
        new AlertBoxHandler(testId, driver).handlePriceChange(commonDetails);

        setInsurance();

        validateItinerary();
        Log.info(testId, "Itinerary Validated");
        PageHandler.sleep(testId,5000L);
        setPaxDetails();
        Log.info(testId, "Passenger Details Set");

        setContactDetails(commonDetails.getCountry());
        Log.info(testId, "Contact Details Set");

        setSSR();
        CommonUtils.javaScriptExecuterClick(driver, proceedToPayment());
        CommonUtils.javaScriptExecuterClick(driver, makePayment());
        PageHandler.sleep(testId, 10000L);
    }

    private void setInsurance() {
        CommonUtils.setImplicitWaitTime(driver, 100L);

        WebElement noIns = null;
        WebElement yesIns = null;
        try {
            noIns = driver.findElement(By.xpath("//input[contains(@id, 'noInsr')]"));
        } catch (Exception e) {
        }

        try {
            yesIns = driver.findElement(By.xpath("//input[contains(@id, 'yesInsr')]"));
        } catch (Exception e) {
        }

        CommonUtils.setImplicitWaitTime(driver);

        if (noIns == null && yesIns == null) {
            return;
        }

        if (noIns != null && !noIns.isSelected()) {
            CommonUtils.javaScriptExecuterClick(driver, noIns);
        }

        else if (yesIns != null && yesIns.isSelected()) {
            CommonUtils.javaScriptExecuterClick(driver, yesIns);
        }
    }

    private void validateItinerary() {
        FlightItinerary flightItinerary = new FlightItinerary(testId, driver, repositoryParser, flightBookingDetails,
                commonDetails);
        itineraryDetails().click();
        flightItinerary.validateDetails();
        flightItinerary.close();
    }

    /**
     * Sets all the passengers details
     */
    private void setPaxDetails() {
        int adults = flightBookingDetails.getAdultsCount();
        int children = flightBookingDetails.getChildrenCount();
        int infants = flightBookingDetails.getInfantsCount();
        RandomValues.setUsedNameList();
        for (int adultIndex = 0; adultIndex < adults; adultIndex++) {
            setAPaxDetails(adultIndex, PassengerType.ADULT);
        }

        for (int childIndex = 0; childIndex < children; childIndex++) {
            setAPaxDetails(adults + childIndex, PassengerType.CHILD);
        }

        for (int infantIndex = 0; infantIndex < infants; infantIndex++) {
            setAPaxDetails(adults + children + infantIndex, PassengerType.INFANT);
        }

    }

    /**
     * Sets a pax details (title, first name and last name) Also sets the the
     * dob and passport info if displayed
     * 
     * @param paxIndex The index of pax starting from 0
     * @param passengerType The passengerType (Adult, child or infant)
     */
    private void setAPaxDetails(int paxIndex, PassengerType passengerType) {
        String paxIndexString = Integer.toString(paxIndex);
        if (paxIndex != 0) {
           CommonUtils.javaScriptExecuterClick(driver, expandPassenger(paxIndexString));
        }

        setTitle(passengerType, paxIndexString);       
        List<String> name=null;
        try {
          name = StringUtilities.split(RandomValues.getRandomName(commonDetails.getCountry()), Constant.WHITESPACE);
        } catch (Exception e) {
          e.printStackTrace();
        }
        String firstName = name.get(0);
        String lastName = name.get(1);
        firstName(paxIndexString).click();
        firstName(paxIndexString).sendKeys(firstName);
        lastName(paxIndexString).click();
        lastName(paxIndexString).sendKeys(lastName);

        if (isDOBInDom(paxIndexString)) {
            WebElement dobElement = dob(paxIndexString);
//            String dateFormat = dobElement.getAttribute("placeholder");
//            dateFormat = dateFormat.replaceAll("(?i)YYYY", getDOBYear(passengerType));
//            dateFormat = dateFormat.replaceAll("(?i)DD", Constant.BIRTH_DATE);
//            dateFormat = dateFormat.replaceAll("(?i)MM", Constant.BIRTH_MONTH);
//            dobElement.clear();
//            dobElement.sendKeys(dateFormat);
          dobElement.sendKeys(Constant.BIRTH_MONTH,Constant.BIRTH_DATE,getDOBYear(passengerType));
          Log.info(testId, "::  DOB Set  :: ");
        }
            
      if (isNationalityInDom(paxIndexString) && nationality(paxIndexString).isDisplayed()) {
        CountryCode countryCode = CountryCode.valueOf(commonDetails.getCountry().toString());
        nationality(paxIndexString).clear();
        nationality(paxIndexString).sendKeys(countryCode.getCountryCode());
        int index=Integer.parseInt(paxIndexString)+1;
        String paxCount=Integer.toString(index);
        WebElement desiredNationality = desiredNationality(paxCount,countryCode.getCountryCode());
        CommonUtils.javaScriptExecuterClick(driver, desiredNationality);
        Log.info(testId, "Nationality Set  :: " + countryCode.getCountryCode());
      }
        if (isPassportInDom(paxIndexString) && passportNo(paxIndexString).isDisplayed()) {
            passportNo(paxIndexString).sendKeys(RandomValues.getRandomPassportNumber());
            WebElement passportExpiry = passportExpiry(paxIndexString);
//            String dateFormat = passportExpiry.getAttribute("placeholder");
//            dateFormat = dateFormat.replaceAll("(?i)YYYY", Constant.PASSPORT_YEAR);
//            dateFormat = dateFormat.replaceAll("(?i)DD", Constant.PASSPORT_DATE);
//            dateFormat = dateFormat.replaceAll("(?i)MM", Constant.PASSPORT_MONTH);
//            passportExpiry.clear();
//            passportExpiry.sendKeys(dateFormat);
            passportExpiry.sendKeys(Constant.PASSPORT_MONTH,Constant.PASSPORT_DATE,Constant.PASSPORT_YEAR);
        }

    }
    private void setTitle(PassengerType passengerType, String paxIndexString) {
        WebElement title = title(paxIndexString);
        if (passengerType == PassengerType.ADULT) {
            CommonUtils.selectBox(title, SelectBy.VALUE, Constant.TITLE_ADULT);
        } else if (passengerType == PassengerType.CHILD) {
            CommonUtils.selectBox(title, SelectBy.VALUE, Constant.TITLE_CHILD);
        } else {
            CommonUtils.selectBox(title, SelectBy.VALUE, Constant.TITLE_INFANT);
        }
    }

    private String getDOBYear(PassengerType passengerType) {
        if (passengerType == PassengerType.ADULT) {
            return Constant.BIRTH_YEAR_ADULT;
        } else if (passengerType == PassengerType.CHILD) {
            return Constant.BIRTH_YEAR_CHILD;
        } else {
            return Constant.BIRTH_YEAR_INFANT;
        }
    }

    private boolean isDOBInDom(String paxIndexString) {
        boolean dobFlag = false;
        try {
            CommonUtils.setImplicitWaitTime(driver, 200);
            String dobDivXpath = "//input[@id='dob-" + paxIndexString + "']";
            WebElement dobDiv = driver.findElement(By.xpath(dobDivXpath));

            if (!StringUtils.containsIgnoreCase(dobDiv.getAttribute("style"), "display:none") && dobDiv.isDisplayed()) {
                dobFlag = true;
            }
        } catch (Exception e) {

        } finally {
            CommonUtils.setImplicitWaitTime(driver);
        }
        return dobFlag;
    }
    
    private boolean isNationalityInDom(String paxIndexString) {
      boolean nationalityFlag = false;
      try {
          CommonUtils.setImplicitWaitTime(driver, 200);
          driver.findElement(By.id("nat-" + paxIndexString));
          nationalityFlag = true;
      } catch (Exception e) {

      } finally {
          CommonUtils.setImplicitWaitTime(driver);
      }
      return nationalityFlag;
  }
    private boolean isPassportInDom(String paxIndexString) {
        boolean passportFlag = false;
        try {
            CommonUtils.setImplicitWaitTime(driver, 200);
            driver.findElement(By.id("num-" + paxIndexString));
            passportFlag = true;
        } catch (Exception e) {

        } finally {
            CommonUtils.setImplicitWaitTime(driver);
        }
        return passportFlag;
    }

    private void setContactDetails(Country country) {
        if (country == Country.THAILAND) {
            contactMobile().sendKeys(Constant.CONTACT_MOBILE_TH);
        } 
        else if(country==Country.HONGKONG){
          contactMobile().sendKeys(Constant.CONTACT_MOBILE_HK);
        }
        else {
            contactMobile().sendKeys(Constant.CONTACT_MOBILE);
        }
        contactEmail().sendKeys(Constant.CONTACT_EMAIL);
    }

    /**
     * Sets the SSR whichever needs to be set and that are available. If not
     * available, prints the error
     */
    private void setSSR(){
        if (!(flightBookingDetails.isMealsFlag() || flightBookingDetails.isBaggageFlag() || flightBookingDetails
                .isSeatsFlag())) {
            return;
        }
        SSR ssr = new SSR(testId, driver, repositoryParser, commonDetails.getCountry());
        ssr.openAddons();
        if (ssr.hasAddons()) {
            if (flightBookingDetails.isMealsFlag()) {
                if (!ssr.selectMeals()) {
                    Log.error(testId, "Meals not available");
                }
            }
            if (flightBookingDetails.isBaggageFlag()) {
                if (!ssr.selectBaggage()) {
                    Log.error(testId, "Baggage not available");
                }
            }
            if (flightBookingDetails.isSeatsFlag()) {
                if (!ssr.selectSeats(flightBookingDetails.getAdultsCount() + flightBookingDetails.getChildrenCount())) {
                    Log.error(testId, "Seats not available");
                }
            }
            ssr.closeAddons();

            double ssrPrice = ssr.getAddonsPrice();
            Log.info(testId, "SSR Price :: " + ssrPrice);
            commonDetails.setSsrCharges(ssrPrice);
        }
    }
}
