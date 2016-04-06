package de.hybris.base;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageObject {
	public WebDriver driver;

	public PageObject(final WebDriver driver, final String url) {
		this.setDriver(driver);
		driver.get(url);
		PageFactory.initElements(driver, this);
	}
	
	public PageObject(final WebDriver driver) {
		this.setDriver(driver);
		PageFactory.initElements(driver, this);
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(final WebDriver driver) {
		this.driver = driver;
	}



}
