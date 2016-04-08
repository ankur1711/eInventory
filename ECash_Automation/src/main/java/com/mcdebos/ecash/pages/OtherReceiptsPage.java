package com.mcdebos.ecash.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class OtherReceiptsPage extends Common {

                public OtherReceiptsPage(WebDriver driver) {
                                super(driver);
                                PageFactory.initElements(driver, this);
                }

                @FindBy(xpath = "//a[contains(text(),'Other Receipts')]")
                public WebElement otherReceiptsTab;

                public void clickOtherReceiptsTab() {
                                otherReceiptsTab.click();
                                Reporter.log("Other Receipts Tab Clicked and Opened");
                }

}
