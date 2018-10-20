package com.via.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Yash Gupta
 *
 */
public class CalendarUtils {

    private static final String CAL_FORMAT = "MMM yyyy";

    private static WebElement selectDate(WebDriver driver, String date, String month, String year)
            throws ParseException {
        String targetDatesXpath = ".//div[@data-month='" + month + "'][@data-year='" + year
                + "']//div[contains(@class, 'vc-cell')][@data-date='" + date + "']";
        List<WebElement> targetDates = driver.findElements(By.xpath(targetDatesXpath));

        for (WebElement dateElement : targetDates) {
            if (!StringUtils.equals(dateElement.getAttribute("class"), "vc-cell vc-disabled-cell")) {
                return dateElement;
            }
        }

        return null;
    }

    public static WebElement selectDate(WebDriver driver, Calendar calendar) throws ParseException{

        goToCalendarMonth(driver, calendar);
        return selectDate(driver, Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)),
                Integer.toString(calendar.get(Calendar.MONTH)), Integer.toString(calendar.get(Calendar.YEAR)));
    }

    private static void goToCalendarMonth(WebDriver driver, Calendar calendar) throws ParseException{

      PageHandler.sleep(1000l);
        List<WebElement> calMonthList = driver.findElements(By.xpath(".//span[@class='vc-month-box-head-cell ']"));

        Calendar currentMonth = CalendarUtils.dateStringToCalendarDate(calMonthList.get(0).getText(), CAL_FORMAT);

        int value = (currentMonth.get(Calendar.MONTH) - calendar.get(Calendar.MONTH))
                + (currentMonth.get(Calendar.YEAR) - calendar.get(Calendar.YEAR)) * 12;

        while (value > 0) {
            CommonUtils.javaScriptExecuterClick(driver,
                    driver.findElement(By.xpath(".//span[contains(@class, 'js-prev-month')]")));
            value--;
        }
    }

    /**
     * Converts the date from string format to date in calendar object
     * 
     * @param dateString The date in string to be converted
     * @param format The format of the dateString
     * @return The date in Calendar data type
     * @throws ParseException
     */
    public static Calendar dateStringToCalendarDate(String dateString, String format) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(dateString));
        return calendar;
    }

    /**
     * Converts the date from Calendar data type to string format
     * 
     * @param calendar The date in calendar data type to be converted
     * @param format The format of the dateString to be converted into
     * @return The date in String
     */
    public static String calendarDateToDateString(Calendar calendar, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        formatter.setCalendar(calendar);
        return formatter.format(calendar.getTime());
    }

    /**
     * Increments the current calendar by days
     * 
     * @param daysIncrement The number of days to be incremented
     * @return The calendar with the days incremented
     */
    public static Calendar getCalendarIncrementedByDays(int daysIncrement) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysIncrement);
        return calendar;
    }

    /**
     * Compares the date and month of two calendars
     * 
     * @param testId The testId of the test case
     * @param calendar1 First calendar
     * @param calendar2 Second Calendar
     * @return Returns true if two calendars have same date and month, else
     *         false
     */
    public static boolean compareDateMonth(String testId, Calendar calendar1, Calendar calendar2) {
        if ((calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH))
                && (calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH))) {
            return true;
        }
        Log.error(
                testId,
                "Expected : " + CalendarUtils.calendarDateToDateString(calendar1, Constant.LOG_FILE_DATE_FORMAT)
                        + " Found : "
                        + CalendarUtils.calendarDateToDateString(calendar2, Constant.LOG_FILE_DATE_FORMAT));
        return false;
    }

    /**
     * Compares the date, month and year of two calendars
     * 
     * @param testId The testId of the test case
     * @param calendar1 First calendar
     * @param calendar2 Second Calendar
     * @return Returns true if two calendars have same date, month and year,
     *         else false
     */
    public static boolean compareDateMonthYear(String testId, Calendar calendar1, Calendar calendar2) {
        if ((calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH))
                && (calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH))
                && (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR))) {
            return true;
        }
        Log.error(
                testId,
                "Expected : " + CalendarUtils.calendarDateToDateString(calendar1, Constant.LOG_FILE_DATE_FORMAT)
                        + " Found : "
                        + CalendarUtils.calendarDateToDateString(calendar2, Constant.LOG_FILE_DATE_FORMAT));
        return true;
    }
}
