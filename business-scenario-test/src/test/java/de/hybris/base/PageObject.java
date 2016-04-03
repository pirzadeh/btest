package de.hybris.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject {
	public WebDriver driver;

	public PageObject(WebDriver driver, String url) {
		this.setDriver(driver);
		driver.get(url);
		PageFactory.initElements(driver, this);
	}

	public PageObject(WebDriver driver) {
		this.setDriver(driver);
		PageFactory.initElements(driver, this);
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	
}
