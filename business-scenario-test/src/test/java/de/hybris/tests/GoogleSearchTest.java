package de.hybris.tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import de.hybris.pages.GoogleSearch;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GoogleSearchTest {

	WebDriver driver;

	@Before
	public void setup(){
		//use FF Driver
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void searchForSomething() {

		String query = "selenium page object";
		GoogleSearch home = new GoogleSearch(driver);
		home
		.enterQuery(query)
		.clickOnSearchButton();

	}

	@After
	public void close(){
		driver.close();
	}

}
