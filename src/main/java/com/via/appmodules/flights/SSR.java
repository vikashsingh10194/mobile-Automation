package com.via.appmodules.flights;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.via.pageobjects.flights.SSRElements;
import com.via.utils.CommonUtils;
import com.via.utils.Constant.Country;
import com.via.utils.NumberUtility;
import com.via.utils.PageHandler;
import com.via.utils.RandomValues;
import com.via.utils.RepositoryParser;

/**
 * 
 * @author Yash Gupta
 *
 */
public class SSR extends SSRElements {

    private Country      country;
    private double       addonsPrice      = 0.0;

    private final String LABEL_BAGGAGE_ID = "baggageLabel";
    private final String LABEL_MEALS_ID   = "mealLabel";
    private final String LABEL_SEATS_ID   = "seatLabel";

    protected SSR(String testId, WebDriver driver, RepositoryParser repositoryParser, Country country) {
        super(testId, driver, repositoryParser);
        this.country = country;
    }

    public void openAddons() {
      CommonUtils.javaScriptExecuterClick(driver, open());
    }

    public void closeAddons() {
        CommonUtils.click(driver, close(), 10);
    }

    /**
     * Checks if addons block has opened or not
     * 
     * @return true if addons has opened, else false
     */
    public boolean hasAddons(){
        PageHandler.sleep(testId,1000L);
        if (StringUtils.equals(addonsContainer().getAttribute("class"), "ui-content")) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a particular addon is present or not
     * 
     * @param addonId The id of the addon label
     */
    private boolean hasAddon(String addonId) {
        List<WebElement> labels = labels();
        for (WebElement label : labels) {
            if (StringUtils.equals(label.getAttribute("id"), addonId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Selects the meals, if available
     * 
     * @return false in case meals is not available, else true
     */
    public boolean selectMeals() {
        if (!hasAddon(LABEL_MEALS_ID)) {
            return false;
        }

        CommonUtils.javaScriptExecuterClick(driver, mealLabel());
        List<WebElement> mealSelectElements = mealSelect();
        List<WebElement> mealPriceElements = mealPrice();
        int totalMealElements = mealSelectElements.size();

        for (int mealElementIndex = 0; mealElementIndex < totalMealElements; mealElementIndex++) {
            addonsPrice += selectAAddon(mealSelectElements.get(mealElementIndex),
                    mealPriceElements.get(mealElementIndex));
        }
        return true;
    }

    /**
     * Selects the baggage, if available
     * 
     * @return false in case baggage is not available, else true
     */
    public boolean selectBaggage() {
        if (!hasAddon(LABEL_BAGGAGE_ID)) {
            return false;
        }

        CommonUtils.javaScriptExecuterClick(driver, baggageLabel());
        List<WebElement> baggageSelectElements = baggageSelect();
        List<WebElement> baggagePriceElements = baggagePrice();
        int totalBaggageElements = baggageSelectElements.size();

        for (int baggageElementIndex = 0; baggageElementIndex < totalBaggageElements; baggageElementIndex++) {
            addonsPrice += selectAAddon(baggageSelectElements.get(baggageElementIndex),
                    baggagePriceElements.get(baggageElementIndex));
        }
        return true;
    }

    /**
     * Selects the seats, if available
     * 
     * @return false in case seats is not available, else true
     */
    public boolean selectSeats(int passengers) {
        if (!hasAddon(LABEL_SEATS_ID)) {
            return false;
        }

        CommonUtils.javaScriptExecuterClick(driver, seatLabel());
        List<WebElement> seatButtonElements = seatButton();

        int totalSeatElements = seatButtonElements.size();

        for (int flightIndex = 0; flightIndex < totalSeatElements; flightIndex++) {
            WebElement seatBtn = seatButtonElements.get(flightIndex);
            String btnId = seatBtn.getAttribute("data-finfo");
            CommonUtils.javaScriptExecuterClick(driver, seatBtn);
            CommonUtils.sleep(1 * 1000l);
            selectAFlightSeats(btnId, passengers);

        }

        CommonUtils.sleep(2000l);

        List<WebElement> seatPriceElements = seatCost();

        for (WebElement priceElement : seatPriceElements) {
            addonsPrice += NumberUtility.getAmountFromString(country, priceElement.getText());
        }

        return true;
    }

    private void selectAFlightSeats(String btnId, int passengers) {
        List<WebElement> availableSeats = availableSeats(btnId);
        for (int passIndex = 0; passIndex < passengers; passIndex++) {
            CommonUtils.javaScriptExecuterClick(driver, availableSeats.get(passIndex));
        }
        CommonUtils.javaScriptExecuterClick(driver, bookSeat());
    }

    private double selectAAddon(WebElement selectElement, WebElement priceElement) {
        CommonUtils.click(driver, selectElement);
        CommonUtils.javaScriptExecuterClick(driver, selectAddonRandomOption(selectElement));
        return NumberUtility.getAmountFromString(country, priceElement.getText());
    }

    private WebElement selectAddonRandomOption(WebElement selectElement) {
        List<WebElement> options = selectElement.findElements(By.tagName("option"));
        int randomOption = RandomValues.getRandomNumberBetween(0, options.size() - 1);
        return options.get(randomOption);
    }

    public double getAddonsPrice() {
        return addonsPrice;
    }

}
