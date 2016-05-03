package de.hybris.pages.cms.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import de.hybris.base.PageObject;
import de.hybris.pages.framework.SmartEdit;
import de.hybris.pages.framework.enums.EditorFiledEnum;
import de.hybris.pages.framework.enums.EditorTabEnum;

public class EditorBase extends PageObject {

	public EditorBase(WebDriver driver) {
		super(driver);

	}

	private static void putMeInContainer(){

		driver.switchTo().defaultContent();
	}

	public static String findComponentType(){
		putMeInContainer();
		WebElement header = driver.findElement(By.cssSelector("h4[id*='smartedit-modal-title-type']"));
		String id = header.getAttribute("id");
		String[] parts = id.split("\\.");

		return parts[1];
	}
	
	public EditorBase selectEditorTab(String tabId){
		putMeInContainer();
		WebElement tabElement = driver.findElement(By.cssSelector("[data-tab-id='"+tabId+"']"));
		tabElement.click();
		delayForAnimation();
		return this;
	}

	private WebElement getFieldById(String id){
		putMeInContainer();
		WebElement field = driver.findElement(By.cssSelector("[id='"+id+"']"));
		delayForAnimation();
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
		putMeInContainer();
		driver.findElement(By.id("save")).click();
		return new SmartEdit(driver);
	}
	
	public SmartEdit cancel(){
		putMeInContainer();
		driver.findElement(By.id("cancel")).click();
		return new SmartEdit(driver);
	}
}
