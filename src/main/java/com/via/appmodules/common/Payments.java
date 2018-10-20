package com.via.appmodules.common;

import java.awt.print.Pageable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.via.appmodules.flights.FlightItinerary;
import com.via.pageobjects.common.CommonDetails;
import com.via.pageobjects.common.PaymentElements;
import com.via.pageobjects.flights.FlightBookingDetails;
import com.via.utils.CommonUtils;
import com.via.utils.Constant;
import com.via.utils.Constant.Booking_Media;
import com.via.utils.Constant.Country;
import com.via.utils.CustomAssert;
import com.via.utils.Log;
import com.via.utils.NumberUtility;
import com.via.utils.PageHandler;
import com.via.utils.RepositoryParser;
import com.via.utils.StringUtilities;

/**
 * 
 * @author Yash Gupta
 *
 */
public class Payments extends PaymentElements {
	private CommonDetails commonDetails;
	private RepositoryParser repositoryParser;
	String paymentMode;
	String paymentType;

	public Payments(String testId, WebDriver driver,
			RepositoryParser repositoryParser, CommonDetails commonDetails) {
		super(testId, driver, repositoryParser);
		this.repositoryParser = repositoryParser;
		this.commonDetails = commonDetails;
	}

	public void execute(Object productDetails, Booking_Media booking_Media)
			throws InterruptedException {
		Log.openPage(testId, "Payments Page");

		paymentMode = commonDetails.getPaymentMode();
		if (commonDetails.getPaymentType() != null) {
			paymentType = commonDetails.getPaymentType();
		}
		setPayment(commonDetails.getCountry(), booking_Media);
		Log.info(testId, "Payment Set");
		if (StringUtils.equals(paymentMode, "ATM")) {
			CommonUtils.click(driver, continueButton());
		}
		if (StringUtils.equals(paymentMode, "NB")) {
			CommonUtils.javaScriptExecuterClick(driver, cancelNB());
			driver.switchTo().alert().accept();
		}
		
		if (commonDetails.getCountry() == Country.THAILAND
				&& StringUtils.equals(paymentMode, "BA123")) {
			CommonUtils.javaScriptExecuterClick(driver,
					get123BankingCancelBtn());
			// clicking on finish button of thailand of mercahant- By Kishore.
			CommonUtils.click(driver, finishButton());
			CommonUtils.click(driver, returnToMerchant());
		}
		if (commonDetails.getCountry() == Country.INDONESIA
				|| (commonDetails.getCountry() == Country.UAE && StringUtils
						.equals(paymentMode, "PBC"))) {
			if (StringUtils.equals(paymentMode, "BA")
					|| StringUtils.equals(paymentMode, "ATM")
					|| StringUtils.equals(paymentMode, "PBC")) {
				validateBlockingDetails(productDetails);
			} else {
				validateBookingDetails(productDetails);
			}
		} else {
			validateBookingDetails(productDetails);
		}
	}

	public void execute(Object productDetails) throws InterruptedException {
		Log.openPage(testId, "Payments Page");

		paymentMode = commonDetails.getPaymentMode();
		if (commonDetails.getPaymentType() != null) {
			paymentType = commonDetails.getPaymentType();
		}
		setPayment(commonDetails.getCountry());
		Log.info(testId, "Payment Set");
		if (StringUtils.equals(paymentMode, "ATM")) {
			CommonUtils.click(driver, continueButton());
		}
		if (StringUtils.equals(paymentMode, "NB")) {
			CommonUtils.javaScriptExecuterClick(driver, cancelNB());
			driver.switchTo().alert().accept();
		}
		if (commonDetails.getCountry() == Country.THAILAND
				&& paymentMode == "BA123") {
			CommonUtils.javaScriptExecuterClick(driver,
					get123BankingCancelBtn());
		}
		
		if (commonDetails.getCountry() == Country.INDONESIA
				|| (commonDetails.getCountry() == Country.UAE && StringUtils
						.equals(paymentMode, "PBC"))) {
			if (StringUtils.equals(paymentMode, "BA")
					|| StringUtils.equals(paymentMode, "ATM")
					|| StringUtils.equals(paymentMode, "PBC")
					) {
				validateBlockingDetails(productDetails);
			} else {
				validateBookingDetails(productDetails);
			}
		} else {
			validateBookingDetails(productDetails);
		}
	}

