package com.mcdebos.ecash.excelutils;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

public class DrawerCountDownData {

	String fileName = "DrawerCountDown.xls";
	int userNumberIndex, storeNumberIndex, businessDateIndex,
			manualRefundsOverringsIndex, giftCertificatesIndex;

	private String userNumber, storeNumber, businessDate,
			manualRefundsOverrings, giftCertificates;

	public DrawerCountDownData(String sheetName) {
		readDataFromExcel(sheetName);
	}

	public String getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getManualRefundsOverrings() {
		return manualRefundsOverrings;
	}

	public void setManualRefundsOverrings(String manualRefundsOverrings) {
		this.manualRefundsOverrings = manualRefundsOverrings;
	}

	public String getGiftCertificates() {
		return giftCertificates;
	}

	public void setGiftCertificates(String giftCertificates) {
		this.giftCertificates = giftCertificates;
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
				case "BUSINESSDATE":
					businessDateIndex = i;
					break;
				case "USERNUMBER":
					userNumberIndex = i;
					break;
				case "MANUALREFUNDSOVERRINGS":
					manualRefundsOverringsIndex = i;
					break;
				case "GIFTCERTIFICATES":
					giftCertificatesIndex = i;
					break;
				default:
					break;
				}
			}
			setStoreNumber(sheet.getCell(storeNumberIndex, 1).getContents());
			setBusinessDate(sheet.getCell(businessDateIndex, 1).getContents());
			setUserNumber(sheet.getCell(userNumberIndex, 1).getContents());
			setManualRefundsOverrings(sheet.getCell(
					manualRefundsOverringsIndex, 1).getContents());
			setGiftCertificates(sheet.getCell(giftCertificatesIndex, 1)
					.getContents());
		} catch (Exception e) {
			System.out
					.println("Error Occured while reading data f,rom the excel file");
		}
	}
}
