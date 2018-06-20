package com.geniisys.automation.marketing.createquote.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.Select;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.ModalDialog;

public class QuotationInformationBlock {

	private BrowserDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(QuotationInformationBlock.class.getName());


	private By sublineLovLocator = By.xpath("//select[@id='subline']");
	private By creditingBranchLovLocator = By.xpath("//select[@id='creditingBranch']");
	private By searchAssuredBtnLocator = By.xpath("//img[@id='oscm']");
	private By msgBox = By.xpath("//div[starts-with(@id,'modal_dialog') "
			+ "and @class='dialog']");
	private By displayListBtnLocator = By.xpath("//input[@id='btn1']");
	private By returnBtnLocator = By.xpath("//input[@id='btnReturn']");

	public QuotationInformationBlock(BrowserDriver driver) {
		this.driver = driver;
	}

	/**
	 * Set value for Subline field
	 * @param sublineCode SUBLINE_CD of subline to be set
	 */
	public void setSubline(String sublineCode) {
		try {
			Select subline = new Select(driver.findElement(sublineLovLocator));
			subline.selectByValue(sublineCode);
			LOGGER.info("Subline field value set to '" + sublineCode + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}

	public void setCreditingBranch(String branchName) {
		try {
			Select credBranchLov = new Select(driver.findElement(creditingBranchLovLocator));
			credBranchLov.selectByValue(branchName);
			LOGGER.info("Crediting Branch field value set to '" + branchName + "'.");
		} catch (TimeoutException e) {
			LOGGER.info(e);
		}
	}

	public void setAssured(String assuredNo) {
		try {
			driver.findClickableElement(searchAssuredBtnLocator).click();
			LOGGER.info("Assured search button clicked.");
		} catch (TimeoutException e) {
			LOGGER.info(e);
		}
		getModalDialog().searchAndSelect(assuredNo);

		if (isMultipleAssured()) {
			LOGGER.info("Multiple Assured prompt displayed.");
			try {
				driver.findClickableElement(displayListBtnLocator).click();
				LOGGER.info("'Display List' button clicked.");
			} catch (TimeoutException e) {
				LOGGER.error(e);
			}
			try {
				driver.findClickableElement(returnBtnLocator).click();
				LOGGER.info("'Return' button clicked.");
			} catch (TimeoutException e) {
				LOGGER.error(e);
			}
		}
		LOGGER.info("Prompt closed.");
	}

	private boolean isMultipleAssured() {
		try {
			driver.findHiddenElement(msgBox);
			return true;
		} catch (NoSuchElementException e) {
			LOGGER.error(e);
			return false;
		}
	}

	private ModalDialog getModalDialog() {
		return new ModalDialog(driver);
	}
}
