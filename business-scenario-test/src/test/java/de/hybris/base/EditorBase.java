package de.hybris.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditorBase extends PageObject {

	public EditorBase(WebDriver driver) {
		super(driver);

	}

	//data-tab-id

	private static void putMeInContainer(){

		driver.switchTo().defaultContent();
	}

	public static void findComponentType(){
		putMeInContainer();
		WebElement header = driver.findElement(By.cssSelector("h4[id*='smartedit-modal-title-type']"));
		String id = header.getAttribute("id");
		String[] parts = id.split("\\.");

		System.out.println(parts[1]);
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
		setFieldValueById(name, "name-shortstring");
		return this;
	}

	public EditorBase setLinkTo(String link){
		putMeInContainer();
		setFieldValueById(link, "urlLink-shortstring");
		return this;
	}

	public EditorBase setFieldValueById(String value, String id) {
		putMeInContainer();
		WebElement field = getFieldById(id);
		field.sendKeys(value);
		return this;
	}
}
