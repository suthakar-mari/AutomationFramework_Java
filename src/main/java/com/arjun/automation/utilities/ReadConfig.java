package com.arjun.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig 
{
	Properties prop;
	
	public ReadConfig()
	{
		File configFile = new File("./configurations/config.properties");
		
		try
		{
			FileInputStream fis = new FileInputStream(configFile);
			prop = new Properties();
			prop.load(fis);
		}
		catch (Exception e)
		{
			System.out.println("exception : " + e.getMessage());
		}
	}
	
	public String getWindowsChromeDriver()
	{
		return prop.getProperty("windowsChromeDriver");
	}
	
	public String getWindowsFirefoxDriver()
	{
		return prop.getProperty("windowsFirefoxDriver");
	}
	
	public String getEdgeDriver()
	{
		return prop.getProperty("edgeDriver");
	}
	
	public String getIEDriver()
	{
		return prop.getProperty("IEDriver");
	}
	
	public String getMacChromeDriver()
	{
		return prop.getProperty("macChromeDriver");
	}
	
	public String getMacFirefoxDriver()
	{
		return prop.getProperty("macFirefoxDriver");
	}
	
	public String getLinuxChromeDriver()
	{
		return prop.getProperty("linuxChromeDriver");
	}
	
	public String getLinuxFirefoxDriver()
	{
		return prop.getProperty("linuxFirefoxDriver"); 
	}
}