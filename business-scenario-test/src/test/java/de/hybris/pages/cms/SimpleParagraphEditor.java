package de.hybris.pages.cms;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import de.hybris.pages.framework.Editor;
import de.hybris.pages.framework.EditorBase;
import de.hybris.pages.framework.SmartEdit;


public class SimpleParagraphEditor extends EditorBase implements Editor {



	public SimpleParagraphEditor(WebDriver driver) {
		super(driver);
	}

	private void putMeInContainer(){

		driver.switchTo().defaultContent();
	}

	private void putMeInEditorOf(String language){
		putMeInContainer();
		List<WebElement> richTextElements = driver.findElements(By.tagName("se-rich-text-field"));
		Optional<WebElement> richTextElement = richTextElements.stream().filter(el -> (el.findElements(By.xpath(".//*[@name='content-"+language+"']"))).size()>0).findFirst();
		WebElement frame = richTextElement.get().findElement(By.xpath(".//iframe"));
		driver.switchTo().frame(frame);
	}


	private String findDefaultLanguage(){
		putMeInContainer();
		List<WebElement> languageTabElements = driver.findElements(By.xpath("//*[@id='content']//li"));
		Optional<WebElement> defaultLanguageElement = languageTabElements.stream().filter(el -> getElementsHtml(el).contains("*")).findFirst();
		String lang = defaultLanguageElement.get().getAttribute("data-tab-id");
		return lang;
	}

	public SimpleParagraphEditor selectLocalizedTab(String language){
		//[TODO] support More menu
		logDetail("Select the \""+language+"\" tab in the editor");
		
		putMeInContainer();
		WebElement tab = driver.findElement(By.xpath("//*[@id='content']//li[@data-tab-id='" + language + "']"));
		tab.click();
		return this;
	}
	
	@Override
	public SmartEdit fillWithPredefinedContent() {
		
		logInteraction("Fill the editor with predefined content");
		
		setEditorContent("This is a sample content");
		return new SmartEdit(driver);
	}

	//methods specific to simple paragraph

	public SimpleParagraphEditor setEditorContent(String content) {

		logDetail("Inputting \""+content+"\" into the editor");
		
		String language = findDefaultLanguage();
		setEditorContent(language, content);
		return this;
	}


	public SimpleParagraphEditor setEditorContent(String language, String content){

		logDetail("Inputting \""+content+"\" into the editor for \""+language+" language");
		
		selectLocalizedTab(language);
		putMeInEditorOf(language);
		WebElement body = driver.findElement(By.cssSelector("body"));
		body.sendKeys(content);
		save();
		return this;

	}

}
