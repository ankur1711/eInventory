package com.mcdebos.ecash.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GroupSalesPage extends Common {

	public GroupSalesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "ebos_page_title")
	public WebElement groupSalesHeading;
}
