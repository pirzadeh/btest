package de.hybris.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject {
	private WebDriver driver;

	public PageObject(WebDriver driver, String url) {
		this.setDriver(driver);
		driver.get(url);
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	
}
