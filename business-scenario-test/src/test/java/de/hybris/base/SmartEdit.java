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
		frame.frameIsReady();
	}

	//Guarantees

	private final WebElement containerLoaded = driver.findElement(By.id("nav-expander"));

	//Locators


	//Helpers

	
	public void createNewComponent(String type, String slotId){
		
		Point source = whiteRibbon.startMovingComponentType(type);		
		frame.moveFromPointToElement(source, slotId);
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
