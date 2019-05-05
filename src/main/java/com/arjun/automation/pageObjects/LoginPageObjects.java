package com.arjun.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects
{
	WebDriver driver;
	
	public LoginPageObjects(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "uid")
	@CacheLookup
	private WebElement txtUserName;
	
	public WebElement getTxtUserName()
	{
		return txtUserName;
	}
	
	@FindBy(name = "password")
	@CacheLookup
	private WebElement txtPassword;
	
	public WebElement getTxtPassword()
	{
		return txtPassword;
	}
	
	@FindBy(name = "btnLogin")
	@CacheLookup
	private WebElement btnLogin;
	
	public WebElement getBtnLogin()
	{
		return btnLogin;
	}
}
