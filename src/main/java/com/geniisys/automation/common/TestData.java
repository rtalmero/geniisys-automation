package com.geniisys.automation.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

@SuppressWarnings("unchecked")
public class TestData {

	FileInputStream fileInput;
	XSSFWorkbook workbook;
	static XSSFSheet datatypeSheet;	
	String fileName;
	
	private static final Logger LOGGER = LogManager.getLogger(TestData.class.getName());
	
	private void setWorkbook(String folderName, String fileName) throws IOException {
		this.fileName = fileName;
		String path = "C:\\SELENIUM-AUTOMATION\\TESTDATA\\" + folderName + "\\" + fileName + ".xlsx";
		fileInput = new FileInputStream(new File(path));
		workbook = new XSSFWorkbook(fileInput);;
	}
	
	private void setSheet(String sheetName) {
		datatypeSheet = workbook.getSheet(sheetName);
	}
	
	@DataProvider
	public Object[][] getData(Method method, ITestContext context) throws IOException {
		setWorkbook(context.getCurrentXmlTest().getName(), method.getName());
		if (workbook.getNumberOfSheets() == 1) {
			return getDataSingleSheet();	
		} else {			
			return getDataMultipleSheets();
		}
	}
	
	private Object[][] getDataSingleSheet() throws IOException {
		Object[][] methodObject;
		Set<String> dataGroupSet;
		List<HashMap<String, String>> dataGroupList;
		HashMap<String, String> dataGroupMap;
		String[] dataColumnHeader;
		
		setSheet("data");
		
		dataGroupSet = new LinkedHashSet<String>();
		
		dataColumnHeader = new String[datatypeSheet.getRow(0).getLastCellNum()-1];
		
		for (int i = 1 ; i <= datatypeSheet.getLastRowNum() ; i++) {
			dataGroupSet.add(datatypeSheet.getRow(i).getCell(1).getStringCellValue());
		}
		
		for (int i = 1; i <= datatypeSheet.getRow(0).getLastCellNum()-1 ; i++ ) {
			dataColumnHeader[i-1] = datatypeSheet.getRow(0).getCell(i).getStringCellValue();
		}
		
		methodObject = new Object[dataGroupSet.size()][2];
		
		int rownum = 0;
		
		for (String group : dataGroupSet) {
			methodObject[rownum][0] = group;
			dataGroupList = new ArrayList<HashMap<String,String>>(); 
			for (int i = 1 ; i <= datatypeSheet.getLastRowNum() ; i++) {	
				if (group.equals(datatypeSheet.getRow(i).getCell(1).getStringCellValue())){
					dataGroupMap = new HashMap<String, String>();
					for (int j = 1 ; j <= datatypeSheet.getRow(0).getLastCellNum()-1 ; j++){
						dataGroupMap.put(datatypeSheet.getRow(0).getCell(j).getStringCellValue(), 
								datatypeSheet.getRow(i).getCell(j).getStringCellValue());
					}
					dataGroupList.add(dataGroupMap);
				}
			}
			methodObject[rownum][1] = dataGroupList;
			rownum++;		
		}
		
		String data = "getDataSingleSheet" + "\n\n" + fileName.toUpperCase() + "\n";
		
		for (int f = 0 ; f < methodObject.length ; f++) {
			data = data + "\n" + methodObject[f][0];
			data = data + "\n" + methodObject[f][1];
		}
		LOGGER.info(data + "\n");
		
		workbook.close();
		return methodObject;
		
	}
	
	
	
	private Object[][] getDataMultipleSheets() throws IOException {
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
		
		setSheet("initialize");
		
		int methodRowCount = datatypeSheet.getLastRowNum();  
		
		methodObject = new Object[methodRowCount][2];
		
		for (int i = 1 ; i <= methodRowCount ; i++) {
			methodData = new String[datatypeSheet.getRow(i).getLastCellNum()-1];
			methodData[0] = datatypeSheet.getRow(i).getCell(1).getStringCellValue() + "-" 
					+ datatypeSheet.getRow(i).getCell(0).getStringCellValue();
    		for (int j = 2 ; j <= methodData.length ; j++) {
    			methodData[j-1] = datatypeSheet.getRow(i).getCell(j).getStringCellValue();
    		}
    		methodDataList.add(methodData);
    	}
		
		for (int i = 0 ; i < methodDataList.size() ; i++) {
			sheetMap = new HashMap<String, Object[][]>();
			methodObject[i][0] = methodDataList.get(i)[0];
			for (int j = 1 ; j < methodDataList.get(i).length ; j++) {
				setSheet(methodDataList.get(i)[j]);
				dataRowCount = datatypeSheet.getLastRowNum();
				dataColCount = datatypeSheet.getRow(0).getLastCellNum()-1;	 
				dataGroupSet = new LinkedHashSet<String>();
				dataColumnHeader = new String[dataColCount];
				String[] methodDataParts = methodDataList.get(i)[0].split("-");
				for (a = 1; a <= dataColCount ; a++ ) {
					dataColumnHeader[a-1] = datatypeSheet.getRow(0).getCell(a).getStringCellValue();
				}

				for (a = 1 ; a <= dataRowCount ; a++) {	
					if (methodDataList.get(i)[0].equals(datatypeSheet.getRow(a).getCell(0).getStringCellValue())
							|| methodDataParts[0].equals(datatypeSheet.getRow(a).getCell(0).getStringCellValue())
							|| methodDataParts[1].equals(datatypeSheet.getRow(a).getCell(0).getStringCellValue())){
						dataGroupSet.add(datatypeSheet.getRow(a).getCell(1).getStringCellValue());
					}
				}
				dataGroup = new Object[dataGroupSet.size()][2];
				
				rowno = 0;
				for (String groupSetValue : dataGroupSet) {
					dataGroupList = new ArrayList<HashMap<String,String>>(); 
			    	for (a = 1 ; a <= dataRowCount ; a++) {
			    		if(groupSetValue.equals(datatypeSheet.getRow(a).getCell(1).getStringCellValue())
			    				&& (methodDataList.get(i)[0].equals(datatypeSheet.getRow(a).getCell(0).getStringCellValue())
									|| methodDataParts[0].equals(datatypeSheet.getRow(a).getCell(0).getStringCellValue())
									|| methodDataParts[1].equals(datatypeSheet.getRow(a).getCell(0).getStringCellValue()))) {
			    			dataGroupMap = new HashMap<String, String>();
			    			for (int k = 1 ; k <= dataColCount ; k++) {
			    				dataGroupMap.put(datatypeSheet.getRow(0).getCell(k).getStringCellValue(), 
			    						datatypeSheet.getRow(a).getCell(k).getStringCellValue());
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
		
		HashMap<String, Object[][]> tempHashMap1;
		Object[][] temppObj1;
		String data = "getDataMultipleSheets" + "\n\n" + fileName.toUpperCase() + "\n";
		
		for (int f = 0 ; f < methodObject.length ; f++) {
			data = data + "\n" + methodObject[f][0];
			tempHashMap1 = (HashMap<String, Object[][]>) methodObject[f][1];
			for (String tempString1 : tempHashMap1.keySet()) {
				data = data + "\n-" + tempString1;
				temppObj1 = tempHashMap1.get(tempString1);
				for (int g = 0 ; g < temppObj1.length ; g++) {
					data = data + "\n\t" +  temppObj1[g][0] + ": " + temppObj1[g][1];
				}
			}
		}
		LOGGER.info(data + "\n");
		
		workbook.close();
		return methodObject;
	}
}