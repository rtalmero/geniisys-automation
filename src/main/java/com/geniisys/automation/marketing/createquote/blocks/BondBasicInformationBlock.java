package com.geniisys.automation.marketing.createquote.blocks;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.Select;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.TestData;

public class BondBasicInformationBlock {

	private BrowserDriver driver;
	TestData testData; 
	private static final Logger LOGGER = LogManager.getLogger(BondBasicInformationBlock.class.getName());
	
	private final By obligeeLOVLocator = By.xpath("//select[@id='obligee']");
	private final By prinSignorLOVLocator = By.xpath("//select[@id='dspPrinSignor']");
	private final By bondUndertakingFldLocator = By.xpath("//textarea[@id='bondDtl']");
	private final By indemnityFldLocator = By.xpath("//textarea[@id='indemnityText']");
	private final By notaryLOVLocator = By.xpath("//select[@id='dspNPName']");
	private final By quoteNoFldLocator = By.xpath("//input[@id='quoteNo']");
	
	public BondBasicInformationBlock(BrowserDriver driver) {
		this.driver = driver;
		testData = new TestData();
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
			driver.findElement(bondUndertakingFldLocator).sendKeys(bondUndertakingTxt);
			LOGGER.info("Bond Undertaking field value set to '" + bondUndertakingTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setIndemnity(String indemnityTxt) {
		try {
			driver.findElement(indemnityFldLocator).sendKeys(indemnityTxt);
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
	
	public String getQuoteNo() {
		return driver.findClickableElement(quoteNoFldLocator).getAttribute("value");
	}
	
	public void saveQuoteNo(String group) throws Exception {
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("group", group);
		try {
			dataMap.put("quotation_no", getQuoteNo());			
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
		testData.addDataUnderwriting("createParPolicy", "selectQuotation", dataMap);
	}
	
	
}
