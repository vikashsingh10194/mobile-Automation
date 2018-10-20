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
public class TravellersDetailsElements extends PageHandler {

    protected RepositoryParser repositoryParser;
    private final String       PAGE_NAME = "travellersDetails";
    private WebDriverWait wait;

    protected TravellersDetailsElements(String testId, WebDriver driver, RepositoryParser repositoryParser) {
        super(testId, driver);
        this.repositoryParser = repositoryParser;
        wait = new WebDriverWait(driver, 15000);
    }

    protected WebElement itineraryDetails() {
        return findElement(repositoryParser, PAGE_NAME, "itineraryDetails");
    }

    protected WebElement expandPassenger(String paxIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "expandPassenger", paxIndex));
    }

    protected WebElement title(String paxIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "title", paxIndex));
    }

    protected WebElement firstName(String paxIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "firstName", paxIndex));
    }

    protected WebElement lastName(String paxIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "lastName", paxIndex));
    }

    protected WebElement dob(String paxIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "dob", paxIndex));
    }

    protected WebElement nationality(String paxIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "nationality", paxIndex));
    }
    protected WebElement desiredNationality(String uLPaxIndex,String spanPaxIndex) {
      WebElement element=wait.until(ExpectedConditions.elementToBeClickable(findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "desiredNationality", uLPaxIndex,spanPaxIndex))));
      return element;
    }
    protected WebElement passportNo(String paxIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "passportNo", paxIndex));
    }

    protected WebElement passportExpiry(String paxIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "passportExpiry", paxIndex));
    }

    protected WebElement isdCode() {
        return findElement(repositoryParser, PAGE_NAME, "isdCode");
    }

    protected WebElement contactMobile() {
        return findElement(repositoryParser, PAGE_NAME, "contactMobile");
    }

    protected WebElement contactEmail() {
        return findElement(repositoryParser, PAGE_NAME, "contactEmail");
    }

    protected WebElement proceedToPayment() {
      return findElement(repositoryParser, PAGE_NAME, "proceed");
    }
    protected WebElement makePayment() {
        return findElement(repositoryParser, PAGE_NAME, "makePayment");
    }
}
