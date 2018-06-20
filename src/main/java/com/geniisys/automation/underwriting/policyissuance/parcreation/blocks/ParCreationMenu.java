package com.geniisys.automation.underwriting.policyissuance.parcreation.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.underwriting.policyissuance.parcreation.pages.ItemInformationPage;

public class ParCreationMenu {

	private BrowserDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(ParCreationMenu.class.getName());

	private By itemInfoMnuLocator = By.xpath("//a[@id='itemInfo']");
	private By billMnuLocator = By.xpath("//a[@id='bill']");
	private By distributionMnuLocator = By.xpath("//a[@id='distribution']");
	private By postMnuLocator = By.xpath("//a[@id='post']");

	public ParCreationMenu(BrowserDriver driver) {
		this.driver = driver;
	}

	public ItemInformationPage goToItemInformation() {
		try {
			driver.findClickableElement(itemInfoMnuLocator).click();
			LOGGER.info("'Item Information' menu clicked.");
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return new ItemInformationPage(driver);
	}

	public BillSubmenu showBillSubmenu() {
		try {
			driver.findClickableElement(billMnuLocator).click();
			LOGGER.info("'Bill' menu clicked.");
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return new BillSubmenu(driver);
	}

	public DistributionSubmenu showDistributionSubmenu() {
		try {
			driver.findClickableElement(distributionMnuLocator).click();
			LOGGER.info("'Distribution' menu clicked.");
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return new DistributionSubmenu(driver);
	}

	public PostParOverlay goToPost() {
		try {
			driver.findClickableElement(postMnuLocator).click();
			LOGGER.info("'Post' menu clicked.");
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return new PostParOverlay(driver);
	}

}
