package com.mcdebos.ecash.othercashfunctions.groupsales;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mcdebos.common.annotations.TestInfo;
import com.mcdebos.ecash.excelutils.GroupSalesData;
import com.mcdebos.ecash.pages.PageObjects;

public class TestTaxExemptSales extends PageObjects {
	public WebDriver driver;
	public WebDriverWait wait;

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser", "url" })
	public void setUp(String browser, String url) {
		driver = getWebDriver(browser);
		wait = new WebDriverWait(driver, 50);
		navigate(url);
	}

	@Test(testName = "Verifying that user is able to view the tax exempt sale entries for the selected date range(e.g. 90 days, 6 months, 9 months , 12 months) on Tax Exempt Sales landing page on cloud app. ", description = "Verifying that user is able to view the tax exempt sale entries for the selected date range(e.g. 90 days, 6 months, 9 months , 12 months) on Tax Exempt Sales landing page on cloud app..", dataProvider = "TC_1298", dataProviderClass = GroupSales_DataProvider.class, priority = 0)
	@TestInfo(testCaseID = "TC 3169", userStory = "US592", testCaseDescription = "Verifying that user is able to view the tax exempt sale entries for the selected date range(e.g. 90 days, 6 months, 9 months , 12 months) on Tax Exempt Sales landing page on cloud app.")
	public void viewDateRangeOptions(GroupSalesData data)
			throws InterruptedException, ParseException {
		// select user number
		getHomePage(driver).selectUser(data.getUserNumber());
		// select location id
		getHomePage(driver).selectStore(data.getStoreNumber());
		// Open Other cash fuction
		getHomePage(driver).openOtherCashFunctionsMenu();
		// select group sales
		getHomePage(driver).selectOtherCashFunctionsOptions("Group Sales");
		// click taxexempt sales
		getTaxExemptSalesPage(driver).clickTaxExemptSales();
		// verify Custom date range option present in date range drop down
		Assert.assertTrue(
				checkDropDownValues(getTaxExemptSalesPage(driver)
						.getOptionsForDateRangeDropDown(), "Custom Date Range"),
				"Custom Date Range option was not displayed");
		Assert.assertTrue(
				checkDropDownValues(getTaxExemptSalesPage(driver)
						.getOptionsForDateRangeDropDown(), "3 Months"),
				"3 Months option was not displayed");
		Assert.assertTrue(
				checkDropDownValues(getTaxExemptSalesPage(driver)
						.getOptionsForDateRangeDropDown(), "6 months"),
				"6 Months option was not displayed");
		Assert.assertTrue(
				checkDropDownValues(getTaxExemptSalesPage(driver)
						.getOptionsForDateRangeDropDown(), "9 months"),
				"9 Months option was not displayed");
		Assert.assertTrue(
				checkDropDownValues(getTaxExemptSalesPage(driver)
						.getOptionsForDateRangeDropDown(), "12 months"),
				"12 Months option was not displayed");
		// Work Around
		// getTaxExemptSalesPage(driver).selectDateRangeFromDropDown("6 months");
		// Select the date range as 3 months
		getTaxExemptSalesPage(driver).selectDateRangeFromDropDown("3 Months");
		// Verify whether the start date is 3 months from current date
		Assert.assertEquals(getTaxExemptSalesPage(driver).getStartDate(),
				getDateByMonths("-3"),
				"Start date was not displayed as expected date - "
						+ getDateByMonths("-3"));
		// Verify whether the end date is current date
		Assert.assertEquals(getTaxExemptSalesPage(driver).getEndDate(),
				getDateByMonths("0"),
				"End date was not displayed as current date - "
						+ getDateByMonths("0"));
		// Click on Show Results button
		getTaxExemptSalesPage(driver).clickOnShowResultsButton(1);
		// Verify whether only 3 months tax exempt sales entries are displayed
		// on tax exempt sales landing page
		// Get all the displayed date for the records
		List<WebElement> dateElementsList3Months = getTaxExemptSalesPage(driver)
				.getListOfDateElements();
		for (WebElement date : dateElementsList3Months) {
			String dateTimeDisplayedonUI = date.getText();
			if (!(dateTimeDisplayedonUI.isEmpty() || dateTimeDisplayedonUI
					.contains("Invalid"))) {
				String dateDisplayedonUI = dateTimeDisplayedonUI.substring(0,
						10);
				Assert.assertTrue(
						verifyDateWithInRange(getTaxExemptSalesPage(driver)
								.getStartDate(), getTaxExemptSalesPage(driver)
								.getEndDate(), dateDisplayedonUI),
						"Date displayed '"
								+ dateDisplayedonUI
								+ "' was not with the specified range '3 Months'");
				Reporter.log("Date displayed '" + dateDisplayedonUI
						+ "' was with the specified range '3 Months'<br>");
			}
		}
		// Select the date range as 6 months
		getTaxExemptSalesPage(driver).selectDateRangeFromDropDown("6 months");
		// Verify whether the start date is 6 months from current date
		Assert.assertEquals(getTaxExemptSalesPage(driver).getStartDate(),
				getDateByMonths("-6"),
				"Start date was not displayed as expected date - "
						+ getDateByMonths("-6"));
		// Verify whether the end date is current date
		Assert.assertEquals(getTaxExemptSalesPage(driver).getEndDate(),
				getDateByMonths("0"),
				"End date was not displayed as current date - "
						+ getDateByMonths("0"));
		// Click on Show Results button
		getTaxExemptSalesPage(driver).clickOnShowResultsButton(1);
		// Verify whether only 6 months tax exempt sales entries are displayed
		// on tax exempt sales landing page
		// Get all the displayed date for the records
		List<WebElement> dateElementsList6Months = getTaxExemptSalesPage(driver)
				.getListOfDateElements();
		for (WebElement date : dateElementsList6Months) {
			String dateTimeDisplayedonUI = date.getText();
			if (!(dateTimeDisplayedonUI.isEmpty() || dateTimeDisplayedonUI
					.contains("Invalid"))) {
				String dateDisplayedonUI = dateTimeDisplayedonUI.substring(0,
						10);
				Assert.assertTrue(
						verifyDateWithInRange(getTaxExemptSalesPage(driver)
								.getStartDate(), getTaxExemptSalesPage(driver)
								.getEndDate(), dateDisplayedonUI),
						"Date displayed '"
								+ dateDisplayedonUI
								+ "' was not with the specified range '6 Months'");
				Reporter.log("Date displayed '" + dateDisplayedonUI
						+ "' was with the specified range '6 Months'<br>");
			}
		}
		// Select the date range as 9 months
		getTaxExemptSalesPage(driver).selectDateRangeFromDropDown("9 months");
		// Verify whether the start date is 9 months from current date
		Assert.assertEquals(getTaxExemptSalesPage(driver).getStartDate(),
				getDateByMonths("-9"),
				"Start date was not displayed as expected date - "
						+ getDateByMonths("-9"));
		// Verify whether the end date is current date
		Assert.assertEquals(getTaxExemptSalesPage(driver).getEndDate(),
				getDateByMonths("0"),
				"End date was not displayed as current date - "
						+ getDateByMonths("0"));
		// Click on Show Results button
		getTaxExemptSalesPage(driver).clickOnShowResultsButton(1);
		// Verify whether only 9 months tax exempt sales entries are displayed
		// on tax exempt sales landing page
		// Get all the displayed date for the records
		List<WebElement> dateElementsList9Months = getTaxExemptSalesPage(driver)
				.getListOfDateElements();
		for (WebElement date : dateElementsList9Months) {
			String dateTimeDisplayedonUI = date.getText();
			if (!(dateTimeDisplayedonUI.isEmpty() || dateTimeDisplayedonUI
					.contains("Invalid"))) {
				String dateDisplayedonUI = dateTimeDisplayedonUI.substring(0,
						10);
				Assert.assertTrue(
						verifyDateWithInRange(getTaxExemptSalesPage(driver)
								.getStartDate(), getTaxExemptSalesPage(driver)
								.getEndDate(), dateDisplayedonUI),
						"Date displayed '"
								+ dateDisplayedonUI
								+ "' was not with the specified range '9 Months'");
				Reporter.log("Date displayed '" + dateDisplayedonUI
						+ "' was with the specified range '9 Months'<br>");
			}
		}
		// Select the date range as 12 months
		getTaxExemptSalesPage(driver).selectDateRangeFromDropDown("12 months");
		// Verify whether the start date is 12 months from current date
		Assert.assertEquals(getTaxExemptSalesPage(driver).getStartDate(),
				getDateByMonths("-12"),
				"Start date was not displayed as expected date - "
						+ getDateByMonths("-12"));
		// Verify whether the end date is current date
		Assert.assertEquals(getTaxExemptSalesPage(driver).getEndDate(),
				getDateByMonths("0"),
				"End date was not displayed as current date - "
						+ getDateByMonths("0"));
		// Click on Show Results button
		getTaxExemptSalesPage(driver).clickOnShowResultsButton(1);
		// Verify whether only 12 months tax exempt sales entries are displayed
		// on tax exempt sales landing page
		// Get all the displayed date for the records
		List<WebElement> dateElementsList12Months = getTaxExemptSalesPage(
				driver).getListOfDateElements();
		for (WebElement date : dateElementsList12Months) {
			String dateTimeDisplayedonUI = date.getText();
			if (!(dateTimeDisplayedonUI.isEmpty() || dateTimeDisplayedonUI
					.contains("Invalid"))) {
				String dateDisplayedonUI = dateTimeDisplayedonUI.substring(0,
						10);
				Assert.assertTrue(
						verifyDateWithInRange(getTaxExemptSalesPage(driver)
								.getStartDate(), getTaxExemptSalesPage(driver)
								.getEndDate(), dateDisplayedonUI),
						"Date displayed '"
								+ dateDisplayedonUI
								+ "' was not with the specified range '12 Months'");
				Reporter.log("Date displayed '" + dateDisplayedonUI
						+ "' was with the specified range '12 Months'<br>");
			}
		}
		// Select the date range as Custom date range
		getTaxExemptSalesPage(driver).selectDateRangeFromDropDown(
				"Custom Date Range");
		// Select the Start Date
		getTaxExemptSalesPage(driver).openStartDateDatePicker();
		getTaxExemptSalesPage(driver).selectDate(
				getTaxExemptSalesPage(driver).taxExemptStartDatePrevButton(),
				getTaxExemptSalesPage(driver).taxExemptStartDateNextButton(),
				getTaxExemptSalesPage(driver).getStartDate(), "-10", "3");
		// Select the End Date
		// getTaxExemptSalesPage(driver).openEndDateDatePicker();
		// getTaxExemptSalesPage(driver).selectDate(getTaxExemptSalesPage(driver).taxExemptEndDatePrevButton(),
		// getTaxExemptSalesPage(driver).taxExemptEndDateNextButton(),
		// getTaxExemptSalesPage(driver).getEndDate(), "0", "6");
		// Click on Show Results button
		getTaxExemptSalesPage(driver).clickOnShowResultsButton(1);
		// Verify whether only selected date tax exempt sales entries are
		// displayed on tax exempt sales landing page
		// Get all the displayed date for the records
		List<WebElement> dateElementsListCustomDate = getTaxExemptSalesPage(
				driver).getListOfDateElements();
		for (WebElement date : dateElementsListCustomDate) {
			String dateTimeDisplayedonUI = date.getText();
			if (!(dateTimeDisplayedonUI.isEmpty() || dateTimeDisplayedonUI
					.contains("Invalid"))) {
				String dateDisplayedonUI = dateTimeDisplayedonUI.substring(0,
						10);
				Assert.assertTrue(
						verifyDateWithInRange(getTaxExemptSalesPage(driver)
								.getStartDate(), getTaxExemptSalesPage(driver)
								.getEndDate(), dateDisplayedonUI),
						"Date displayed '"
								+ dateDisplayedonUI
								+ "' was not with the specified range 'Custom Date Range'");
				Reporter.log("Date displayed '"
						+ dateDisplayedonUI
						+ "' was with the specified range 'Custom Date Range'<br>");
			}
		}

	}

