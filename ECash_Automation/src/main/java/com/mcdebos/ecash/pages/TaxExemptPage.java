package com.mcdebos.ecash.pages;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class TaxExemptPage extends Common {

	public TaxExemptPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(text(),'Tax Exempt Sales')]")
	public WebElement taxExemptSalesTab;

	@FindBy(xpath = "(//button[@value='Show Results'])[1]")
	public WebElement showResultsButton;

	@FindBy(xpath = "//input[@id='start_date_picker_input_te']")
	public WebElement startDatePicker;

	@FindBy(xpath = "//input[@id='end_date_picker_input_te']")
	public WebElement endDatePicker;

	public void clickOnShowResultsButton(int index) {
		Actions action = new Actions(driver);
		action.moveToElement(
				driver.findElement(By
						.xpath("(//button[@value='Show Results'])[" + index
								+ "]"))).perform();
		action.click(
				driver.findElement(By
						.xpath("(//button[@value='Show Results'])[" + index
								+ "]"))).perform();
		// driver.findElement(By
		// .xpath("(//button[@value='Show Results'])["+index+"]")).click();
		Reporter.log("Show Results button clicked<br>");
	}

	public WebElement taxExemptStartDatePrevButton() {
		// wait.until(ExpectedConditions)
		return driver.findElement(By
				.xpath("(//button[@class='xdsoft_prev'])[9]"));
	}

	public WebElement taxExemptStartDateNextButton() {
		return driver.findElement(By
				.xpath("(//button[@class='xdsoft_next'])[9]"));
	}

	public WebElement taxExemptEndDatePrevButton() {
		return driver.findElement(By
				.xpath("(//button[@class='xdsoft_prev'])[11]"));
	}

	public WebElement taxExemptEndDateNextButton() {
		return driver.findElement(By
				.xpath("(//button[@class='xdsoft_next'])[11]"));
	}

	public void openStartDateDatePicker() {
		startDatePicker.click();
		Reporter.log("Start Date Picker was Clicked and Opened<br>");
	}

	public void openEndDateDatePicker() {
		endDatePicker.click();
		Reporter.log("End Date Picker was Clicked and Opened<br>");
	}

	public void clickTaxExemptSales() {
		taxExemptSalesTab.click();
		Reporter.log("Tax Exempt Sales Tab Clicked and Opened<br>");
	}

	public void clickShowResultsButton() {
		showResultsButton.click();
		Reporter.log("Click on Show Results Button<br>");
		sleep(1000);
	}

	@FindBy(id = "tax_exempt_sales_date_range")
	public WebElement taxExemptSalesDateRange;

	@FindBy(xpath = "//div[@id='header-tax']")
	public WebElement autoPopulatedFields;

	@FindBy(xpath = "//div[@id='header-tax']/span")
	public WebElement taxDateAndTime;

	@Deprecated
	public void selectDateRangeForTaxExemptSales(String dateRangeOptions) {
		Reporter.log("Click on Date Range Drop Down<br>");
		wait.until(ExpectedConditions
				.elementToBeClickable(taxExemptSalesDateRange));
		taxExemptSalesDateRange.click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By
				.xpath("//select[@id='tax_exempt_sales_date_range']/option[text()='"
						+ dateRangeOptions + "']"))));
		driver.findElement(
				By.xpath("//select[@id='tax_exempt_sales_date_range']/option[text()='"
						+ dateRangeOptions + "']")).click();

	}

	public WebElement startDateTextBox() {
		return driver.findElement(By.id("start_date_picker_input_te"));
	}

	public WebElement endDateTextBox() {
		return driver.findElement(By.id("end_date_picker_input_te"));
	}

	public String getTotalTaxExemptSalesDisplayed() {
		return driver.findElement(By.xpath("//span[@id='totalTax']")).getText()
				.replace("$", "").trim();
	}

	/*
	 * public String getEndDateValue() {
	 * Reporter.log("Getting the End date<br>"); return
	 * endDateTextBox().getAttribute("value"); }
	 */

	public List<WebElement> getOptionsForDateRangeDropDown() {
		return getOptionsForDropDown("//select[@id='tax_exempt_sales_date_range']");
	}

	public void selectDateRangeFromDropDown(String dateRange) {
		Select dateRangeDropDown = new Select(driver.findElement(By
				.id("tax_exempt_sales_date_range")));
		dateRangeDropDown.selectByVisibleText(dateRange);
		// driver.findElement(By.xpath(".//*[@id='ebos_page_title']")).click();
		Reporter.log("Select Date Range as '" + dateRange
				+ "' from the drop down<br>");
	}

	public String getStartDate() {
		return getTextFromTextBox(startDateTextBox());
	}

	public String getEndDate() {
		return getTextFromTextBox(endDateTextBox());
	}

	public void selectUserID(String userId) {
		Select dateRangeDropDown = new Select(driver.findElement(By
				.id("user_te")));
		dateRangeDropDown.selectByVisibleText(userId);
		driver.findElement(By.xpath(".//*[@id='ebos_page_title']")).click();
		Reporter.log("Select User ID as '" + userId
				+ "' from the drop down<br>");
	}

	public void selectOrganizationName(String organizationName) {
		Select dateRangeDropDown = new Select(driver.findElement(By
				.id("organization_te")));
		dateRangeDropDown.selectByVisibleText(organizationName);
		driver.findElement(By.xpath(".//*[@id='ebos_page_title']")).click();
		Reporter.log("Select Organization Name as '" + organizationName
				+ "' from the drop down<br>");
	}

	public void selectDepositStatus(String depositCode) {
		Select dateRangeDropDown = new Select(driver.findElement(By
				.id("deposit_status_te")));
		dateRangeDropDown.selectByVisibleText(depositCode);
		driver.findElement(By.xpath(".//*[@id='ebos_page_title']")).click();
		Reporter.log("Select Deposit Status as '" + depositCode
				+ "' from the drop down<br>");
	}

	public void movetToTopScreen() {
		sleep(500);
		driver.findElement(By.xpath(".//*[@id='ebos_page_title']")).click();
		Reporter.log("Move to the top of the screen<br>");
	}

	@FindBy(xpath = "(//label[text()='Date & Time'])[1]")
	public WebElement taxExemptDateAndTime;

	@FindBy(xpath = "(//label[text()='Register'])[1]")
	public WebElement taxExemptRegisterField;

	@FindBy(xpath = "(//label[text()='Amount'])[1]")
	public WebElement taxExemptAmountField;

	@FindBy(xpath = "(//label[text()='User'])[1]")
	public WebElement taxExemptUserField;

	@FindBy(xpath = "(//label[text()='Organization'])[1]")
	public WebElement taxExemptOrganizationField;

	@FindBy(xpath = "(//label[contains(text(),'Deposit Status')])[2]")
	public WebElement depositStatus;

	@FindBy(xpath = "//label[text()='Tax ID Number']")
	public WebElement taxIDNumberField;

	public void isTaxExemptSalesDetailsDisplayed() {
		Reporter.log("Checking whether Drawer count details was displayed<br>");
		List<WebElement> dateElements = driver.findElements(By
				.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[2]"));
		for (int count = 0; count < dateElements.size(); count++) {
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[2]["
							+ (count + 1) + "]")));
		}
	}

	public List<WebElement> getListOfDateElements() {
		return driver.findElements(By
				.xpath("(//table[@id='tax_exempt_table']//tr//td[2]/span)"));
	}

	public List<WebElement> getListOfAmountElements() {
		return driver.findElements(By
				.xpath("(//table[@id='tax_exempt_table']//tr//td[4])"));
	}

	public List<WebElement> getListOfRegisterElements() {
		return driver.findElements(By
				.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[3]/span"));
	}

	/*
	 * public List<WebElement> getListOfAmountElements(){ return
	 * driver.findElement
	 * (By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[4]")); }
	 */

	public String getTotalTaxExemptValue() {
		float totalAmount = 0;
		int i = 1;
		// Get all the displayed amount for the records
		List<WebElement> amountElementsList = getListOfAmountElements();
		for (WebElement amount : amountElementsList) {
			String amountDisplayedonUIWithDollar = amount.getText();
			if (!(amountDisplayedonUIWithDollar.isEmpty() || i > amountElementsList
					.size() - 1)) {
				String amountDisplayedonUI = amountDisplayedonUIWithDollar
						.replace("$", "").trim();
				totalAmount = totalAmount
						+ Float.parseFloat(amountDisplayedonUI);
				i++;
			}
		}
		DecimalFormat df = new DecimalFormat("#.00");
		Reporter.log("Total Amount calculated was '" + df.format(totalAmount)
				+ "'<br>");
		return df.format(totalAmount);
	}

	public WebElement organizationTextBox() {
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//input[@id='tax_exempt_organization_input']")));
		return driver.findElement(By
				.xpath("//input[@id='tax_exempt_organization_input']"));
	}

	public WebElement taxIdNumberTextBox() {
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("(//input[@id='validatedInput'])[1]")));
		return driver.findElement(By
				.xpath("(//input[@id='validatedInput'])[1]"));
	}

	public WebElement contactNameTextBox() {
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//input[@id='contact_te0']")));
		return driver.findElement(By.xpath("//input[@id='contact_te0']"));

	}

	public WebElement addressLine1TextBox() {
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[@id='detail_te0']/div[2]/div[1]/div/input")));
		return driver.findElement(By
				.xpath("//div[@id='detail_te0']/div[2]/div[1]/div/input"));
	}

	public WebElement addressLine2TextBox() {
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[@id='detail_te0']/div[2]/div[3]/div/input")));
		return driver.findElement(By
				.xpath("//div[@id='detail_te0']/div[2]/div[3]/div/input"));
	}

	public WebElement cityTextBox() {
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[@id='detail_te0']/div[3]/div[1]/div/input")));
		return driver.findElement(By
				.xpath("//div[@id='detail_te0']/div[3]/div[1]/div/input"));
	}

	public WebElement zipCodeTextBox() {
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("(//input[@id='validatedInput'])[2]")));
		return driver.findElement(By
				.xpath("(//input[@id='validatedInput'])[2]"));
	}

	public WebElement stateDropDown() {
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//select[@class='ebos-input form-control']")));
		return driver.findElement(By
				.xpath("//select[@class='ebos-input form-control']"));

	}

	public WebElement emailTextBox() {
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[@id='detail_te0']/div[4]/div[1]/div/input")));
		return driver.findElement(By
				.xpath("//div[@id='detail_te0']/div[4]/div[1]/div/input"));
	}

	public WebElement phoneNumberTextBox() {
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//input[@id='0']")));
		return driver.findElement(By.xpath("//input[@id='0']"));
	}

	public WebElement enterValueInTaxIdNumber() {
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//input[@class='tx_number ebos-input form-control']")));
		return driver.findElement(By
				.xpath("//input[@class='tx_number ebos-input form-control']"));
	}

	public WebElement closeTaxExemptModularWindow() {
		return wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("(//button[@value='Close'])[1]")));

	}

	public WebElement zipCodePopUpValidation() {
		return wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[contains(text(),'Only numbers up to five digits are allowed')]")));
	}

	public WebElement submitTaxExemptSalesModularWindow() {
		Reporter.log("Submit button found");
		return wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[@id='cancel_save']/eb-button[@id='save_te']")));

	}

	public void selectState(int state) {
		Select stateDropDown = new Select(driver.findElement(By
				.xpath("//select[@class='ebos-input form-control']")));
		stateDropDown.selectByIndex(state);
		Reporter.log("Select State'" + state + "'from drop down");
	}

	public String[] moveToDate(WebElement prevMonthElement,
			WebElement nextMonthElement, String actualDateDisplayed,
			String noOfDays) throws ParseException {
		Reporter.log("Click on Date Picker Button<br>");
		SimpleDateFormat date = new SimpleDateFormat("dd");
		SimpleDateFormat month = new SimpleDateFormat("MMMM");
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, Integer.parseInt(noOfDays));
		Date expectedCalender = calendar.getTime();
		System.out.println("Expected Date : "
				+ formatter.format(expectedCalender));
		String expectedDate = date.format(expectedCalender).replaceFirst(
				"^0+(?!$)", "");
		System.out.println("Expected Date : " + expectedDate);
		String expectedMonth = month.format(expectedCalender);
		System.out.println("Expected Month : " + expectedMonth);
		String expectedYear = year.format(expectedCalender);
		System.out.println("Expected Year : " + expectedYear);
		// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Actual Date : " + actualDateDisplayed);
		Date currentCalender = formatter.parse(actualDateDisplayed);
		System.out.println("Displayed Date : "
				+ formatter.format(currentCalender));
		System.out.println("Current Date : "
				+ formatter.format(currentCalender));
		// String currentMonth = actualDateDisplayed.trim().substring(0, 2);
		String currentMonth = month.format(currentCalender);
		System.out.println("Current Month : " + currentMonth);
		String currentYear = actualDateDisplayed.trim().substring(6);
		System.out.println("Current Year : " + currentYear);
		int yearDiff = Integer.parseInt(expectedYear)
				- Integer.parseInt(currentYear);
		System.out.println("Year Diff : " + yearDiff);
		String monthIndex = Integer.toString(getMonthIndex(expectedMonth));
		System.out.println("getMonthIndex(expectedMonth) : "
				+ getMonthIndex(expectedMonth));
		System.out.println("getMonthIndex(currentMonth) : "
				+ getMonthIndex(currentMonth));
		System.out
				.println("getMonthIndex(expectedMonth) - getMonthIndex(currentMonth) : "
						+ (getMonthIndex(expectedMonth) - getMonthIndex(currentMonth)));
		int diffInIndex = (getMonthIndex(expectedMonth) - getMonthIndex(currentMonth))
				+ yearDiff * 12;
		System.out.println("diffInIndex : " + diffInIndex);
		moveToCorrectMonth(prevMonthElement, nextMonthElement, diffInIndex);
		return new String[] { monthIndex, expectedDate };
	}

	private void moveToCorrectMonth(WebElement prevMonthElement,
			WebElement nextMonthElement, int diffInIndex) {
		if (diffInIndex > 0) {
			for (int noOfClicks = 0; noOfClicks < diffInIndex; noOfClicks++) {
				nextMonthElement.click();
				Reporter.log("Click on Next month button<br>");
			}
		} else if (diffInIndex < 0) {
			for (int noOfClicks = 0; noOfClicks > diffInIndex; noOfClicks--) {
				prevMonthElement.click();
				Reporter.log("Click on Previous month button<br>");
			}
		} else if (diffInIndex == 0) {
			// Do nothing
			Reporter.log("No change in month<br>");
		}
	}

	public void selectDate(WebElement prevMonthElement,
			WebElement nextMonthElement, String actualDateDisplayed,
			String date, String noOfElement) throws InterruptedException,
			ParseException {
		String expectedDate[] = moveToDate(prevMonthElement, nextMonthElement,
				actualDateDisplayed, date);
		System.out.println("//td[@data-month='" + expectedDate[0]
				+ "' and @data-date='" + expectedDate[1] + "']");
		// Select the date
		wait.until(ExpectedConditions.elementToBeClickable(driver
				.findElement(By.xpath("(//td[@data-month='" + expectedDate[0]
						+ "' and @data-date='" + expectedDate[1] + "'])["
						+ noOfElement + "]"))));
		driver.findElement(
				By.xpath("(//td[@data-month='" + expectedDate[0]
						+ "' and @data-date='" + expectedDate[1] + "'])["
						+ noOfElement + "]")).click();
		Reporter.log("Click on the date " + expectedDate[1] + "<br>");
	}

	public List<WebElement> getListOfOrganizationName() {
		return driver.findElements(By
				.xpath("//table[@id='tax_exempt_table']//td[6]/span"));

	}

	public List<WebElement> getListOfTaxIdNumber() {
		return driver.findElements(By
				.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[14]/span"));
	}

	public List<WebElement> getListOfUserID() {
		return driver
				.findElements(By
						.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[5]/span[2]"));
	}

	public List<WebElement> getListDepositStatus() {
		return driver.findElements(By
				.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[15]"));
	}

	public List<WebElement> getListOfInprogressDepositStatus() {
		return driver.findElements(By
				.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[15]"));
	}

	public void clickOnDateandTimeAscendingButton(int index) {
		driver.findElement(
				By.xpath("(//label[text()='Date & Time']/../..//th[@aria-sort='descending'])["
						+ index + "]")).click();
		Reporter.log("Click on the Ascending Button for Date & Time <br>");
	}

	public void clickRegisterAscendingButton() {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By
				.xpath("//label[text()='Register']/../..//th[@aria-sort='ascending']"))));
		driver.findElement(
				By.xpath("//label[text()='Register']/../..//th[@aria-sort='ascending']"))
				.click();
		Reporter.log("Clicked on the Ascending Button for Register<br>");
	}

	public void clickRegisterDscendingButton() {
		driver.findElement(
				By.xpath("//label[text()='Register']/../..//th[@aria-sort='descending'][1]"))
				.click();
		Reporter.log("Click on the Dscending Button for Register");
	}

	public List<WebElement> getListOfDepositStatus() {
		return driver.findElements(By
				.xpath("//table[@id='tax_exempt_table']/tbody//td[15]"));
	}

	@Deprecated
	public WebElement editButton(int count) {
		sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("(//table[@id='tax_exempt_table']/tbody/tr/td[contains(text(),'Finalized')]/following-sibling::td/eb-button)["
						+ count + "]")));
		Reporter.log("Click on " + count + " Edit Button");
		return driver
				.findElement(By
						.xpath("(//table[@id='tax_exempt_table']/tbody/tr/td[contains(text(),'Finalized')]/following-sibling::td/eb-button)["
								+ count + "]"));
	}

	public void clickOnEditButton(int count) {
		sleep(2000);
		WebElement editButton = driver
				.findElement(By
						.xpath("(//table[@id='tax_exempt_table']//button[@value='Edit'])["
								+ count + "]"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", editButton);
		jse.executeScript("arguments[0].click();", editButton);
		Reporter.log("Click on " + count + " Edit Button");
	}

	public void clickOnEditButton(WebElement editBtn) {
		sleep(1000);
		action.moveToElement(editBtn).click().perform();
		Reporter.log("Click on Edit Button");
	}

	public WebElement taxAmount() {
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//inp;ut[@class='text-right money-format ebos-input form-control']")));
		return driver
				.findElement(By
						.xpath("//input[@class='text-right money-format ebos-input form-control']"));

	}
}