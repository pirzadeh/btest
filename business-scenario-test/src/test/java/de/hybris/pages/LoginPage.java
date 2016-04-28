package de.hybris.pages;

import de.hybris.base.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject
{

	//Page URL
	private static String url = "https://e2e4.prod.wcms.b2c.ydev.hybris.com:9002/smartedit/#/";

	public LoginPage(final WebDriver driver)
	{
		super(driver, url);

	}

	//Locators

	//Username
	private final WebElement username = driver.findElement(By.xpath("//input[contains(@placeholder, 'Username')]"));

	//Password
	private final WebElement password = driver.findElement(By.xpath("//input[contains(@placeholder, 'Password')]"));

	@FindBy(name = "submit")
	private WebElement signin;


	public LoginPage enterUsername(final String name)
	{
		logInteraction("Enter username: "+ name);
		username.sendKeys(name);
		return this;
	}

	public LoginPage enterPassword(final String pass)
	{
		logInteraction("Enter password: "+ pass);
		password.sendKeys(pass);
		return this;
	}

	public LandingPage signin()
	{
		logInteraction("Press the Sign In");
		signin.click();
		
		return (new LandingPage(driver));
	}
}