	@Test(testName = "Verifying user is able to view the total of Tax Exempt Sales for the day on Tax Exempt Sales Detail Landing page on cloud app.", description = "Verifying that user is able to view the tax exempt sale entries for the selected date range(e.g. 90 days, 6 months, 9 months , 12 months) on Tax Exempt Sales landing page on cloud app..", dataProvider = "TC_1298", dataProviderClass = GroupSales_DataProvider.class, priority = 1)
	@TestInfo(testCaseID = "TC 3171", userStory = "US592", testCaseDescription = "Verifying user is able to view the total of Tax Exempt Sales for the day on Tax Exempt Sales Detail Landing page on cloud app.")
	public void viewTotalOfTaxExemptSales(GroupSalesData data)
			throws InterruptedException, ParseException {
		// Select user number
		getHomePage(driver).selectUser(data.getUserNumber());
		// Select Store Number
		getHomePage(driver).selectStore(data.getStoreNumber());
		// Open E-Cash - Other Cash Functions Menu
		getHomePage(driver).openOtherCashFunctionsMenu();
		// Select the Option as Group Sales
		getHomePage(driver).selectOtherCashFunctionsOptions("Group Sales");
		// Open Tax Exempt Sales Tab
		getTaxExemptSalesPage(driver).clickTaxExemptSales();
		// Verify the Date Range Drop Down contains "Custom Date Range"
		Assert.assertTrue(
				checkDropDownValues(getTaxExemptSalesPage(driver)
						.getOptionsForDateRangeDropDown(), "Custom Date Range"),
				"Custom Date Range option was not displayed");
		// Verify the Date Range Drop Down contains "3 Months"
		Assert.assertTrue(
				checkDropDownValues(getTaxExemptSalesPage(driver)
						.getOptionsForDateRangeDropDown(), "3 Months"),
				"3 Months option was not displayed");
		// Verify the Date Range Drop Down contains "6 months"
		Assert.assertTrue(
				checkDropDownValues(getTaxExemptSalesPage(driver)
						.getOptionsForDateRangeDropDown(), "6 months"),
				"6 Months option was not displayed");
		// Verify the Date Range Drop Down contains "9 months"
		Assert.assertTrue(
				checkDropDownValues(getTaxExemptSalesPage(driver)
						.getOptionsForDateRangeDropDown(), "9 months"),
				"9 Months option was not displayed");
		// Verify the Date Range Drop Down contains "12 months"
		Assert.assertTrue(
				checkDropDownValues(getTaxExemptSalesPage(driver)
						.getOptionsForDateRangeDropDown(), "12 months"),
				"12 Months option was not displayed");
		// Select the date range as 3 months, i.e., 90 Days
		getTaxExemptSalesPage(driver).selectDateRangeFromDropDown("3 Months");
		// Click on Show Results button
		getTaxExemptSalesPage(driver).clickOnShowResultsButton(1);
		// Verify whether only 3 months tax exempt sales entries are displayed
		// on taxexempt sales landing page
		// Get all the displayed date for the records
		List<WebElement> dateElementsList3Months = getTaxExemptSalesPage(driver)
				.getListOfDateElements();
		for (WebElement date : dateElementsList3Months) {
			String dateTimeDisplayedonUI = date.getText();
			if (!(dateTimeDisplayedonUI.isEmpty() || dateTimeDisplayedonUI
					.contains("Invalid"))) {
				String dateDisplayedonUI = dateTimeDisplayedonUI.substring(0,
						10);
				Assert.assertTrue(
						verifyDateWithInRange(getTaxExemptSalesPage(driver)
								.getStartDate(), getTaxExemptSalesPage(driver)
								.getEndDate(), dateDisplayedonUI),
						"Date displayed '"
								+ dateDisplayedonUI
								+ "' was not with the specified range '3 Months'<br>");
				Reporter.log("Date displayed '" + dateDisplayedonUI
						+ "' was with the specified range '3 Months'<br>");
			}
		}
		// View "Total of Tax Exempt Sales"
		Assert.assertEquals(getTaxExemptSalesPage(driver)
				.getTotalTaxExemptSalesDisplayed(),
				getTaxExemptSalesPage(driver).getTotalTaxExemptValue(),
				"Total of Tax Exempt Sales was not matching with the calculated total");
		Reporter.log("Total of Tax Exempt Sales displayed '"
				+ getTaxExemptSalesPage(driver)
						.getTotalTaxExemptSalesDisplayed()
				+ "' was matching with the displayed enteries total of '"
				+ getTaxExemptSalesPage(driver).getTotalTaxExemptValue()
				+ "'<br>");
		// Select the date range as 6 months
		getTaxExemptSalesPage(driver).selectDateRangeFromDropDown("6 months");
		// Verify whether the start date is 6 months from current date
		Assert.assertEquals(getTaxExemptSalesPage(driver).getStartDate(),
				getDateByMonths("-6"),
				"Start date was not displayed as expected date - "
						+ getDateByMonths("-6"));
		// Verify whether the end date is current date
		Assert.assertEquals(getTaxExemptSalesPage(driver).getEndDate(),
				getDateByMonths("0"),
				"End date was not displayed as current date - "
						+ getDateByMonths("0"));
		// Click on Show Results button
		getTaxExemptSalesPage(driver).clickOnShowResultsButton(1);
		// Verify whether only 6 months tax exempt sales entries are displayed
		// on taxexempt sales landing page
		// Get all the displayed date for the records
		List<WebElement> dateElementsList6Months = getTaxExemptSalesPage(driver)
				.getListOfDateElements();
		for (WebElement date : dateElementsList6Months) {
			String dateTimeDisplayedonUI = date.getText();
			if (!(dateTimeDisplayedonUI.isEmpty() || dateTimeDisplayedonUI
					.contains("Invalid"))) {
				String dateDisplayedonUI = dateTimeDisplayedonUI.substring(0,
						10);
				Assert.assertTrue(
						verifyDateWithInRange(getTaxExemptSalesPage(driver)
								.getStartDate(), getTaxExemptSalesPage(driver)
								.getEndDate(), dateDisplayedonUI),
						"Date displayed '"
								+ dateDisplayedonUI
								+ "' was not with the specified range '6 Months'");
				Reporter.log("Date displayed '" + dateDisplayedonUI
						+ "' was with the specified range '6 Months'<br>");
			}
		}
		// View "Total of Tax Exempt Sales"
		Assert.assertEquals(getTaxExemptSalesPage(driver)
				.getTotalTaxExemptSalesDisplayed(),
				getTaxExemptSalesPage(driver).getTotalTaxExemptValue(),
				"Total of Tax Exempt Sales was not matching with the calculated total");
		Reporter.log("Total of Tax Exempt Sales displayed '"
				+ getTaxExemptSalesPage(driver)
						.getTotalTaxExemptSalesDisplayed()
				+ "' was matching with the displayed enteries total of '"
				+ getTaxExemptSalesPage(driver).getTotalTaxExemptValue()
				+ "'<br>");
		// Select the date range as 9 months
		getTaxExemptSalesPage(driver).selectDateRangeFromDropDown("9 months");
		// Click on Show Results button
		getTaxExemptSalesPage(driver).clickOnShowResultsButton(1);
		// Verify whether only 9 months tax exempt sales entries are displayed
		// on taxexempt sales landing page
		// Get all the displayed date for the records
		List<WebElement> dateElementsList9Months = getTaxExemptSalesPage(driver)
				.getListOfDateElements();
		for (WebElement date : dateElementsList9Months) {
			String dateTimeDisplayedonUI = date.getText();
			if (!(dateTimeDisplayedonUI.isEmpty() || dateTimeDisplayedonUI
					.contains("Invalid"))) {
				String dateDisplayedonUI = dateTimeDisplayedonUI.substring(0,
						10);
				Assert.assertTrue(
						verifyDateWithInRange(getTaxExemptSalesPage(driver)
								.getStartDate(), getTaxExemptSalesPage(driver)
								.getEndDate(), dateDisplayedonUI),
						"Date displayed '"
								+ dateDisplayedonUI
								+ "' was not with the specified range '9 Months'");
				Reporter.log("Date displayed '" + dateDisplayedonUI
						+ "' was with the specified range '9 Months'<br>");
			}
		}
		// View "Total of Tax Exempt Sales"
		Assert.assertEquals(getTaxExemptSalesPage(driver)
				.getTotalTaxExemptSalesDisplayed(),
				getTaxExemptSalesPage(driver).getTotalTaxExemptValue(),
				"Total of Tax Exempt Sales was not matching with the calculated total");
		Reporter.log("Total of Tax Exempt Sales displayed '"
				+ getTaxExemptSalesPage(driver)
						.getTotalTaxExemptSalesDisplayed()
				+ "' was matching with the displayed enteries total of '"
				+ getTaxExemptSalesPage(driver).getTotalTaxExemptValue()
				+ "'<br>");
		// Select the date range as 12 months
		getTaxExemptSalesPage(driver).selectDateRangeFromDropDown("12 months");
		// Click on Show Results button
		getTaxExemptSalesPage(driver).clickOnShowResultsButton(1);
		// Verify whether only 12 months tax exempt sales entries are displayed
		// on taxexempt sales landing page
		// Get all the displayed date for the records
		List<WebElement> dateElementsList12Months = getTaxExemptSalesPage(
				driver).getListOfDateElements();
		for (WebElement date : dateElementsList12Months) {
			String dateTimeDisplayedonUI = date.getText();
			if (!(dateTimeDisplayedonUI.isEmpty() || dateTimeDisplayedonUI
					.contains("Invalid"))) {
				String dateDisplayedonUI = dateTimeDisplayedonUI.substring(0,
						10);
				Assert.assertTrue(
						verifyDateWithInRange(getTaxExemptSalesPage(driver)
								.getStartDate(), getTaxExemptSalesPage(driver)
								.getEndDate(), dateDisplayedonUI),
						"Date displayed '"
								+ dateDisplayedonUI
								+ "' was not with the specified range '12 Months'");
				Reporter.log("Date displayed '" + dateDisplayedonUI
						+ "' was with the specified range '12 Months'<br>");
			}
		}
		// View "Total of Tax Exempt Sales"
		Assert.assertEquals(getTaxExemptSalesPage(driver)
				.getTotalTaxExemptSalesDisplayed(),
				getTaxExemptSalesPage(driver).getTotalTaxExemptValue(),
				"Total of Tax Exempt Sales was not matching with the calculated total");
		Reporter.log("Total of Tax Exempt Sales displayed '"
				+ getTaxExemptSalesPage(driver)
						.getTotalTaxExemptSalesDisplayed()
				+ "' was matching with the displayed enteries total of '"
				+ getTaxExemptSalesPage(driver).getTotalTaxExemptValue()
				+ "'<br>");
		// Select the date range as Custom date range
		getTaxExemptSalesPage(driver).selectDateRangeFromDropDown(
				"Custom Date Range");
		// Select the Start Date
		getTaxExemptSalesPage(driver).openStartDateDatePicker();
		getTaxExemptSalesPage(driver).selectDate(
				getTaxExemptSalesPage(driver).taxExemptStartDatePrevButton(),
				getTaxExemptSalesPage(driver).taxExemptStartDateNextButton(),
				getTaxExemptSalesPage(driver).getStartDate(), "+10", "3");

		// Select the End Date
		// getTaxExemptSalesPage(driver).openEndDateDatePicker();
		// getTaxExemptSalesPage(driver).selectDate(getTaxExemptSalesPage(driver).taxExemptEndDatePrevButton(),
		// getTaxExemptSalesPage(driver).taxExemptEndDateNextButton(),
		// getTaxExemptSalesPage(driver).getEndDate(), "0", "6");
		// Click on Show Results button
		getTaxExemptSalesPage(driver).clickOnShowResultsButton(1);
		// Verify whether only selected tax exempt sales entries are displayed
		// on tax exempt sales landing page
		// Get all the displayed date for the records
		List<WebElement> dateElementsListCustomDate = getTaxExemptSalesPage(
				driver).getListOfDateElements();
		for (WebElement date : dateElementsListCustomDate) {
			String dateTimeDisplayedonUI = date.getText();
			if (!(dateTimeDisplayedonUI.isEmpty() || dateTimeDisplayedonUI
					.contains("Invalid"))) {
				String dateDisplayedonUI = dateTimeDisplayedonUI.substring(0,
						10);
				Assert.assertTrue(
						verifyDateWithInRange(getTaxExemptSalesPage(driver)
								.getStartDate(), getTaxExemptSalesPage(driver)
								.getEndDate(), dateDisplayedonUI),
						"Date displayed '"
								+ dateDisplayedonUI
								+ "' was not with the specified range 'Custom Date Range'");
				Reporter.log("Date displayed '"
						+ dateDisplayedonUI
						+ "' was with the specified range 'Custom Date Range'<br>");
			}
		}
		// View "Total of Tax Exempt Sales"
		Assert.assertEquals(getTaxExemptSalesPage(driver)
				.getTotalTaxExemptSalesDisplayed(),
				getTaxExemptSalesPage(driver).getTotalTaxExemptValue(),
				"Total of Tax Exempt Sales was not matching with the calculated total");
		Reporter.log("Total of Tax Exempt Sales displayed '"
				+ getTaxExemptSalesPage(driver)
						.getTotalTaxExemptSalesDisplayed()
				+ "' was matching with the displayed enteries total of '"
				+ getTaxExemptSalesPage(driver).getTotalTaxExemptValue()
				+ "'<br>");
	}

