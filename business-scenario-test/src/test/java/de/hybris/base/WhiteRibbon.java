package de.hybris.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WhiteRibbon extends PageObject implements Coordinatable{

	private final WebElement menuButton = driver.findElement(By.tagName("component-menu"));

	
	public WhiteRibbon(WebDriver driver) {
		super(driver);
	}

	@Override
	public Point findCoord(WebElement element) {
		Point point = element.getLocation();
		return point;
	}

	@Override
	public Point findCoordById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point findCoordByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	private void putMeInContainer(){
		
		driver.switchTo().defaultContent();
	}
	
	public WhiteRibbon openComponentMenu() {

		putMeInContainer();
		menuButton.click();
		return this;
	}

	public WebElement findComponentType(String type) {
		putMeInContainer();
		openComponentMenu();
		selectComponetMenuTab("Component Types");
		WebElement element = driver.findElement(By.cssSelector(".smartEditComponent[data-smartedit-component-type='"+type+"']"));
		return element;
	}

	public WhiteRibbon selectComponetMenuTab(String tabName){
		WebElement tabElement = driver.findElement(By.cssSelector("[heading='"+tabName+"']"));
		tabElement.click();
		return this;
	}
}
