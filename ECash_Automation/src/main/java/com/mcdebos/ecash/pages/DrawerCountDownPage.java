package com.mcdebos.ecash.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class DrawerCountDownPage extends Common {

	public DrawerCountDownPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Date Picker button
	public WebElement datePickerButton() {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By
				.xpath("//button[@id='dc_biz_date_picker_btn']"))));
		return driver.findElement(By
				.xpath("//button[@id='dc_biz_date_picker_btn']"));
	}

	// Current Month button
	public WebElement currentMonth() {
		return driver.findElement(By
				.xpath("//div[@class='xdsoft_label xdsoft_month']/span"));
	}

	// Year Drop down
	public WebElement yearInDatePicker() {
		return driver.findElement(By
				.xpath("//div[contains(@class,'xdsoft_year')]"));
	}

	// Next month navigation button
	public WebElement nextMonthBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("xdsoft_next"))));
		return driver.findElement(By.className("xdsoft_next"));
	}

	// Previous month navigation button
	public WebElement previousMonthBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("xdsoft_prev"))));
		return driver.findElement(By.className("xdsoft_prev"));
	}

	public WebElement drawerCountDownBusinessDateText() {
		return driver.findElement(By
				.xpath("//div/label/strong[contains(text(),'Drawer')]"));
	}

	// Store Number field
	public WebElement storeNumberField() {
		return driver.findElement(By.xpath("//div[@id='nsn']"));
	}

	// Register field
	public WebElement registerField() {
		return driver
				.findElement(By
						.xpath("//th[@class='sorting_asc']/span[contains(text(),'Register')]"));
	}

	@FindBy(xpath = "//th[@class='sorting']/span[contains(text(),'Type')]")
	public WebElement Type_Field;

	@FindBy(xpath = "//th[@class='sorting']/span[contains(text(),'Close Time')]")
	public WebElement Close_Time_Field;

	@FindBy(xpath = "//th[@class='sorting']/span[contains(text(),'Manager')]")
	public WebElement Manager_Field;

	@FindBy(xpath = "//th[@class='sorting']/span[contains(text(),'Cashier')]")
	public WebElement Cashier_Field;

	@FindBy(xpath = "//th[@class='sorting']/span[contains(text(),'Deposit Code')]")
	public WebElement Deposit_Code_Field;

	@FindBy(xpath = "//th[@class='sorting']/span[contains(text(),'Status')]")
	public WebElement Status_Field;

	@FindBy(xpath = "//th[@class='sorting']/span[contains(text(),'Sales')]")
	public WebElement Sales_Field;

	@FindBy(xpath = "//input[@id='dc_counted_cash_input']")
	public WebElement drawer_Count_Cash;

	@FindBy(xpath = "//select[@id='shift_mngr_input']")
	public WebElement shiftManagerOption;

	@FindBy(xpath = "//select[@id='cashier_input']")
	public WebElement cashierOption;

	@FindBy(xpath = "//select[@id='deposit_code_input']")
	public WebElement depositCodeSelectionDD;

	@FindBy(xpath = "//input[@id='co_adj_ref_ovr_amt_input']")
	public WebElement manualRefundsAndOverringsText;
	
	@FindBy(id="deposit_code_input")
	public WebElement depositCode;
	
	public WebElement pencil_Icon_Uncounted() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("(//span[contains(text(),'Uncounted')]/../following-sibling::td//a)[1]")));
		return driver
				.findElement(By
						.xpath("(//span[contains(text(),'Uncounted')]/../following-sibling::td//a)[1]"));
	}

	public WebElement pencil_Icon_Inprogress() throws InterruptedException {
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("(//span[contains(text(),'Inprogress')]/../following-sibling::td//a)[1]")));
		return driver
				.findElement(By
						.xpath("(//span[contains(text(),'Inprogress')]/../following-sibling::td//a)[1]"));
	}

	public WebElement pencil_Icon_Counted() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("(//span[contains(text(),'Counted')]/../following-sibling::td//a)[1]")));
		return driver
				.findElement(By
						.xpath("(//span[contains(text(),'Counted')]/../following-sibling::td//a)[1]"));
	}

	// Business Date text box
	public WebElement businessDateTextBox() {
		return driver.findElement(By.id("dc_biz_date_picker_input"));
	}

	// Business date display
	public WebElement businessDateDisplayed() {
		return driver.findElement(By.id("dc_biz_date_picker_input"));
	}

	// Business Date text
	public WebElement businessDateText() {
		return driver
				.findElement(By
						.xpath("//div[@id='business_date_picker_div']//div[@class='form-group']//strong"));
	}

	public String[] moveToDate(String noOfDays) {
		// Select the date picker button
		datePickerButton().click();
		Reporter.log("Click on Date Picker Button<br>");
		SimpleDateFormat date = new SimpleDateFormat("dd");
		SimpleDateFormat month = new SimpleDateFormat("MMMM");
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, Integer.parseInt(noOfDays));
		Date expectedCalender = calendar.getTime();
		String expectedDate = date.format(expectedCalender).replaceFirst(
				"^0+(?!$)", "");
		String expectedMonth = month.format(expectedCalender);
		String expectedYear = year.format(expectedCalender);
		Date currentCalender = new Date();
		String currentMonth = month.format(currentCalender);
		String currentYear = year.format(currentCalender);
		int yearDiff = Integer.parseInt(expectedYear)
				- Integer.parseInt(currentYear);
		String monthIndex = Integer.toString(getMonthIndex(expectedMonth));
		int diffInIndex = (getMonthIndex(expectedMonth) - getMonthIndex(currentMonth))
				+ yearDiff * 12;
		moveToCorrectMonth(diffInIndex);
		return new String[] { monthIndex, expectedDate };
	}

	public int getMonthIndex(String month) {
		int monthIndex = 0;
		switch (month.toUpperCase()) {
		case "JANUARY":
			monthIndex = 0;
			break;
		case "FEBRUARY":
			monthIndex = 1;
			break;
		case "MARCH":
			monthIndex = 2;
			break;
		case "APRIL":
			monthIndex = 3;
			break;
		case "MAY":
			monthIndex = 4;
			break;
		case "JUNE":
			monthIndex = 5;
			break;
		case "JULY":
			monthIndex = 6;
			break;
		case "AUGUST":
			monthIndex = 7;
			break;
		case "SEPTEMBER":
			monthIndex = 8;
			break;
		case "OCTOBER":
			monthIndex = 9;
			break;
		case "NOVEMBER":
			monthIndex = 10;
			break;
		case "DECEMBER":
			monthIndex = 11;
			break;
		default:
			break;
		}
		return monthIndex;
	}

	private void moveToCorrectMonth(int diffInIndex) {
		if (diffInIndex > 0) {
			for (int noOfClicks = 0; noOfClicks < diffInIndex; noOfClicks++) {
				nextMonthBtn().click();
				Reporter.log("Click on Next month button<br>");
			}
		} else if (diffInIndex < 0) {
			for (int noOfClicks = 0; noOfClicks > diffInIndex; noOfClicks--) {
				previousMonthBtn().click();
				Reporter.log("Click on Previous month button<br>");
				
			}
		} else if (diffInIndex == 0) {
			// Do nothing
			Reporter.log("No change in month<br>");
		}
	}

	public void selectDate(String date) throws InterruptedException {
		String expectedDate[] = moveToDate(date);
		// Select the date
		
		wait.until(ExpectedConditions.elementToBeClickable(driver
				.findElement(By.xpath("//td[@data-month='" + expectedDate[0]
						+ "' and @data-date='" + expectedDate[1] + "']"))));
		
		driver.findElement(
				By.xpath("//td[@data-month='" + expectedDate[0]
						+ "' and @data-date='" + expectedDate[1] + "']"))
				.click();
		Reporter.log("Click on the date " + expectedDate[1] + "<br>");
	}

	public boolean isDCDPage() throws InterruptedException {
		Reporter.log("Checking whether DCD Page was displayed<br>");
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
				.xpath("//div/label/strong[contains(text(),'Drawer')]")));
		return driver.findElement(
				By.xpath("//div/label/strong[contains(text(),'Drawer')]"))
				.isDisplayed();
	}

	public boolean isStoreNumberFieldInDCD() {
		Reporter.log("Checking whether Store number was displayed<br>");
		return storeNumberField().isDisplayed();
	}

	public boolean isRegisterFields() {
		Reporter.log("Checking whether Register fields were displayed<br>");
		return wait.until(ExpectedConditions.visibilityOf(registerField()))
				.isDisplayed();
	}

	public boolean isTypeField() {
		Reporter.log("Checking whether Type field was displayed<br>");
		return wait.until(ExpectedConditions.visibilityOf(Type_Field))
				.isDisplayed();
	}

	public boolean isCloseTimeField() {
		Reporter.log("Checking whether close time was displayed<br>");
		return wait.until(ExpectedConditions.visibilityOf(Close_Time_Field))
				.isDisplayed();
	}

	public boolean isManagerField() {
		Reporter.log("Checking whether Manager field was displayed<br>");
		return wait.until(ExpectedConditions.visibilityOf(Manager_Field))
				.isDisplayed();
	}

	public boolean isCashierField() {
		Reporter.log("Checking whether cashier field was displayed<br>");
		return wait.until(ExpectedConditions.visibilityOf(Cashier_Field))
				.isDisplayed();
	}

	public boolean isDepositCodeField() {
		Reporter.log("Checking whether Deposit code was displayed<br>");
		return wait.until(ExpectedConditions.visibilityOf(Deposit_Code_Field))
				.isDisplayed();
	}

	public boolean isStatusField() {
		Reporter.log("Checking whether status was displayed<br>");
		return wait.until(ExpectedConditions.visibilityOf(Status_Field))
				.isDisplayed();
	}

	public boolean isSalesField() {
		Reporter.log("Checking whether sales field was displayed<br>");
		return wait.until(ExpectedConditions.visibilityOf(Sales_Field))
				.isDisplayed();
	}

	public String getStoreNumber() {
		Reporter.log("Retrieving store number<br>");
		return storeNumberField().getText();
	}

	public String getDefaultDate() {
		Reporter.log("Getting the default date<br>");
		return businessDateDisplayed().getAttribute("value");
	}

	public boolean isBusinessDateTextBoxDisplayed() throws InterruptedException {
		Reporter.log("Checking whether business date text box was displayed<br>");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.id("dc_biz_date_picker_input")));
		return driver.findElement(By.id("dc_biz_date_picker_input"))
				.isDisplayed();
	}

	public boolean isDatePickerButtonDisplayed() {
		Reporter.log("Checking whether date pickr buttton was displayed<br>");
		return wait.until(ExpectedConditions.visibilityOf(datePickerButton()))
				.isDisplayed();
	}

	public String getCurrentMonth() {
		Reporter.log("Retrieving the current month from the date picker<br>");
		return wait.until(ExpectedConditions.visibilityOf(currentMonth()))
				.getText();
	}

	public String getExpectedDate(String noOfDays) {
		SimpleDateFormat expected = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, Integer.parseInt(noOfDays));
		Date expectedCalender = calendar.getTime();
		String expectedDate = expected.format(expectedCalender);
		return expectedDate;
	}

	public boolean isDrawerCountDetailsDisplayed(String date) {
		Reporter.log("Checking whether Drawer count details was displayed<br>");
		return wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//table[@id='drawer_counts_table']//span[contains(text(),'"
								+ date + "')]"))).isDisplayed();
	}

	public boolean isDrawerCountedCashField() {
		Reporter.log("Check Whether Drawer Couted Cash Field is Available on screen<br> ");
		return wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//input[@id='dc_counted_cash_input']")))
				.isDisplayed();
	}

	public boolean isPreparerField() {
		Reporter.log("Check Whether Preparer Field is Available on DCD Detail screen<br> ");
		 wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By
						.xpath("//span[contains(text(),'Drawer Bank')]"))));
		 return driver.findElement(By.xpath("//span[contains(text(),'Drawer Bank')]")).isDisplayed();
				
	}

	public boolean isDrawerBankField() {
		Reporter.log("Check Whether Drawer Bank Field is Available on DCD Detail screen<br> ");
		return wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Preparer')]")))
				.isDisplayed();
	}

	public boolean isCurrentForeverField() {
		Reporter.log("Check Whether Current Forever Field is Available on DCD Detail screen<br> ");
		return wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Current Forever')]")))
				.isDisplayed();
	}

	public boolean isForeverDifferenceField() {
		Reporter.log("Check Whether Forever Difference Field is Available on DCD Detail screen<br> ");
		return wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Forever Difference')]")))
				.isDisplayed();
	}

	public boolean isPOSOverringsField() {
		Reporter.log("Check Whether POS Overrings Field is Available on DCD Detail screen<br> ");
		return wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'POS Overrings')]")))
				.isDisplayed();
	}

	public boolean isCashRefundsField() {
		Reporter.log("Check Whether Forever Difference Field is Available on DCD Detail screen<br> ");
		return wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Cash Refunds')]")))
				.isDisplayed();
	}

	public boolean isManualRefundsOrOverrings() {
		Reporter.log("Check Whether Forever Difference Field is Available on DCD Detail screen<br> ");
		return wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Manual Refund/Overrings')]")))
				.isDisplayed();
	}

	public boolean isBillableSalesField() {
		Reporter.log("Check Whether Billable Sales Field is Available on DCD Detail screen<br> ");
		return wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Billable Sales')]")))
				.isDisplayed();
	}

	public boolean isNonTaxableSalesField() {
		Reporter.log("Check Whether Non Taxable Sales Field is Available on DCD Detail screen<br> ");
		return wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Non Taxable Sales')]")))
				.isDisplayed();
	}

	public boolean isCashlessSales() {
		Reporter.log("Check Whether Non Taxable Sales Field is Available on DCD Detail screen<br> ");
		return wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Cashless Sales')]")))
				.isDisplayed();

	}

	public boolean isGiftCertRedeemed() {
		Reporter.log("Check Whether Gift Cert.Redeemed Field is Available on DCD Detail screen<br> ");
		return wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Gift Cert. Redeemed')]")))
				.isDisplayed();

	}

	public boolean isArchCardRedeemed() {
		Reporter.log("Check Whether Arch Card Redeemed Field is Available on DCD Detail screen<br> ");
		return wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Arch Card Redeemed')]")))
				.isDisplayed();
	}

	public boolean isSkimsField() {
		Reporter.log("Check Whether Skims Field is Available on DCD Detail screen<br> ");
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By
						.xpath("//span[contains(text(),'Skims')]"))));
		return driver.findElement(By.xpath("//span[contains(text(),'Skims')]")).isDisplayed();
	}

	public boolean isExpectedDrawerCashField() {
		Reporter.log("Check Whether Expected Drawer Cash Field is Available on DCD Detail screen<br> ");
		return wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Expected Drawer Cash')]")))
				.isDisplayed();
	}

	public boolean isForeignCurrencyinUSDField() {
		Reporter.log("Check Whether  Field Foreign Currency(in USD) is Available on DCD Detail screen<br> ");
		return wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Foreign Currency(in USD)')]")))
				.isDisplayed();
	}

	public boolean drawerCountCashField() {
		Reporter.log("Check Whether  Field Drawer Count Cash is Available on DCD Detail screen<br> ");
		return wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Drawer Count Cash')]")))
				.isDisplayed();
	}

	public boolean isCashOverShortField() {
		Reporter.log("Check Whether  Field Cash Over/Short is Available on DCD Detail screen<br> ");
		return wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Cash Over/Short')]")))
				.isDisplayed();
	}

	public boolean isOtherReceiptsField() {
		Reporter.log("Check Whether  Field OtherReceiptsis Available on DCD Detail screen<br> ");
		return wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Other Receipts')]")))
				.isDisplayed();
	}

	public boolean isTransactionCountField() {
		Reporter.log("Check Whether  Field Transaction Count Available on DCD Detail screen<br> ");
		return wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Transaction Count')]")))
				.isDisplayed();
	}

	public boolean isAverageCheckField() {
		Reporter.log("Check Whether  Field Average Check Available on DCD Detail screen<br> ");
		return wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Average Check')]")))
				.isDisplayed();
	}

	public boolean isEmployeeMealsField() {
		Reporter.log("Check Whether  Employee Meals Available on DCD Detail screen<br> ");
		return wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Employee Meals')]")))
				.isDisplayed();
	}

	public boolean isManagerMeals() {
		Reporter.log("Check Whether  Manager Meals Available on DCD Detail screen<br> ");
		return wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Manager Meals')]")))
				.isDisplayed();
	}

	public boolean isBeforeTREDField() {
		Reporter.log("Check Whether  Before-TRED Available on DCD Detail screen<br> ");
		return wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Before-TRED')]")))
				.isDisplayed();
	}

	public boolean isAfterTREDField() {
		Reporter.log("Check Whether  After-TRED Available on DCD Detail screen<br> ");
		return wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'After-TRED')]")))
				.isDisplayed();
	}

	public boolean isPromoField() {
		Reporter.log("Check Whether  Promo Field Available on DCD Detail screen When user is giving Promo Order Faht transaction<br> ");
		return wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//span[contains(text(),'Promo')]")))
				.isDisplayed();
	}

	public boolean isDateGreyedOut(String noOfDays) {
		SimpleDateFormat date = new SimpleDateFormat("dd");
		SimpleDateFormat month = new SimpleDateFormat("MMMM");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, Integer.parseInt(noOfDays));
		Date expectedCalender = calendar.getTime();
		String expectedDate = date.format(expectedCalender).replaceFirst(
				"^0+(?!$)", "");
		String expectedMonth = month.format(expectedCalender);
		String monthIndex = Integer.toString(getMonthIndex(expectedMonth));
		Reporter.log("Checking whether the date was greyed out<br>");
		return wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By
						.xpath("//td[(contains(@class,'disabled')) and @data-month='"
								+ monthIndex
								+ "' and @data-date='"
								+ expectedDate + "']")))).isDisplayed();
	}

	public boolean unCountedToInprogress(String envelopeID) throws InterruptedException {
		boolean executionResult = true;
		
		wait.until(ExpectedConditions.visibilityOf(pencil_Icon_Uncounted()));
		List<WebElement> pencilElements = driver
				.findElements(By
						.xpath("//span[contains(text(),'Uncounted')]/../following-sibling::td//a"));
		for (int count = 0; count < pencilElements.size(); count++) {
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("(//span[contains(text(),'Uncounted')]/../following-sibling::td//a)["+ (count + 1) + "]")));
			driver.findElement(
					By.xpath("(//span[contains(text(),'Uncounted')]/../following-sibling::td//a)["
							+ (count + 1) + "]")).click();
			Reporter.log("OPen the " + (count + 1) + " entry in DCD<br>");
			wait.until(ExpectedConditions.elementToBeClickable(By
					.id("env_id_input")));
			driver.findElement(By.id("env_id_input")).click();
			System.out.println("Old Env ID in "
					+ (count + 1)
					+ " : "
					+ driver.findElement(By.id("env_id_input")).getAttribute(
							"value"));
			driver.findElement(By.id("env_id_input")).clear();
			driver.findElement(By.id("env_id_input")).sendKeys(Keys.BACK_SPACE,
					envelopeID);
			Reporter.log("Enter Envelope ID as " + envelopeID + "<br>");
			System.out.println("New Env ID entered in " + (count + 1) + " : "
					+ envelopeID);
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@class='pull-right']/input[@value='Save']")));
			driver.findElement(
					By.xpath("//div[@class='pull-right']/input[@value='Save']"))
					.click();
			Reporter.log("Click on Save Button<br>");
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("(//span[contains(text(),'Inprogress')])["
							+ (count + 1) + "]")));
			String updatedStatus = driver.findElement(
					By.xpath("(//span[contains(text(),'Inprogress')])["
							+ (count + 1) + "]")).getText();
			System.out.println("Status of " + (count + 1) + ": "
					+ updatedStatus);
			if (updatedStatus.equalsIgnoreCase("Inprogress")) {
				executionResult = true;
			} else {
				executionResult = false;
			}
			Reporter.log("Verify the status of envelope ID after saving.<br>Expected: Inprogress, Actual: "
					+ updatedStatus + "<br>");
		}
		return executionResult;
	}

	public boolean inprogressToCounted(String envelopeID)
			throws InterruptedException {
		boolean executionResult = true;
		wait.until(ExpectedConditions.visibilityOf(pencil_Icon_Inprogress()));
		List<WebElement> pencilElements = driver
				.findElements(By
						.xpath("//span[contains(text(),'Inprogress')]/../following-sibling::td//a"));
		for (int count = 0; count < pencilElements.size(); count++) {
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("(//span[contains(text(),'Inprogress')]/../following-sibling::td//a)["
							+ (count + 1) + "]")));
			driver.findElement(
					By.xpath("(//span[contains(text(),'Inprogress')]/../following-sibling::td//a)["
							+ (count + 1) + "]")).click();
			Reporter.log("Open the " + (count + 1) + " entry in DCD<br>");
			wait.until(ExpectedConditions.elementToBeClickable(By
					.id("env_id_input")));
			driver.findElement(By.id("env_id_input")).click();
			driver.findElement(By.id("env_id_input")).clear();
			driver.findElement(By.id("env_id_input")).sendKeys(Keys.BACK_SPACE,
					envelopeID);
			Reporter.log("Enter Envelope ID as " + envelopeID + "<br>");
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@class='pull-right']/input[@value='Finalize']")));
			driver.findElement(
					By.xpath("//div[@class='pull-right']/input[@value='Finalize']"))
					.click();
			Reporter.log("Click on Finalize Button<br>");
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='warning_modal_body']//input[@id='continue']")));
			driver.findElement(By.xpath("//div[@id='warning_modal_body']//input[@id='continue']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("(//span[contains(text(),'Counted')])["
							+ (count + 1) + "]")));
			String updatedStatus = driver.findElement(
					By.xpath("(//span[contains(text(),'Counted')])["
							+ (count + 1) + "]")).getText();
			if (updatedStatus.equalsIgnoreCase("Counted")) {
				executionResult = true;
			} else {

				executionResult = false;

			}

			Reporter.log("Verify the status of envelope ID after saving.<br>Expected: Counted, Actual: "
					+ updatedStatus + "<br>");

		}

		return executionResult;

	}

	public boolean countedToInprogress(String drawerCountedCash,
			String envelopeID) {
		boolean executionResult = true;
		wait.until(ExpectedConditions.visibilityOf(pencil_Icon_Counted()));
		List<WebElement> pencilElements = driver
				.findElements(By
						.xpath("//span[contains(text(),'Counted')]/../following-sibling::td//a"));
		for (int count = 0; count < pencilElements.size(); count++) {
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("(//span[contains(text(),'Counted')]/../following-sibling::td//a)["
							+ (count + 1) + "]")));
			driver.findElement(
					By.xpath("(//span[contains(text(),'Counted')]/../following-sibling::td//a)["
							+ (count + 1) + "]")).click();
			Reporter.log("Open the " + (count + 1) + " entry in DCD<br>");
			wait.until(ExpectedConditions.elementToBeClickable(By
					.id("dc_counted_cash_input")));
			driver.findElement(By.id("dc_counted_cash_input")).click();
			driver.findElement(By.id("dc_counted_cash_input")).clear();
			driver.findElement(By.id("env_id_input")).sendKeys(Keys.BACK_SPACE,
					drawerCountedCash);
			Reporter.log("Enter Drawer Counted Cash as" + drawerCountedCash
					+ "<br>");
			wait.until(ExpectedConditions.elementToBeClickable(By
					.id("env_id_input")));
			driver.findElement(By.id("env_id_input")).click();
			driver.findElement(By.id("env_id_input")).clear();
			driver.findElement(By.id("env_id_input")).sendKeys(Keys.BACK_SPACE,
					envelopeID);
			Reporter.log("Enter Envelope ID as " + envelopeID + "<br>");
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@class='pull-right']/input[@value='Save']")));
			driver.findElement(
					By.xpath("//div[@class='pull-right']/input[@value='Save']"))
					.click();
			Reporter.log("Click on Save Button<br>");
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("(//span[contains(text(),'Inprogress')])["
							+ (count + 1) + "]")));
			String updatedStatus = driver.findElement(
					By.xpath("(//span[contains(text(),'Inprogress')])["
							+ (count + 1) + "]")).getText();
			if (updatedStatus.equalsIgnoreCase("Inprogress")) {
				executionResult = true;
			} else {
				executionResult = false;
			}
			Reporter.log("Verify the status of envelope ID after saving.<br>Expected: Counted, Actual: "
					+ updatedStatus + "<br>");
		}
		return executionResult;
	}

	public boolean clickOnInprogressStatusRecords() throws InterruptedException {
		boolean executionResult = true;
		
		wait.until(ExpectedConditions.visibilityOf(pencil_Icon_Inprogress()));
		List<WebElement> pencilElements = driver
				.findElements(By
						.xpath("//span[contains(text(),'Inprogress')]/../following-sibling::td//a"));
		for (int count = 0; count < pencilElements.size(); count++) {
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("(//span[contains(text(),'Inprogress')]/../following-sibling::td//a)["
							+ (count + 1) + "]")));
			driver.findElement(
					By.xpath("(//span[contains(text(),'Inprogress')]/../following-sibling::td//a)["
							+ (count + 1) + "]")).click();
			Reporter.log("OPen the " + (count + 1) + " entry in DCD<br>");
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@class='pull-right']/input[@value='Save']")));
			driver.findElement(
					By.xpath("//div[@class='pull-right']/input[@value='Save']"))
					.click();
			Reporter.log("Click on Save Button<br>");
		}
		return executionResult;
	}

	public boolean shiftManagerOption() throws InterruptedException {
		boolean executionResult = true;
		
		driver.findElement(By.id("shift_mngr_input")).click();
		Select select = new Select(
				driver.findElement(By.id("shift_mngr_input")));
		select.selectByIndex(1);
		System.out.println(select.getFirstSelectedOption().getText());
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[@class='pull-right']/input[@value='Save']")));
		driver.findElement(
				By.xpath("//div[@class='pull-right']/input[@value='Save']"))
				.click();
		return executionResult;

	}

	public boolean cashierOption() {
		boolean executionResult = true;
		driver.findElement(By.id("cashier_input")).click();
		Select select = new Select(driver.findElement(By.id("cashier_input")));
		select.selectByIndex(1);
		System.out.println(select.getFirstSelectedOption().getText());
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[@class='pull-right']/input[@value='Save']")));
		driver.findElement(
				By.xpath("//div[@class='pull-right']/input[@value='Save']"))
				.click();
		return executionResult;

	}
	public boolean selectDepositCode(){
		boolean executionResult = true;
		driver.findElement(By.id("deposit_code_input")).click();
		Select select = new Select(driver.findElement(By.id("deposit_code_input")));
		select.selectByIndex(1);
		System.out.println(select.getFirstSelectedOption().getText());
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[@class='pull-right']/input[@value='Save']")));
		driver.findElement(
				By.xpath("//div[@class='pull-right']/input[@value='Save']"))
				.click();
		return executionResult;
	}

	public WebElement manualRefundsAndOverrings(String manualRefunds) {
		driver.findElement(By.id("co_adj_ref_ovr_amt_input")).click();
		driver.findElement(By.id("co_adj_ref_ovr_amt_input")).clear();
		driver.findElement(By.id("co_adj_ref_ovr_amt_input")).sendKeys(
				Keys.BACK_SPACE, manualRefunds);
		Reporter.log("Enter Envelope ID as " + manualRefunds + "<br>");
		return wait.until(ExpectedConditions.elementToBeClickable(By
				.id("co_adj_ref_ovr_amt_input")));

	}

	public WebElement giftCertificatesQuantity(String giftCertificate) {
		driver.findElement(By.id("co_adj_rdm_gct_amt_input")).click();
		driver.findElement(By.id("co_adj_rdm_gct_amt_input")).clear();
		driver.findElement(By.id("co_adj_ref_ovr_amt_input")).sendKeys(
				Keys.BACK_SPACE, giftCertificate);
		Reporter.log("Enter Envelope ID as " + giftCertificate + "<br>");
		return wait.until(ExpectedConditions.elementToBeClickable(By
				.id("co_adj_ref_ovr_amt_input")));

	}
}