	@Test(testName = "Verify the Fields of Tax Exempt Sales Landing Page on cloud app.", description = "Verify that Shift manager is able to view Following Fields are Displayed on Tax Exempt Sales Landing Page(Date and Time,Register #,Amount,User,Tax ID Number,Organization Name,Deposit Status).", dataProvider = "TC_1298", dataProviderClass = GroupSales_DataProvider.class, priority = 2)
	@TestInfo(testCaseID = "TC 3172", userStory = "US592", testCaseDescription = "Verify that Shift manager is able to view Following Fields are Displayed on Tax Exempt Sales Landing Page(Date and Time,Register #,Amount,User,Tax ID Number,Organization Name,Deposit Status).")
	public void viewTaxExemptSalesLandingPageFields(GroupSalesData data) {
		// select user id
		getHomePage(driver).selectUser(data.getUserNumber());
		// select Location ID
		getHomePage(driver).selectStore(data.getStoreNumber());
		// Open the Other Cash menu
		getHomePage(driver).openOtherCashFunctionsMenu();
		// select Group Sales option
		getHomePage(driver).selectOtherCashFunctionsOptions("Group Sales");
		// click on Tax exemptsales tab
		getTaxExemptSalesPage(driver).clickTaxExemptSales();

		// Verify Date And Time Field is displayed on Tax Exempt Sales Landing
		// Page
		Assert.assertTrue(getTaxExemptSalesPage(driver).taxExemptDateAndTime
				.isDisplayed(),
				"Date And Time Field is not displated on Tax Exempt sales landing page");
		// Verify Register# Field is displayed on Tax Exempt Sales Landing Page
		Assert.assertTrue(getTaxExemptSalesPage(driver).taxExemptRegisterField
				.isDisplayed(),
				"Regiter Field is not displayed on Tax Exempt Sales Landing Page");
		// Verify Amount Field is displayed on Tax Exempt Sales Landing Page
		Assert.assertTrue(getTaxExemptSalesPage(driver).taxExemptAmountField
				.isDisplayed(),
				"Amount Field is not displayed on Tax Exempt Sales Landing Page");
		// Verify User//Verify Register# Field is displayed on Tax Exempt Sales
		// Landing Page
		Assert.assertTrue(
				getTaxExemptSalesPage(driver).taxExemptUserField.isDisplayed(),
				"User Field name is not displayed on Tax Exempt sales Landing Page");
		// Verify Organization Field is displayed on Tax Exempt Sales Landing
		// Page
		Assert.assertTrue(
				getTaxExemptSalesPage(driver).taxExemptOrganizationField
						.isDisplayed(),
				"Organization Field name is not displayed on Tax Exempt sales Landing Page");
		// Verify Tax Id Number Field is displayed on Tax Exempt Sales Landing
		// Page
		Assert.assertTrue(
				getTaxExemptSalesPage(driver).taxIDNumberField.isDisplayed(),
				"Tax ID Number Field is not displayed on Tax Exempt Sales Landing Page");
		// Verify DepositCode Status Field is displayed on Tax Exempt Sales
		// Landing page
		Assert.assertTrue(
				getTaxExemptSalesPage(driver).depositStatus.isDisplayed(),
				"Deposit Status Field is not displayed on Tax Exempt Sales Landing Page");

	}

