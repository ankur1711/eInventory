package com.mcdebos.ecash.excelutils;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class GroupSalesData {

	String fileName = "GroupSalesData.xls";
	int userNumberIndex, storeNumberIndex;
	private String userNumber, storeNumber;
	
	public GroupSalesData(String sheetName) {
		readDataFromExcel(sheetName);
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}

	public void readDataFromExcel(String sheetName) {
		try {
			Workbook excel = Workbook
					.getWorkbook(new File(System.getProperty("user.dir")
							+ "\\src\\test\\data\\" + fileName));
			Sheet sheet = excel.getSheet(sheetName);
			int columns = sheet.getColumns();
			for (int i = 0; i < columns; i++) {
				switch (sheet.getCell(i, 0).getContents().toUpperCase()) {
				case "STORENUMBER":
					storeNumberIndex = i;
					break;
				case "USERNUMBER":
					userNumberIndex = i;
					break;
				default:
					break;

				}
			}
			setUserNumber(sheet.getCell(userNumberIndex, 1).getContents());
			setStoreNumber(sheet.getCell(storeNumberIndex, 1).getContents());

		} catch (BiffException | IOException e) {
			System.out
					.println("Error Occured while reading data f,rom the excel file");
			e.printStackTrace();
		}

	}
}
