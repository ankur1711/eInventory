package com.mcdebos.ecash.pages;

import org.openqa.selenium.WebDriver;

import com.mcdebos.ecash.excelutils.DrawerCountDownData;

public class PageObjects extends Common {

	public HomePage getHomePage(WebDriver driver) {
		return new HomePage(driver);
	}

	public DrawerCountDownPage getDrawerCountDownPage(WebDriver driver) {
		return new DrawerCountDownPage(driver);
	}

	public DrawerCountDownData getDrawerCountDownData(String sheetName) {
		return new DrawerCountDownData(sheetName);
	}

	public TaxExemptPage getTaxExemptSalesPage(WebDriver driver) {
		return new TaxExemptPage(driver);

	}

	public BillableSalesPage getBillableSalesPage(WebDriver driver) {
		return new BillableSalesPage(driver);
	}
	public OtherReceiptsPage getOtherReceiptsPage(WebDriver driver){
		return new OtherReceiptsPage(driver);
	}
	public GroupSalesPage getGroupSalesPage(WebDriver driver){
		return new GroupSalesPage(driver);
	}
}
