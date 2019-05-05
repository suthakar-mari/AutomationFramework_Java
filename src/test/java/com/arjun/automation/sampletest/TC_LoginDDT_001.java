package com.arjun.automation.sampletest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.arjun.automation.genericLib.BaseClass;
import com.arjun.automation.pageActions.DashboardPageActions;
import com.arjun.automation.pageActions.LoginPageActions;
import com.arjun.automation.utilities.ExcelUtil;

public class TC_LoginDDT_001 extends BaseClass
{
	@Test(dataProvider = "LoginData")
	public void loginDDT(String username, String password)
	{
		LoginPageActions loginAct = new LoginPageActions(driver, logger);
		Assert.assertTrue(loginAct.login(username, password), "Login to Application failed");
		
		DashboardPageActions dbAct = new DashboardPageActions(driver, logger);
		Assert.assertTrue(dbAct.logout(username, password), "Logout failed");
	}
	
	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir") + "/test-data/logins.xlsx";
		
		int rowCount = ExcelUtil.getRowCount(path, "Sheet1");
		int colCount = ExcelUtil.getCellCount(path, "Sheet1", 1);
		
		String loginData[][] = new String[rowCount][colCount];
		
		for(int i = 1; i <= rowCount; i++)
		{
			for(int j = 0; j < colCount; j++)
			{
				loginData[i-1][j] = ExcelUtil.getCellData(path, "Sheet1", i, j); // 1, 0
			}
		}
		return loginData;
	}
}
