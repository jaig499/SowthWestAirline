package org.SW;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseClass {
	public static WebDriver driver;
	
	@BeforeTest
	public void openBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jai\\eclipse-workspace\\SouthWest\\driver\\chromedriver.exe");
		driver=new ChromeDriver();
	}
	
	@BeforeClass
	public static void url() {
		driver.get("https://www.southwest.com/");
	}
	
	//--------------------------------------------------
	
	public BaseClass() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="LandingAirBookingSearchForm_originationAirportCode")
	private WebElement from;
	
	@FindBy(id="LandingAirBookingSearchForm_destinationAirportCode")
	private WebElement to;

	@FindBy(xpath="(//input[@autocorrect='off'])[3]")
	private WebElement fDate;
	
	@FindBy(xpath="(//input[@autocorrect='off'])[4]")
	private WebElement tDate;
	
	@FindBy(xpath="//input[@aria-autocomplete='list']")
	private WebElement numOfPass;
	
	public WebElement getFrom() {
		return from;
	}

	public WebElement getTo() {
		return to;
	}

	public WebElement getfDate() {
		return fDate;
	}

	public WebElement gettDate() {
		return tDate;
	}

	public WebElement getNumOfPass() {
		return numOfPass;
	}
//--------------------------------------------
	
	@Test
	public static String ddfm(int row, int col) throws IOException {
		
	File f=new File("C:\\Users\\Jai\\eclipse-workspace\\SouthWest\\file\\fromTo.xlsx");
	FileInputStream fin=new FileInputStream(f);
	Workbook book=new XSSFWorkbook(fin);
	Sheet sheet= book.getSheet("Sheet1");
	
	Row r = sheet.getRow(row);
	Cell cell = r.getCell(col);
	String stringVal = cell.getStringCellValue();
	
	return stringVal;
	
	}
	
	public static void fromTo(WebElement ele, String srt) {
		ele.sendKeys(srt);
	}
	
	public static void enterKey() throws AWTException {
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public static void screenShot(String loc) throws IOException {
		
		TakesScreenshot tk=(TakesScreenshot)driver;
		File source = tk.getScreenshotAs(OutputType.FILE);
		File dest=new File(loc);
		FileUtils.copyFile(source, dest);
	}
	
	
}
