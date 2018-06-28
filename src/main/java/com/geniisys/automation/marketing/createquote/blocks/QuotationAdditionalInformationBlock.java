package com.geniisys.automation.marketing.createquote.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.DatePicker;
import com.geniisys.automation.common.ModalDialog;

public class QuotationAdditionalInformationBlock {

	private BrowserDriver driver;
	private DatePicker datePicker;
	private ModalDialog modalDialog;
	private static final Logger LOGGER = LogManager.getLogger(QuotationAdditionalInformationBlock.class.getName());
	
	
	private final By showAddInfoTxtLocator = By.xpath("//label[@id='additionalInfoAccordionLbl']");
	private final By assigneeFldLocator = By.xpath("//input[@id='txtAssignee']");
	private final By typeSearchBtnLocator = By.xpath("//img[@id='hrefFrItemType']");
	private final By provinceSearchBtnLocator = By.xpath("//img[@id='hrefProvince']");
	private final By citySearchBtnLocator = By.xpath("//img[@id='hrefCity']");
	private final By districtSearchBtnLocator = By.xpath("//img[@id='hrefDistrict']");
	private final By blockSearchBtnLocator = By.xpath("//img[@id='hrefBlock']");
	private final By riskSearchBtnLocator = By.xpath("//img[@id='hrefRisk']");
	private final By occupancySearchBtnLocator = By.xpath("//img[@id='hrefOccupancy']");
	private final By tariffZoneSearchBtnLocator = By.xpath("//img[@id='hrefTariffZone']");
	private final By tariffCodeSearchBtnLocator = By.xpath("//img[@id='hrefTarfCd']");
	private final By constructionSearchBtnLocator = By.xpath("//img[@id='hrefConstructionCd']");
	private final By constructionRemarksTxtLocator = By.xpath("//textarea[@id='txtConstructionRemarks']");
	private final By occupancyRemarksTxtLocator = By.xpath("//textarea[@id='txtOccupancyRemarks']");
	private final By fromDateDprLocator = By.xpath("//img[@id='hrefFromDate']");
	private final By toDateDprLocator = By.xpath("//img[@id='hrefToDate']");
	private final By location1FldLocator = By.xpath("//input[@id='txtLocRisk1']");
	private final By location2FldLocator = By.xpath("//input[@id='txtLocRisk2']");
	private final By location3FldLocator = By.xpath("//input[@id='txtLocRisk3']");
	private final By latitudeFldLocator = By.xpath("//input[@id='txtLatitude']");
	private final By longitudeFldLocator = By.xpath("//input[@id='txtLongitude']");
	private final By frontTxtLocator = By.xpath("//textarea[@id='txtFront']");
	private final By rightfrontTxtLocator = By.xpath("//textarea[@id='txtRight']");
	private final By leftfrontTxtLocator = By.xpath("//textarea[@id='txtLeft']");
	private final By rearfrontTxtLocator = By.xpath("//textarea[@id='txtRear']");
	private final By applyChangesBtnLocator = By.xpath("//input[@id='aiUpdateBtn']");
	
	public QuotationAdditionalInformationBlock(BrowserDriver driver) {
		this.driver = driver;
		datePicker = new DatePicker(driver);
		modalDialog = new ModalDialog(driver);
	}
	
	public void show() {
		try {
			driver.findClickableElement(showAddInfoTxtLocator).click();
			LOGGER.info("Show Additional Information block.");
		} catch (TimeoutException e) {
			LOGGER.info(e);
		}
	}
	
