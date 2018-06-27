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
	private static final Logger LOGGER = LogManager.getLogger(QuoteItemInformationBlock.class.getName());
	
	
	private final By showAddInfoTxtLocator = By.xpath("//label[@id='additionalInfoAccordionLbl']");
	private final By assigneeField = By.xpath("//input[@id='txtAssignee']");
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
	private final By constructionRemarksField = By.xpath("//textarea[@id='txtConstructionRemarks']");
	private final By occupancyRemarksField = By.xpath("//textarea[@id='txtOccupancyRemarks']");
	private final By fromDateDprLocator = By.xpath("//img[@id='hrefFromDate']");
	private final By toDateDprLocator = By.xpath("//img[@id='hrefToDate']");
	private final By locationField1 = By.xpath("//input[@id='txtLocRisk1']");
	private final By locationField2 = By.xpath("//input[@id='txtLocRisk2']");
	private final By locationField3 = By.xpath("//input[@id='txtLocRisk3']");
	private final By latitudeField = By.xpath("//input[@id='txtLatitude']");
	private final By longitudeField = By.xpath("//input[@id='txtLongitude']");
	private final By frontField = By.xpath("//textarea[@id='txtFront']");
	private final By rightField = By.xpath("//textarea[@id='txtRight']");
	private final By leftField = By.xpath("//textarea[@id='txtLeft']");
	private final By rearField = By.xpath("//textarea[@id='txtRear']");
	private final By applyChangesButton = By.xpath("//input[@id='aiUpdateBtn']");
	
	public QuotationAdditionalInformationBlock(BrowserDriver driver) {
		this.driver = driver;
		datePicker = new DatePicker(driver);
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
			driver.findElement(assigneeField).sendKeys(assigneeTxt);
			LOGGER.info("Assignee field value set to '" + assigneeTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setType(String typeCd) {
		ModalDialog typelLov = openLov(typeSearchBtnLocator);

		if(typelLov.isDisplayed()) {
			LOGGER.info("Type LOV overlay is displayed.");
			typelLov.searchAndSelect(typeCd);
		}
	}
	
	public void setProvince(String provinceCd) {
		ModalDialog provinceLov = openLov(provinceSearchBtnLocator);

		if(provinceLov.isDisplayed()) {
			LOGGER.info("Province LOV overlay is displayed.");
			provinceLov.searchAndSelect(provinceCd);
		}
	}
	
	public void setCity(String cityCd) {
		ModalDialog cityLov = openLov(citySearchBtnLocator);

		if(cityLov.isDisplayed()) {
			LOGGER.info("City LOV overlay is displayed.");
			cityLov.searchAndSelect(cityCd);
		}
	}
	
	public void setDistrict(String districtCd) {
		ModalDialog districtLov = openLov(districtSearchBtnLocator);

		if(districtLov.isDisplayed()) {
			LOGGER.info("District LOV overlay is displayed.");
			districtLov.searchAndSelect(districtCd);
		}
	}
	
	public void setBlock(String blockCd) {
		ModalDialog blockLov = openLov(blockSearchBtnLocator);

		if(blockLov.isDisplayed()) {
			LOGGER.info("Block LOV overlay is displayed.");
			blockLov.searchAndSelect(blockCd);
		}
	}
	
	public void setRisk(String riskCd) {
		ModalDialog riskLov = openLov(riskSearchBtnLocator);

		if(riskLov.isDisplayed()) {
			LOGGER.info("Risk LOV overlay is displayed.");
			riskLov.searchAndSelect(riskCd);
		}
	}
	
	public void setOccupancy(String occupancyCd) {
		ModalDialog occupancyLov = openLov(occupancySearchBtnLocator);

		if(occupancyLov.isDisplayed()) {
			LOGGER.info("Occupancy LOV overlay is displayed.");
			occupancyLov.searchAndSelect(occupancyCd);
		}
	}
	
	public void setTariffZone(String tariffZn) {
		ModalDialog tariffZoneLov = openLov(tariffZoneSearchBtnLocator);

		if(tariffZoneLov.isDisplayed()) {
			LOGGER.info("Tariff Zone LOV overlay is displayed.");
			tariffZoneLov.searchAndSelect(tariffZn);
		}
	}
	
	public void setTariffCode(String tariffCd) {
		ModalDialog tariffCodeLov = openLov(tariffCodeSearchBtnLocator);

		if(tariffCodeLov.isDisplayed()) {
			LOGGER.info("Tariff Code LOV overlay is displayed.");
			tariffCodeLov.searchAndSelect(tariffCd);
		}
	}
	
	public void setConstruction(String constructionCd) {
		ModalDialog ConstructionLov = openLov(constructionSearchBtnLocator);

		if(ConstructionLov.isDisplayed()) {
			LOGGER.info("Construction LOV overlay is displayed.");
			ConstructionLov.searchAndSelect(constructionCd);
		}
	}
	
	public void setConstructionRemarks(String constRemrksTxt) {
		try {
			driver.findElement(constructionRemarksField).sendKeys(constRemrksTxt);
			LOGGER.info("Construction Remarks field value set to '" + constRemrksTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setOccupancyRemarks(String occupancyRemrksTxt) {
		try {
			driver.findElement(occupancyRemarksField).sendKeys(occupancyRemrksTxt);
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
	
	public void setLocation1(String location1) {
		try {
			driver.findElement(locationField1).sendKeys(location1);
			LOGGER.info("Location (1) field value set to '" + location1 + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setLocation2(String location2) {
		try {
			driver.findElement(locationField2).sendKeys(location2);
			LOGGER.info("Location (2) field value set to '" + location2 + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setLocation3(String location3) {
		try {
			driver.findElement(locationField3).sendKeys(location3);
			LOGGER.info("Location (3) field value set to '" + location3 + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setLatitude(String latitudeTxt) {
		try {
			driver.findElement(latitudeField).sendKeys(latitudeTxt);
			LOGGER.info("Latitude field value set to '" + latitudeTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setLongitude(String longitudeTxt) {
		try {
			driver.findElement(longitudeField).sendKeys(longitudeTxt);
			LOGGER.info("Longitude field value set to '" + longitudeTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setFront(String frontTxt) {
		try {
			driver.findElement(frontField).sendKeys(frontTxt);
			LOGGER.info("Boundary Front field value set to '" + frontTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setRight(String rightTxt) {
		try {
			driver.findElement(rightField).sendKeys(rightTxt);
			LOGGER.info("Boundary Rigth field value set to '" + rightTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setLeft(String leftTxt) {
		try {
			driver.findElement(leftField).sendKeys(leftTxt);
			LOGGER.info("Boundary Left field value set to '" + leftTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setRear(String rearTxt) {
		try {
			driver.findElement(rearField).sendKeys(rearTxt);
			LOGGER.info("Boundary Rear field value set to '" + rearTxt + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void clickApplyChanges() {
		try {
			driver.findClickableElement(applyChangesButton).click();
			LOGGER.info("'Apply Changes' button clicked.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	private ModalDialog openLov(By lovLocator) {
		try {
			driver.findClickableElement(lovLocator).click();
			LOGGER.info("LOV search button clicked.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
		return new ModalDialog(driver);
	}
	
}
