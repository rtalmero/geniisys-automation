package com.geniisys.automation.marketing.createquote.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.Select;

import com.geniisys.automation.common.BrowserDriver;

public class BondBasicInformationBlock {

	private BrowserDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(QuoteItemInformationBlock.class.getName());
	
	private final By obligeeLOVLocator = By.xpath("//select[@id='obligee']");
	private final By prinSignorLOVLocator = By.xpath("//select[@id='dspPrinSignor']");
	private final By bondUndertakingField = By.xpath("//textarea[@id='bondDtl']");
	private final By indemnityField = By.xpath("//textarea[@id='indemnityText']");
	private final By notaryLOVLocator = By.xpath("//select[@id='dspNPName']");
	
	public BondBasicInformationBlock(BrowserDriver driver) {
		this.driver = driver;
	}
	
	public void setObligee(String obligeeCd) {
		try {
			Select obligee = new Select(driver.findElement(obligeeLOVLocator));
			obligee.selectByValue(obligeeCd);
			LOGGER.info("Obligee field value set to '" + obligeeCd + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setPrincipalSignatory(String prinSignorCd) {
		try {
			Select prinSignor = new Select(driver.findElement(prinSignorLOVLocator));
			prinSignor.selectByValue(prinSignorCd);
			LOGGER.info("Principal Signatory field value set to '" + prinSignorCd + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setBondUndertaking(String bondUndertakingTxt) {
		try {
			driver.findElement(bondUndertakingField).sendKeys(bondUndertakingTxt);
			LOGGER.info("Bond Undertaking field value set to '" + bondUndertakingTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setIndemnity(String indemnityTxt) {
		try {
			driver.findElement(indemnityField).sendKeys(indemnityTxt);
			LOGGER.info("Indemnity field value set to '" + indemnityTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setNotary(String notaryCd) {
		try {
			Select notary = new Select(driver.findElement(notaryLOVLocator));
			notary.selectByValue(notaryCd);
			LOGGER.info("Notary Signatory field value set to '" + notaryCd + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	
}
