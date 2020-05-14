package org.SW;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SouthWestAir extends BaseClass {
	
	@Test(priority=1)
	public void fill_1_from_to() throws InterruptedException, AWTException, IOException {
	
		BaseClass bs=new BaseClass();
		
		fromTo(bs.getFrom(), ddfm(1, 0));
		enterKey();
		fromTo(bs.getTo(), ddfm(1, 1));
		enterKey();
		
	}
	
	@Test(priority=2)
	public void fill_2_dateup_datedown() throws InterruptedException, IOException {
		BaseClass bs=new BaseClass();

		bs.getfDate().click();
		driver.findElement(By.id("calendar-112-2020-06-08")).click();
		
		bs.gettDate().click();
		driver.findElement(By.xpath("//button[@aria-label='Saturday, June 13 2020']")).click();
		
		Thread.sleep(2000);
		bs.getNumOfPass().click();
		
		driver.findElement(By.id("LandingAirBookingSearchForm_adultPassengersCount--item-1")).click();
		driver.findElement(By.id("LandingAirBookingSearchForm_submit-button")).click();
		
		Thread.sleep(5000);
		WebElement text = driver.findElement(By.xpath("//p[contains(text(),'Getting you to where')]"));
		
		String webText = text.getText();
		
		if(webText.startsWith("Getting")) {
			
			System.out.println("Server error occured!");
			screenShot("C:\\Users\\Jai\\Desktop\\scot\\report.png");
		}

	}

}
