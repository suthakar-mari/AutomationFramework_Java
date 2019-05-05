package com.arjun.automation.utilities;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtil 
{
	WebDriver driver;
	WebDriverWait wait;
	
	public WebDriverUtil(WebDriver driver) 
	{
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}
	
	public void waitForPageLoad() 
	{
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
	}
	
	public void waitForPageLoad_JS() 
	{
		try
		{
			WebDriverWait PageWait = new WebDriverWait(driver, 60);
			PageWait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void waitForElementVisiblity(WebElement e) 
	{
		wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	public void waitForElementsVisibility(List<WebElement> es) 
	{
		wait.until(ExpectedConditions.visibilityOfAllElements((es)));
	}
	
	public void waitForStaleElement(WebElement e) 
	{
		wait.until(ExpectedConditions.stalenessOf(e));
	}
	
	public void waitUntillElementPresenceFound(By locator) 
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void waitForElementName(String name) 
	{
		wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
	}
	
	public void setImpWait(int seconds)
	{
		driver.manage().timeouts().implicitlyWait(seconds,TimeUnit.SECONDS);
	}
	
	public void setImpWait()
	{
		driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
	}
	
	public void resetImpWait()
	{
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	public void Sleep(int timeInMs) 
	{
		try 
		{
			Thread.sleep(timeInMs);
		} 
		catch (Exception e) 
		{
			//ignore
		}
	}
	
	public void waitTillClickable(WebElement element, int maxWaitTime)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, maxWaitTime); 
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*---verifies if the alert is present---*/
	public boolean verifyAlertPresent() 
	{
		try 
		{
			driver.switchTo().alert();
			return true;
		} 
		catch (NoAlertPresentException e) 
		{
			return false;
		}
	}
	
	/*----performs passed action on the alert----*/
	enum Actions
	{
		accept,dismiss
	}

	public void alertAction(String action) throws NoAlertPresentException
	{
		Actions act = Actions.valueOf(action);
		try 
		{
			switch (act)
			{
				case accept :
					
					driver.switchTo().alert().accept();
					break;
	
				case dismiss :
					
					driver.switchTo().alert().dismiss();
					break;
			}
		} 
		catch (NoAlertPresentException e) 
		{
			throw e;
		}
	}
	
	/*----Get the text from alert----*/
	public String alertGetText() throws NoAlertPresentException 
	{
		String text = null;
		try 
		{
			text = driver.switchTo().alert().getText();
		} 
		catch (NoAlertPresentException e) 
		{
			throw e;
		}
		return text;
	}
	
	public void switchToDefaultContent()
	{
		driver.switchTo().defaultContent();
	}
}
