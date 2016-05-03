package de.hybris.pages.framework;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import de.hybris.base.Coordinatable;
import de.hybris.base.PageObject;

public class FrameContent extends PageObject implements Coordinatable{

	public FrameContent(WebDriver driver) {
		super(driver);
		frameIsReady();
	}

	private void putMeInMyFrame(){
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
	}
	
	private void frameIsReady(){
		putMeInMyFrame();
		waitUntilXpathAtrributeValueAvailable("//body", "data-smartedit-ready", "true");
	}
	
	public FrameContent moveToElementAndDrop(String slotId) {
		frameIsReady();
		WebElement slot = findFrameElementById(slotId);
		List<String> conditions = Arrays.asList("ySEDnDPlaceHolder","over-slot-enabled");
		jiggleWithinUntilAttributeIsPresent(slot, conditions);
		mouse.release().build().perform();
		return this;
	}
	
	@Override
	public Point findCoordById(String id){
		frameIsReady();
		WebElement element = findFrameElementById(id);
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
		WebElement element = findFrameElementByName(name);
		return findCoord(element);
	}
	
	//helper
	
	public WebElement findFrameElementById(String id){
		return findFrameElementById(null, id);
	}

		
	public WebElement findFrameElementById(WebElement scope, String smartEditId){
		frameIsReady();
		String cssSelector = ".smartEditComponent[data-smartedit-component-id='"+smartEditId+"']";	
		return findElementByCssSelector(scope, cssSelector);
	}
	
	private WebElement findFrameElementByName(String name){
		return findFrameElementByName(null, name);
	}
	
	private WebElement findFrameElementByName(WebElement scope, String name){
		frameIsReady();
		WebElement element = null;
		
		if (scope == null){
			element = driver.findElement(By.name(name));
		}
		else{
			element = scope.findElement(By.name(name));
		}
		return element;
	}

	public void prepareElementForMove(String componentId) {
		frameIsReady();
		WebElement component = findFrameElementById(componentId);
		mouse.clickAndHold(component).build().perform();
	}

	public boolean elementIsAvailable(WebElement scope, String smartEditId) {

		String cssSelector = ".smartEditComponent[data-smartedit-component-id='"+smartEditId+"']";
		return cssSelectorIsAvailable(scope, cssSelector);
	}



}
