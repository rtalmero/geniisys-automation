package com.geniisys.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
	
	private static FileInputStream excelFile;
	private static XSSFWorkbook workbook;
	private static XSSFSheet datatypeSheet;	
	
	@Test(priority = 0, dataProvider = "getData")
	public void createQuotation(String lineCd, HashMap<String, Object[][]> data) {
		
		homePage = new HomePage(driver);
		mktgPage = homePage.goToMarketingPage();
		lineListing = mktgPage.menu().goToQuotationProcessing().goToQuoatationListing();
		quotListing = lineListing.selectLine(lineCd);
		createQuotePage = quotListing.addNewRecord();
		
		
		if ("EN".equals(lineCd)) {
			
			popQuotationBasicinfo(data.get("quotationBasicinfo"));
						
			createQuoteMenu = new CreateQuotationMenu(driver);
			quoteInfoPage =  createQuoteMenu.goToQuotationInfomation();
			
			popQuotaionItmInfoBlk(data.get("quotationItmInfoBlk"));
			popQuotaionPrilInfoBlk(data.get("quotationPerilInfoBlk"));
			
		} else if ("SU".equals(lineCd)) {
			
			popQuotationBasicinfo(data.get("quotationBasicInfo"));
			
			createQuoteMenu = new CreateQuotationMenu(driver);
			bondPolicyDataPage =  createQuoteMenu.goToBondPolicyDataPage();
			
			popQuotaionBondBasInfoBlk(data.get("quotationBondBasicInfoBlk"));
			
		} else if ("FI".equals(lineCd)) {
			popQuotationBasicinfo(data.get("quotationBasicInfo"));
			
			createQuoteMenu = new CreateQuotationMenu(driver);
			quoteInfoPage =  createQuoteMenu.goToQuotationInfomation();
			
			popQuotaionItmInfoBlk(data.get("quotationItmInfoBlk"));
			
			quoteInfoPage.getAddInfoBlk().show();
			
			popQuotaionAddInfoBlk(data.get("quotationAddInfoBlk"));
			
			popQuotaionPrilInfoBlk(data.get("quotationPerilInfoBlk"));
			
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
	
	public void popQuotaionItmInfoBlk(Object[][] quotaionItmInfoBlk){
		for (int i = 0 ; i < quotaionItmInfoBlk.length ; i++) {
			for (HashMap itmInfoBlk : (List<HashMap>) quotaionItmInfoBlk[i][1]) {
				quoteInfoPage.getItemInfoBlk().setItemTitle(itmInfoBlk.get("item_title").toString());
				quoteInfoPage.getItemInfoBlk().setItemDescription1(itmInfoBlk.get("item_desc1").toString());
				quoteInfoPage.getItemInfoBlk().setItemDescription2(itmInfoBlk.get("item_desc2").toString());
				quoteInfoPage.getItemInfoBlk().setCoverage(itmInfoBlk.get("coverage_cd").toString());
				quoteInfoPage.getItemInfoBlk().setCoverage(itmInfoBlk.get("coverage_cd").toString());
				quoteInfoPage.getItemInfoBlk().clickAdd();
			}
		}
	}
	
	public void popQuotaionAddInfoBlk(Object[][] quotaionAddInfoBlk){
		for (int i = 0 ; i < quotaionAddInfoBlk.length ; i++) {
			quoteInfoPage.getItemInfoBlk().selectItem(Integer.parseInt(quotaionAddInfoBlk[i][0].toString()));
			for (HashMap addInfoBlk : (List<HashMap>) quotaionAddInfoBlk[i][1]) {
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
				quoteInfoPage.getAddInfoBlk().setLocation1(addInfoBlk.get("location1").toString());
				quoteInfoPage.getAddInfoBlk().setLocation2(addInfoBlk.get("location2").toString());
				quoteInfoPage.getAddInfoBlk().setLocation3(addInfoBlk.get("location3").toString());
				quoteInfoPage.getAddInfoBlk().setLatitude(addInfoBlk.get("latitude").toString());
				quoteInfoPage.getAddInfoBlk().setLongitude(addInfoBlk.get("longitude").toString());
				quoteInfoPage.getAddInfoBlk().setFront(addInfoBlk.get("boundary_front").toString());
				quoteInfoPage.getAddInfoBlk().setRight(addInfoBlk.get("boundary_right").toString());
				quoteInfoPage.getAddInfoBlk().setLeft(addInfoBlk.get("boundary_left").toString());
				quoteInfoPage.getAddInfoBlk().setRear(addInfoBlk.get("boundary_rear").toString());
			}
			quoteInfoPage.getAddInfoBlk().clickApplyChanges();
		}
	}

	
	public void popQuotaionPrilInfoBlk(Object[][] quotaionPrilInfoBlk){
		for (int i = 0 ; i < quotaionPrilInfoBlk.length ; i++) {
			quoteInfoPage.getItemInfoBlk().selectItem(Integer.parseInt(quotaionPrilInfoBlk[i][0].toString()));
			quoteInfoPage.getPerilInfoBlk().show();
			for (HashMap perilInfoBlk : (List<HashMap>) quotaionPrilInfoBlk[i][1]) {
				quoteInfoPage.getPerilInfoBlk().setPeril(perilInfoBlk.get("peril_name").toString());
				quoteInfoPage.getPerilInfoBlk().setPerilTsi(Double.parseDouble(perilInfoBlk.get("tsi_amt").toString()));
				quoteInfoPage.getPerilInfoBlk().setPerilRate(Double.parseDouble(perilInfoBlk.get("rate").toString()));
				quoteInfoPage.getPerilInfoBlk().addPeril();
			}
			quoteInfoPage.save();
		}
	}
	
	public void popQuotaionBondBasInfoBlk(Object[][] quotaionBondBasInfoBlk){
		for (int i = 0 ; i < quotaionBondBasInfoBlk.length ; i++) {
			for (HashMap bondBasicInfo : (List<HashMap>) quotaionBondBasInfoBlk[i][1]) {
				bondPolicyDataPage.getBondBasicInformationBlk().setObligee(bondBasicInfo.get("obligee_cd").toString());
				bondPolicyDataPage.getBondBasicInformationBlk().setPrincipalSignatory(bondBasicInfo.get("prin_signor").toString());
				bondPolicyDataPage.getBondBasicInformationBlk().setBondUndertaking(bondBasicInfo.get("bond_undertaking").toString());
				bondPolicyDataPage.getBondBasicInformationBlk().setIndemnity(bondBasicInfo.get("indemnity").toString());
				bondPolicyDataPage.getBondBasicInformationBlk().setNotary(bondBasicInfo.get("notary_cd").toString());
				bondPolicyDataPage.save();
			}
		}
	}
	
	
	public static void setWorksheet(String sheetName) throws IOException {
		String file_name = "C:\\SELENIUM-AUTOMATION\\TESTDATA\\testdata.xlsx";
		excelFile = new FileInputStream(new File(file_name));
		workbook = new XSSFWorkbook(excelFile);
		datatypeSheet = workbook.getSheet(sheetName);
	}
	
	
	@DataProvider
	public Object[][] getData(Method method) throws IOException {
		 
		Object[][] methodObject;
		String[] methodData;
		int dataRowCount;
		int dataColCount;
		HashMap<String, Object[][]> sheetMap;
		Object[][] dataGroup;
		List<HashMap<String, String>> dataGroupList;
		Set<String> dataGroupSet;
		String[] dataColumnHeader;
		HashMap<String, String> dataGroupMap;
		List<String[]> methodDataList =  new ArrayList<String[]>();		
		int a;
		int rowno;
		
		setWorksheet(method.getName());
		int methodRowCount = datatypeSheet.getLastRowNum();  
		
		methodObject = new Object[methodRowCount][2];
		
		for (int i = 1 ; i <= methodRowCount ; i++) {
			methodData = new String[datatypeSheet.getRow(i).getLastCellNum()-1];
			methodData[0] = datatypeSheet.getRow(i).getCell(1).getStringCellValue();
    		for (int j = 2 ; j <= methodData.length ; j++) {
    			methodData[j-1] = datatypeSheet.getRow(i).getCell(j).getStringCellValue();
    		}
    		methodDataList.add(methodData);
    	}
		
		for (int i = 0 ; i < methodDataList.size() ; i++) {
			sheetMap = new HashMap<String, Object[][]>();
			methodObject[i][0] = methodDataList.get(i)[0];
			for (int j = 1 ; j < methodDataList.get(i).length ; j++) {
				setWorksheet(methodDataList.get(i)[j] +methodDataList.get(i)[0]);
				dataRowCount = datatypeSheet.getLastRowNum();
				dataColCount = datatypeSheet.getRow(0).getLastCellNum()-1;	 
				dataGroupSet = new LinkedHashSet<String>();
				dataColumnHeader = new String[dataColCount];
				
				for (a = 1; a <= dataColCount ; a++ ) {
					dataColumnHeader[a-1] = datatypeSheet.getRow(0).getCell(a).getStringCellValue();
				}

				for (a = 1 ; a <= dataRowCount ; a++) {	
					dataGroupSet.add(datatypeSheet.getRow(a).getCell(1).getStringCellValue());
				}
				dataGroup = new Object[dataGroupSet.size()][2];
				
				rowno = 0;
				for (String groupSetValue : dataGroupSet) {
					dataGroupList = new ArrayList<HashMap<String,String>>(); 
			    	for (a = 1 ; a <= dataRowCount ; a++) {
			    		if(groupSetValue.equals(datatypeSheet.getRow(a).getCell(1).getStringCellValue())) {
			    			dataGroupMap = new HashMap<String, String>();
			    			for (int k = 1 ; k <= dataColCount ; k++) {
			    				dataGroupMap.put(datatypeSheet.getRow(0).getCell(k).getStringCellValue(), datatypeSheet.getRow(a).getCell(k).getStringCellValue());
			    			}
			    			dataGroupList.add(dataGroupMap);
			    		}
			        }
			    	dataGroup[rowno][0] = groupSetValue;
			    	dataGroup[rowno][1] = dataGroupList;
			    	rowno++;
				}
				sheetMap.put(methodDataList.get(i)[j], dataGroup);
			}
			methodObject[i][1] = sheetMap;
		}
		return methodObject;
	}
	
}
