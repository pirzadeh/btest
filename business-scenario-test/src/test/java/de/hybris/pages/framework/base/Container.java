package de.hybris.pages.framework.base;

import org.openqa.selenium.WebDriver;

import de.hybris.base.PageObject;

public class Container extends PageObject{

	public Container(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	public void putMeInContainer(){
		
		driver.switchTo().defaultContent();
	}
}
