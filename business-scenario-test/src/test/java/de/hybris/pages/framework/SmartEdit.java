package de.hybris.pages.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import de.hybris.base.PageObject;
import de.hybris.pages.cms.SimpleParagraphEditor;

public class SmartEdit extends PageObject
{

	FrameContent frame;
	LeftPane leftPane;
	BlueRibbon blueRibbon;
	WhiteRibbon whiteRibbon;
	
	static final String VISIBILITY_CSS_SELECTOR = "body > div.UIhintDragAndDrop.top.visible";



	
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

	@FindBy(css="iframe")
	private WebElement origin;
	
	//Helpers

	private void putMeInContainer(){

		driver.switchTo().defaultContent();
	}
	
	public Editor createNewComponent(String type, String slotId){

		logInteraction("Drag and Drop the component type \""+type+"\" from the component menu to slot \""+slotId+"\" to create a new customized component");
		
		whiteRibbon.selectPerspective(PerspectiveChoices.BASIC).prepareComponentTypeForMove(type);	
		prepareForDrop();
		frame.moveToElementAndDrop(slotId);
		EditorFactory editorFactory = new EditorFactory();
		Editor editor = editorFactory.getEditor(EditorBase.findComponentType(), driver);
		return editor;
	}
	
	public SmartEdit addExistingComponent(String item, String slotId){

		logInteraction("Drag and Drop the customized component \""+item+"\" from the component menu to slot \""+slotId+"\"");
		
		whiteRibbon.selectPerspective(PerspectiveChoices.BASIC).prepareCustomizedComponentForMove(item);	
		prepareForDrop();
		frame.moveToElementAndDrop(slotId);
		delayForDebugging();
		return this;
	}

	public SmartEdit moveComponentToSlot(String componentId, String slotId){
		
		logInteraction("Drag and Drop component \""+componentId+"\" to slot \""+slotId+"\"");
		
		whiteRibbon.selectPerspective(PerspectiveChoices.BASIC);
		frame.prepareElementForMove(componentId);
		prepareForDrop();
		frame.moveToElementAndDrop(slotId);
		delayForDebugging();
		return this;
	}

	public SmartEdit assertComponentContentInSlot(String componentId, String Content, String slotId){
		
		//[TODO] assert that the specified component has the specified content.
		return this;
	}
	private void prepareForDrop() {

		scrollToY(0);
		putMeInContainer();
		mouse.moveToElement(origin, 0, 0).build().perform();
		waitUntilCssLocatorIsAvailable(VISIBILITY_CSS_SELECTOR);	
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
