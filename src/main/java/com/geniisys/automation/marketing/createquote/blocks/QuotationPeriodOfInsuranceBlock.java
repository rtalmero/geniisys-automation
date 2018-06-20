package com.geniisys.automation.marketing.createquote.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.DatePicker;

public class QuotationPeriodOfInsuranceBlock {

	private DatePicker datePicker;
	private static final Logger LOGGER = LogManager.getLogger(QuotationPeriodOfInsuranceBlock.class.getName());

	private final By inceptionDprLocator = By.xpath("//img[@id='hrefInceptionDate']");
	private final By expiryDprLocator = By.xpath("//img[@id='hrefExpiryDate']");

	public QuotationPeriodOfInsuranceBlock(BrowserDriver driver) {
		datePicker = new DatePicker(driver);
	}

	public void setInceptDate(String date) {
		try {
			datePicker.setDate(inceptionDprLocator, date);
			LOGGER.info("Inception date set to '" + date + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}

	public void setExpiryDate(String date) {
		try {
			datePicker.setDate(expiryDprLocator, date);
			LOGGER.info("Expiry date set to '" + date + "'.");
		} catch (TimeoutException e) {
			LOGGER.info(e);
		}
	}
}
