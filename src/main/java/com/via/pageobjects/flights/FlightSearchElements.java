package com.via.pageobjects.flights;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.via.utils.CommonUtils;
import com.via.utils.PageHandler;
import com.via.utils.RepositoryParser;

/**
 * 
 * @author Yash Gupta
 *
 */
public class FlightSearchElements extends PageHandler {

    private RepositoryParser repositoryParser;
    private final String     PAGE_NAME = "flightSearch";
    private WebDriverWait wait ;

    protected FlightSearchElements(String testId, WebDriver driver, RepositoryParser repositoryParser) {
        super(testId, driver);
        this.repositoryParser = repositoryParser;
        wait = new WebDriverWait(driver, 15);
    }
    
    //get Flights link
    protected WebElement getFlightLink()
    {
      return findElement(repositoryParser, PAGE_NAME, "flightLink");
    }
    
    protected WebElement oneWay() {
        return findElement(repositoryParser, PAGE_NAME, "oneWay");
    }

    protected WebElement roundTrip() {
        return findElement(repositoryParser, PAGE_NAME, "roundTrip");
    }

    protected WebElement srcCity() {
        return findElement(repositoryParser, PAGE_NAME, "srcCity");
    }

    protected WebElement destCity() {
        return findElement(repositoryParser, PAGE_NAME, "destCity");
    }

    protected WebElement srcCityInput() {
        return findElement(repositoryParser, PAGE_NAME, "srcCityInput");
    }

    protected WebElement desiredSrcCity(String arptCode) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "desiredSrcCity", arptCode));
    }

    protected WebElement destCityInput() {
        return findElement(repositoryParser, PAGE_NAME, "destCityInput");
    }

    protected WebElement desiredDestCity(String arptCode) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "desiredDestCity", arptCode));
    }

    protected WebElement departureDate() {
        return findElement(repositoryParser, PAGE_NAME, "departureDate");
    }

    protected WebElement returnDate() {
        return findElement(repositoryParser, PAGE_NAME, "returnDate");
    }
    protected WebElement addReturnDate() {
        return findElement(repositoryParser, PAGE_NAME, "addReturnDate");  
    }

  protected WebElement unCheckReturnDate() {
    WebElement element =
        wait.until(ExpectedConditions.elementToBeClickable(findElement(repositoryParser, PAGE_NAME,
            "unCheckReturnDate")));
    return element;
  }
    protected WebElement adultsSelectBox() {
        return findElement(repositoryParser, PAGE_NAME, "adultsSelectBox");
    }

    protected WebElement childrenSelectBox() {
        return findElement(repositoryParser, PAGE_NAME, "childrenSelectBox");
    }

    protected WebElement infantsSelectBox() {
        return findElement(repositoryParser, PAGE_NAME, "infantsSelectBox");
    }
    //Added to select count box..
    protected WebElement selectCountBox(String count) {
      return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "selectCountBox", count));
    }
    protected WebElement searchFlightButton() {
        return findElement(repositoryParser, PAGE_NAME, "searchFlightButton");
    }

}
