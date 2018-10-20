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
public class SSRElements extends PageHandler {

    private RepositoryParser repositoryParser;
    private final String     PAGE_NAME = "ssr";

    protected SSRElements(String testId, WebDriver driver, RepositoryParser repositoryParser) {
        super(testId, driver);
        this.repositoryParser = repositoryParser;
    }

    protected WebElement open() {
        return findElement(repositoryParser, PAGE_NAME, "addonButton");
    }

    protected WebElement addonsContainer() {
        return findElement(repositoryParser, PAGE_NAME, "addonsContainer");
    }

    protected List<WebElement> labels() {
        return findElements(repositoryParser, PAGE_NAME, "labels");
    }

    protected WebElement baggageLabel() {
        return findElement(repositoryParser, PAGE_NAME, "baggageLabel");
    }

    protected WebElement seatLabel() {
        return findElement(repositoryParser, PAGE_NAME, "seatLabel");
    }

    protected WebElement mealLabel() {
        return findElement(repositoryParser, PAGE_NAME, "mealLabel");
    }

    protected List<WebElement> baggagePrice() {
        return findElements(repositoryParser, PAGE_NAME, "baggagePrice");
    }

    protected List<WebElement> baggageSelect() {
        return findElements(repositoryParser, PAGE_NAME, "baggageSelect");
    }

    protected List<WebElement> mealPrice() {
        return findElements(repositoryParser, PAGE_NAME, "mealPrice");
    }

    protected List<WebElement> mealSelect() {
        return findElements(repositoryParser, PAGE_NAME, "mealSelect");
    }

    protected List<WebElement> seatCost() {
        return findElements(repositoryParser, PAGE_NAME, "seatCost");
    }

    protected List<WebElement> seatButton() {
        return findElements(repositoryParser, PAGE_NAME, "seatButton");
    }

    protected List<WebElement> availableSeats(String btnId) {
        return findElements(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "availableSeats", btnId));
    }

    protected WebElement seatsSelected() {
        return findElement(repositoryParser, PAGE_NAME, "seatsSelected");
    }

    protected WebElement bookSeat() {
        return findElement(repositoryParser, PAGE_NAME, "bookSeat");
    }

    protected WebElement close() {
        return findElement(repositoryParser, PAGE_NAME, "closeAddons");
    }

}