	public void setAssignee(String assigneeTxt) {
		try {
			driver.findElement(assigneeFldLocator).sendKeys(assigneeTxt);
			LOGGER.info("Assignee field value set to '" + assigneeTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setType(String typeCd) {
		modalDialog.openLov(typeSearchBtnLocator);

		if(modalDialog.isDisplayed()) {
			LOGGER.info("Type LOV overlay is displayed.");
			modalDialog.searchAndSelect(typeCd);
		}
	}
	
	public void setProvince(String provinceCd) {
		modalDialog.openLov(provinceSearchBtnLocator);

		if(modalDialog.isDisplayed()) {
			LOGGER.info("Province LOV overlay is displayed.");
			modalDialog.searchAndSelect(provinceCd);
		}
	}
	
	public void setCity(String cityCd) {
		modalDialog.openLov(citySearchBtnLocator);

		if(modalDialog.isDisplayed()) {
			LOGGER.info("City LOV overlay is displayed.");
			modalDialog.searchAndSelect(cityCd);
		}
	}
	
	public void setDistrict(String districtCd) {
		modalDialog.openLov(districtSearchBtnLocator);

		if(modalDialog.isDisplayed()) {
			LOGGER.info("District LOV overlay is displayed.");
			modalDialog.searchAndSelect(districtCd);
		}
	}
	
	public void setBlock(String blockCd) {
		modalDialog.openLov(blockSearchBtnLocator);

		if(modalDialog.isDisplayed()) {
			LOGGER.info("Block LOV overlay is displayed.");
			modalDialog.searchAndSelect(blockCd);
		}
	}
	
	public void setRisk(String riskCd) {
		modalDialog.openLov(riskSearchBtnLocator);

		if(modalDialog.isDisplayed()) {
			LOGGER.info("Risk LOV overlay is displayed.");
			modalDialog.searchAndSelect(riskCd);
		}
	}
	
	public void setOccupancy(String occupancyCd) {
		modalDialog.openLov(occupancySearchBtnLocator);

		if(modalDialog.isDisplayed()) {
			LOGGER.info("Occupancy LOV overlay is displayed.");
			modalDialog.searchAndSelect(occupancyCd);
		}
	}
	
	public void setTariffZone(String tariffZn) {
		modalDialog.openLov(tariffZoneSearchBtnLocator);

		if(modalDialog.isDisplayed()) {
			LOGGER.info("Tariff Zone LOV overlay is displayed.");
			modalDialog.searchAndSelect(tariffZn);
		}
	}
	
	public void setTariffCode(String tariffCd) {
		modalDialog.openLov(tariffCodeSearchBtnLocator);

		if(modalDialog.isDisplayed()) {
			LOGGER.info("Tariff Code LOV overlay is displayed.");
			modalDialog.searchAndSelect(tariffCd);
		}
	}
	
	public void setConstruction(String constructionCd) {
		modalDialog.openLov(constructionSearchBtnLocator);

		if(modalDialog.isDisplayed()) {
			LOGGER.info("Construction LOV overlay is displayed.");
			modalDialog.searchAndSelect(constructionCd);
		}
	}
	
	public void setConstructionRemarks(String constRemrksTxt) {
		try {
			driver.findElement(constructionRemarksTxtLocator).sendKeys(constRemrksTxt);
			LOGGER.info("Construction Remarks field value set to '" + constRemrksTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setOccupancyRemarks(String occupancyRemrksTxt) {
		try {
			driver.findElement(occupancyRemarksTxtLocator).sendKeys(occupancyRemrksTxt);
			LOGGER.info("Occupancy Remarks field value set to '" + occupancyRemrksTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	
	public void setFromDate(String date) {
		try {
			datePicker.setDate(fromDateDprLocator, date);
			LOGGER.info("From date set to '" + date + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setToDate(String date) {
		try {
			datePicker.setDate(toDateDprLocator, date);
			LOGGER.info("To date set to '" + date + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setLocation(String location1, String location2, String location3) {
		
		if (location1 != null || location1 != "") {
			try {
				driver.findElement(location1FldLocator).sendKeys(location1);
				LOGGER.info("Location (1) field value set to '" + location1 + "'.");
			} catch (TimeoutException e) {
				LOGGER.error(e);
			}
		}
		
		if (location2 != null || location2 != "") {
			try {
				driver.findElement(location2FldLocator).sendKeys(location2);
				LOGGER.info("Location (2) field value set to '" + location2 + "'.");
			} catch (TimeoutException e) {
				LOGGER.error(e);
			}
		}
		
		if (location3 != null || location3 != "") {
			try {
				driver.findElement(location3FldLocator).sendKeys(location3);
				LOGGER.info("Location (3) field value set to '" + location3 + "'.");
			} catch (TimeoutException e) {
				LOGGER.error(e);
			}
		}
	}
	
	public void setCoordinates(String latitude, String longitude) {
		try {
			driver.findElement(latitudeFldLocator).sendKeys(latitude);
			LOGGER.info("Latitude field value set to '" + latitude + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
		try {
			driver.findElement(longitudeFldLocator).sendKeys(longitude);
			LOGGER.info("Longitude field value set to '" + longitude + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setFront(String frontTxt) {
		try {
			driver.findElement(frontTxtLocator).sendKeys(frontTxt);
			LOGGER.info("Boundary Front field value set to '" + frontTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setRight(String rightTxt) {
		try {
			driver.findElement(rightfrontTxtLocator).sendKeys(rightTxt);
			LOGGER.info("Boundary Rigth field value set to '" + rightTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setLeft(String leftTxt) {
		try {
			driver.findElement(leftfrontTxtLocator).sendKeys(leftTxt);
			LOGGER.info("Boundary Left field value set to '" + leftTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setRear(String rearTxt) {
		try {
			driver.findElement(rearfrontTxtLocator).sendKeys(rearTxt);
			LOGGER.info("Boundary Rear field value set to '" + rearTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void applyChanges() {
		try {
			driver.findClickableElement(applyChangesBtnLocator).click();
			LOGGER.info("'Apply Changes' button clicked.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
}
