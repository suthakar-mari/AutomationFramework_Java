package com.arjun.automation.pageActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.arjun.automation.pageObjects.LoginPageObjects;
import com.arjun.automation.utilities.WebDriverUtil;

public class LoginPageActions 
{
	WebDriver driver;
	Logger logger;
	WebDriverUtil wbUtil;
	
	public LoginPageActions(WebDriver driver, Logger logger)
	{
		this.driver = driver;
		this.logger = logger;
		wbUtil = new WebDriverUtil(driver);
	}
	
	public boolean login(String username, String password)
	{
		LoginPageObjects loginObj = new LoginPageObjects(driver);
		
		try
		{
			loginObj.getTxtUserName().sendKeys(username);
			loginObj.getTxtPassword().sendKeys(password);
			loginObj.getBtnLogin().click();
			logger.info("Entered username and password, clicked on Login button");
			
			if(wbUtil.verifyAlertPresent())
			{
				wbUtil.alertAction("accept");
				wbUtil.switchToDefaultContent();
				return false;
			}
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
