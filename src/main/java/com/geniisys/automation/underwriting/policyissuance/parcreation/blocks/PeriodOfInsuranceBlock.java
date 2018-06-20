package com.geniisys.automation.underwriting.policyissuance.parcreation.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.DatePicker;

public class PeriodOfInsuranceBlock {

	private static final Logger LOGGER = LogManager.getLogger(PeriodOfInsuranceBlock.class.getName());

	private DatePicker datePicker;
	private By inceptDatePkrLocator = By.xpath("//img[@id='hrefDoiDate']");
	private By expiryDatePkrLocator = By.xpath("//img[@id='hrefDoeDate']");

	public PeriodOfInsuranceBlock(BrowserDriver driver) {
		datePicker = new DatePicker(driver);
	}

	/**
	 * Set value of Inception Date field
	 * @param date in Mon-DD-YYYY format
	 * (e.g. 'Aug-22-1992')
	 */
	public void setInceptionDate(String date) {
		try {
			datePicker.setDate(inceptDatePkrLocator, date);
			LOGGER.info("Inception Date set to '" + date + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}

	/**
	 * Set value of Expiry Date field
	 * @param date in Mon-DD-YYYY format
	 * (e.g. 'Aug-22-1992')
	 */
	public void setExpiryDate(String date) {
		try {
			datePicker.setDate(expiryDatePkrLocator, date);
			LOGGER.info("Expiry Date set to '" + date + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
}
