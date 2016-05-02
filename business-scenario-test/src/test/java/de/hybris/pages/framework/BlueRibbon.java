package de.hybris.pages.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BlueRibbon extends Container{

	@FindBy(id="nav-expander")
	private WebElement hamburgaerMenu;
	
	public BlueRibbon(WebDriver driver) {
		super(driver);
	}

	public LeftPane openHamburgerMenu() {
		
		logDetail("Open the Hamburger menu");
		
		putMeInContainer();
		hamburgaerMenu.click();	
		delayForAnimation();
		return new LeftPane(driver);
	}


	public BlueRibbon clickOnRibbonButton(String buttonTagName){
		
		logDetail("Click on the \""+buttonTagName+"\" button");
		
		putMeInContainer();
		WebElement button = driver.findElement(By.tagName(buttonTagName));		
		button.click();
		delayForAnimation();
		return this;
	}
}