	/**
	 * Validates the total amount, set the payment details and validates the
	 * payment fees if.
	 * 
	 * @param country
	 * @param paymentMode
	 * @param paymentType
	 */
	private void setPayment(Country country, Booking_Media booking_Media) {
		validateTotalAmount(commonDetails.getTotalFare()
				+ commonDetails.getSsrCharges());
		switch (paymentMode) {
		case "BA123":
			PageHandler.javaScriptExecuterClick(driver,
					select123BankingPaymentMode());
			Log.info(testId, "123 Banking Mode Selected");

			// selecting the payment type for th-Kishor
			selectBank123(paymentType);

			validatePaymentFees(getDesiredPaymentFeesForBank123(commonDetails
					.getTotalFare() + commonDetails.getSsrCharges()));
			break;
		case "SC":
			if (selectSavedCardPaymentMode() != null) {
				CommonUtils.javaScriptExecuterClick(driver,
						selectSavedCardPaymentMode());
				Log.info(testId, "saved card Payment Mode Selected");
				paymentType = paymentType.toUpperCase();
				savedCardInput(country, paymentType);
			} else {
				Log.info(testId, "No Saved card is added");
			}
			if (commonDetails.getCountry() == Country.INDONESIA
					&& booking_Media == Booking_Media.B2C) {
				break;
			}
			validatePaymentFees(getDesiredPaymentFees(commonDetails
					.getTotalFare() + commonDetails.getSsrCharges()));
			break;
		case "BA":
			CommonUtils.click(driver, bankTransfer());
			selectBank(paymentType);
			break;

		case "ATM":
			CommonUtils.click(driver, atm());
			break;

		case "EMI":
			CommonUtils.click(driver, emi());
			setEMIDetails(country, paymentType);
			break;

		case "NB":
			CommonUtils.click(driver, netBanking());
			selectBank(paymentType);
			break;

		case "CC":
			CommonUtils.javaScriptExecuterClick(driver, creditCard());
			setCardDetails(country);
			if (commonDetails.getCountry() == Country.INDONESIA
					&& booking_Media == Booking_Media.B2C) {
				break;
			}
			validatePaymentFees(getDesiredPaymentFees(commonDetails
					.getTotalFare() + commonDetails.getSsrCharges()));
			break;

		case "DC":
			CommonUtils.javaScriptExecuterClick(driver, debitCard());
			setCardDetails(country);
			validatePaymentFees(getDesiredPaymentFees(commonDetails
					.getTotalFare() + commonDetails.getSsrCharges()));
			break;

		case "PBC":
			CommonUtils.click(driver, cash());
			break;
			
		case "VENDOR_JETCO":
			proceedToPay();
			break;

		case "MasterPass":
			proceedForMasterPass();
			break;
			
		default:
			break;
		}

		Log.info(testId, "Payment Mode :: " + paymentMode);
		Log.info(testId, "Payment Type :: " + paymentType);
		if (commonDetails.getCountry() == Country.HONGKONG )  {
			return;
		}
		if (commonDetails.getCountry()==country.UAE && paymentMode.equalsIgnoreCase("MasterPass")) {
			return;
		}
	
		CommonUtils.javaScriptExecuterClick(driver, pay());
	}

	private void selectBank123(String THbank) {
		CommonUtils.click(driver, bankSelectionTH(THbank));
	}

