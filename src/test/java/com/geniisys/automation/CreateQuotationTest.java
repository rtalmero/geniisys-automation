package com.geniisys.automation;

import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;

import com.geniisys.automation.common.TestData;
import com.geniisys.automation.main.pages.HomePage;
import com.geniisys.automation.marketing.createquote.blocks.CreateQuotationMenu;
import com.geniisys.automation.marketing.createquote.pages.BondPolicyDataPage;
import com.geniisys.automation.marketing.createquote.pages.CreateQuotationPage;
import com.geniisys.automation.marketing.createquote.pages.QuotationInformationPage;
import com.geniisys.automation.marketing.createquote.pages.QuotationListingPage;
import com.geniisys.automation.marketing.createquote.pages.QuoteLineListingPage;
import com.geniisys.automation.marketing.home.pages.MarketingHomePage;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class CreateQuotationTest extends BaseTest {
	
	private HomePage homePage;
	MarketingHomePage mktgPage;
	QuoteLineListingPage lineListing;
	QuotationListingPage quotListing;
	CreateQuotationPage createQuotePage;
	CreateQuotationMenu createQuoteMenu;
	QuotationInformationPage quoteInfoPage;
	BondPolicyDataPage bondPolicyDataPage;	
	
	
	
	@Test(priority = 0, dataProvider = "getData", dataProviderClass = TestData.class)
	public void createQuotation(String lineCd, HashMap<String, Object[][]> data) {
		
		homePage = new HomePage(driver);
		mktgPage = homePage.goToMarketingPage();
		lineListing = mktgPage.menu().goToQuotationProcessing().goToQuoatationListing();
		quotListing = lineListing.selectLine(lineCd.split("-")[0]);
		createQuotePage = quotListing.addNewRecord();
		
		
		if (lineCd.contains("EN")) {
			
			popQuotationBasicinfo(data.get("quotationBasicInfo"));
						
			createQuoteMenu = new CreateQuotationMenu(driver);
			quoteInfoPage =  createQuoteMenu.goToQuotationInfomation();
			
			popQuotationItmInfoBlk(data.get("quotationItmInfoBlk"));
			popQuotationPrilInfoBlk(data.get("quotationPerilInfoBlk"));
			
		} else if (lineCd.contains("SU")) {
			
			popQuotationBasicinfo(data.get("quotationBasicInfo"));
			
			createQuoteMenu = new CreateQuotationMenu(driver);
			bondPolicyDataPage =  createQuoteMenu.goToBondPolicyDataPage();
			
			popQuotationBondBasInfoBlk(data.get("quotationBondBasicInfoBlk"));
			
		} else if (lineCd.contains("FI")) {
			popQuotationBasicinfo(data.get("quotationBasicInfo"));
			
			createQuoteMenu = new CreateQuotationMenu(driver);
			quoteInfoPage =  createQuoteMenu.goToQuotationInfomation();
			
			popQuotationItmInfoBlk(data.get("quotationItmInfoBlk"));
			
			quoteInfoPage.getAddInfoBlk().show();
			
			popQuotationAddInfoBlk(data.get("quotationAddInfoBlkFI"));
			
			popQuotationPrilInfoBlk(data.get("quotationPerilInfoBlk"));
			
		}
		
	}
	
	
	public void popQuotationBasicinfo(Object[][] quotationBasicinfo){
		for (int i = 0 ; i < quotationBasicinfo.length ; i++) {
			for (HashMap basicInfo : (List<HashMap>) quotationBasicinfo[i][1]) {
				createQuotePage.getQuotationInfoBlk().setSubline(basicInfo.get("subline_cd").toString());
				createQuotePage.getQuotationInfoBlk().setCreditingBranch(basicInfo.get("crediting_branch").toString());
				createQuotePage.getQuotationInfoBlk().setAssured(basicInfo.get("assd_cd").toString());
				createQuotePage.getPeriodOfInsuranceBlk().setInceptDate(basicInfo.get("incept_date").toString());
				createQuotePage.getPeriodOfInsuranceBlk().setExpiryDate(basicInfo.get("expiry_date").toString());
				createQuotePage.save();
			}
		}
	}
	
	public void popQuotationItmInfoBlk(Object[][] quotationItmInfoBlk){
		for (int i = 0 ; i < quotationItmInfoBlk.length ; i++) {
			for (HashMap itmInfoBlk : (List<HashMap>) quotationItmInfoBlk[i][1]) {
				quoteInfoPage.getItemInfoBlk().setItemTitle(itmInfoBlk.get("item_title").toString());
				quoteInfoPage.getItemInfoBlk().setItemDescription1(itmInfoBlk.get("item_desc1").toString());
				quoteInfoPage.getItemInfoBlk().setItemDescription2(itmInfoBlk.get("item_desc2").toString());
				quoteInfoPage.getItemInfoBlk().setCoverage(itmInfoBlk.get("coverage_cd").toString());
				quoteInfoPage.getItemInfoBlk().setCoverage(itmInfoBlk.get("coverage_cd").toString());
				quoteInfoPage.getItemInfoBlk().clickAdd();
			}
		}
	}
	
	public void popQuotationAddInfoBlk(Object[][] quotationAddInfoBlk){
		for (int i = 0 ; i < quotationAddInfoBlk.length ; i++) {
			quoteInfoPage.getItemInfoBlk().selectItem(Integer.parseInt(quotationAddInfoBlk[i][0].toString()));
			for (HashMap addInfoBlk : (List<HashMap>) quotationAddInfoBlk[i][1]) {
				quoteInfoPage.getAddInfoBlk().setAssignee(addInfoBlk.get("assignee_txt").toString());
				quoteInfoPage.getAddInfoBlk().setType(addInfoBlk.get("item_type").toString());
				quoteInfoPage.getAddInfoBlk().setProvince(addInfoBlk.get("province").toString());
				quoteInfoPage.getAddInfoBlk().setCity(addInfoBlk.get("city").toString());
				quoteInfoPage.getAddInfoBlk().setDistrict(addInfoBlk.get("district").toString());
				quoteInfoPage.getAddInfoBlk().setRisk(addInfoBlk.get("risks").toString());
				quoteInfoPage.getAddInfoBlk().setOccupancy(addInfoBlk.get("occupancy").toString());
				quoteInfoPage.getAddInfoBlk().setTariffZone(addInfoBlk.get("tariff_zone").toString());
				quoteInfoPage.getAddInfoBlk().setTariffCode(addInfoBlk.get("tariff_cd").toString());
				quoteInfoPage.getAddInfoBlk().setConstruction(addInfoBlk.get("construction").toString());
				quoteInfoPage.getAddInfoBlk().setConstructionRemarks(addInfoBlk.get("construction_remarks").toString());
				quoteInfoPage.getAddInfoBlk().setOccupancyRemarks(addInfoBlk.get("occupancy_remarks").toString());
				quoteInfoPage.getAddInfoBlk().setFromDate(addInfoBlk.get("from_date").toString());
				quoteInfoPage.getAddInfoBlk().setToDate(addInfoBlk.get("to_date").toString());
				quoteInfoPage.getAddInfoBlk().setLocation(addInfoBlk.get("location1").toString(), addInfoBlk.get("location2").toString(), addInfoBlk.get("location3").toString());
				quoteInfoPage.getAddInfoBlk().setCoordinates(addInfoBlk.get("latitude").toString(), addInfoBlk.get("longitude").toString());
				quoteInfoPage.getAddInfoBlk().setFront(addInfoBlk.get("boundary_front").toString());
				quoteInfoPage.getAddInfoBlk().setRight(addInfoBlk.get("boundary_right").toString());
				quoteInfoPage.getAddInfoBlk().setLeft(addInfoBlk.get("boundary_left").toString());
				quoteInfoPage.getAddInfoBlk().setRear(addInfoBlk.get("boundary_rear").toString());
			}
			quoteInfoPage.getAddInfoBlk().applyChanges();
		}
	}

	
	public void popQuotationPrilInfoBlk(Object[][] quotationPrilInfoBlk){
		for (int i = 0 ; i < quotationPrilInfoBlk.length ; i++) {
			quoteInfoPage.getItemInfoBlk().selectItem(Integer.parseInt(quotationPrilInfoBlk[i][0].toString()));
			quoteInfoPage.getPerilInfoBlk().show();
			for (HashMap perilInfoBlk : (List<HashMap>) quotationPrilInfoBlk[i][1]) {
				quoteInfoPage.getPerilInfoBlk().setPeril(perilInfoBlk.get("peril_name").toString());
				quoteInfoPage.getPerilInfoBlk().setPerilTsi(Double.parseDouble(perilInfoBlk.get("tsi_amt").toString()));
				quoteInfoPage.getPerilInfoBlk().setPerilRate(Double.parseDouble(perilInfoBlk.get("rate").toString()));
				quoteInfoPage.getPerilInfoBlk().addPeril();
			}
			quoteInfoPage.save();
		}
	}
	
	public void popQuotationBondBasInfoBlk(Object[][] quotationBondBasInfoBlk){
		for (int i = 0 ; i < quotationBondBasInfoBlk.length ; i++) {
			for (HashMap bondBasicInfo : (List<HashMap>) quotationBondBasInfoBlk[i][1]) {
				bondPolicyDataPage.getBondBasicInformationBlk().setObligee(bondBasicInfo.get("obligee_cd").toString());
				bondPolicyDataPage.getBondBasicInformationBlk().setPrincipalSignatory(bondBasicInfo.get("prin_signor").toString());
				bondPolicyDataPage.getBondBasicInformationBlk().setBondUndertaking(bondBasicInfo.get("bond_undertaking").toString());
				bondPolicyDataPage.getBondBasicInformationBlk().setIndemnity(bondBasicInfo.get("indemnity").toString());
				bondPolicyDataPage.getBondBasicInformationBlk().setNotary(bondBasicInfo.get("notary_cd").toString());
				bondPolicyDataPage.save();
			}
		}
	}
	
	
	
	
}
