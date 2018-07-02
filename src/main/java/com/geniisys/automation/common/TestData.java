package com.geniisys.automation.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

@SuppressWarnings("unchecked")
public class TestData {

	private FileInputStream fileInput;
	private XSSFWorkbook workbook;
	private volatile  XSSFSheet datatypeSheet;	
	private String fileName;
	private String path;
	private final int noOfData = 2;
	
	private static final Logger LOGGER = LogManager.getLogger(TestData.class.getName());
	
	
	private void setWorkbook(String folderName, String fileName) throws IOException {
		this.fileName = fileName;
		path = "C:\\SELENIUM-AUTOMATION\\TESTDATA\\" + folderName ;
		fileInput = new FileInputStream(new File(path + "\\" + fileName + ".xlsx"));
		workbook = new XSSFWorkbook(fileInput);
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
		
		setSheet("data");
		
		dataGroupSet = new LinkedHashSet<String>();
		
		for (int i = 1 ; i <= datatypeSheet.getLastRowNum() ; i++) {
			dataGroupSet.add(datatypeSheet.getRow(i).getCell(1).getStringCellValue());
		}
		
		methodObject = new Object[dataGroupSet.size()][noOfData];
		
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
		
		logData("getDataSingleSheet", methodObject);
		
		workbook.close();
		return methodObject;
		
	}
	
	private Object[][] getDataMultipleSheets() throws IOException {
		Object[][] methodObject;
		HashMap<String, Object[][]> sheetMap;
		Object[][] dataGroup;
		List<String[]> methodDataList;
		
		setSheet("initialize");
		
		int methodRowCount = datatypeSheet.getLastRowNum();  
		
		methodObject = new Object[methodRowCount][noOfData];
		
    	methodDataList = accessInitializer(methodRowCount);
		
		for (int i = 0 ; i < methodDataList.size() ; i++) {
			sheetMap = new HashMap<String, Object[][]>();
			methodObject[i][0] = methodDataList.get(i)[0];
			for (int j = 1 ; j < methodDataList.get(i).length ; j++) {
				
				setSheet(methodDataList.get(i)[j]);
				
				dataGroup = getDataGroup(getDataSet(methodDataList, i));
				sheetMap.put(methodDataList.get(i)[j], dataGroup);
			}
			methodObject[i][1] = sheetMap;
		}
		logData("getDataMultipleSheets", methodObject);
		
		workbook.close();
		return methodObject;
	}
	
	private void logData(String method, Object[][] methodObject) {
		StringBuilder data = new StringBuilder();
		data.append(method + "\t" + path +  "\n\n" + fileName.toUpperCase(Locale.ENGLISH) + "\n");
        if ("getDataSingleSheet".equals(method)) {
        	for (int f = 0 ; f < methodObject.length ; f++) {
    			data.append("\n" + methodObject[f][0] + "\n" + methodObject[f][1]);
    		}
        } else if ("getDataMultipleSheets".equals(method)) {
        	HashMap<String, Object[][]> tempHashMap1;
        	for (int f = 0 ; f < methodObject.length ; f++) {
    			data.append("\n" + methodObject[f][0]);
    			tempHashMap1 = (HashMap<String, Object[][]>) methodObject[f][1];
    			for (Entry<String, Object[][]> entry : tempHashMap1.entrySet()) {
    			    data.append("\n-" +  entry.getKey());
    			    Object[][] value = entry.getValue();
    			    for (int g = 0 ; g < value.length ; g++) {
    					data.append("\n\t" +  value[g][0] + ": " + value[g][1]);
    				}
    			}
    		}
        }	
        
        LOGGER.info(data.append("\n"));
	}
	
	private List<String[]> accessInitializer(int methodRowCount) {
		List<String[]> methodDataList =  new ArrayList<String[]>();
		String[] methodData;
		for (int i = 1 ; i <= methodRowCount ; i++) {
			methodData = new String[datatypeSheet.getRow(i).getLastCellNum()-1];
			methodData[0] = getCellValue(i,1) + "-" + getCellValue(i,0);
    		for (int j = 2 ; j <= methodData.length ; j++) {
    			methodData[j-1] = getCellValue(i,j);
    		}
    		methodDataList.add(methodData);
    	}
		return methodDataList;
	}
	
	private boolean checkForGroups(String group, int rownum) {
		String[] methodDataParts = group.split("-");
		String dataGroup = getCellValue(rownum,0);
		boolean result = false;
		if (group.equals(dataGroup) || methodDataParts[0].equals(dataGroup) || methodDataParts[1].equals(dataGroup)
				|| "".equals(dataGroup)){
			result = true;
		}
		
		return result;
	}
	