	private void setPayment(Country country) {
		validateTotalAmount(commonDetails.getTotalFare()
				+ commonDetails.getSsrCharges());
		switch (paymentMode) {
		case "SC":
			if (selectSavedCardPaymentMode() != null) {
				CommonUtils.javaScriptExecuterClick(driver,
						selectSavedCardPaymentMode());
				Log.info(testId, "saved card Payment Mode Selected");
				paymentType = paymentType.toUpperCase();
				savedCardInput(country, paymentType);
			} else {
				Log.info(testId, "No Saved card is added");
			}
			validatePaymentFees(getDesiredPaymentFees(commonDetails
					.getTotalFare() + commonDetails.getSsrCharges()));
			break;
		case "BA123":
			PageHandler.javaScriptExecuterClick(driver,
					select123BankingPaymentMode());
			Log.info(testId, "123 Banking Mode Selected");
			validatePaymentFees(getDesiredPaymentFeesForBank123(commonDetails
					.getTotalFare() + commonDetails.getSsrCharges()));
			break;
		case "BA":
			CommonUtils.click(driver, bankTransfer());
			selectBank(paymentType);
			break;

		case "ATM":
			CommonUtils.click(driver, atm());
			break;

		case "EMI":
			CommonUtils.click(driver, emi());
			setEMIDetails(country, paymentType);
			break;

		case "NB":
			CommonUtils.click(driver, netBanking());
			selectBank(paymentType);
			break;

		case "CC":
			CommonUtils.click(driver, creditCard());
			setCardDetails(country);
			validatePaymentFees(getDesiredPaymentFees(commonDetails
					.getTotalFare() + commonDetails.getSsrCharges()));
			break;

		case "DC":
			CommonUtils.click(driver, debitCard());
			setCardDetails(country);
			validatePaymentFees(getDesiredPaymentFees(commonDetails
					.getTotalFare() + commonDetails.getSsrCharges()));
			break;

		case "PBC":
			CommonUtils.click(driver, cash());
			break;

		case "VENDOR_JETCO":
			proceedToPay();
			break;

		case "MasterPass":
			proceedForMasterPass();
			break;
		
		default:
			break;
		}

		if (commonDetails.getCountry() == Country.HONGKONG) {
			return;
		}
			
		Log.info(testId, "Payment Mode :: " + paymentMode);
		Log.info(testId, "Payment Type :: " + paymentType);

		CommonUtils.javaScriptExecuterClick(driver, pay());
	}

	private void savedCardInput(Country country, String paymentType) {
		Select select = new Select(selectSavedCardDropDown());
		String cCNo = Constant.CARD_NUMBER;

		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			if (option.getText().contains(cCNo.substring(12, 16))) {
				select.selectByVisibleText("XXXX-XXXX-XXXX-"
						+ cCNo.substring(12, 16) + " " + paymentType);
				Log.info(testId, cCNo + " Is selected");
			}
		}
		Log.info(testId, "credit card number is selected");
		selectSavedCVV().sendKeys(Constant.CARD_CVV);
		Log.info(testId, "credit card cvv number is entered");
	}

	private void proceedToPay() {
		CommonUtils.click(driver, hKCreditDebitCard());
		CommonUtils.click(driver, hKVisaMaster(paymentType));
		validatePaymentFees(getDesiredPaymentFees(commonDetails.getTotalFare()
				+ commonDetails.getSsrCharges()));
		CommonUtils.javaScriptExecuterClick(driver, pay());
		CommonUtils.click(driver, radioVM());
		CommonUtils.click(driver, proceed());
		hKCreditCardInputs();
		CommonUtils.click(driver, proceed());
		PageHandler.sleep(5 * 1000l);
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {

		}
	}

	private void hKCreditCardInputs() {
		String creditCardNo = Constant.CARD_NUMBER;
		int beginIndex = 0;
		int endIndex = 4;
		for (int block = 1; block <= 4; block++, beginIndex += 4, endIndex += 4) {
			hKCreditCardNo(Integer.toString(block)).sendKeys(
					creditCardNo.substring(beginIndex, endIndex));
		}
		Log.info(testId, Constant.CARD_NUMBER
				+ " Type:-credit card number is entered");
		hKCCValidityMonth().sendKeys(Constant.CARD_MONTH);
		Log.info(testId, "credit card validity month is entered");
		hKCCValidityYear().sendKeys(Constant.CARD_YEAR);
		Log.info(testId, "credit card validity Year is entered");
		hKCCCVVNo().sendKeys(Constant.CARD_CVV);
		Log.info(testId, "credit card CVV is entered");

	}

	private void selectBank(String bank) {

		// Payment selection for Bank transfer & Net Banking - Kishor
		if (paymentMode.equalsIgnoreCase("BA")) {
			CommonUtils.click(driver, baBank(bank));
		} else {
			CommonUtils.click(driver, bank(bank));
		}
	}
	
