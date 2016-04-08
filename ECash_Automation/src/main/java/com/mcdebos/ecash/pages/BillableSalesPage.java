package com.mcdebos.ecash.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class BillableSalesPage extends Common {

                public BillableSalesPage(WebDriver driver) {
                                super(driver);
                                PageFactory.initElements(driver, this);
                }

                @FindBy(xpath = "//a[contains(text(),'Billable Sales')]")
                public WebElement billableSalesTab;

                public void clickBillableSalesTab() {
                                billableSalesTab.click();
                                Reporter.log("Billable Sales Tab Clicked and Opened");
                }

}
