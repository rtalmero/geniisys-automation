package com.geniisys.automation.marketing.createquote.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.marketing.createquote.pages.BondPolicyDataPage;
import com.geniisys.automation.marketing.createquote.pages.QuotationInformationPage;

public class CreateQuotationMenu {

	private BrowserDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(CreateQuotationMenu.class.getName());


	private final By quotationInformation = By.xpath("//a[@id='quoteInformation']");
	private final By bondPolicyData = By.xpath("//a[@id='bondPolicyData']");

	public CreateQuotationMenu(BrowserDriver driver) {
		this.driver = driver;
	}

	public QuotationInformationPage goToQuotationInfomation() {
		try {
			driver.findClickableElement(quotationInformation).click();
			LOGGER.info("'Quotation Information' menu clicked.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
		return new QuotationInformationPage(driver);
	}
	
	public BondPolicyDataPage goToBondPolicyDataPage() {
		try {
			driver.findClickableElement(bondPolicyData).click();
			LOGGER.info("'Bond Policy Data' menu clicked.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
		return new BondPolicyDataPage(driver);
	}

}
