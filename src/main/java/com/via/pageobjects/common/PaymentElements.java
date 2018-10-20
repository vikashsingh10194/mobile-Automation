package com.via.pageobjects.common;

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
public class PaymentElements extends PageHandler {

    private RepositoryParser repositoryParser;
    private final String     PAGE_NAME = "payments";
    private WebDriverWait wait;

    protected PaymentElements(String testId, WebDriver driver, RepositoryParser repositoryParser) {
        super(testId, driver);
        this.repositoryParser = repositoryParser;
        wait = new WebDriverWait(driver, 30);
    }

    /* Payment Amount */
    protected WebElement paymentFees() {
      WebElement element=findElement(repositoryParser, PAGE_NAME, "paymentFees");
      wait.until(ExpectedConditions.visibilityOf(element));
      return element;
    }
    protected WebElement hKPaymentFees() {
      WebElement element=findElement(repositoryParser, PAGE_NAME, "hKPaymentFees");
      wait.until(ExpectedConditions.visibilityOf(element));
      return element;
    }
    
    protected WebElement outerSpanValue() {
        return findElement(repositoryParser, PAGE_NAME, "outerSpanValue");
    }
    protected WebElement innerSpanValue(){
        return findElement(repositoryParser, PAGE_NAME, "innerSpanValue");
    }
    protected WebElement netAmount(){   
        WebDriverWait wait=new WebDriverWait(driver, 30);
        WebElement element=findElement(repositoryParser, PAGE_NAME, "netAmount");
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    /* Payment Mode */
    protected WebElement selectSavedCardPaymentMode() {
      return wait.until(ExpectedConditions.elementToBeClickable(findElement(repositoryParser,
          PAGE_NAME, "savedCard")));
    }
    public WebElement select123BankingPaymentMode() {
      return wait.until(ExpectedConditions.elementToBeClickable(findElement(repositoryParser,
          PAGE_NAME, "123Banking")));
    }
    protected WebElement get123BankingCancelBtn() {
      return wait.until(ExpectedConditions.elementToBeClickable(findElement(repositoryParser,
          PAGE_NAME, "123BankingCancelBtn")));
    }
    protected WebElement selectSavedCardDropDown() {
      return findElement(repositoryParser, PAGE_NAME, "selectSavedCard");
    }
    protected WebElement selectSavedCVV() {
      return findElement(repositoryParser, PAGE_NAME, "savedCardCVV");
    }
    protected WebElement bankTransfer() {
        return findElement(repositoryParser, PAGE_NAME, "bankTransfer");
    }

    protected WebElement atm() {
        return findElement(repositoryParser, PAGE_NAME, "atm");
    }

    protected WebElement netBanking() {
        return findElement(repositoryParser, PAGE_NAME, "netBanking");
    }
    
    protected WebElement creditCardDebitCard() {
      return findElement(repositoryParser, PAGE_NAME, "creditCardDebitCard");
    }
    protected WebElement creditCard() {
        return findElement(repositoryParser, PAGE_NAME, "creditCard");
    }

    protected WebElement debitCard() {
        return findElement(repositoryParser, PAGE_NAME, "debitCard");
    }
    
    protected WebElement emi() {
        return findElement(repositoryParser, PAGE_NAME, "emi");
    }

    protected WebElement cash() {
        return findElement(repositoryParser, PAGE_NAME, "cash");
    }

    /* Bank */
    protected WebElement baBank(String bank) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "BAbank", bank));
    }

    protected WebElement bank(String bank) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "bank", bank));
    }

    
    /* EMI */
    protected WebElement emiBank(String bank) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "emiBank", bank));
    }

    protected WebElement tenureIndex(String tenureIndex) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "tenureIndex", tenureIndex));
    }

    protected WebElement emiCardNum() {
        return findElement(repositoryParser, PAGE_NAME, "emiCardNum");
    }

    protected WebElement emiCardName() {
        return findElement(repositoryParser, PAGE_NAME, "emiCardName");
    }

    protected WebElement emiCardMon() {
        return findElement(repositoryParser, PAGE_NAME, "emiCardMon");
    }

    protected WebElement emiCardYear() {
        return findElement(repositoryParser, PAGE_NAME, "emiCardYear");
    }

    protected WebElement emiCardCvv() {
        return findElement(repositoryParser, PAGE_NAME, "emiCardCvv");
    }

    protected WebElement emiCardTnC() {
        return findElement(repositoryParser, PAGE_NAME, "emiCardTnC");
    }

    /* Credit Debit Card */
    protected WebElement cardNum() {
        return findElement(repositoryParser, PAGE_NAME, "cardNum");
    }

    protected WebElement cardName() {
        return findElement(repositoryParser, PAGE_NAME, "cardName");
    }

    protected WebElement cardMon() {
        return findElement(repositoryParser, PAGE_NAME, "cardMon");
    }

    protected WebElement cardYear() {
        return findElement(repositoryParser, PAGE_NAME, "cardYear");
    }

    protected WebElement cardCvv() {
        return findElement(repositoryParser, PAGE_NAME, "cardCvv");
    }

    protected WebElement cardTnc() {
        return findElement(repositoryParser, PAGE_NAME, "cardTnc");
    }

    protected WebElement hKCreditCardNo(String block) {
      return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "hKCreditCardNo", block));
    }
    protected WebElement hKCCValidityMonth() {
      return findElement(repositoryParser, PAGE_NAME, "hKCCValidityMonth");
    }
    protected WebElement hKCCValidityYear() {
      return findElement(repositoryParser, PAGE_NAME, "hKCCValidityYear");
    }
    protected WebElement hKCCCVVNo() {
      return findElement(repositoryParser, PAGE_NAME, "hKCCCVVNo");
    }
    /* Pay */
    protected WebElement pay() {
        return findElement(repositoryParser, PAGE_NAME, "pay");
    }
    protected WebElement continueButton() {
      return findElement(repositoryParser, PAGE_NAME, "continue");
    }
    protected WebElement cancelNB() {
        return findElement(repositoryParser, PAGE_NAME, "cancelNB");
    }
    protected WebElement hKCreditDebitCard() {
      return findElement(repositoryParser, PAGE_NAME, "hKCreditDebitCard");
    }
    
    protected WebElement masterPass(){
    	return findElement(repositoryParser, PAGE_NAME, "MasterPass");
    }
    protected WebElement uaeMasterPass(String UaeBank){
    	return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "UaeBankingSelection", UaeBank));
    }
    //HkBanking Selection 
    protected WebElement hKVisaMaster(String HkBank) {
        return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "HKBankingSelection", HkBank));
      }
    protected WebElement hKCancelButton() {
      return findElement(repositoryParser, PAGE_NAME, "hKCancel");
    }
    protected WebElement radioVM() {
      return findElement(repositoryParser, PAGE_NAME, "radioVM");
    }
    protected WebElement proceed() {
      return findElement(repositoryParser, PAGE_NAME, "proceed");
    }
    
    protected WebElement crossKey(){
    	return findElement(repositoryParser, PAGE_NAME, "cross");
    }
    
    protected WebElement close(){
    	return findElement(repositoryParser, PAGE_NAME, "close");
    }
    /* Details */
    protected WebElement paymentDetails() {
        return findElement(repositoryParser, PAGE_NAME, "paymentDetails");
    }

    protected WebElement bookingDetails() {
        return findElement(repositoryParser, PAGE_NAME, "bookingDetails");
    }

    /* Payment Details */
    protected WebElement paymentDetFMN() {
        return findElement(repositoryParser, PAGE_NAME, "paymentDetFMN");
    }

    protected WebElement paymentDetAccNo() {
        return findElement(repositoryParser, PAGE_NAME, "paymentDetAccNo");
    }

    protected WebElement paymentDetStatus() {
        return findElement(repositoryParser, PAGE_NAME, "paymentDetStatus");
    }

    protected WebElement paymentDetTotalAmount() {
        return findElement(repositoryParser, PAGE_NAME, "paymentDetTotalAmount");
    }

    protected WebElement paymentDetTimeLimit() {
        return findElement(repositoryParser, PAGE_NAME, "paymentDetTimeLimit");
    }

    /* Booking Details */
    protected WebElement ticketDetSrcDest() {
      WebElement element=findElement(repositoryParser, PAGE_NAME, "ticketDetSrcDest");
      wait.until(ExpectedConditions.visibilityOf(element));
      return element;
    }

    protected WebElement ticketDetFMN() {
        return findElement(repositoryParser, PAGE_NAME, "ticketDetFMN");
    }

    protected WebElement ticketDetStatus() {
        return findElement(repositoryParser, PAGE_NAME, "ticketDetStatus");
    }

    protected WebElement ticketDetTotalAmount() {
        return findElement(repositoryParser, PAGE_NAME, "ticketDetTotalAmount");
    }
    
    //clicking on finish & return to merchant bank- Kishor
    protected WebElement finishButton() {
        return findElement(repositoryParser, PAGE_NAME, "finish");
      }
    protected WebElement returnToMerchant(){
     return findElement(repositoryParser, PAGE_NAME, "returnToMerchant");
    }
    protected WebElement bankSelectionTH(String THbank) {
      return findElement(CommonUtils.modifyPageElement(repositoryParser, PAGE_NAME, "123BankingSelection", THbank));
    }

}
