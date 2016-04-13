package de.hybris.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WhiteRibbon extends PageObject implements Coordinatable{

	private final WebElement menuButton = driver.findElement(By.tagName("component-menu"));

	
	public WhiteRibbon(WebDriver driver) {
		super(driver);
	}

	public Point startMovingComponentType(String type) {
		putMeInContainer();
		WebElement typeElement  = findComponentType(type);
		mouse.clickAndHold(typeElement).build().perform();
		Point source = findCoordWithin(typeElement);
		return source;
	}
	


	@Override
	public Point findCoord(WebElement element) {
		putMeInContainer();
		Point point = element.getLocation();
		return point;
	}
	@Override
	public Point findCoordWithin(WebElement element) {
		putMeInContainer();
		Point point = element.getLocation();
		Dimension size = element.getSize();
		Point pointWithin = new Point(point.x + size.width/2, point.y + size.height/2);
		return pointWithin;
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
		delay(600);
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
		putMeInContainer();
		WebElement tabElement = driver.findElement(By.cssSelector("[heading='"+tabName+"']"));
		tabElement.click();
		delay(600);
		return this;
	}

	
}
