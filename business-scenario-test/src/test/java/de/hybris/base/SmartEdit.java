package de.hybris.base;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SmartEdit extends PageObject
{

	FrameContent frame;
	LeftPane leftPane;
	BlueRibbon blueRibbon;
	WhiteRibbon whiteRibbon;


	public SmartEdit(WebDriver driver) {
		super(driver);
		this.frame = new FrameContent(driver);
		this.leftPane = new LeftPane(driver);
		this.blueRibbon = new BlueRibbon(driver);
		this.whiteRibbon = new WhiteRibbon(driver);
	}

	//Guarantees

	private final WebElement containerLoaded = driver.findElement(By.id("nav-expander"));

	//Locators


	//Helpers

//	
//	public void value(){
//		
//		
//		System.out.println(driver.findElement(By.xpath("//body")).getAttribute("data-smartedit-ready"));
//	}

	//Behaviours
//	public void createNewComponent(String type, String slotId){
//		Actions builder = new Actions(driver);
//		 
//		Action dragAndDrop = builder.clickAndHold(From).moveToElement(To).release(To).build();
//		dragAndDrop.perform();
//	}
	
	public void createNewComponent(String type, String slotId){
		
//		WebElement slot = frame.findElementById(slotId);
//		Point destination = frame.findCoord(slot);
		
		frame.frameIsReady();
		WebElement typeElement  = whiteRibbon.findComponentType(type);
		
//		Point offset = findOffset(destination, source);
		
		dragAndDropByOffset(typeElement, slotId);
	}
	
	private Point findOffset(Point source, Point destination) {
		
		Point offset = new Point(source.x - destination.x, source.y - destination.y);
		return offset;
	}
	
	private void dragAndDropByOffset(WebElement element, String slotId){
		
		mouse.clickAndHold(element).build().perform();
		Point source = whiteRibbon.findCoordWithin(element);
		mouse.moveByOffset(0 , -1).build().perform();

		WebElement slot = frame.findElementById(slotId);
		jiggleWithinUntilAttributeIsPresent(slot, "ySEDnDPlaceHolder");
		mouse.release().build().perform();
		delay(20000);
	}
	
	public FrameContent getFrame() {
		
		return frame;
	}

	public void setFrame(FrameContent frame) {
		this.frame = frame;
	}

	public LeftPane getLeftPane() {
		return leftPane;
	}

	public void setLeftPane(LeftPane leftPane) {
		this.leftPane = leftPane;
	}

	public BlueRibbon getBlueRibbon() {
		return blueRibbon;
	}

	public void setBlueRibbon(BlueRibbon blueRibbon) {
		this.blueRibbon = blueRibbon;
	}

	public WhiteRibbon getWhiteRibbon() {
		return whiteRibbon;
	}

	public void setWhiteRibbon(WhiteRibbon whiteRibbon) {
		this.whiteRibbon = whiteRibbon;
	}

	
}
