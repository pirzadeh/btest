package de.hybris.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WhiteRibbon extends PageObject implements Coordinatable{

	private final WebElement menuButton = driver.findElement(By.xpath("//input[contains(@placeholder, 'Username')]"));

	
	public WhiteRibbon(WebDriver driver) {
		super(driver);
	}

	@Override
	public Point findCoord(WebElement element) {
		// TODO Auto-generated method stub
		return null;
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

	public WebElement findComponentTypeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
