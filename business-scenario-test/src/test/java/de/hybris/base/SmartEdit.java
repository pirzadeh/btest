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

public class SmartEdit extends PageObject implements Coordinatable
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
	public void createNewComponent(String type, String slotId){
		Actions builder = new Actions(driver);
		 
		Action dragAndDrop = builder.clickAndHold(From).moveToElement(To).release(To).build();
		dragAndDrop.perform();
	}
	
	private void createNewComponentByOffset(int x, int y){
		WebElement draggable = driver.findElement(By.xpath("//*[@id='container']/div[2]"));
		 new Actions(driver)
		 .dragAndDropBy(draggable, -200 , 0 )
		 .build()
		 .perform();
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
