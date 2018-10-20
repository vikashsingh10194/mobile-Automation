package com.via.pageobjects.flights;

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
public class FlightResultElements extends PageHandler {

    protected RepositoryParser repositoryParser;
    private final String       PAGE_NAME = "flightResults";

    protected FlightResultElements(String testId, WebDriver driver, RepositoryParser repositoryParser) {
        super(testId, driver);
        this.repositoryParser = repositoryParser;
    }

    protected WebElement header() {
        return findElement(repositoryParser, PAGE_NAME, "header");
    }

    protected WebElement onwardFlight(String flightCode, String flightIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "onwardFlight", flightCode,
                flightIndex));
    }

    protected WebElement returnFlight(String flightCode, String flightIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "returnFlight", flightCode,
                flightIndex));
    }

    protected WebElement intlRoundTrip(String onwardFlightCode, String returnFlightCode, String flightIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "intlRoundTrip",
                onwardFlightCode, returnFlightCode, flightIndex));
    }

    protected WebElement bookButton() {
        return findElement(repositoryParser, PAGE_NAME, "bookButton");
    }

}
