package com.mcdebos.ecash.drawercount;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mcdebos.common.annotations.TestInfo;
import com.mcdebos.ecash.excelutils.DrawerCountDownData;
import com.mcdebos.ecash.pages.PageObjects;

public class TestDrawerCount extends PageObjects {

	public WebDriver driver;
	public WebDriverWait wait;

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser", "url" })
	public void setUp(String browser, String url) {
		driver = getWebDriver(browser);
		wait = new WebDriverWait(driver, 30);
		navigate(url);
	}

	@Test(testName = "Pathway to DCD", description = "Verify the Pathway to navigate to Drawer count down Landing Page from eProfitability Main Menu", dataProvider = "TC_548", dataProviderClass = DCD_DataProvider.class, priority = 0)
	@TestInfo(testCaseID = "TC 547", userStory = "US 84", testCaseDescription = "Verify the Pathway to navigate to Drawer count down Landing Page from eProfitability Main Menu")
	public void pathwayToDCD(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
	}

	@Test(testName = "Identification of DCD in Cash System", description = "Verify that DCD is available in Cash system", dataProvider = "TC_548", dataProviderClass = DCD_DataProvider.class, priority = 1)
	@TestInfo(testCaseID = "TC 548", userStory = "US 84", testCaseDescription = "Verify that DCD is available in Cash system")
	public void identificationOfDCDInCashSystem(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");

		Assert.assertTrue(getDrawerCountDownPage(driver).isDCDPage(),
				"Drawer Count Down Landing Page was not displayed");
	}

	@Test(testName = "Identification of Store Number on DCD Page", description = "Verify that user is able to view Store number on DCD landing page", dataProvider = "TC_548", dataProviderClass = DCD_DataProvider.class, priority = 2)
	@TestInfo(testCaseID = "TC 549", userStory = "US 85", testCaseDescription = "Verify that user is able to view Store number on DCD landing page")
	public void identificationOfStoreNumberOnDCDPage(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());

