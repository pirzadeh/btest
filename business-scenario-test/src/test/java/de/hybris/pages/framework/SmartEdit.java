package de.hybris.pages.framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import de.hybris.base.PageObject;
import de.hybris.pages.LoginPage;
import de.hybris.pages.cms.Component;
import de.hybris.pages.cms.SimpleParagraphEditor;
import de.hybris.pages.framework.enums.PerspectiveEnum;

public class SmartEdit extends Container
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
	
	public SmartEdit createNewComponentAndFillIt(Component component) {

		Editor editor = createNewComponent(component.getType(), component.getSlotId());
		editor.fillWithOptionalContent(component.getContentList());
		component.setId(editor.getComponentId());
		editor.save();
		return this;
	}
	
	public Editor createNewComponent(String type, String slotId){

		logInteraction("Drag and Drop the component type \""+type+"\" from the component menu to slot \""+slotId+"\" to create a new customized component");
		
		whiteRibbon.selectPerspective(PerspectiveEnum.BASIC).prepareComponentTypeForMove(type);	
		prepareForDrop();
		frame.moveToElementAndDrop(slotId);
		EditorFactory editorFactory = new EditorFactory();
		Editor editor = editorFactory.getEditor(EditorBase.findComponentType(), driver);
		return editor;
	}
	
	public SmartEdit addExistingComponent(String item, String slotId){

		logInteraction("Drag and Drop the customized component \""+item+"\" from the component menu to slot \""+slotId+"\"");
		
		whiteRibbon.selectPerspective(PerspectiveEnum.BASIC).prepareCustomizedComponentForMove(item);	
		prepareForDrop();
		frame.moveToElementAndDrop(slotId);
		delayForDebugging();
		return this;
	}

	public SmartEdit moveComponentToSlot(String componentId, String slotId){
		
		logInteraction("Drag and Drop component \""+componentId+"\" to slot \""+slotId+"\"");
		
		whiteRibbon.selectPerspective(PerspectiveEnum.BASIC);
		frame.prepareElementForMove(componentId);
		prepareForDrop();
		frame.moveToElementAndDrop(slotId);
		delayForDebugging();
		return this;
	}

	public LoginPage logout() {
		
		return blueRibbon.openHamburgerMenu().logout();
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

	public void validateExistence(List<Component> validationList) {
		// TODO Auto-generated method stub
		
		
	}

	




}
