package com.via.appmodules.common;

import lombok.AllArgsConstructor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.via.pageobjects.common.CommonDetails;
import com.via.utils.CommonUtils;
import com.via.utils.Constant;
import com.via.utils.Log;
import com.via.utils.NumberUtility;
import com.via.utils.StringUtilities;

/**
 * Handles the alert boxes displayed (Fare change alert box)
 * 
 * @author Yash Gupta
 *
 */
@AllArgsConstructor
public class AlertBoxHandler {

    private String    testId;
    private WebDriver driver;

    public boolean isDisplayed() {
        boolean alertFlag = false;
        try {
            CommonUtils.setImplicitWaitTime(driver, 1000);
            driver.findElement(By.id("expireDiv"));
            alertFlag = true;
        } catch (Exception e) {

        } finally {
            CommonUtils.setImplicitWaitTime(driver);
        }
        return alertFlag;
    }

    public String getMessage() {
        return driver.findElement(By.className("alertHead")).getText();
    }

    public void accept() {
        driver.findElement(By.id("modalConfirmCTA")).click();
    }

    public void cancel() {
        driver.findElement(By.id("modalCancelCTA")).click();
    }

    /**
     * Handles the price change alert box Id dispayed, parses the price change
     * msg and updates the new price and accepts the alert
     * 
     * @return true in case the alert is displayed, else false
     */
    public boolean handlePriceChange(CommonDetails commonDetails) {
        if (isDisplayed()) {
            String msg = getMessage();
            Log.info(testId, msg);
            double newFare = NumberUtility.getAmountFromString(commonDetails.getCountry(),
                    StringUtilities.split(msg, Constant.WHITESPACE).get(6));
            commonDetails.setTotalFare(newFare);
            accept();
            return true;
        }
        return false;
    }
}
