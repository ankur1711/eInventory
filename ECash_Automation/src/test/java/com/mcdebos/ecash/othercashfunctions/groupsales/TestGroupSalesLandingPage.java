package com.mcdebos.ecash.othercashfunctions.groupsales;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mcdebos.common.annotations.TestInfo;
import com.mcdebos.ecash.excelutils.GroupSalesData;
import com.mcdebos.ecash.pages.PageObjects;

public class TestGroupSalesLandingPage extends PageObjects {
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser", "url" })
	public void setUp(String browser, String url) {
		driver = getWebDriver(browser);
		wait = new WebDriverWait(driver, 30);
		navigate(url);
	}

	@Test(testName = "Pathway to group sales", description = "Verify that as a shift manager is able to navigate into Group Sale Landing Page from Cash management main menu.", dataProvider = "TC_1298", dataProviderClass = GroupSales_DataProvider.class, priority = 0)
	@TestInfo(testCaseID = "TC 1298", userStory = "US448", testCaseDescription = "Verify that as a shift manager is able to navigate into Group Sale Landing Page from Cash management main menu.")
	public void pathwayToGroupSales(GroupSalesData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).openOtherCashFunctionsMenu();
		getHomePage(driver).selectOtherCashFunctionsOptions("Group Sales");
		Assert.assertTrue(
				getTaxExemptSalesPage(driver).taxExemptSalesTab.isDisplayed(),
				"Tax Exempt Sales Tab was not displayed on Group Sales Landing Page");
		Assert.assertTrue(
				getBillableSalesPage(driver).billableSalesTab.isDisplayed(),
				"Billable Sales Tab was not displayed on Group Sales Landing Page");
		Assert.assertTrue(
				getOtherReceiptsPage(driver).otherReceiptsTab.isDisplayed(),
				"Other Receipts Tab was not displayed on Group Sales Landing Page");
	}

	@Test(testName = "Identification of Group Sales in Cash System", description = "Verify that Group Sales is available within Cash system", dataProvider = "TC_1298", dataProviderClass = GroupSales_DataProvider.class, priority = 1)
	@TestInfo(testCaseID = "TC 1300", userStory = "US448", testCaseDescription = "Identifying that user is in group sales application within Cash System.")
	public void identificationOfGroupSalesInCashSystem(GroupSalesData data) {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).openOtherCashFunctionsMenu();
		Assert.assertTrue(
				getHomePage(driver).otherCashFunctionOptionLink("Group Sales")
						.isDisplayed(),
				"Group Sales Option was not displayed under Ecash System");

	}

	@Test(testName = "Verify that user is able to navigate into different sub-functions of group sales", description = "Verify that user is able to navigate into different sub-functions of group sales", dataProvider = "TC_1298", dataProviderClass = GroupSales_DataProvider.class, priority = 2)
	@TestInfo(testCaseID = "TC 3327", userStory = "US448", testCaseDescription = "Verify that user is able to navigate into different sub-functions of group sales")
	public void navigateIntoSubFunctionsOfGroupSales(GroupSalesData data) {
		// select user number
		getHomePage(driver).selectUser(data.getUserNumber());
		// select store number
		getHomePage(driver).selectStore(data.getStoreNumber());
		// open other cash menu
		getHomePage(driver).openOtherCashFunctionsMenu();
		// select group sales option
		getHomePage(driver).selectOtherCashFunctionsOptions("Group Sales");
		// click billable sales tab
		getBillableSalesPage(driver).clickBillableSalesTab();
		// click taxexempt sales tab
		getTaxExemptSalesPage(driver).clickTaxExemptSales();
		// verify user is on tax exempt sales page
		Assert.assertTrue(getTaxExemptSalesPage(driver).taxExemptSalesTab
				.getAttribute("aria-expanded").contains("true"),
				"User is not be able to navigate into Tax Exempt Sales page");
		// click billable sales tab
		getBillableSalesPage(driver).clickBillableSalesTab();
		// verify user is on billable sales page
		Assert.assertTrue(getBillableSalesPage(driver).billableSalesTab
				.getAttribute("aria-expanded").contains("true"),
				"User is not be able to navigate into Billable Sales page");
		// click Other receipts tab
		getOtherReceiptsPage(driver).clickOtherReceiptsTab();
		// verify user is on other receipts page
		Assert.assertTrue(getOtherReceiptsPage(driver).otherReceiptsTab
				.getAttribute("aria-expanded").contains("true"),
				"User is not be able to navigate into Other Receipts page");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
