package com.via.appmodules.common.reseller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;

import com.via.appmodules.common.HomePage;
import com.via.pageobjects.common.CommonDetails;
import com.via.pageobjects.common.reseller.AgentLoginPageElements;
import com.via.testcases.common.TestCaseExcelConstant;
import com.via.utils.CommonUtils;
import com.via.utils.Constant;
import com.via.utils.Constant.Country;
import com.via.utils.CustomAssert;
import com.via.utils.RepositoryParser;
import com.via.utils.StringUtilities;

public class AgentLoginPage extends AgentLoginPageElements {

    private HomePage homePage;

    public AgentLoginPage(String testId, WebDriver driver, RepositoryParser repositoryParser, Country country,
            Map<Integer, String> testCase){
        super(testId, driver, repositoryParser);
        homePage = new HomePage(testId, driver, repositoryParser, country, testCase);
        agentLogin(testCase, country);
    }

    public CommonDetails execute(){
        CommonDetails commonDetails = homePage.getCommonDetails();
        Country country = commonDetails.getCountry();

        if(country==Country.PHILIPPINES){
        	homePage.crossPopup().click();
        }
        
        if (country == Country.INDONESIA || country == Country.THAILAND) {
            homePage.setEnglishLanguage();
        }
        return commonDetails;
    }

    private void agentLogin(Map<Integer, String> testCase, Country country){
        CommonDetails commonDetails = homePage.getCommonDetails();
        commonDetails.setCountry(country);

        String loginData = testCase.get(TestCaseExcelConstant.COL_LOGIN);
        List<String> login = StringUtilities.split(loginData, Constant.UNDERSCORE);

        if (StringUtils.isBlank(loginData) || StringUtils.equalsIgnoreCase(login.get(0), "No")
                || StringUtils.equalsIgnoreCase(login.get(0), "N")) {
            CustomAssert.assertFail(testId, "Please provide login details correctly.");
        }

        String loginType = "Admin";

        if (login.size() > 1 && StringUtils.containsIgnoreCase(login.get(1), "Desk")) {
            loginType = "Desk";
            loginDesk(country);
        } else {
            loginAdmin(country);
        }

        CustomAssert.assertTrue(testId, homePage.isLoggedin(), "Successfully Login As : " + loginType,
                "Invalid Login Credentials.");

        commonDetails.setLogin(true);
    }

    private void loginDesk(Country country){

        CommonUtils.javaScriptExecuterClick(driver, deskUserRadio());

        switch (country) {
        case INDONESIA:
            username().sendKeys(Constant.ID_DESK_USERNAME);
            deskId().sendKeys(Constant.ID_DESK_ID);
            password().sendKeys(Constant.ID_DESK_PASSWORD);
            break;
        case SINGAPORE:
            username().sendKeys(Constant.SG_DESK_USERNAME);
            deskId().sendKeys(Constant.SG_DESK_ID);
            password().sendKeys(Constant.SG_DESK_PASSWORD);
            break;
        case UAE:
            username().sendKeys(Constant.UAE_DESK_USERNAME);
            deskId().sendKeys(Constant.UAE_DESK_ID);
            password().sendKeys(Constant.UAE_DESK_PASSWORD);
            break;           
        case OMAN:
          username().sendKeys(Constant.OMAN_DESK_USERNAME);
          deskId().sendKeys(Constant.OMAN_DESK_ID);
          password().sendKeys(Constant.OMAN_DESK_PASSWORD);
          break;
        case THAILAND:
            username().sendKeys(Constant.TH_DESK_USERNAME);
            deskId().sendKeys(Constant.TH_DESK_ID);
            password().sendKeys(Constant.TH_DESK_PASSWORD);
            break;
        case PHILIPPINES:
            username().sendKeys(Constant.PH_DESK_USERNAME);
            deskId().sendKeys(Constant.PH_DESK_ID);
            password().sendKeys(Constant.PH_DESK_PASSWORD);
            break;
        default:
            break;
        }

        loginButton().click();
    }

    private void loginAdmin(Country country){

        CommonUtils.javaScriptExecuterClick(driver, adminRadio());

        switch (country) {
        case INDONESIA:
            username().sendKeys(Constant.ID_ADMIN_USERNAME);
            password().sendKeys(Constant.ID_ADMIN_PASSWORD);
            break;
        case SINGAPORE:
            username().sendKeys(Constant.SG_ADMIN_USERNAME);
            password().sendKeys(Constant.SG_ADMIN_PASSWORD);
            break;
        case UAE:
            username().sendKeys(Constant.UAE_ADMIN_USERNAME);
            password().sendKeys(Constant.UAE_ADMIN_PASSWORD);
            break;
        case OMAN:
            username().sendKeys(Constant.OMAN_ADMIN_USERNAME);
            password().sendKeys(Constant.OMAN_ADMIN_PASSWORD);
            break;
        case THAILAND:
            username().sendKeys(Constant.TH_ADMIN_USERNAME);
            password().sendKeys(Constant.TH_ADMIN_PASSWORD);
            break;
        case PHILIPPINES:
            username().sendKeys(Constant.PH_ADMIN_USERNAME);
            password().sendKeys(Constant.PH_ADMIN_PASSWORD);
            break;
        default:
            break;
        }

        loginButton().click();
    }
}
