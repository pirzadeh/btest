package de.hybris.pages.cms;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import de.hybris.data.Content;
import de.hybris.pages.cms.base.Editor;
import de.hybris.pages.cms.base.EditorBase;
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
	public void fillWithOptionalContent(List<Content> contents) {
		
		logInteraction("Fill the editor with the provided content");
		
		setEditorContent(contents);

	}
	@Override
	public void fillWithPredefinedContent() {
		
		logInteraction("Fill the editor with predefined content");
		
		List<Content> contents = new ArrayList<Content>();
		Content content = new Content(null, "Predefined", null, null);
		contents.add(content);
		setEditorContent(contents );

	}

	//methods specific to simple paragraph

	public void setEditorContent(List<Content> contents){

		for (Content content:contents){
			
			//if the language is not set, set it with default language
			if (content.getLanguage() == null){
				content.setLanguage(findDefaultLanguage());
			}
			
		logDetail("Inputting \""+content.getTextualContent()+"\" into the editor for \""+content.getLanguage()+" language");
		
		selectLocalizedTab(content.getLanguage());
		putMeInEditorOf(content.getLanguage());
		WebElement body = driver.findElement(By.cssSelector("body"));
		body.sendKeys(content.getTextualContent());
		}
	


	}

}
