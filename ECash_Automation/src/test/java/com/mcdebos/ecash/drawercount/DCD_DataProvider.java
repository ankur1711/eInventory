package com.mcdebos.ecash.drawercount;

import org.testng.annotations.DataProvider;

import com.mcdebos.ecash.excelutils.DrawerCountDownData;

public class DCD_DataProvider {
	
	@DataProvider(name = "TC_548")
	public static Object[][] DCDTestData() {
		DrawerCountDownData drawerCountData = new DrawerCountDownData("TC_548");
		return new Object[][] {{drawerCountData}};
	}
	
	@DataProvider(name = "TC_549")
	public static Object[][] DCDTestData1() {
		DrawerCountDownData drawerCountData = new DrawerCountDownData("TC_549");
		return new Object[][] {{drawerCountData}};
	}
	@DataProvider(name = "TC_569")
	public static Object[][] DCDTestData2() {
		DrawerCountDownData drawerCountData = new DrawerCountDownData("TC_569");
		return new Object[][] {{drawerCountData}};
	}

}
