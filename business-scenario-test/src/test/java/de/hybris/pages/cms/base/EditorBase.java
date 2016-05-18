package de.hybris.pages.cms.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import de.hybris.base.PageObject;
import de.hybris.pages.framework.SmartEdit;
import de.hybris.pages.framework.enums.EditorFiledEnum;
import de.hybris.pages.framework.enums.EditorTabEnum;

public abstract class EditorBase extends PageObject {

	public EditorBase(WebDriver driver) {
		super(driver);

	}

	public static void putMeInContainer(){

		driver.switchTo().defaultContent();
	}

	public static  String findComponentType(){
		putMeInContainer();
		WebElement header = findElementByCssSelector("h4[id*='smartedit-modal-title-type']");
		String id = header.getAttribute("id");
		String[] parts = id.split("\\.");

		return parts[1];
	}
	
	public EditorBase selectEditorTab(String tabId){
		putMeInContainer();
		WebElement tabElement =  findElementByCssSelector("[data-tab-id='"+tabId+"']");
		tabElement.click();
//		delayForAnimation();
		return this;
	}

	private WebElement getFieldById(String id){
		putMeInContainer();
		WebElement field = findElementByCssSelector("[id='"+id+"']");
//		delayForAnimation();
		return field;
	}

	public EditorBase setName(String name){
		putMeInContainer();
		setFieldValueById(name, EditorFiledEnum.NAME);
		return this;
	}

	public EditorBase setLinkTo(String link){
		putMeInContainer();
		setFieldValueById(link, EditorFiledEnum.LINK_TO);
		return this;
	}

	public EditorBase setFieldValueById(String value, String id) {
		putMeInContainer();
		WebElement field = getFieldById(id);
		field.sendKeys(value);
		return this;
	}
	
	public String getComponentId(){
		putMeInContainer();
		selectEditorTab(EditorTabEnum.ADMIN);
		String componentId = getFieldById(EditorFiledEnum.ID).getAttribute("value");
		return componentId;
	}
	
	public SmartEdit save(){
		logDetail("Press the Save button");
		
		putMeInContainer();
		clickOn(findElementById("save"));
		return new SmartEdit(driver);
	}
	
	public SmartEdit cancel(){
		putMeInContainer();
		clickOn(findElementById("cancel"));
		return new SmartEdit(driver);
	}
}
