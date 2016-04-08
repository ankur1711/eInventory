package com.mcdebos.ecash.pages;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public abstract class Common {

	public WebDriver driver;
	public WebDriverWait wait;
	public Actions action;
    public DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    public DecimalFormat decimalFormat=new DecimalFormat();
    
	public Common(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 50);
		action = new Actions(driver);
	}

	public Common(String browser) {
		getWebDriver(browser);
	}

	public Common() {
	}

	public WebDriver getWebDriver(String browser) {
		switch (browser) {
		case "internet-explorer":
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir")
							+ "\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
		default:
			break;
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}

	public void navigate(String applicationURL) {
		driver.get(applicationURL);
		(new WebDriverWait(driver, 200)).until(ExpectedConditions
				.elementToBeClickable(By
						.xpath("//i[@class='user-settings-icon']")));
	}

	public void sleep(long seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param xPathExpression
	 * @return date range drop down option
	 */
	public List<WebElement> getOptionsForDropDown(String xPathExpression) {
		Select dropdown = new Select(driver.findElement(By
				.xpath(xPathExpression)));
		return dropdown.getOptions();
	}

	public boolean checkDropDownValues(List<WebElement> listOfOptions,
			String dropDownOption) {
		boolean resultFound = false;
		for (WebElement option : listOfOptions) {
			if (option.getText().trim().equals(dropDownOption)) {
				resultFound = true;
				Reporter.log("Option '" + dropDownOption
						+ "' was present in the drop down<br>");
			}
		}
		return resultFound;
	}

	public String getTextFromTextBox(WebElement element) {
		String resultText = null;
		resultText = element.getText();
		if (resultText.equals(null) || resultText.equals("")) {
			resultText = ((JavascriptExecutor) driver).executeScript(
					"return arguments[0].value", element).toString();
		}
		return resultText;
	}

	/*
	 * public String getTextFromAutoPopulatedFields(WebElement element){
	 * JavascriptExecutor jse = (JavascriptExecutor) driver; String
	 * textOfAutoPopulatedField = (String) jse.executeScript(“return
	 * arguments[0].value;”,autoPopulatedField);
	 * 
	 * return ;
	 * 
	 * }
	 */

	public String getDate(String noOfDays) {
		SimpleDateFormat expected = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, Integer.parseInt(noOfDays));
		Date expectedCalender = calendar.getTime();
		String expectedDate = expected.format(expectedCalender);
		return expectedDate;
	}

	public String getDateByMonths(String noOfMonths) {
		SimpleDateFormat expected = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, Integer.parseInt(noOfMonths));
		Date expectedCalender = calendar.getTime();
		String expectedDate = expected.format(expectedCalender);
		return expectedDate;
	}

	public static boolean verifyDateWithInRange(String startDateRange,
			String endDateRange, String actualDate) {
		boolean executionResult = false;
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date startDate = df.parse(startDateRange);
			Date endDate = df.parse(endDateRange);
			Date actual = df.parse(actualDate);
			if ((actual.after(startDate) && actual.before(endDate))) {
				executionResult = true;
			}
		} catch (ParseException e) {
			executionResult = false;
			Reporter.log("Unable to calculate the date ranges");
		}
		return executionResult;
	}

	public static void main(String[] args) {
		System.out.println(Common.verifyDateWithInRange("12/17/2015",
				"03/17/2016", "12/18/2015"));
	}
public static boolean compareDate(){
	return false;
	
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
}