	@Test(testName = "Verifying details from Tax Exempt were entered in DCD, those details need to populate on Tax Exempt Landing Page.", description = "Verifying details from Tax Exempts were entered in DCD, those details need to populate on Tax Exempts Landing Page.", dataProvider = "TC_1298", dataProviderClass = GroupSales_DataProvider.class, priority = 3)
	@TestInfo(testCaseID = "TC 3481", userStory = "US440", testCaseDescription = "Verifying details from Tax Exempt were entered in DCD, those details need to populate on Tax Exempt Landing Page.")
	public void verifyUserAbleToEditTaxExemptSalesForFinalizedDeposit(
			GroupSalesData data) {
		// Select user number
		getHomePage(driver).selectUser(data.getUserNumber());
		// Select Store Number
		getHomePage(driver).selectStore(data.getStoreNumber());
		// Open E-Cash - Other Cash Functions Menu
		getHomePage(driver).openOtherCashFunctionsMenu();
		// Select the Option as Group Sales
		getHomePage(driver).selectOtherCashFunctionsOptions("Group Sales");
		// Click on TaxExempt sales tab
		getTaxExemptSalesPage(driver).clickTaxExemptSales();
		// Get all edit button and store it in list
		List<WebElement> editButtonElemens = driver
				.findElements(By
						.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[contains(text(),'Finalized')]/following-sibling::td/eb-button"));
		for (int count = 0; count < editButtonElemens.size(); count++) {
			getTaxExemptSalesPage(driver).clickOnEditButton(count + 1);
			Reporter.log("OPen the " + (count + 1)
					+ " entry in Tax Exempt Sales<br>");
			// Verify OrganizationTextBox is enabled
			Assert.assertTrue(getTaxExemptSalesPage(driver)
					.organizationTextBox().isEnabled(),
					"User should not be able to edit the Organization Name");
			// Verify TaxIdNumber is enabled
			Assert.assertTrue(getTaxExemptSalesPage(driver)
					.taxIdNumberTextBox().isEnabled(),
					"User should not be able to edit the Tax Id Number");
			// Verify ContactNameText is enabled
			Assert.assertTrue(getTaxExemptSalesPage(driver)
					.contactNameTextBox().isEnabled(),
					"User should not be able to edit the Contact Name");
			// Verify AddressLine1 Text box is enabled
			Assert.assertTrue(getTaxExemptSalesPage(driver)
					.addressLine1TextBox().isEnabled(),
					"User Should not be able to edit the Address Line1");
			// Verify AddressLine2 Text box is enabled
			Assert.assertTrue(getTaxExemptSalesPage(driver)
					.addressLine2TextBox().isEnabled(),
					"Address Line2 is not enabled");
			// Verify City Text box is enabled
			Assert.assertTrue(getTaxExemptSalesPage(driver).cityTextBox()
					.isEnabled(), "City is not enabled");
			// Verify email text box is enabled
			Assert.assertTrue(
					getTaxExemptSalesPage(driver).emailTextBox().isEnabled(),
					"Email is not enabled on Tax Exempt sales Modular Form For Finalized Deposit, so User is not be able to enter/edit the values");
			// Verify phone number Text box is enabled
			Assert.assertTrue(
					getTaxExemptSalesPage(driver).phoneNumberTextBox()
							.isEnabled(),
					"Phone Number is not enabled on Tax Exempt sales Modular Form For Finalized Deposit, so User is not be able to enter/edit the values");
			// Verify zip code Text box is enabled
			Assert.assertTrue(
					getTaxExemptSalesPage(driver).zipCodeTextBox().isEnabled(),
					"Zip Code Text Box is not enabled on Tax Exempt sales Modular Form For Finalized Deposit, so User is not be able to enter/edit the values");
			Assert.assertTrue(
					getTaxExemptSalesPage(driver).stateDropDown().isEnabled(),
					"State Drop Down list is not enabled on Tax Exempt sales Modular Form For Finalized Deposit, so User is not be able to enter/edit the values ");

			getTaxExemptSalesPage(driver).closeTaxExemptModularWindow().click();

		}
	}

	@Test(testName = "Verifying details from Tax Exempt were entered in DCD, those details need to populate on Tax Exempt Landing Page.", description = "Viewing and verifying that following Fields are manual entry fields  in tax exempt Sales Detail Entry Page on Cloud App.", dataProvider = "TC_1298", dataProviderClass = GroupSales_DataProvider.class, priority = 4)
	@TestInfo(testCaseID = "TC 3480", userStory = "US440", testCaseDescription = "Viewing and verifying that following Fields are manual entry fields  in tax exempt Sales Detail Entry Page on Cloud App.")
	public void verifyManualEntryFields(GroupSalesData data) {
		getHomePage(driver).selectUser(data.getUserNumber());
		// Select Store Number
		getHomePage(driver).selectStore(data.getStoreNumber());
		// Open E-Cash - Other Cash Functions Menu
		getHomePage(driver).openOtherCashFunctionsMenu();
		// Select the Option as Group Sales
		getHomePage(driver).selectOtherCashFunctionsOptions("Group Sales");
		getTaxExemptSalesPage(driver).clickTaxExemptSales();
		// Verify the
		List<WebElement> editButtonElemens = driver
				.findElements(By
						.xpath("//table[@id='tax_exempt_table']//button[@value='Edit']"));
		for (int count = 0; count < editButtonElemens.size(); count++) {
			getTaxExemptSalesPage(driver).clickOnEditButton(count + 1);
			Reporter.log("OPen the " + (count + 1)
					+ " entry in Tax Exempt Sales<br>");
			getTaxExemptSalesPage(driver).organizationTextBox().click();
			// Enter Organization Name
			wait.until(
					ExpectedConditions.elementToBeClickable(By
							.xpath("//input[@id='tax_exempt_organization_input']")))
					.clear();
			getTaxExemptSalesPage(driver).organizationTextBox().sendKeys(
					"Test Organization" + Integer.toString(count));

			getTaxExemptSalesPage(driver).taxIdNumberTextBox().click();
			getTaxExemptSalesPage(driver).taxIdNumberTextBox().clear();
			getTaxExemptSalesPage(driver).taxIdNumberTextBox().sendKeys(
					"TID" + Integer.toString(generateRandomNumber()));

			getTaxExemptSalesPage(driver).contactNameTextBox().click();
			wait.until(
					ExpectedConditions.elementToBeClickable(By
							.xpath("//input[@id='contact_te0']"))).clear();
			getTaxExemptSalesPage(driver).contactNameTextBox().sendKeys(
					"Test Contact Name_"
							+ Integer.toString(generateRandomNumber()));

			getTaxExemptSalesPage(driver).addressLine1TextBox().click();
			wait.until(
					ExpectedConditions.elementToBeClickable(By
							.xpath("//div[@id='detail_te0']/div[2]/div[1]/div/input")))
					.clear();
			getTaxExemptSalesPage(driver).addressLine1TextBox().sendKeys(
					"Test Address 1_"
							+ Integer.toString(generateRandomNumber()));

			getTaxExemptSalesPage(driver).addressLine2TextBox().click();
			getTaxExemptSalesPage(driver).addressLine2TextBox().clear();
			getTaxExemptSalesPage(driver).addressLine2TextBox().sendKeys(
					"Test Address 2_"
							+ Integer.toString(generateRandomNumber()));

			getTaxExemptSalesPage(driver).cityTextBox().click();
			getTaxExemptSalesPage(driver).cityTextBox().clear();
			getTaxExemptSalesPage(driver).cityTextBox().sendKeys(
					"Test City_" + Integer.toString(generateRandomNumber()));
			/*
			 * Commented as the field property has been changed to take only a
			 * maximum of 5 characters
			 * 
			 * Done on 11-04-2016 by Jansi
			 * 
			 * getTaxExemptSalesPage(driver).zipCodeTextBox().click();
			 * getTaxExemptSalesPage(driver).zipCodeTextBox().clear();
			 * getTaxExemptSalesPage
			 * (driver).zipCodeTextBox().sendKeys("123456");
			 * Assert.assertTrue(getTaxExemptSalesPage(driver)
			 * .zipCodePopUpValidation().isDisplayed(),
			 * "Zip Code error message is not displayed");
			 */
			getTaxExemptSalesPage(driver).zipCodeTextBox().click();
			getTaxExemptSalesPage(driver).zipCodeTextBox().clear();
			getTaxExemptSalesPage(driver).zipCodeTextBox().sendKeys("12345");
			/*
			 * Assert.assertFalse(getTaxExemptSalesPage(driver)
			 * .zipCodePopUpValidation().isDisplayed(),
			 * "Zip Code error message is dispaled When entering only 5 digits numeric values"
			 * );
			 */
			// Select State
			getTaxExemptSalesPage(driver).selectState(1);
			// Enter Email Address
			getTaxExemptSalesPage(driver).emailTextBox().click();
			getTaxExemptSalesPage(driver).emailTextBox().clear();
			getTaxExemptSalesPage(driver).emailTextBox().sendKeys(
					"TestEmailAddr_" + Integer.toString(generateRandomNumber())
							+ "@mcd.com");
			// Enter Phone Number
			getTaxExemptSalesPage(driver).phoneNumberTextBox().click();
			getTaxExemptSalesPage(driver).phoneNumberTextBox().clear();
			getTaxExemptSalesPage(driver).phoneNumberTextBox().sendKeys(
					Integer.toString(generateRandomNumber()));

			getTaxExemptSalesPage(driver).submitTaxExemptSalesModularWindow()
					.click();
		}

	}

	/*
	 * @Test(testName =
	 * "Viewing and verifying that following Fields are auto-populated in tax exempt Sales Detail Entry Page in Cloud App."
	 * , description =
	 * "Viewing and verifying that following Fields are auto-populated in tax exempt Sales Detail Entry Page in Cloud App."
	 * , dataProvider = "TC_1298", dataProviderClass =
	 * GroupSales_DataProvider.class, priority = 5)
	 * 
	 * @TestInfo(testCaseID = "TC 3478", userStory = "US440",
	 * testCaseDescription =
	 * "Viewing and verifying that following Fields are auto-populated in tax exempt Sales Detail Entry Page in Cloud App."
	 * ) public void verifyAutopopulatedFields(GroupSalesData data) { // Select
	 * user number getHomePage(driver).selectUser(data.getUserNumber()); //
	 * Select Store Number
	 * getHomePage(driver).selectStore(data.getStoreNumber()); // Open E-Cash -
	 * Other Cash Functions Menu
	 * getHomePage(driver).openOtherCashFunctionsMenu(); // Select the Option as
	 * Group Sales
	 * getHomePage(driver).selectOtherCashFunctionsOptions("Group Sales");
	 * getTaxExemptSalesPage(driver).clickTaxExemptSales(); // Verify the
	 * List<WebElement> editButtonElemens = driver .findElements(By .xpath(
	 * "//table[@id='tax_exempt_table']/tbody/tr/td[contains(text(),'Finalized')]/following-sibling::td/eb-button"
	 * )); for (int count = 0; count < editButtonElemens.size(); count++) {
	 * wait.until(ExpectedConditions.elementToBeClickable(By .xpath(
	 * "(//table[@id='tax_exempt_table']/tbody/tr/td[contains(text(),'Finalized')]/following-sibling::td/eb-button)["
	 * + (count + 1) + "]")));
	 * 
	 * driver.findElement( By.xpath(
	 * "(//table[@id='tax_exempt_table']/tbody/tr/td[contains(text(),'Finalized')]/following-sibling::td/eb-button)["
	 * + (count + 1) + "]")).click(); Reporter.log("OPen the " + (count + 1) +
	 * " entry in Tax Exempt Sales<br>"); // Tax Amount Field is not enabled
	 * Assert.assertFalse(getTaxExemptSalesPage(driver).taxAmount()
	 * .isEnabled(), "User should be able to view Amout Field is Enabled");
	 * 
	 * Assert.assertFalse( getTaxExemptSalesPage(driver).autoPopulatedFields
	 * .isDisplayed(),
	 * "User should be able to view the all auto-populated fields are Enabled");
	 * getTaxExemptSalesPage(driver).closeTaxExemptModularWindow().click();
	 * 
	 * }
	 * 
	 * }
	 */

	@Test(testName = "Verify that store level user is able to filter Tax Exempt sales entries by organization name,user Id and Deposit code  on Tax Exempt sales landing page.", description = "Verify that store level user is able to filter Tax Exempt sales entries by organization name,user Id and Deposit codeon Tax Exempt sales landing page.", dataProvider = "TC_1298", dataProviderClass = GroupSales_DataProvider.class, priority = 6)
	@TestInfo(testCaseID = "TC1494", userStory = "US442", testCaseDescription = "Verify that store level user is able to filter Tax Exempt sales entries by organization name,user Id and Deposit code on Tax Exempt sales landing page.")
	public void filterTaxExemptSalesDetailsByOrganizationUserAndDepositCode(
			GroupSalesData data) {
		getHomePage(driver).selectUser(data.getUserNumber());
		// Select Store Number
		getHomePage(driver).selectStore(data.getStoreNumber());
		// Open E-Cash - Other Cash Functions Menu
		getHomePage(driver).openOtherCashFunctionsMenu();
		// Select the Option as Group Sales
		getHomePage(driver).selectOtherCashFunctionsOptions("Group Sales");
		getTaxExemptSalesPage(driver).clickTaxExemptSales();
		getTaxExemptSalesPage(driver).selectOrganizationName(
				"Test Organization1");
		getTaxExemptSalesPage(driver).clickOnShowResultsButton(1);
		sleep(1000);
		List<WebElement> organizationNameList = getTaxExemptSalesPage(driver)
				.getListOfOrganizationName();
		for (WebElement organization : organizationNameList) {
			String organizationDisplayedOnUI = organization.getText().trim();
			Assert.assertEquals(organizationDisplayedOnUI, "Test Organization1");
			Reporter.log("Organization name was displayed as expected - "
					+ organizationDisplayedOnUI + "<br>");
		}
		// View "Total of Tax Exempt Sales"
		Assert.assertEquals(getTaxExemptSalesPage(driver)
				.getTotalTaxExemptSalesDisplayed(),
				getTaxExemptSalesPage(driver).getTotalTaxExemptValue(),
				"Total of Tax Exempt Sales was not matching with the calculated total");
		// Select User id and filter the tax exempt sales entries as per user id
		getTaxExemptSalesPage(driver).selectUserID("- 3");
		getTaxExemptSalesPage(driver).clickOnShowResultsButton(1);
		sleep(1000);
		List<WebElement> userNameList = getTaxExemptSalesPage(driver)
				.getListOfUserID();
		for (WebElement user : userNameList) {
			String userDisplayedOnUI = user.getText().trim();
			Assert.assertEquals(userDisplayedOnUI, "3");
		}
		// View "Total of Tax Exempt Sales"
		Assert.assertEquals(getTaxExemptSalesPage(driver)
				.getTotalTaxExemptSalesDisplayed(),
				getTaxExemptSalesPage(driver).getTotalTaxExemptValue(),
				"Total of Tax Exempt Sales was not matching with the calculated total");
		// Select Deposit Status and filter the tax exempt sales entries are
		// filter as per Deposit code
		getTaxExemptSalesPage(driver).selectDepositStatus("Finalized");
		getTaxExemptSalesPage(driver).clickOnShowResultsButton(1);
		sleep(1000);
		List<WebElement> depositStatusList = getTaxExemptSalesPage(driver)
				.getListDepositStatus();
		for (WebElement depositStatus : depositStatusList) {
			String depositStatusDisplayedOnUI = depositStatus.getText().trim();
			Assert.assertEquals(depositStatusDisplayedOnUI, "Finalized");
		}
		Assert.assertEquals(
				getTaxExemptSalesPage(driver).getTotalTaxExemptSalesDisplayed(),
				getTaxExemptSalesPage(driver).getTotalTaxExemptValue(),
				"Total of Tax Exempt Sales was not matching with the calculated total as per filter");
		// Select Deposit Status as In progress and filter the tax exempt sales
		// entries are filtered as per deposit code
		getTaxExemptSalesPage(driver).selectOrganizationName("All");
		getTaxExemptSalesPage(driver).selectDepositStatus("In-Progress");
		getTaxExemptSalesPage(driver).clickOnShowResultsButton(1);
		sleep(1000);
		List<WebElement> inprogressDepositStatusList = getTaxExemptSalesPage(
				driver).getListOfInprogressDepositStatus();
		for (WebElement inprogressDepositStatus : inprogressDepositStatusList) {
			String inprogressDepositStatusOnUI = inprogressDepositStatus
					.getText().trim();
			Assert.assertEquals(inprogressDepositStatusOnUI, "In-Progress");
		}
		// View "Total of Tax Exempt Sales"
		Assert.assertEquals(
				getTaxExemptSalesPage(driver).getTotalTaxExemptSalesDisplayed(),
				getTaxExemptSalesPage(driver).getTotalTaxExemptValue(),
				"Total of Tax Exempt Sales was not matching with the calculated total as per filter");
	}

	@Test(testName = "Verifying user is able to Sort(Ascending/Descending Order) by Tax Exempt Sales entries on cloud app.", description = "Verifying user is able to Sort(Ascending/Descending Order) by Tax Exempt Sales entries on cloud app.", dataProvider = "TC_1298", dataProviderClass = GroupSales_DataProvider.class, priority = 7)
	@TestInfo(testCaseID = "TC1492", userStory = "US442", testCaseDescription = "Verifying user is able to Sort(Ascending/Descending Order) by Tax Exempt Sales entries on cloud app.")
	public void sortTaxExemptSalesDetailsByFieldDisplayedOnTaxExemptSalesPage(
			GroupSalesData data) throws ParseException {
		getHomePage(driver).selectUser(data.getUserNumber());
		// Select Store Number
		getHomePage(driver).selectStore(data.getStoreNumber());
		// Open E-Cash - Other Cash Functions Menu
		getHomePage(driver).openOtherCashFunctionsMenu();
		// Select the Option as Group Sales
		getHomePage(driver).selectOtherCashFunctionsOptions("Group Sales");
		// Click on Tax Exempt Sales Tab
		getTaxExemptSalesPage(driver).clickTaxExemptSales();
		// Get all the date elements as it was in descending order by default
		List<WebElement> dateElementsDisplayedOnUIDescending = getTaxExemptSalesPage(
				driver).getListOfDateElements();
		for (int count = 0; count < dateElementsDisplayedOnUIDescending.size() - 2; count++) {
			// Get the date alone and parse it as per the application format
			Date firstDateDisplayedOnUI = df
					.parse(dateElementsDisplayedOnUIDescending.get(count)
							.getText().substring(0, 10));
			Date secondDateDisplayedOnUI = df
					.parse(dateElementsDisplayedOnUIDescending.get(count + 1)
							.getText().substring(0, 10));
			// Verify the dates were in descending order
			Assert.assertTrue(
					firstDateDisplayedOnUI.after(secondDateDisplayedOnUI)
							|| firstDateDisplayedOnUI
									.equals(secondDateDisplayedOnUI),
					df.format(firstDateDisplayedOnUI)
							+ " was not equal to or after "
							+ df.format(secondDateDisplayedOnUI)
							+ ", so dates were not not in descending order by default");
			Reporter.log(df.format(firstDateDisplayedOnUI)
					+ " was equal to or after "
					+ df.format(secondDateDisplayedOnUI) + "<br>");
		}
		// Click on Ascending button for Date
		getTaxExemptSalesPage(driver).clickOnDateandTimeAscendingButton(1);
		// Get all the date elements as it was in descending order by default
		List<WebElement> dateElementsDisplayedOnUIAscending = getTaxExemptSalesPage(
				driver).getListOfDateElements();
		for (int count = 0; count < dateElementsDisplayedOnUIAscending.size() - 2; count++) {
			// Get the date alone and parse it as per the application format
			Date firstDateDisplayedOnUI = df
					.parse(dateElementsDisplayedOnUIAscending.get(count)
							.getText().substring(0, 10));
			Date secondDateDisplayedOnUI = df
					.parse(dateElementsDisplayedOnUIAscending.get(count + 1)
							.getText().substring(0, 10));
			// Verify the dates were in descending order
			Assert.assertTrue(
					firstDateDisplayedOnUI.before(secondDateDisplayedOnUI)
							|| firstDateDisplayedOnUI
									.equals(secondDateDisplayedOnUI),
					df.format(firstDateDisplayedOnUI)
							+ " was not equal to or before "
							+ df.format(secondDateDisplayedOnUI)
							+ ", so dates were not not in ascending order by default");
			Reporter.log(df.format(firstDateDisplayedOnUI)
					+ " was equal to or before "
					+ df.format(secondDateDisplayedOnUI) + "<br>");
		}
		// Click on Ascending button for Register
		getTaxExemptSalesPage(driver).taxExemptRegisterField.click();
		// Get all the register elements as it was in ascending order after
		// clicked on Register button
		List<WebElement> registerElementsDisplayedOnUIAscending = getTaxExemptSalesPage(
				driver).getListOfRegisterElements();
		for (int count = 0; count < registerElementsDisplayedOnUIAscending
				.size() - 1; count++) {
			int firstRegisteIdDisplayedOnUI = Integer
					.parseInt(registerElementsDisplayedOnUIAscending.get(count)
							.getText().trim());
			int secondRegisterIdDisplayedOnUI = Integer
					.parseInt(registerElementsDisplayedOnUIAscending
							.get(count + 1).getText().trim());
			// Verify the
			Assert.assertTrue(
					firstRegisteIdDisplayedOnUI <= secondRegisterIdDisplayedOnUI,
					"Register ID's are not Sorted in Asending Order");
			Reporter.log(firstRegisteIdDisplayedOnUI
					+ " was dislayed in ascending order<br>");
		}
		// Perform double click on Regiter
		getTaxExemptSalesPage(driver).taxExemptRegisterField.click();
		// Get all the Register elements as it was in descending order by
		// default
		List<WebElement> registerElementsDisplayedOnUIDDescending = getTaxExemptSalesPage(
				driver).getListOfRegisterElements();
		for (int count = 0; count < registerElementsDisplayedOnUIDDescending
				.size() - 1; count++) {
			int firstRegisterIdDislayedOnUIDescending = Integer
					.parseInt(registerElementsDisplayedOnUIDDescending
							.get(count).getText().trim());
			int secondRegisterDisplayedOnUIDescending = Integer
					.parseInt(registerElementsDisplayedOnUIDDescending
							.get(count + 1).getText().trim());
			Assert.assertTrue(
					firstRegisterIdDislayedOnUIDescending >= secondRegisterDisplayedOnUIDescending,
					"Register ID is not displayed in Descending Order on UI");
			Reporter.log(firstRegisterIdDislayedOnUIDescending
					+ " was dislayed in descending order<br>");
		}
		getTaxExemptSalesPage(driver).taxExemptAmountField.click();
		// Get all the Amount elements as it was in descending order by default
		List<WebElement> amountElementsDisplayedOnUI = getTaxExemptSalesPage(
				driver).getListOfAmountElements();
		for (int count = 0; count < amountElementsDisplayedOnUI.size() - 2; count++) {
			float firstAmountDisplayedOnUI = Float
					.parseFloat(amountElementsDisplayedOnUI.get(count)
							.getText().replace("$", ""));
			float secondAmountDisplayedOnUI = Float
					.parseFloat(amountElementsDisplayedOnUI.get(count + 1)
							.getText().replace("$", ""));
			Assert.assertTrue(
					firstAmountDisplayedOnUI <= secondAmountDisplayedOnUI,
					"Amount is not displayed on Ascending Order");
			Reporter.log(firstAmountDisplayedOnUI
					+ " was dislayed in ascending order<br>");
		}
		getTaxExemptSalesPage(driver).taxExemptAmountField.click();
		// Get all the Amount elements as it was in descending order by default
		List<WebElement> amountDisplayedOnUIDescending = getTaxExemptSalesPage(
				driver).getListOfAmountElements();
		for (int count = 0; count < amountDisplayedOnUIDescending.size() - 2; count++) {
			float firstAmountDisplayedOnUIDescendingOrder = Float
					.parseFloat(amountDisplayedOnUIDescending.get(count)
							.getText().replace("$", ""));
			float secondAmountDisplayedOnUIDescendingOrder = Float
					.parseFloat(amountDisplayedOnUIDescending.get(count + 1)
							.getText().replace("$", ""));
			Assert.assertTrue(
					firstAmountDisplayedOnUIDescendingOrder >= secondAmountDisplayedOnUIDescendingOrder,
					"Amount is not displayed on Descending Order");
			Reporter.log(firstAmountDisplayedOnUIDescendingOrder
					+ " was dislayed in descending order<br>");
		}
		getTaxExemptSalesPage(driver).taxExemptUserField.click();
		// Get all the User elements as it was in descending order by default
		List<WebElement> userDisplayedOnUIAscending = getTaxExemptSalesPage(
				driver).getListOfUserID();
		for (int count = 0; count < userDisplayedOnUIAscending.size() - 1; count++) {
			int firstUserDisplayedOnUIAscending = Integer
					.parseInt(userDisplayedOnUIAscending.get(count).getText()
							.trim());
			int secondUserDisplayedOnUIAscending = Integer
					.parseInt(userDisplayedOnUIAscending.get(count + 1)
							.getText().trim());
			Assert.assertTrue(
					firstUserDisplayedOnUIAscending <= secondUserDisplayedOnUIAscending,
					"User id is not displayed in Ascending Order");
			Reporter.log(firstUserDisplayedOnUIAscending
					+ " was dislayed in ascending order<br>");
		}
		getTaxExemptSalesPage(driver).taxExemptUserField.click();
		// Get all the User elements as it was in descending order by default
		List<WebElement> userIdDisplayedOnUIDescending = getTaxExemptSalesPage(
				driver).getListOfUserID();
		for (int count = 0; count < userDisplayedOnUIAscending.size() - 1; count++) {
			int firstUserIdDisplayedOnUIDescending = Integer
					.parseInt(userIdDisplayedOnUIDescending.get(count)
							.getText().trim());
			int secondUserIdDisplayedOnUIDescending = Integer
					.parseInt(userIdDisplayedOnUIDescending.get(count + 1)
							.getText().trim());
			Assert.assertTrue(
					firstUserIdDisplayedOnUIDescending >= secondUserIdDisplayedOnUIDescending,
					"User Id is not displayed Descending Order");
			Reporter.log(firstUserIdDisplayedOnUIDescending
					+ " was dislayed in descending order<br>");
		}
		getTaxExemptSalesPage(driver).taxExemptOrganizationField.click();
		// Get all the OrganizationName elements as it was in descending order
		// by default
		List<WebElement> organizationNameDisplayedOnUIAscending = getTaxExemptSalesPage(
				driver).getListOfOrganizationName();
		for (int count = 0; count < organizationNameDisplayedOnUIAscending
				.size() - 1; count++) {
			String firstOrganizationNameDisplayedOnUIAscending = organizationNameDisplayedOnUIAscending
					.get(count).getText().trim();
			String secondOrganizationNameDisplayedOnUIAscending = organizationNameDisplayedOnUIAscending
					.get(count + 1).getText().trim();
			if (!(firstOrganizationNameDisplayedOnUIAscending.isEmpty() || firstOrganizationNameDisplayedOnUIAscending
					.contains("Invalid"))) {
				if (!(secondOrganizationNameDisplayedOnUIAscending.isEmpty() || firstOrganizationNameDisplayedOnUIAscending
						.contains("Invalid"))) {
					Assert.assertTrue(
							firstOrganizationNameDisplayedOnUIAscending
									.compareToIgnoreCase(secondOrganizationNameDisplayedOnUIAscending) <= 0,
							"Organization name is not displayed in Ascending Order");
					Reporter.log(firstOrganizationNameDisplayedOnUIAscending
							+ " was dislayed in ascending order<br>");
				}

			}
		}
		getTaxExemptSalesPage(driver).taxExemptOrganizationField.click();
		// Get all the User OrganizationName as it was in descending order by
		// default
		List<WebElement> organizationNameDisplayedOnUIDescending = getTaxExemptSalesPage(
				driver).getListOfOrganizationName();
		for (int count = 0; count < organizationNameDisplayedOnUIDescending
				.size() - 1; count++) {
			String firstOrganizationNameDisplayedOnUIDescending = organizationNameDisplayedOnUIDescending
					.get(count).getText().trim();
			String secondOrganizationNameDisplayedOnUIDescending = organizationNameDisplayedOnUIDescending
					.get(count + 1).getText().trim();
			if (!(firstOrganizationNameDisplayedOnUIDescending.isEmpty() || firstOrganizationNameDisplayedOnUIDescending
					.contains("Invalid"))) {
				if (!(secondOrganizationNameDisplayedOnUIDescending.isEmpty() || secondOrganizationNameDisplayedOnUIDescending
						.contains("Invalid"))) {
					Assert.assertTrue(
							firstOrganizationNameDisplayedOnUIDescending
									.compareToIgnoreCase(secondOrganizationNameDisplayedOnUIDescending) >= 0,
							"Organization Name is not displayed in Descending Order");
					Reporter.log(firstOrganizationNameDisplayedOnUIDescending
							+ " was dislayed in descending order<br>");
				}
			}
		}
		// Click on Tax ID Number field to sort in ascending order
		getTaxExemptSalesPage(driver).taxIDNumberField.click();
		// Get all the TaxId Numbers elements as it was in descending order by
		// default
		List<WebElement> taxIDNumberDisplayedOnUIAscendingOrder = getTaxExemptSalesPage(
				driver).getListOfTaxIdNumber();
		for (int count = 0; count < taxIDNumberDisplayedOnUIAscendingOrder
				.size() - 1; count++) {
			String firsttaxIDNumberDisplayedOnUIAscendingOrder = taxIDNumberDisplayedOnUIAscendingOrder
					.get(count).getText().trim();
			String secondtaxIDNumberDisplayedOnUIAscendingOrder = taxIDNumberDisplayedOnUIAscendingOrder
					.get(count + 1).getText().trim();
			if (!(firsttaxIDNumberDisplayedOnUIAscendingOrder.isEmpty() || firsttaxIDNumberDisplayedOnUIAscendingOrder
					.contains("Invalid"))) {
				if (!(secondtaxIDNumberDisplayedOnUIAscendingOrder.isEmpty() || secondtaxIDNumberDisplayedOnUIAscendingOrder
						.contains("Invalid"))) {
					Assert.assertTrue(
							firsttaxIDNumberDisplayedOnUIAscendingOrder
									.compareToIgnoreCase(secondtaxIDNumberDisplayedOnUIAscendingOrder) <= 0,
							"Tax ID Number is not displayed in Ascending Order");
					Reporter.log(firsttaxIDNumberDisplayedOnUIAscendingOrder
							+ " was dislayed in ascending order<br>");
				}

			}
		}
		// Click on Tax ID Number field to sort in descending order
		getTaxExemptSalesPage(driver).taxIDNumberField.click();
		// Get all the TaxId Numbers elements as it was in descending order by
		// default
		List<WebElement> taxIDNumberDisplayedOnUIDescendingOrder = getTaxExemptSalesPage(
				driver).getListOfTaxIdNumber();
		for (int count = 0; count < taxIDNumberDisplayedOnUIDescendingOrder
				.size() - 1; count++) {
			String firsttaxIDNumberDisplayedOnUIDescendingOrder = taxIDNumberDisplayedOnUIDescendingOrder
					.get(count).getText().trim();
			String secondtaxIDNumberDisplayedOnUIDescendingOrder = taxIDNumberDisplayedOnUIDescendingOrder
					.get(count + 1).getText().trim();
			if (!(firsttaxIDNumberDisplayedOnUIDescendingOrder.isEmpty() || firsttaxIDNumberDisplayedOnUIDescendingOrder
					.contains("Invalid"))) {
				if (!(secondtaxIDNumberDisplayedOnUIDescendingOrder.isEmpty() || secondtaxIDNumberDisplayedOnUIDescendingOrder
						.contains("Invalid"))) {
					Assert.assertTrue(
							firsttaxIDNumberDisplayedOnUIDescendingOrder
									.compareToIgnoreCase(secondtaxIDNumberDisplayedOnUIDescendingOrder) >= 0,
							"Tax ID Number is not displayed in Descending Order");
					Reporter.log(firsttaxIDNumberDisplayedOnUIDescendingOrder
							+ " was dislayed in descending order<br>");
				}

			}
		}
		// Click on Deposit Status field to sort in ascending order
		getTaxExemptSalesPage(driver).depositStatus.click();
		// Get all the Deposit Status elements as it was in descending order by
		// default
		List<WebElement> depositStatusDisplayedOnUIAscendingOrder = getTaxExemptSalesPage(
				driver).getListOfDepositStatus();
		for (int count = 0; count < depositStatusDisplayedOnUIAscendingOrder
				.size() - 1; count++) {
			String firstDepositStatusDisplayedOnUIAscendingOrder = depositStatusDisplayedOnUIAscendingOrder
					.get(count).getText().trim();
			String secondDepositStatusDisplayedOnUIAscendingOrder = depositStatusDisplayedOnUIAscendingOrder
					.get(count + 1).getText().trim();
			if (!(firstDepositStatusDisplayedOnUIAscendingOrder.isEmpty() || firstDepositStatusDisplayedOnUIAscendingOrder
					.contains("Invalid"))) {
				if (!(secondDepositStatusDisplayedOnUIAscendingOrder.isEmpty() || secondDepositStatusDisplayedOnUIAscendingOrder
						.contains("Invalid"))) {
					Assert.assertTrue(
							firstDepositStatusDisplayedOnUIAscendingOrder
									.compareToIgnoreCase(secondDepositStatusDisplayedOnUIAscendingOrder) <= 0,
							"Deposit Status is not displayed in Ascending Order");
					Reporter.log(firstDepositStatusDisplayedOnUIAscendingOrder
							+ " was dislayed in ascending order<br>");
				}

			}
		}
		// Click on Deposit Status field to sort in descending order
		getTaxExemptSalesPage(driver).depositStatus.click();
		// Get all the Deposit Status elements as it was in descending order by
		// default
		List<WebElement> depositStatusDisplayedOnUIDescendingOrder = getTaxExemptSalesPage(
				driver).getListOfDepositStatus();
		for (int count = 0; count < depositStatusDisplayedOnUIDescendingOrder
				.size() - 1; count++) {
			String firstDepositStatusDisplayedOnUIDescendingOrder = depositStatusDisplayedOnUIDescendingOrder
					.get(count).getText().trim();
			String secondDepositStatusDisplayedOnUIDescendingOrder = depositStatusDisplayedOnUIDescendingOrder
					.get(count + 1).getText().trim();
			if (!(firstDepositStatusDisplayedOnUIDescendingOrder.isEmpty() || firstDepositStatusDisplayedOnUIDescendingOrder
					.contains("Invalid"))) {
				if (!(secondDepositStatusDisplayedOnUIDescendingOrder.isEmpty() || secondDepositStatusDisplayedOnUIDescendingOrder
						.contains("Invalid"))) {
					Assert.assertTrue(
							firstDepositStatusDisplayedOnUIDescendingOrder
									.compareToIgnoreCase(secondDepositStatusDisplayedOnUIDescendingOrder) >= 0,
							"Deposit Status is not displayed in Descending Order");
					Reporter.log(firstDepositStatusDisplayedOnUIDescendingOrder
							+ " was dislayed in descending order<br>");
				}

			}
		}
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
