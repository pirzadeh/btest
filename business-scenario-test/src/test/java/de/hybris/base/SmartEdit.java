package de.hybris.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

	@FindBy(css="iframe")
	private WebElement origin;
	//Helpers

	private void putMeInContainer(){

		driver.switchTo().defaultContent();
	}

	public SmartEdit createNewComponent(String type, String slotId){

		whiteRibbon.prepareComponentTypeForMove(type);	
		prepareForDrop();
		frame.moveToElementAndDrop(slotId);
		delayForDebugging();
		return this;
	}

	public SmartEdit moveComponentToSlot(String componentId, String slotId){

		frame.prepareElementForMove(componentId);
		prepareForDrop();
		frame.moveToElementAndDrop(slotId);
		delayForDebugging();
		return this;
	}

	private void prepareForDrop() {

		scrollToY(0);
		putMeInContainer();
		mouse.moveToElement(origin, 0, 0).build().perform();
		waitUntilCssLocatorIsAvailable("body > div.UIhintDragAndDrop.top.visible");	
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
