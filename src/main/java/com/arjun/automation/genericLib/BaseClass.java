package com.arjun.automation.genericLib;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{
	public WebDriver driver;
	public Logger logger;

	@BeforeClass
	@Parameters({ "browser", "url", "title"})
	public void setup(String browser, String url, String title)
	{
		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");
		
		driver = new Driver(driver).getDriver(browser);
		
		int maxAttempts = 3;
		int AttemptNum = 0;
		do
		{
			driver.get(url);
			AttemptNum++;
			
			if(driver.getTitle().contains(title))
			{
				logger.info(url + " loaded successfully..!!");
				break;
			}
			
			if(AttemptNum == maxAttempts)
			{
				logger.info("failed to load " + url);
				Assert.assertEquals(driver.getTitle().trim(), title, "Failed to load URL: " + url + ", WebPage title not matched");
			}
		}
		while((!driver.getTitle().contains(title)));
	}

	@AfterClass
	public void tearDown()
	{
//		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
}