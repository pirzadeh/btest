package de.hybris.pages.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import de.hybris.pages.framework.base.Container;

public class BlueRibbon extends Container{


	
	public BlueRibbon(WebDriver driver) {
		super(driver);
	}

	public LeftPane openHamburgerMenu() {
		
		logDetail("Open the Hamburger menu");
		
		putMeInContainer();
		WebElement hamburgaerMenu = findElementById("nav-expander");
		clickOn(hamburgaerMenu);
		return new LeftPane(driver);
	}


	private BlueRibbon clickOnRibbonButton(String buttonTagName){
		
		logDetail("Click on the \""+buttonTagName+"\" button");
		
		putMeInContainer();
		WebElement button = findElement(By.tagName(buttonTagName));		
		button.click();
		return this;
	}
}
