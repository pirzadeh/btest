package de.hybris.pages;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import de.hybris.base.Editor;
import de.hybris.base.EditorBase;
import de.hybris.base.SmartEdit;


public class SimpleParagraphEditor extends EditorBase implements Editor {

	

	public SimpleParagraphEditor(WebDriver driver) {
		super(driver);
	}
	
	private void putMeInEditorOf(String language){
		driver.switchTo().defaultContent();
		List<WebElement> richTextElements = driver.findElements(By.tagName("se-rich-text-field"));
		Optional<WebElement> richText = richTextElements.stream().filter(el -> (el.findElements(By.xpath("//*[@name='content-"+language+"']"))).size()>0).findFirst();
		driver.switchTo().frame(richText.get());
	}

	
	public SimpleParagraphEditor selectLocalizedTab(String language){
//		if (isHidden) {
//	        element.all(by.xpath("//*[@id='" + qualifier + "']//*[@data-toggle='dropdown']")).click();
//	    }
//
//	    element.all(by.xpath('//*[@id="content"]//li[@data-tab-id="' + language + '"]')).click();
		
		driver.findElement(By.xpath("//*[@id='content']//li[@data-tab-id='" + language + "']")).click();
		return this;
	}
	@Override
	public SmartEdit fillWithNewContent() {
		setEditorContent("en","This is a sample content");
		return new SmartEdit(driver);
	}

	//methods specific to simple paragraph
	
//	browser.switchTo().frame(element(by.css(iframeId)).getWebElement(''));
//    browser.driver.findElement(by.tagName('body')).sendKeys(content);
	public SimpleParagraphEditor setEditorContent(String language, String content){
		
		selectLocalizedTab(language);
		putMeInEditorOf(language);
		driver.findElement(By.tagName("body")).sendKeys(content);
		return this;
		
	}
	
}
