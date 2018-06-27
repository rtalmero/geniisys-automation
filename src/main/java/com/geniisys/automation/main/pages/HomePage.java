package com.geniisys.automation.main.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.marketing.home.pages.MarketingHomePage;
import com.geniisys.automation.underwriting.main.pages.UnderwritingPage;

public class HomePage {

	private BrowserDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(HomePage.class.getName());

	private By underwritingBtnLocator = By.xpath("//div[@id='iconUnderwriting']");
	private By marketingBtnLocator = By.xpath("//div[@id='iconMarketing']");
	private By bannerLocator = By.xpath("//img[starts-with(@id, 'banner')]");
	private By homeBtnLocator = By.xpath("//a[@id='home']");
	private By exitBtnLocator = By.xpath("//a[contains (text(), 'Exit')]");

	public HomePage(BrowserDriver driver) {
		this.driver = driver;
	}

	public UnderwritingPage goToUnderwritingPage() {
		try {
			driver.findClickableElement(underwritingBtnLocator).click();
			LOGGER.info("Go to Underwriting main page.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
		return new UnderwritingPage(driver);
	}

	public MarketingHomePage goToMarketingPage() {
		goToMainPage(marketingBtnLocator, "Marketing");
		return new MarketingHomePage(driver);
	}
	
	public void goToMainPage(By genericLocator, String banner) {
		
		if (driver.findElements(genericLocator).size() > 0) {
			try {
				driver.findClickableElement(genericLocator).click();
				LOGGER.info("Go to " + banner + " main page");
			} catch (TimeoutException e) {
				LOGGER.error(e);
			}
		} else {
			while (!(driver.findElements(bannerLocator).size() > 0)) {
				try {
					driver.findClickableElement(exitBtnLocator).click();
					LOGGER.info("'Exit' button clicked.");
				} catch (TimeoutException e) {
					LOGGER.error(e);
				}
			}
			if (!(driver.findElements(By.xpath("//img[@id='banner" + banner + "']")).size() > 0)) {
				try {
					driver.findClickableElement(homeBtnLocator).click();
					LOGGER.info("Go to Home page");
				} catch (TimeoutException e) {
					LOGGER.error(e);
				}
				try {
					driver.findClickableElement(genericLocator).click();
					LOGGER.info("Go to " + banner + " main page");
				} catch (TimeoutException e) {
					LOGGER.error(e);
				}
			}
		}
		
		
	}

}
