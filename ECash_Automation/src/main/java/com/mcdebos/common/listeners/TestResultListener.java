package com.mcdebos.common.listeners;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.CellView;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.mcdebos.common.annotations.TestInfo;
import com.mcdebos.ecash.pages.Common;

public class TestResultListener extends TestListenerAdapter {

	private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
	WritableWorkbook writableWorkbook;
	WritableSheet writableSheet;

	@Override
	public void onStart(ITestContext testContext) {
		Reporter.setEscapeHtml(false);
		System.setProperty(ESCAPE_PROPERTY, "false");
		createExcelReport();
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		TestInfo annotaionMethod = tr.getMethod().getConstructorOrMethod()
				.getMethod().getAnnotation(TestInfo.class);
		enterTestCaseResult(annotaionMethod.userStory(),
				annotaionMethod.testCaseID(),
				annotaionMethod.testCaseDescription(), "Pass", "", "");
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		String fileName = null;
		try {
			fileName = takeSnapShot(tr.getName(), tr);
		} catch (Exception e) {
			System.out.println("Error in taking screen shot");
			e.printStackTrace();
		}
		Reporter.log("<br> <a href=\"file:///" + fileName
				+ "\">View Screenshot</a><br>");
		String testFailureReasonFull = tr.getThrowable().toString();
		String testFailureReasonPartial = testFailureReasonFull;
		if(testFailureReasonFull.contains("Build info:")) {
			testFailureReasonPartial = testFailureReasonFull.substring(0,
					testFailureReasonFull.indexOf("Build info:"));
		}		
		TestInfo annotationMethod = tr.getMethod().getConstructorOrMethod()
				.getMethod().getAnnotation(TestInfo.class);
		enterTestCaseResult(annotationMethod.userStory(),
				annotationMethod.testCaseID(),
				annotationMethod.testCaseDescription(), "Fail",
				testFailureReasonPartial, "file:///" + fileName);
		super.onTestFailure(tr);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		TestInfo annotationMethod = tr.getMethod().getConstructorOrMethod()
				.getMethod().getAnnotation(TestInfo.class);
		enterTestCaseResult(annotationMethod.userStory(),
				annotationMethod.testCaseID(),
				annotationMethod.testCaseDescription(), "Skipped", "Configuration failure", "");
		super.onTestSkipped(tr);
	}

	// @Override
	// public void onConfigurationFailure(ITestResult itr) {
	// super.onConfigurationFailure(itr);
	// TestInfo annotaionMethod =
	// itr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestInfo.class);
	// enterTestCaseResult(annotaionMethod.userStory(),
	// annotaionMethod.testCaseID(), annotaionMethod.testCaseDescription(),
	// "Configuration Failure", "", "");
	// }
	//
	// @Override
	// public void onConfigurationSkip(ITestResult itr) {
	// super.onConfigurationSkip(itr);
	// TestInfo annotaionMethod =
	// itr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestInfo.class);
	// enterTestCaseResult(annotaionMethod.userStory(),
	// annotaionMethod.testCaseID(), annotaionMethod.testCaseDescription(),
	// "Configuration Skipped", "", "");
	// }

	public String takeSnapShot(String tcName, ITestResult result)
			throws Exception {
		Object currentClass = result.getInstance();
		WebDriver driver = ((Common) currentClass).driver;
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		String fileName = System.getProperty("user.dir") + "\\ScreenShots\\"
				+ tcName + System.currentTimeMillis() + ".png";
		File DestFile = new File(fileName);
		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
		return fileName;
	}

