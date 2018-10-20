package com.via.pageobjects.flights;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.via.utils.CommonUtils;
import com.via.utils.PageHandler;
import com.via.utils.RepositoryParser;

/**
 * 
 * @author Yash Gupta
 *
 */
public class FlightItineraryElements extends PageHandler {

    private RepositoryParser repositoryParser;
    private final String     PAGE_NAME = "reviewItinerary";

    protected FlightItineraryElements(String testId, WebDriver driver, RepositoryParser repositoryParser) {
        super(testId, driver);
        this.repositoryParser = repositoryParser;
    }

    protected List<WebElement> flights() {
        return findElements(repositoryParser, PAGE_NAME, "flights");
    }

  protected WebElement flightName(String flightIndex) {
    return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "flightName",
        flightIndex, flightIndex));
  }

  protected WebElement checkRefundable(String flightIndex) {
    return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME,
        "refundableOrNot", flightIndex, flightIndex));
  }

  protected WebElement flightCode(String flightIndex) {
    return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "flightCode",
        flightIndex, flightIndex));
  }

  protected WebElement operatingCarrier(String flightIndex) {
    return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME,
        "operatingCarrier", flightIndex, flightIndex));
  }

  protected WebElement srcArptCode(String flightIndex) {
    return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "srcArptCode",
        flightIndex, flightIndex));
  }

  protected WebElement departureTime(String flightIndex) {
    return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "departureTime",
        flightIndex, flightIndex));
  }

  protected WebElement departureDate(String flightIndex) {
    return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "departureDate",
        flightIndex, flightIndex));
  }

  protected WebElement srcArptName(String flightIndex) {
    return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "srcArptName",
        flightIndex, flightIndex));
  }

  protected WebElement flightDuration(String flightIndex) {
    return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "flightDuration",
        flightIndex, flightIndex));
  }

    protected WebElement destArptCode(String flightIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "destArptCode", flightIndex,
                flightIndex));
    }

    protected WebElement arrivalTime(String flightIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "arrivalTime", flightIndex));
    }

    protected WebElement arrivalDate(String flightIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "arrivalDate", flightIndex));
    }

    protected WebElement destArptName(String flightIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "destArptName", flightIndex));
    }

    protected WebElement totalFare() {
      return findElement(repositoryParser, PAGE_NAME, "totalFare");
    }
    protected WebElement totalAmount() {
        return findElement(repositoryParser, PAGE_NAME, "totalAmount");
    }

    protected WebElement continueButton() {
        return findElement(repositoryParser, PAGE_NAME, "continueButton");
    }

    protected WebElement closeItinerary() {
        return findElement(repositoryParser, PAGE_NAME, "closeItinerary");
    }
}
