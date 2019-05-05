package com.arjun.automation.pageActions;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.arjun.automation.pageObjects.DashboardPageObjects;
import com.arjun.automation.utilities.WebDriverUtil;

public class DashboardPageActions 
{
	WebDriver driver;
	Logger logger;
	WebDriverUtil wbUtil;
	
	
	public DashboardPageActions(WebDriver driver, Logger logger)
	{
		this.driver = driver;
		this.logger = logger;
		wbUtil = new WebDriverUtil(driver);
	}
	
	
	public boolean logout(String username, String password)
	{
		DashboardPageObjects dp = new DashboardPageObjects(driver);
		
		try
		{
			dp.getLnkLogout().click();
			logger.info("Clicked on Logout link");
			
			if(wbUtil.verifyAlertPresent())
			{
				wbUtil.alertAction("accept");
				wbUtil.switchToDefaultContent();
				return true;
			}
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	
	public boolean AddCustomer(HashMap<String, String> CustomerCredentials)
	{
		DashboardPageObjects dp = new DashboardPageObjects(driver);
		
		try
		{
			dp.getLnkNewCustomer().click();
			logger.info("Clicked on New Customer link");
			
			dp.getTxtCustomerName().sendKeys(CustomerCredentials.get("custName"));
			dp.getRdGender().click();
			dp.getTxtDOB().sendKeys(CustomerCredentials.get("DOB"));
			dp.getTxtAddress().sendKeys(CustomerCredentials.get("Address"));
			dp.getTxtCity().sendKeys(CustomerCredentials.get("City"));
			dp.getTxtState().sendKeys(CustomerCredentials.get("State"));
			dp.getTxtPIN().sendKeys(CustomerCredentials.get("PIN"));
			dp.getTxtMobNum().sendKeys(CustomerCredentials.get("MobNum"));
			dp.getTxtEmail().sendKeys(CustomerCredentials.get("Email"));
			dp.getTxtPassword().sendKeys(CustomerCredentials.get("Password"));
			logger.info("Enetered all customer details in Add New Customer page");
			
			dp.getBtnSubmit().click();
			logger.info("Clicked on Submit button on Add New Customer Page");
			
			if(wbUtil.verifyAlertPresent())
			{
				logger.warn("Adding new customer failed, with alert message : " + wbUtil.alertGetText());
				Assert.assertTrue(false, "Adding new customer failed, with alert message : " + wbUtil.alertGetText());
			}
			else if(driver.getPageSource().contains("Customer Registered Successfully!!!"))
			{
				logger.info("Customer registration successfull");
				return true;
			}
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
}
