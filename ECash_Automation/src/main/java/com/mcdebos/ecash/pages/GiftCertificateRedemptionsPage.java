package com.mcdebos.ecash.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class GiftCertificateRedemptionsPage extends Common {

	public GiftCertificateRedemptionsPage(WebDriver driver) {

		super(driver);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//nav[@id='menu']//a[text()='Gift Certificate Redemption']")
	public WebElement giftCertificateRedemptionTab;

	@FindBy(xpath = "//button[@id='htmlButton' and @value='Create New Ticket']']")
	public WebElement createNewTciketButton;

	@FindBy(xpath = "//select[@id='gift_certificates_date_range']")
	public WebElement dateRangeDropDown;

	@FindBy(xpath = "//input[@id='start_date_picker_input']")
	public WebElement startDateCalendarWidget;

	@FindBy(xpath = "//input[@id='end_date_picker_input']")
	public WebElement endDateCalendarWidget;

	@FindBy(xpath = "//select[@id = 'preparer']")
	public WebElement preparerDropDown;

	@FindBy(xpath = "//eb-button[@value='Show Results']")
	public WebElement showResultsButton;

	@FindBy(xpath = "//table[@id='ticketTable']//th[text()='All']")
	public WebElement allButton;

	@FindBy(xpath = "//table[@id='ticketTable']//label[text()='Ticket #']")
	public WebElement ticketNumberButton;

	@FindBy(xpath = "//table[@id='ticketTable']//label[text()='Creation Date']")
	public WebElement creationDateButton;

	@FindBy(xpath = "//table[@id='ticketTable']//label[text()='Start Date']")
	public WebElement startDateButton;

	@FindBy(xpath = "//table[@id = 'ticketTable']//label[text()='End Date']")
	public WebElement endDateButton;

	@FindBy(xpath = "//table[@id = 'ticketTable']//label[text()='Preparer']")
	public WebElement preparerButton;

	@FindBy(xpath = "//table[@id = 'ticketTable']//label[text()='Amount']")
	public WebElement amountButton;

	@FindBy(xpath = "(//div[@class='xdsoft_datepicker active'])[5]" + "//button[@class='xdsoft_prev']")
	public WebElement startDateCalenderWidgetPreviousButton;

	@FindBy(xpath = "(//div[@class='xdsoft_datepicker active'])[5]" + "//button[@class='xdsoft_next']")
	public WebElement startDateCalenderWidgetNextButton;

	@FindBy(xpath = "(//div[@class='xdsoft_datepicker active'])[6]" + "//button[@class='xdsoft_prev']")
	public WebElement endDateCalenderWidgetPreviousButton;

	@FindBy(xpath = "(//div[@class='xdsoft_datepicker active'])[6]" + "//button[@class='xdsoft_next']")
	public WebElement endDateCalenderWidgetNextButton;

	public void clickOnViewButton(int index) {

		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.xpath("//table[@id='ticketTable']/tbody/tr[" + index + "]/td[9]")))
				.perform();

		action.click(driver.findElement(By.xpath("//table[@id='ticketTable']/tbody/tr[" + index + "]/td[9]")))
				.perform();

		Reporter.log("View Button clicked<br>");

	}

	public void openDateRangeDropDown() {
		dateRangeDropDown.click();
		Reporter.log("Date Range Drop Down Clicked and opened<br>");
	}

	public void openStartDateCalenderWidget() {
		startDateCalendarWidget.click();
		Reporter.log("Start Date Calender Widget was Clicked and Opened");
	}

	public void openEndDateCalenderWidget() {
		endDateCalendarWidget.click();
		Reporter.log("End Date Calender Widget was Clicked and Opened");
	}

	public void clickGiftCertificatesRedemptionTab() {
		giftCertificateRedemptionTab.click();
		Reporter.log("Gift Certificate Redemption Tab was Clicked and Opened");
	}

}
