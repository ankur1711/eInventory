package com.mcdebos.ecash.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

public class HomePage extends Common {

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// User Icon
	@FindBy(xpath = "//i[@class='user-settings-icon']")
	public WebElement userIcon;

	// Title link
	@FindBy(xpath = "//a[contains(@class,'navbar-brand') and contains(text(),'eProfitability')]")
	WebElement eProfitablityLink;

	// Inventory Options Drop Down
	@FindBy(xpath = "//a[contains(@class,'dropdown') and contains(text(),'Inventory')]")
	WebElement inventoryDropDown;

	// ECash Options Drop Down
	@FindBy(xpath = "//a[contains(@class,'dropdown') and contains(text(),'Cash')]")
	WebElement eCashDropDown;

	// Logs Selection Drop Down
	@FindBy(xpath = "//a[contains(@class,'dropdown') and contains(text(),'Logs')]")
	WebElement logsDropDown;

	// Store Selection Drop Down
	@FindBy(xpath = "//i[@class='locations-icon']")
	WebElement storeDropDown;

	// Notices Icon
	@FindBy(id = "status_notice")
	WebElement noticesIcon;

	// Help Icon
	@FindBy(id = "status_help")
	WebElement helpIcon;

	// Menu Icon
	WebElement menuIcon() {
		return driver.findElement(By.id("menu-icon"));
	}

	// Other Cash Functions Menu Icon
	WebElement otherCashFunctionsMenuIcon() {
		return driver.findElement(By
				.xpath("//span[text()='Other Cash Functions']/../a"));
	}

	// Cash Management Link
	WebElement cashManagementLink() {
		return driver
				.findElement(By
						.xpath("//nav[@id='menu']//span[contains(text(),'Cash Management')]/../a"));
	}

	// Drawer Countdown link
	@FindBy(xpath = "//a[text()='Drawer Countdown']")
	WebElement drawerCountDownLink;

	// Select area outside to close the menu icon
	WebElement goToMainMenuListIcon() {
		return driver.findElement(By.xpath("//a[@class='mm-prev mm-btn']"));
	}

	// Select area outside to close the menu icon
	WebElement closeMenuIcon() {
		return driver.findElement(By.id("mm-blocker"));
	}

	public void selectUser(String userNumber) {
		userIcon.click();
		Reporter.log("Click on User Drop Down<br>");
		Reporter.log("Select the User as " + userNumber + "<br>");
		driver.findElement(
				By.xpath(".//*[@id='user-settings-dropdown']//a[contains(text(),'"
						+ userNumber + "')]")).click();
	}

	public void selectDrawerCountDown() {
		Reporter.log("Click on Menu Icon");
		menuIcon().click();
		Reporter.log("Wait until Cash Management Option is enabled");
		wait.until(ExpectedConditions
				.elementToBeClickable(cashManagementLink()));
		Reporter.log("Click on Cash Management Option");
		cashManagementLink().click();
		Reporter.log("Click on Drawer CountDown");
		wait.until(ExpectedConditions.elementToBeClickable(drawerCountDownLink));
		// Actions act = new Actions(driver);
		// act.moveToElement(drawerCountDownLink).perform();
		// act.click(drawerCountDownLink);
		// act.build().perform();
		drawerCountDownLink.click();
	}

	public void selectStore(String storeNumber) {
		storeDropDown.click();
		Reporter.log("Click on Store Dropdown<br>");
		Reporter.log("Select the Store as " + storeNumber + "<br>");
		driver.findElement(
				By.xpath(".//*[@id='store-locations-dropdown']//a[contains(text(),'"
						+ storeNumber + "')]")).click();
		sleep(1000);
	}

	public boolean isStoreAvailable(String storeNumber) {
		storeDropDown.click();
		Reporter.log("Click on Store Dropdown<br>");
		Reporter.log("Checking whether the store " + storeNumber
				+ " was available<br>");
		return driver
				.findElement(
						By.xpath("//*[@id='store-locations-dropdown']//a[contains(text(),'"
								+ storeNumber + "')]")).isDisplayed();
	}

	public void selectECashOption(String eCashOption)
			throws InterruptedException {
		Reporter.log("Click on Menu Icon<br>");
		wait.until(ExpectedConditions.elementToBeClickable(menuIcon()));
		menuIcon().click();
		wait.until(ExpectedConditions
				.elementToBeClickable(cashManagementLink()));
		Reporter.log("Click on Cash Management Menu<br>");
		cashManagementLink().click();
		Reporter.log("Verifying whether the cash option '" + eCashOption
				+ "' was present in the dropdown<br>");

		driver.findElement(
				By.xpath("//a[contains(text(),'" + eCashOption + "')]"))
				.isDisplayed();
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By
						.xpath("//a[text()='" + eCashOption + "']"))));
		Reporter.log("Select the Cash management option as " + eCashOption
				+ "<br>");
		driver.findElement(By.xpath("//a[text()='" + eCashOption + "']"))
				.click();
	}

	public void openOtherCashFunctionsMenu() {
		Reporter.log("Click on Menu Icon<br>");
		wait.until(ExpectedConditions.elementToBeClickable(menuIcon()));
		menuIcon().click();
		Reporter.log("Wait until Cash Management Option is enabled<br>");
		wait.until(ExpectedConditions
				.elementToBeClickable(cashManagementLink()));
		Reporter.log("Click on Cash Management Option<br>");
		cashManagementLink().click();
		Reporter.log("Click on Other Cash Functions Menu<br>");
		wait.until(ExpectedConditions
				.elementToBeClickable(otherCashFunctionsMenuIcon()));
		otherCashFunctionsMenuIcon().click();
		Reporter.log("Other Cash Functions Menu opened<br>");
	}

	/**
	 * Used to select the option from the Other Cash Functions Menu
	 * 
	 * @param optionName
	 *            - used as a reference for the option name
	 */
	public void selectOtherCashFunctionsOptions(String optionName) {
		Reporter.log("Click on " + optionName + " Icon<br>");
		wait.until(ExpectedConditions
				.elementToBeClickable(otherCashFunctionOptionLink(optionName)));
		otherCashFunctionOptionLink(optionName).click();
		Reporter.log(optionName + " Link Clicked<br>");
	}

	/**
	 * Used to click on the other cash options menu options by its name
	 * 
	 * @param optionName
	 *            - used as a reference for the option name
	 * 
	 * @return - the element of the option name
	 */
	public WebElement otherCashFunctionOptionLink(String optionName) {
		return driver.findElement(By.xpath("//a[text()='" + optionName + "']"));
	}
	
	
	/*
	 * public boolean isECashOptionAvailable(String eCashOption) {
	 * Reporter.log("Click on Menu Icon<br>"); menuIcon().click();
	 * wait.until(ExpectedConditions
	 * .elementToBeClickable(cashManagementLink()));
	 * Reporter.log("Click on Cash Management Menu<br>");
	 * cashManagementLink().click();
	 * Reporter.log("Verifying whether the cash option '" + eCashOption +
	 * "' was present in the dropdown<br>"); boolean result =
	 * driver.findElement( By.xpath("//a[contains(text(),'" + eCashOption +
	 * "')]")) .isDisplayed(); goToMainMenuListIcon().click();
	 * closeMenuIcon().click(); return result; }
	 */
}
