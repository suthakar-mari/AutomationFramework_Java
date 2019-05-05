package com.arjun.automation.sampletest;

import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.arjun.automation.genericLib.BaseClass;
import com.arjun.automation.pageActions.DashboardPageActions;
import com.arjun.automation.pageActions.LoginPageActions;

public class TC_AddCustomer_002 extends BaseClass 
{
	@Test
	public void AddNewCustomer()
	{
		LoginPageActions loginAct = new LoginPageActions(driver, logger);
		DashboardPageActions dp = new DashboardPageActions(driver, logger);
		HashMap<String, String> CustomerCredentials = new HashMap<String, String>();
		
		Assert.assertTrue(loginAct.login("mngr26593", "abc@123"), "Login to Application failed");
		
		String EmailId = RandomStringUtils.randomAlphabetic(5) + "@mail.com";
		
		CustomerCredentials.put("custName", "Arjun");
		CustomerCredentials.put("gender", "male");
		CustomerCredentials.put("DOB", "19041995");
		CustomerCredentials.put("Address", "house num street locality");
		CustomerCredentials.put("City", "Bengaluru");
		CustomerCredentials.put("State", "India");
		CustomerCredentials.put("PIN", "560091");
		CustomerCredentials.put("MobNum", "9874563210");
		CustomerCredentials.put("Email", EmailId);
		CustomerCredentials.put("Password", "abc@123");
		
		Assert.assertTrue(dp.AddCustomer(CustomerCredentials), "Adding New Customer failed");
	}
}