//UAE payment mode for MasterPass.
	private void proceedForMasterPass() {
		CommonUtils.click(driver, masterPass());
		CommonUtils.click(driver, uaeMasterPass(paymentType));
		validatePaymentFees(getDesiredPaymentMasterPassUAE(commonDetails.getTotalFare()
				+ commonDetails.getSsrCharges()));
		CommonUtils.javaScriptExecuterClick(driver, pay());
		CommonUtils.click(driver, crossKey());
		CommonUtils.click(driver, close());
		PageHandler.sleep(5 * 1000l);
	}


	private void setEMIDetails(Country country, String emiDetails) {
		List<String> emiDetailsList = StringUtilities.split(emiDetails,
				Constant.PIPE);
		String bankName = emiDetailsList.get(0);
		String tenureIndex = emiDetailsList.get(1);

		CommonUtils.click(driver, emiBank(bankName));
		CommonUtils.click(driver, tenureIndex(tenureIndex));

		emiCardNum().sendKeys(Constant.CARD_NUMBER);
		Log.info(testId, Constant.CARD_NUMBER
				+ " credit card number is entered");
		emiCardName().sendKeys(Constant.CARD_NAME);
		Log.info(testId, "credit card Holder Name is entered");
		emiCardMon().sendKeys(Constant.CARD_MONTH);
		Log.info(testId, "credit card validity month is entered");
		emiCardYear().sendKeys(Constant.CARD_YEAR);
		Log.info(testId, "credit card validity Year is entered");
		emiCardCvv().sendKeys(Constant.CARD_CVV);
		Log.info(testId, "credit card CVV is entered");
		if (country == Country.INDONESIA) {
			validatePaymentFees(getDesiredPaymentFeesForEmi(commonDetails
					.getTotalFare() + commonDetails.getSsrCharges()));
			CommonUtils.javaScriptExecuterClick(driver, emiCardTnC());
		}
	}

	private void setCardDetails(Country country) {
		cardNum().sendKeys(Constant.CARD_NUMBER);
		Log.info(testId, Constant.CARD_NUMBER
				+ " credit card number is entered");
		cardName().sendKeys(Constant.CARD_NAME);
		Log.info(testId, "credit card Holder Name is entered");
		cardMon().sendKeys(Constant.CARD_MONTH);
		Log.info(testId, "credit card validity month is entered");
		cardYear().sendKeys(Constant.CARD_YEAR);
		Log.info(testId, "credit card validity Year is entered");
		cardCvv().sendKeys(Constant.CARD_CVV);
		Log.info(testId, "credit card CVV is entered");
		if (country == Country.INDONESIA) {
			CommonUtils.javaScriptExecuterClick(driver, cardTnc());
		}
	}

	private void validateBlockingDetails(Object productDetails) {
		paymentDetails();
		printPaymentDetails();
		bookingDetails().click();
		validateBookingDetails(productDetails);
	}

	private void printPaymentDetails() {
		PageHandler.sleep(testId, 10000L);
		Log.divider(testId, "Payment Details");
		Log.info(testId,
				"FMN Number :: " + paymentDetFMN().getAttribute("innerHTML"));
		Log.info(testId, "Virtual Account Number :: "
				+ paymentDetAccNo().getAttribute("innerHTML"));
		Log.info(testId,
				"Status :: " + paymentDetStatus().getAttribute("innerHTML"));
		Log.info(testId, "Total Amount :: " + paymentDetTotalAmount().getText());
		Log.info(testId, "Time Limit :: " + paymentDetTimeLimit().getText());
	}

	private void validateBookingDetails(Object productDetails) {

		PageHandler.sleep(testId, 5000L);
		Log.divider(testId, "Booking Details");
		Log.info(testId, "Src Dest :: " + ticketDetSrcDest().getText());
		Log.info(testId, "FMN Number :: " + ticketDetFMN().getText());
		Log.info(testId, "Status :: " + ticketDetStatus().getText());
		Log.info(testId, "Total Amount :: " + ticketDetTotalAmount().getText());

		if (productDetails.getClass().equals(FlightBookingDetails.class)) {
			FlightItinerary flightItinerary = new FlightItinerary(testId,
					driver, repositoryParser,
					(FlightBookingDetails) productDetails, commonDetails);
			flightItinerary.validateDetails();
		}
		Log.info(testId,
				":::::::::::::::::::Verification successfull:::::::::::::::::::::");
	}

	private void validateTotalAmount(double desiredAmount) {
		double totalAmount = 0.0;
		totalAmount = NumberUtility.getAmountFromString(
				commonDetails.getCountry(), netAmount().getText());
		Log.info(testId, "Total Amount :: " + totalAmount);
		CustomAssert
				.assertTrue(testId,
						NumberUtility.validAmountDiff(testId,
								commonDetails.getCountry(), desiredAmount,
								totalAmount),
						"Error in validating total amount");
		Log.info(testId,
				":::::::::::::::::Total Amount Varified::::::::::::::::: ");
	}

	private void validatePaymentFees(double desiredPaymentFees) {
		if (desiredPaymentFees != 0.0) {
			double payFees = NumberUtility.getAmountFromString(
					commonDetails.getCountry(),
					paymentFees().getAttribute("innerHTML"));
			Log.info(testId, "Payment Fees :: " + payFees);
			CustomAssert.assertTrue(testId, NumberUtility.validAmountDiff(
					testId, commonDetails.getCountry(), desiredPaymentFees,
					payFees), "Error in validating payment fees");
			commonDetails.setPayfee(payFees);
			Log.info(testId,
					":::::::::::::::::Payment Fees Varified::::::::::::::::: ");
		}
	}

	private double getDesiredPaymentFeesForEmi(double totalAmount) {
		double percentage = 0.0;
		switch (commonDetails.getCountry()) {
		case INDONESIA:
			percentage = 0.03;
			break;
		default:
			percentage = 0.0;
			break;
		}
		return totalAmount * percentage;
	}

	private double getDesiredPaymentFeesForBank123(double totalAmount) {
		double percentage = 0.0;
		switch (commonDetails.getCountry()) {
		case THAILAND:
			percentage = 0.0193;
			break;
		default:
			percentage = 0.0;
			break;
		}
		return totalAmount * percentage;
	}
	
	//Payment fee for Masterpass of UAE.
	private double getDesiredPaymentMasterPassUAE(double totalAmount) {
		double percentage = 0.0;
		switch (commonDetails.getCountry()) {
		case UAE:
			percentage = 0.0235;
			break;
		default:
			percentage = 0.0;
			break;
		}
		return totalAmount * percentage;
	}
	
	private double getDesiredPaymentFees(double totalAmount) {
		double percentage = 0.0;
		switch (commonDetails.getCountry()) {
		case INDONESIA:
			percentage = 0.02;
			break;
		case PHILIPPINES:
			percentage = 0.0285;
			break;
		case SINGAPORE:
			percentage = 0.02;
			break;
		case UAE:
			// percentage = 0.02; changes to 0.024-Kishor
			percentage =0.024;
			break;
		case SAUDI:
			percentage = 0.028;
			break;
		case OMAN:
			percentage = 0.028;
			break;
		case HONGKONG:
			// percentage = 0.016;
			// By kishore
			percentage = 0.018;
			break;
		case THAILAND:
			percentage = 0.03;
			break;
		default:
			percentage = 0.0;
			break;
		}
		return totalAmount * percentage;
	}
}