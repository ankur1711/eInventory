package com.mcdebos.ecash.othercashfunctions.groupsales;

import org.testng.annotations.DataProvider;

import com.mcdebos.ecash.excelutils.GroupSalesData;

public class GroupSales_DataProvider {
	@DataProvider(name = "TC_1298")
	public static Object[][] DCDTestData() {
		GroupSalesData groupsalesdata = new GroupSalesData("TC_1298");
		return new Object[][] {{groupsalesdata}};
	}

}
