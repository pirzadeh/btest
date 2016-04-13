package de.hybris.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrameContent extends PageObject implements Coordinatable{

	public FrameContent(WebDriver driver) {
		super(driver);
	}

	private void putMeInMyFrame(){
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
	}
	
	public void frameIsReady(){
		putMeInMyFrame();
		waitUntilXpathAtrributeValueAvailable("//body", "data-smartedit-ready", "true");
	}
	
	public void moveFromPointToElement(String slotId) {
		frameIsReady();
		WebElement slot = findElementById(slotId);
		jiggleWithinUntilAttributeIsPresent(slot, "ySEDnDPlaceHolder");
		mouse.release().build().perform();
	}
	
	@Override
	public Point findCoordById(String id){
		frameIsReady();
		WebElement element = findElementById(id);
		return findCoord(element);
	}

	@Override
	public Point findCoordWithin(WebElement element) {
		
		Point point = element.getLocation();
		Dimension size = element.getSize();
		Point pointWithin = new Point(point.x + size.width/2-1, point.y + size.height/2-1);
		return pointWithin;
	}
	
	@Override
	public Point findCoord(WebElement element) {

		Point point = element.getLocation();
		return point;
	}

	@Override
	public Point findCoordByName(String name) {
		frameIsReady();
		WebElement element = findElementByName(name);
		return findCoord(element);
	}
	
	//helper
	
	public WebElement findElementById(String id){
		frameIsReady();
		WebElement element = driver.findElement(By.cssSelector(".smartEditComponent[data-smartedit-component-id='"+id+"']"));
		return element;
	}
	
	public WebElement findElementByName(String name){
		frameIsReady();
		WebElement element = driver.findElement(By.name(name));
		return element;
	}





}
