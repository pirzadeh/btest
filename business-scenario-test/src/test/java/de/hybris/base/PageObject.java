package de.hybris.base;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class PageObject {
	public static WebDriver driver;
	public static Actions mouse;

	public PageObject(final WebDriver driver, final String url) {
		driver.get(url);
		pageSetup(driver);
		
	}

	public PageObject(WebDriver driver) {
		pageSetup(driver);
	}
	
	private void pageSetup(WebDriver driver) {
		this.setDriver(driver);
		PageFactory.initElements(driver, this);	
		setupMouse(driver);
	}
	
	private void setupMouse(WebDriver driver) {

		if (mouse == null)
			mouse = new Actions(driver);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(final WebDriver driver) {
		this.driver = driver;
	}



}
