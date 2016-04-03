package de.hybris.base;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class Persona {

	String name;
	String role;
	
	String username;
	String password;
		
	protected WebDriver driver;

	public abstract String reportExperience();
	@Before
	public void basesetup(){
		//use FF Driver
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	

	@After
	public void baseclose(){
		driver.close();
	}
}