		Assert.assertEquals(getDrawerCountDownPage(driver).getStoreNumber(),
				data.getStoreNumber(),
				"Store Number was not displayed on DCD Page");
	}

	@Test(testName = "Default Business Date in DCD", description = "Verify the default value for DCD Business Date field on DCD landing page", dataProvider = "TC_548", dataProviderClass = DCD_DataProvider.class, priority = 3)
	@TestInfo(testCaseID = "TC 550", userStory = "US 85", testCaseDescription = "Verify the default value for DCD Business Date field on DCD landing page")
	public void identificationOfDefaultBusinessDay(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");

		Assert.assertTrue(getDrawerCountDownPage(driver)
				.isBusinessDateTextBoxDisplayed(),
				"DCD business date field was not displayed");

		Assert.assertTrue(getDrawerCountDownPage(driver)
				.isDatePickerButtonDisplayed(),
				"DCD business date field was not displayed with calender function");

		String defaultDate = getDrawerCountDownPage(driver).getDefaultDate();

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String currentDate = sdf.format(date);

		Assert.assertTrue(currentDate.equals(defaultDate),
				"Current Date is not Displayed as a Default date on DCD landing page");

	}

	@Test(testName = "User Confirmation Not Required After Selecting Date", description = "Verify that user confirmation is not required to view Drawer Counts after selecting desired business date", dataProvider = "TC_548", dataProviderClass = DCD_DataProvider.class, priority = 4)
	@TestInfo(testCaseID = "TC 551", userStory = "US 85", testCaseDescription = "Verify that user confirmation is not required to view Drawer Counts after selecting desired business date")
	public void userConfirmationNotRequired(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");

		getDrawerCountDownPage(driver).selectDate("0");
	}

	@Test(testName = "Selecting date by Calender function", description = "Verify that user is able to select date by using Calendar function", dataProvider = "TC_548", dataProviderClass = DCD_DataProvider.class, priority = 5)
	@TestInfo(testCaseID = "TC 552", userStory = "US 85", testCaseDescription = "Verify that user is able to select date by using Calendar function")
	public void selectingDateCalenderFunction(DrawerCountDownData data)
			throws InterruptedException {
		String validDate = "-24";
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		// Click on calendar widget in Drawer Count down business date field
		getDrawerCountDownPage(driver).datePickerButton().click();
		// Verify User should be able to view calendar of current month
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
		Date date = new Date();
		String currentMonth = sdf.format(date);

		Assert.assertEquals(getDrawerCountDownPage(driver).getCurrentMonth(),
				currentMonth,
				"Current month was not displayed cuurrectly on DCD Page");

		// Click on Date picker button again to pop out from UI
		getDrawerCountDownPage(driver).datePickerButton().click();

		// Select any valid date using calendar function
		getDrawerCountDownPage(driver).selectDate(validDate);
		// User should be able to select date

		Assert.assertEquals(getDrawerCountDownPage(driver).getDefaultDate(),
				getDrawerCountDownPage(driver).getExpectedDate(validDate),
				"Date was not matching with the date which user has selected");
		// User should be able view Drawer Count down details for the selected
		// date

		Assert.assertTrue(
				getDrawerCountDownPage(driver).isDrawerCountDetailsDisplayed(
						getDrawerCountDownPage(driver).getExpectedDate(
								validDate)),
				"User was not able view Drawer Count down details for the selected date");
	}

	@Test(testName = "Selecting Past Business date", description = "Verify that user is able to go back 60 days within the calendar view and after selecting any date in this range, result appears in 'View Only' format", dataProvider = "TC_548", dataProviderClass = DCD_DataProvider.class, priority = 6)
	@TestInfo(testCaseID = "TC 553", userStory = "US 85", testCaseDescription = "Verify that user is able to go back 60 days within the calendar view and after selecting any date in this range, result appears in 'View Only' format")
	public void selectingPastBusinessDate(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		// Click on Calendar widget of 'Drawer Count down Business Date' field
		// on Drawer Count down page and go back 60 days

		getDrawerCountDownPage(driver).moveToDate(data.getBusinessDate());
		// Dates beyond 60 days back within the calendar widget are grayed out

		Assert.assertTrue(
				getDrawerCountDownPage(driver).isDateGreyedOut(
						data.getBusinessDate()),
				"Date beyond 60 days back within the calendar widget was not grayed out");
		// Click on date picker button to pop out from the UI

		getDrawerCountDownPage(driver).datePickerButton().click();
		// Select the date beyond 60 days

		getDrawerCountDownPage(driver).selectDate(data.getBusinessDate());
		// User should NOT be able to select any date

		Assert.assertNotEquals(
				getDrawerCountDownPage(driver).getDefaultDate(),
				getDrawerCountDownPage(driver).getExpectedDate(
						data.getBusinessDate()),
				"User was able to select any date beyond 60 days");

		// Click on date picker button to pop out from the UI
		getDrawerCountDownPage(driver).datePickerButton().click();
		// Select any past date within the 60 days
		String dateWithin60Days = "-59";
		// Click on date picker button again to pop out from the UI
		getDrawerCountDownPage(driver).selectDate(dateWithin60Days);
		// User should be able to select any date
		Assert.assertEquals(getDrawerCountDownPage(driver).getDefaultDate(),
				getDrawerCountDownPage(driver)
						.getExpectedDate(dateWithin60Days),
				"User was not able to select any date within 60 days");
		// User should be able to view Drawer Count down details for the
		// selected date
		/*
		 * Assert.assertTrue(
		 * getDrawerCountDownPage(driver).isDrawerCountDetailsDisplayed(
		 * getDrawerCountDownPage(driver).getExpectedDate( dateWithin60Days)),
		 * "User was not able view Drawer Count down details for the selected date"
		 * );
		 */
	}

	@Test(testName = "Select Future Date from Current Date in Date picker", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 7)
	@TestInfo(testCaseID = "TC 554", userStory = "US 85", testCaseDescription = "Select Future Date from Current Date in Date picker")
	public void selectFutureDate(DrawerCountDownData data1)
			throws InterruptedException {
		getHomePage(driver).selectUser(data1.getUserNumber());
		getHomePage(driver).selectStore(data1.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");

		getDrawerCountDownPage(driver).selectDate(data1.getBusinessDate());
	}

	@Test(testName = "Identifying Drawer Count Down Entries", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 8)
	@TestInfo(testCaseID = "TC 555", userStory = "US 85", testCaseDescription = "Identifying Drawer Count Down Entries")
	public void viewDCDEntries(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).isRegisterFields();
		getDrawerCountDownPage(driver).isTypeField();
		getDrawerCountDownPage(driver).isCloseTimeField();
		getDrawerCountDownPage(driver).isManagerField();
		getDrawerCountDownPage(driver).isCashierField();
		getDrawerCountDownPage(driver).isDepositCodeField();
		getDrawerCountDownPage(driver).isStatusField();
		getDrawerCountDownPage(driver).isSalesField();
	}

	/*
	 * @Test(testName = "Verifying the Uncounted status of DCD entries.",
	 * dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class,
	 * priority = 9)
	 * 
	 * @TestInfo(testCaseID = "TC 2204", userStory = "US 85",
	 * testCaseDescription = "Verifying the Uncounted status of DCD entries.") public void
	 * changeDCDDetailsFromUncountedToInprogress( DrawerCountDownData data)
	 * throws InterruptedException {
	 * getHomePage(driver).selectUser(data.getUserNumber());
	 * getHomePage(driver).selectStore(data.getStoreNumber());
	 * getHomePage(driver).selectECashOption("Drawer Countdown"); String
	 * currentDate = "0";
	 * getDrawerCountDownPage(driver).selectDate(currentDate);
	 * Assert.assertTrue(
	 * getDrawerCountDownPage(driver).unCountedToInprogress("AA"),
	 * "Status not changed to Inprogress"); }
	 */

	@Test(testName = " Verifying the Inprogress status of DCD entries.", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 10/*
																																							 * ,
																																							 * dependsOnMethods
																																							 * =
																																							 * {
																																							 * "changeDCDDetailsFromUncountedToInprogress"
																																							 * }
																																							 */)
	@TestInfo(testCaseID = "TC 555", userStory = "US 85", testCaseDescription = "Verifying the Inprogress status of DCD entries.")
	public void changeDCDDetailsFromInprogressToCounted(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		Assert.assertTrue(
				getDrawerCountDownPage(driver).inprogressToCounted("AA"),
				"Status not changed to Counted");
	}

	@Test(testName = " Verifying the Counted status of DCD entries.", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 11/*,dependsOnMethods={"changeDCDDetailsFromUncountedToInprogress"}*/)
	@TestInfo(testCaseID = "TC 555", userStory = "US 85", testCaseDescription = " Verifying the Counted status of DCD entries.")
	public void changeDCDDetailsFromCountedToInprogress(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";

		getDrawerCountDownPage(driver).selectDate(currentDate);

		Assert.assertTrue(
				getDrawerCountDownPage(driver).countedToInprogress("170.13",
						"AA"), "Status not changed to Counted");
	}

	@Test(testName = " Viweing the Manager Meals on DCD detail screen.", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 12)
	@TestInfo(testCaseID = "TC 585", userStory = "US 1", testCaseDescription = "Viweing the Manager Meals on DCD detail screen.")
	public void viewingManagerMeals(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).pencil_Icon_Inprogress().click();

		Assert.assertTrue(getDrawerCountDownPage(driver).isManagerMeals(),
				"Manager Meals Field is not displayed on DCD Detail page");

	}

	@Test(testName = " Viweing all dcd detail fields on DCD detail screen.", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 13)
	@TestInfo(testCaseID = "TC 568", userStory = "US 1", testCaseDescription = "Viweing all dcd detail fields on DCD detail screen.")
	public void viewingFieldsOnDCDDetailScreen(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();

		Assert.assertTrue(getDrawerCountDownPage(driver).isPreparerField(),
				"Preparer Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver).isDrawerBankField(),
				"Drawer Bank Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver)
				.isCurrentForeverField(),
				"Current Forever Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver)
				.isForeverDifferenceField(),
				"Current Forever Difference Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver).isPOSOverringsField(),
				"POS Overrings Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver).isCashRefundsField(),
				"Cash Refunds Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver)
				.isManualRefundsOrOverrings(),
				"Manual Refunds Overrings Field is not displayed on DCD Detail page");
		Assert.assertTrue(
				getDrawerCountDownPage(driver).isBillableSalesField(),
				"Billable Sales Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver).isCashlessSales(),
				"Cashless Sales Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver).isGiftCertRedeemed(),
				"Gift Cert Redeemed Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver).isArchCardRedeemed(),
				"Arch card Redeemed Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver).isSkimsField(),
				"Skims Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver)
				.isExpectedDrawerCashField(),
				"Expected Drawer Cash Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver)
				.isDrawerCountedCashField(),
				"Expected Drawer Counted Cash Field is not displayed on DCD Detail page");
		Assert.assertTrue(
				getDrawerCountDownPage(driver).isCashOverShortField(),
				"Expected Cash Over/short Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver)
				.isTransactionCountField(),
				"Trasaction CountDown Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver).isAverageCheckField(),
				"Average Check Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver).isBeforeTREDField(),
				"Before TRED Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver).isAfterTREDField(),
				"After TRED Field is not displayed on DCD Detail page");

	}

	@Test(testName = "Viewing DCD Manual Entry Field On DCD Detail Page.", dataProvider = "TC_569", dataProviderClass = DCD_DataProvider.class, priority = 14)
	@TestInfo(testCaseID = "TC 569", userStory = "US 1", testCaseDescription = "Viewing DCD Manual Entry Field On DCD Detail Page.")
	public void viewingManualEntryFieldOnDCDDetailPage(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";

		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();

		Assert.assertTrue(
				getDrawerCountDownPage(driver).shiftManagerOption.isDisplayed(),
				"Shift Manager Field is not displayed on DCD Detail page");

		Select select = new Select(
				getDrawerCountDownPage(driver).shiftManagerOption);

		select.selectByIndex(1);
		System.out.println(select.getFirstSelectedOption().getText());
		Select selectCashier = new Select(
				getDrawerCountDownPage(driver).cashierOption);
		selectCashier.selectByIndex(1);
		System.out.println(selectCashier.getFirstSelectedOption().getText());
		getDrawerCountDownPage(driver).manualRefundsAndOverrings(
				data.getManualRefundsOverrings());
		getDrawerCountDownPage(driver).giftCertificatesQuantity(
				data.getGiftCertificates());

	}

	@Test(testName = "Selct Manager Option From Manager Drop Down", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 15)
	@TestInfo(testCaseID = "TC 563", userStory = "US 4", testCaseDescription = "Selecting Shift Manager From Drop Down")
	public void selctManagerOption(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();
		getDrawerCountDownPage(driver).shiftManagerOption();

	}

	@Test(testName = "Selct Cashier Option From Cashier Drop Down", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 16)
	@TestInfo(testCaseID = "TC 563", userStory = "US 4", testCaseDescription = "Selecting Cashier From Drop Down")
	public void selctCashierOption(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();
		getDrawerCountDownPage(driver).cashierOption();

	}

	@Test(testName = "Viewing the manual refunds/overrings field on cash detail screen ", dataProvider = "TC_569", dataProviderClass = DCD_DataProvider.class, priority = 17)
	@TestInfo(testCaseID = "TC 586", userStory = "US 4", testCaseDescription = "Viewing the manual refunds/overrings field on cash detail screen ")
	public void viewManualRefundsOverrings(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();
		getDrawerCountDownPage(driver).manualRefundsAndOverrings(
				data.getManualRefundsOverrings());

	}

	@Test(testName = "Viewing the gift certificates redeemed on cash detail screen", dataProvider = "TC_569", dataProviderClass = DCD_DataProvider.class, priority = 18)
	@TestInfo(testCaseID = "TC 587", userStory = "US 4", testCaseDescription = "Viewing the gift certificates redeemed on cash detail screen ")
	public void viewGiftCertificates(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();
		getDrawerCountDownPage(driver).giftCertificatesQuantity(
				data.getGiftCertificates());

	}

	@Test(testName = " Viweing the Employee Meals on DCD detail screen.", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 19)
	@TestInfo(testCaseID = "TC 584", userStory = "US 1", testCaseDescription = "Viweing the Employee Meals on DCD detail screen.")
	public void viewingEmployeeMeals(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();
		Assert.assertTrue(
				getDrawerCountDownPage(driver).isEmployeeMealsField(),
				"Employee Meals Field is not displayed on DCD Detail page");

	}

	@Test(testName = " Viweing the After T-RED on DCD detail screen.", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 20)
	@TestInfo(testCaseID = "TC 583", userStory = "US 1", testCaseDescription = "Viweing the After T-RED on DCD detail screen.")
	public void viewingAfterTRed(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();
		Assert.assertTrue(getDrawerCountDownPage(driver).isAfterTREDField(),
				"After T-RED Field is not displayed on DCD Detail page");

	}

	@Test(testName = " Viweing the Before T-RED on DCD detail screen.", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 21)
	@TestInfo(testCaseID = "TC 582", userStory = "US 1", testCaseDescription = "Viweing the Before T-RED on DCD detail screen.")
	public void viewingBeforeTRed(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();
		Assert.assertTrue(getDrawerCountDownPage(driver).isBeforeTREDField(),
				"Before T-RED Field is not displayed on DCD Detail page");

	}

	@Test(testName = " Viweing the Drawer Countdown Fields on DCD Detail Page.", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 22)
	@TestInfo(testCaseID = "TC 588", userStory = "US 1", testCaseDescription = " Viweing the Drawer Countdown Fields on DCD Detail Page.")
	public void viewingDrawerCountedCash(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();
		Assert.assertTrue(getDrawerCountDownPage(driver)
				.isDrawerCountedCashField(),
				"Drawer Counted Cash Field is not displayed on DCD Detail page");
		getDrawerCountDownPage(driver).drawer_Count_Cash.sendKeys("170.13");

	}

	@Test(testName = " Viewing and verify that Forever difference is system calculated field on cash detail screen.", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 23)
	@TestInfo(testCaseID = "TC 571", userStory = "US 1", testCaseDescription = "Viewing and verify that Forever difference is system calculated field on cash detail screen")
	public void viewingForeverDifferenceIsSystemCalculated(
			DrawerCountDownData data) throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();
		Assert.assertTrue(getDrawerCountDownPage(driver)
				.isForeverDifferenceField(),
				"Forever Difference Field is not displayed on DCD Detail page");

	}

	@Test(testName = " Viewing and verify that Expected Drawer Cash is system calculated field on cash detail screen.", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 24)
	@TestInfo(testCaseID = "TC 573", userStory = "US 1", testCaseDescription = "Viewing and verify that Expected Drawer Cash is system calculated field on cash detail screen..")
	public void viewingExpectedDrawerCashIsSystemCalculated(
			DrawerCountDownData data) throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();
		Assert.assertTrue(getDrawerCountDownPage(driver)
				.isForeverDifferenceField(),
				"Expected Drawer Cash Field is not displayed on DCD Detail page");
	}

	@Test(testName = " Viewing and verify that Cash Over Short  is system calculated field on cash detail screen.", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 25)
	@TestInfo(testCaseID = "TC 574", userStory = "US 1", testCaseDescription = "Viewing and verify that Cash Over Short  is system calculated field on cash detail screen.")
	public void viewingOverShortIsSystemCalculated(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();
		Assert.assertTrue(
				getDrawerCountDownPage(driver).isCashOverShortField(),
				"Cash Over Shot Field is not displayed on DCD Detail page");
	}

	@Test(testName = "Viewing and Verifying System Calculated Fields.", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 26)
	@TestInfo(testCaseID = "TC 574", userStory = "US 1", testCaseDescription = "Viewing and Verifying System Calculated Fields.")
	public void VerfyingDCSystemCalculatedFields(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();
		Assert.assertTrue(getDrawerCountDownPage(driver)
				.isForeverDifferenceField(),
				"Forever Difference Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver).isSkimsField(),
				"Skims Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver)
				.isExpectedDrawerCashField(),
				"Expected Drawer Cash Field  is not displayed on DCD Detail page");
		Assert.assertTrue(
				getDrawerCountDownPage(driver).isCashOverShortField(),
				"Cash Over/Short Field  is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver)
				.isTransactionCountField(),
				"Transaction Count Field  is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver).isPreparerField(),
				"Preparerer Field  is not displayed on DCD Detail page");
	}

	@Test(testName = " Viewing and verify Manager and Cashier ID and Name is on Drawer Count Detail Screen.", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 27)
	@TestInfo(testCaseID = "TC 578", userStory = "US 1", testCaseDescription = "Viewing and verify Manager and Cashier ID and Name is on Drawer Count Detail Screen.")
	public void viewingManagerAndCashier(DrawerCountDownData data)
			throws InterruptedException {
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();
		Assert.assertTrue(getDrawerCountDownPage(driver).isManagerField(),
				"Manager Field is not displayed on DCD Detail page");
		Assert.assertTrue(getDrawerCountDownPage(driver).isCashierField(),
				"Cashier Field is not displayed on DCD Detail page");
	}
	@Test(testName="Viewing Deposit Codes field on Drawer count detail screen.", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 28)
	@TestInfo(testCaseID = "TC 578", userStory = "US 5", testCaseDescription = "Viewing Deposit Codes field on Drawer count detail screen.")
	public void viewDepositCodeFieldOnDCDdetailPage(DrawerCountDownData data) throws InterruptedException{
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();
		getDrawerCountDownPage(driver).depositCode.isDisplayed();
		Assert.assertTrue(getDrawerCountDownPage(driver).depositCode.isDisplayed(),
				"Deposit Code  Field is not displayed on DCD Detail page");
	}
	@Test(testName="Selecting Depsoit codes from drop down.", dataProvider = "TC_549", dataProviderClass = DCD_DataProvider.class, priority = 29)
	@TestInfo(testCaseID = "TC 578", userStory = "US 5", testCaseDescription = "Selecting Depsoit codes from drop down.")
	public void selectDepositCodeFromDepositDropDown(DrawerCountDownData data) throws InterruptedException{
		getHomePage(driver).selectUser(data.getUserNumber());
		getHomePage(driver).selectStore(data.getStoreNumber());
		getHomePage(driver).selectECashOption("Drawer Countdown");
		String currentDate = "-24";
		getDrawerCountDownPage(driver).selectDate(currentDate);
		getDrawerCountDownPage(driver).clickOnInprogressStatusRecords();
	getDrawerCountDownPage(driver).selectDepositCode();
		
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
