package com.arjun.automation.genericLib;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import com.arjun.automation.utilities.ReadConfig;

class Driver
{
	private WebDriver driver;
	private ReadConfig readConfig = new ReadConfig();
	
	Driver(WebDriver driver)
	{
		super();
		this.driver = driver;
	}

	enum Browsers
	{
		chrome,edge,ie,firefox,unknown
	}

	enum OperatingSystems
	{
		windows,mac,linux,unknown
	}

	private void instantiateChromeDriver()
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		options.addArguments("test-type");
		options.addArguments("--disable-extenstions");
		options.addArguments("--disable-notifications");
		options.addArguments("disable-infobars");

		driver = new ChromeDriver(options);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	private void instantiateEdgeDriver()
	{
		driver = new EdgeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	private void instantiateIEDriver()
	{
		driver = new InternetExplorerDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	private void instantiateFirefoxDriver()
	{
		driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/** Browser Driver initialization
	 * 
	 * @author Arjun Reddy
	 * @param browserName is the name of browser which hast to be initialized
	 * @return driver
	 */

	WebDriver getDriver(String browserName)
	{
		browserName = browserName.toLowerCase();
		Browsers browser = Browsers.unknown;

		for (Browsers b : Browsers.values())
		{
			if (b.name().equals(browserName))
			{
				browser = Browsers.valueOf(browserName);
			}
		}

		/*
		 * Determining the Operating System
		 * on which browser driver need to be initialized
		 */

		String OSName = System.getProperty("os.name").toLowerCase();
		OperatingSystems OS = OperatingSystems.unknown;

		if(OSName.contains("windows") || SystemUtils.IS_OS_WINDOWS)
		{
			OS = OperatingSystems.windows;
		}
		else if(OSName.contains("mac") || SystemUtils.IS_OS_MAC)
		{
			OS = OperatingSystems.mac;
		}
		else if(OSName.contains("linux") || SystemUtils.IS_OS_LINUX)
		{
			OS = OperatingSystems.linux;
		}
		else
		{
			Assert.fail("Unhandled OS detected for initializing " + browserName +" browser, OS Name : " + OSName);
		}


		/*
		 * Initializing appropriate browser driver based on Operating System
		 */
		
		switch(browser)
		{
			case chrome :
					
				switch(OS)
				{
					case windows :
						
						System.setProperty("webdriver.chrome.driver", readConfig.getWindowsChromeDriver() );
						break;
						
					case mac :
						
						System.setProperty("webdriver.chrome.driver", readConfig.getMacChromeDriver() );
						break;
						
					case linux :
						
						System.setProperty("webdriver.chrome.driver", readConfig.getLinuxChromeDriver() );
						break;
						
					default :
						
						Assert.fail(browserName + " browser not available for " + OSName + " Operating System (or) Browser initialization unhandled");
				}

				instantiateChromeDriver();
				
				break;
				
			case edge :

				switch(OS)
				{
					case windows :

						System.setProperty("webdriver.edge.driver", readConfig.getEdgeDriver());
						break;

					default :

						Assert.fail(browserName + " browser not available for " + OSName + " Operating System (or) Browser initialization unhandled");
				}

				instantiateEdgeDriver();
				
				break;
				
			case ie :
				
				switch(OS)
				{
					case windows :
						
						System.setProperty("webdriver.ie.driver", readConfig.getIEDriver());
						break;
						
					default :
						
						Assert.fail(browserName + " browser not available for " + OSName + " Operating System (or) Browser initialization unhandled");
				}

				instantiateIEDriver();
				
				break;
 
			case firefox :
				
				switch(OS)
				{
					case windows :
						
						System.setProperty("webdriver.gecko.driver", readConfig.getWindowsFirefoxDriver());
						break;
						
					case mac :
						
						System.setProperty("webdriver.gecko.driver", readConfig.getMacFirefoxDriver() );
						break;
						
					case linux :
						
						System.setProperty("webdriver.gecko.driver", readConfig.getLinuxFirefoxDriver() );
						break;
						
					default :
						
						Assert.fail(browserName + " browser not available for " + OSName + " Operating System (or) Browser initialization unhandled");
				}

				instantiateFirefoxDriver();
				
				break;
				
			default :
				
				Assert.fail("Cannot create instance of " + browserName + " driver for Operating System " + OSName );
		}
		return driver;
	}
}