	private String getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy");
		Date currentCalender = new Date();
		String currentDate = dateFormat.format(currentCalender);
		return currentDate;
	}

	private void createExcelReport() {
		File exlFile = new File(System.getProperty("user.dir")
				+ "//ExcelReports//Automation_Report_" + getCurrentDate()
				+ ".xls");
		if (exlFile.exists()) {
			exlFile.delete();
		}
		try {
			writableWorkbook = Workbook.createWorkbook(exlFile);
		} catch (IOException e) {
			System.out.println("Unable to create the excel workbook");
		}
		writableSheet = writableWorkbook.createSheet("Execution Report", 0);
		WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
		try {
			cellFont.setBoldStyle(WritableFont.BOLD);
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
		try {
			cellFormat.setWrap(false);
			cellFormat.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			cellFormat.setBackground(jxl.format.Colour.SKY_BLUE);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Label sNoLabel = new Label(0, 0, "S.No", cellFormat);
		Label userStoryLabel = new Label(1, 0, "User Story #", cellFormat);
		Label testCaseIDLabel = new Label(2, 0, "Test Case ID", cellFormat);
		Label testCaseDescriptionLabel = new Label(3, 0,
				"Test Case Description", cellFormat);
		Label resultLabel = new Label(4, 0, "Result", cellFormat);
		Label failureReasonLabel = new Label(5, 0, "Failure Reason", cellFormat);
		Label screenShotLabel = new Label(6, 0, "Screen Shot", cellFormat);
		try {
			writableSheet.addCell(sNoLabel);
			writableSheet.addCell(userStoryLabel);
			writableSheet.addCell(testCaseIDLabel);
			writableSheet.addCell(testCaseDescriptionLabel);
			writableSheet.addCell(resultLabel);
			writableSheet.addCell(failureReasonLabel);
			writableSheet.addCell(screenShotLabel);
			createCellView(writableSheet);
			writableWorkbook.write();
			writableWorkbook.close();
		} catch (WriteException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createCellView(WritableSheet workingSheet) {
		CellView cell;
		for (int x = 0; x < workingSheet.getColumns(); x++) {
			cell = workingSheet.getColumnView(x);
			cell.setAutosize(true);
			workingSheet.setColumnView(x, cell);
		}
	}

	private void enterTestCaseResult(String userStory, String testCaseID,
			String testCaseDescription, String result, String failureReason,
			String screenShotPath) {
		Workbook workbook = null;
		WritableWorkbook workbookCopy = null;
		try {
			workbook = Workbook.getWorkbook(new File(System
					.getProperty("user.dir")
					+ "//ExcelReports//Automation_Report_"
					+ getCurrentDate()
					+ ".xls"));
			workbookCopy = Workbook.createWorkbook(
					new File(System.getProperty("user.dir")
							+ "//ExcelReports//Temp_Automation_Report_"
							+ getCurrentDate() + ".xls"), workbook);
		} catch (BiffException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WritableSheet wSheet = workbookCopy.getSheet("Execution Report");
		int rows = wSheet.getRows();
		// int columns = wSheet.getColumns();
		WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
		try {
			cellFont.setBoldStyle(WritableFont.NO_BOLD);
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
		WritableCellFormat cellFormatPass = new WritableCellFormat(cellFont);
		WritableCellFormat cellFormatFail = new WritableCellFormat(cellFont);
		WritableCellFormat cellFormatDescription = new WritableCellFormat(
				cellFont);
		try {
			cellFormat.setWrap(true);
			cellFormat.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			cellFormatPass.setWrap(true);
			cellFormatPass.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			cellFormatPass.setBackground(jxl.format.Colour.GREEN);
			cellFormatFail.setWrap(true);
			cellFormatFail.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			cellFormatFail.setBackground(jxl.format.Colour.RED);
			cellFormatDescription.setWrap(true);
			cellFormatDescription.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Label sNoCountLabel = new Label(0, rows, Integer.toString(rows),
				cellFormat);
		Label userStoryNoLabel = new Label(1, rows, userStory, cellFormat);
		Label testCaseIDLabel = new Label(2, rows, testCaseID, cellFormat);
		Label TestCaseDescriptionLabel = new Label(3, rows,
				testCaseDescription, cellFormatDescription);
		Label resultLabel;
		if (result.toLowerCase().equals("pass")) {
			resultLabel = new Label(4, rows, result, cellFormatPass);
		} else {
			resultLabel = new Label(4, rows, result, cellFormatFail);
		}
		Label failureReasonLabel = new Label(5, rows, failureReason,
				cellFormatDescription);
		Label screenShotLabel;
		if (result.toLowerCase().equals("fail")) {
			WritableHyperlink wh;
			try {
				wh = new WritableHyperlink(6, rows, 6, rows, new URL(
						screenShotPath));
				wSheet.addHyperlink(wh);
			} catch (MalformedURLException | WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			screenShotLabel = new Label(6, rows, "View Screen Shot", cellFormat);
		} else {
			screenShotLabel = new Label(6, rows, "", cellFormat);
		}

		try {
			wSheet.addCell(sNoCountLabel);
			wSheet.addCell(userStoryNoLabel);
			wSheet.addCell(testCaseIDLabel);
			wSheet.addCell(TestCaseDescriptionLabel);
			wSheet.addCell(resultLabel);
			wSheet.addCell(failureReasonLabel);
			wSheet.addCell(screenShotLabel);
			createCellView(wSheet);
			workbook.close();
			workbookCopy.write();
			workbookCopy.close();
		} catch (WriteException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			File tempFile = new File(System.getProperty("user.dir")
					+ "//ExcelReports//Temp_Automation_Report_"
					+ getCurrentDate() + ".xls");
			workbook = Workbook.getWorkbook(tempFile);
			workbookCopy = Workbook.createWorkbook(
					new File(System.getProperty("user.dir")
							+ "//ExcelReports//Automation_Report_"
							+ getCurrentDate() + ".xls"), workbook);
			workbook.close();
			tempFile.delete();
			workbookCopy.write();
			workbookCopy.close();
		} catch (BiffException | IOException | WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestResultListener c = new TestResultListener();
		for (int i = 0; i <= 100001; i++) {
			// c.createExcelReport();
			c.enterTestCaseResult("US 567", "TC 356", "Tset ddhgfh", "Pass",
					"", "");
			c.enterTestCaseResult(
					"US 554",
					"TC 323",
					"Tset ddhgfh",
					"Fail",
					"Assertion Error",
					"file:///C:\\Users\\admin\\Documents\\eProfitabilityEcash\\eProfitabilityEcash\\eProfitability_Ecash\\ScreenShots\\pathwayToDCD1439499944447.png");
			c.enterTestCaseResult("US 565", "TC 312", "Tset ddhgfh", "Pass",
					"", "");
			c.enterTestCaseResult(
					"US 512",
					"TC 398",
					"Tset ddhgfh",
					"Fail",
					"Trigger Issue",
					"file:///C:\\Users\\admin\\Documents\\eProfitabilityEcash\\eProfitabilityEcash\\eProfitability_Ecash\\ScreenShots\\pathwayToDCD1439499944447.png");
		}

	}
}
