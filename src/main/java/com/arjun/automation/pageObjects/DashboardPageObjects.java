package com.arjun.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPageObjects 
{
	WebDriver driver;
	
	public DashboardPageObjects(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[text()='Log out']")
	@CacheLookup
	private WebElement lnkLogout;
	
	public WebElement getLnkLogout()
	{
		return lnkLogout;
	}
	
	@FindBy(xpath = "//a[text()='New Customer']")
	@CacheLookup
	private WebElement lnkNewCustomer;
	
	public WebElement getLnkNewCustomer()
	{
		return lnkNewCustomer;
	}
	
	@FindBy(xpath = "//input[@name='name']")
	@CacheLookup
	private WebElement txtCustomerName;
	
	public WebElement getTxtCustomerName()
	{
		return txtCustomerName;
	}
	
	@FindBy(xpath = "//input[@name='rad1']")
	@CacheLookup
	private WebElement rdGender;
	
	public WebElement getRdGender()
	{
		return rdGender;
	}
	
	@FindBy(xpath = "//input[@id='dob']")
	@CacheLookup
	private WebElement txtDOB;
	
	public WebElement getTxtDOB()
	{
		return txtDOB;
	}
	
	@FindBy(xpath = "//textarea[@name='addr']")
	@CacheLookup
	private WebElement txtAddress;
	
	public WebElement getTxtAddress()
	{
		return txtAddress;
	}
	
	@FindBy(xpath = "//input[@name='city']")
	@CacheLookup
	private WebElement txtCity;
	
	public WebElement getTxtCity()
	{
		return txtCity;
	}
	
	@FindBy(xpath = "//input[@name='state']")
	@CacheLookup
	private WebElement txtState;
	
	public WebElement getTxtState()
	{
		return txtState;
	}
	
	@FindBy(xpath = "//input[@name='pinno']")
	@CacheLookup
	private WebElement txtPIN;
	
	public WebElement getTxtPIN()
	{
		return txtPIN;
	}
	
	@FindBy(xpath = "//input[@name='telephoneno']")
	@CacheLookup
	private WebElement txtMobNum;
	
	public WebElement getTxtMobNum()
	{
		return txtMobNum;
	}
	
	@FindBy(xpath = "//input[@name='emailid']")
	@CacheLookup
	private WebElement txtEmail;
	
	public WebElement getTxtEmail()
	{
		return txtEmail;
	}
	
	@FindBy(xpath = "//input[@name='password']")
	@CacheLookup
	private WebElement txtPassword;
	
	public WebElement getTxtPassword()
	{
		return txtPassword;
	}
	
	@FindBy(xpath = "//input[@name='sub']")
	@CacheLookup
	private WebElement btnSubmit;
	
	public WebElement getBtnSubmit()
	{
		return btnSubmit;
	}
	
}