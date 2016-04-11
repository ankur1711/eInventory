package com.mcdebos.ecash.pages;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class GiftCertificateRedemptionsPage extends Common{
	
	public GiftCertificateRedemptionsPage(WebDriver driver){
		
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
	
	

}