	private Object[][] getDataGroup(HashMap<String, Set<Integer>> dataSet) {
		List<HashMap<String, String>> dataGroupList;
		HashMap<String, String> dataGroupMap;
		Object[][] dataGroup = new Object[dataSet.size()][noOfData];
		int rowno = 0;
		
		for (String group : dataSet.keySet()) {
			dataGroupList = new ArrayList<HashMap<String,String>>(); 
	    	
	    	for (int dataRow : dataSet.get(group)) {
	    		dataGroupMap = new HashMap<String, String>();
    			for (int k = 1 ; k <= (datatypeSheet.getRow(0).getLastCellNum()-1) ; k++) {
    				dataGroupMap.put(getCellValue(0,k), getCellValue(dataRow,k));
    			}
    			dataGroupList.add(dataGroupMap);
	    	}
	    	
	    	dataGroup[rowno][0] = group;
	    	dataGroup[rowno][1] = dataGroupList;
	    	rowno++;
		}
		
		
		
		return dataGroup;
	}
	
	private HashMap<String, Set<Integer>> getDataSet(List<String[]> methodDataList, int i) {
		
		HashMap<String, Set<Integer>> dataSet = new HashMap<String, Set<Integer>>();
		Set<String> dataGroupSet = new LinkedHashSet<String>();
		Set<Integer> dataRowSet = new LinkedHashSet<Integer>();
		
		for (int a = 1 ; a <= datatypeSheet.getLastRowNum() ; a++) {	
			if (checkForGroups(methodDataList.get(i)[0], a)){
				dataGroupSet.add(getCellValue(a,1));
			}
		}
		for (String dataGroup : dataGroupSet) {
			dataRowSet = new LinkedHashSet<Integer>();
			for (int a = 1 ; a <= datatypeSheet.getLastRowNum() ; a++) {	
				if (checkForGroups(methodDataList.get(i)[0], a) && dataGroup.equals(getCellValue(a, 1))){
					dataRowSet.add(a);
				}
			}
			dataSet.put(dataGroup, dataRowSet);
			
		}
		
		return dataSet;
		
		
	}
	
	
	private String getCellValue(int row, int column) {
		return datatypeSheet.getRow(row).getCell(column).getStringCellValue();
	}
	
	public void addDataUnderwriting(String file, String sheet, HashMap<String, String> dataMap) throws Exception {
		setWorkbook("UNDERWRITING", file);
		setSheet(sheet);
		addData(dataMap);
	}
	
	public void addData(HashMap<String, String> dataMap) throws Exception	{
		HashMap<String, Integer> dataColumnHeader = new HashMap<String, Integer>();
		Row rowCurrent;
		Cell cellCurrent; 
		
		for (int i = 0; i < datatypeSheet.getRow(0).getLastCellNum() ; i++ ) {
			dataColumnHeader.put(getCellValue(0,i), i);
		}		
		
		FileOutputStream fileOut = new FileOutputStream(new File(path + "\\" + fileName + ".xlsx"));
		rowCurrent = getCurrentRow();

		for (Entry<String, String> data : dataMap.entrySet()) {
				
			cellCurrent = getCurrentCell(rowCurrent, dataColumnHeader.get(data.getKey()));
				
			cellCurrent.setCellValue(data.getValue());
		}
		
		logInsertData(dataMap);
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}	
	
	private void logInsertData(HashMap<String, String> dataMap) {
		StringBuilder data = new StringBuilder();
		data.append("Add data\t"+ path + "\n\n" + fileName.toUpperCase(Locale.ENGLISH) + "\n[");
		for (Entry<String, String> dataEntry : dataMap.entrySet()) {
			data.append(dataEntry.getKey() + " = " +  dataEntry.getValue() + ", ");	
		}
		data.delete(data.length()-2, data.length());
		LOGGER.info(data.append("]\n"));
		
	}
	
	private Row getCurrentRow() {
		
		Row rowCurrent;
		int row;
		
		row = datatypeSheet.getLastRowNum()+1;
		rowCurrent = datatypeSheet.getRow(row);
		
		if (rowCurrent == null) {
			rowCurrent = datatypeSheet.createRow(row);
		}
		
		return rowCurrent;
		
	}
	
	private Cell getCurrentCell(Row rowCurrent, int cellNo) {
		Cell cellCurrent; 
		
		cellCurrent = rowCurrent.getCell(cellNo);
		if (cellCurrent == null) {
			cellCurrent = rowCurrent.createCell(cellNo);
		}
		
		return cellCurrent;
		
	}
	
}