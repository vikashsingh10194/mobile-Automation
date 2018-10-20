package com.via.appmodules.common;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;

import com.via.pageobjects.common.CommonDetails;
import com.via.pageobjects.common.HomePageElements;
import com.via.testcases.common.TestCaseExcelConstant;
import com.via.utils.CommonUtils;
import com.via.utils.Constant;
import com.via.utils.Log;
import com.via.utils.PageHandler;
import com.via.utils.RepositoryParser;
import com.via.utils.Constant.Country;
import com.via.utils.Constant.SelectBy;

/**
 * 
 * @author Yash Gupta
 *
 */
public class HomePage extends HomePageElements {

    private CommonDetails commonDetails;

    public HomePage(String testId, WebDriver driver, RepositoryParser repositoryParser, Country country,
            Map<Integer, String> testCase) {
        super(testId, driver, repositoryParser);
        setCommonDetails(testCase, country);
    }

    public CommonDetails execute(){
        Country country = commonDetails.getCountry();
        if (country == Country.INDONESIA || country == Country.THAILAND) {
            setEnglishLanguage();
        }
        loginLogout();

        return commonDetails;
    }

    //Handeling via msg of oman & uae-Kishor
    public void closeViaMsg(Country country){
    	try{
    	CommonUtils.click(driver, crossHomepageKey());
        PageHandler.sleep(testId, 1000L);
    	}
        catch(Exception e){

        }
    	}
    
    private void setCommonDetails(Map<Integer, String> testCase, Country country) {
        commonDetails = new CommonDetails();
        commonDetails.setCountry(country);

              if(country==Country.OMAN ||country==Country.UAE || country==Country.SAUDI){
              closeViaMsg(country);    
              }
        
        String login = testCase.get(TestCaseExcelConstant.COL_LOGIN);
        if (StringUtils.equalsIgnoreCase(login, "Yes") || StringUtils.equalsIgnoreCase(login, "Y")) {
            commonDetails.setLogin(true);
        }
        commonDetails.setPromoCode(testCase.get(TestCaseExcelConstant.COL_PROMO_CODE));
        commonDetails.setPaymentMode(testCase.get(TestCaseExcelConstant.COL_PAYMENT_MODE));
        commonDetails.setPaymentType(testCase.get(TestCaseExcelConstant.COL_PAYMENT_TYPE));
    }

    /**
     * Opens the navigation drawer and sets the language to english
     */
    public void setEnglishLanguage(){
        openNavigationDrawer().click();
        PageHandler.sleep(testId,2 * 1000L);
        CommonUtils.selectBox(languageSelectBox(), SelectBy.VISIBLETEXT, "English");
        Log.info(testId, "Language Changed to English");
        PageHandler.sleep(testId,1 * 1000L);
    }

    /**
     * Login or logout, if not according to the requirement
     */
    private void loginLogout(){
        boolean isLoggedin = isLoggedin();
        if (!isLoggedin && commonDetails.isLogin()) {
            login(commonDetails.getCountry());
        } else if (isLoggedin && !commonDetails.isLogin()) {
            logout();
        }
    }

    /**
     * Checks if its loggedin, by checking the user name. If username is 'Login'
     * returns false
     */
    public boolean isLoggedin() {
        if (StringUtils.equalsIgnoreCase(currUserName().getAttribute("textContent"), "Login")) {
            return false;
        }
        return true;
    }

    private void login(Country country){
        openNavigationDrawer().click();
        PageHandler.sleep(testId,1000l);
        loginDrawerOption().click();

        switch (country) {
        case INDONESIA:
            username().sendKeys(Constant.ID_USERNAME);
            password().sendKeys(Constant.ID_PASSWORD);
            break;
        case SINGAPORE:
            username().sendKeys(Constant.SG_USERNAME);
            password().sendKeys(Constant.SG_PASSWORD);
            break;
        case UAE:
            username().sendKeys(Constant.UAE_USERNAME);
            password().sendKeys(Constant.UAE_PASSWORD);
            break;
        case OMAN:
          username().sendKeys(Constant.OMAN_USERNAME);
          password().sendKeys(Constant.OMAN_PASSWORD);
          break;
        case SAUDI:
          username().sendKeys(Constant.SAUDI_USERNAME);
          password().sendKeys(Constant.SAUDI_PASSWORD);
          break;
        case HONGKONG:
          username().sendKeys(Constant.HONGKONG_USERNAME);
          password().sendKeys(Constant.HONGKONG_PASSWORD);
          break;
        case THAILAND:
            username().sendKeys(Constant.TH_USERNAME);
            password().sendKeys(Constant.TH_PASSWORD);
            break;
        case PHILIPPINES:
            username().sendKeys(Constant.PH_USERNAME);
            password().sendKeys(Constant.PH_PASSWORD);
            break;
        default:
            break;
        }

        loginButton().click();
    }

    private void logout(){
        openNavigationDrawer().click();
        PageHandler.sleep(testId,1000l);
        logoutDrawerOption().click();
    }

    public CommonDetails getCommonDetails() {
        return commonDetails;
    }
}
