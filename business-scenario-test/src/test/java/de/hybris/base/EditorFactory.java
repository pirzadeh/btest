package de.hybris.base;

import org.openqa.selenium.WebDriver;

import de.hybris.pages.cms.SimpleBannerEditor;
import de.hybris.pages.cms.SimpleParagraphEditor;
import de.hybris.pages.cms.SimpleResponsiveBannerEditor;

public class EditorFactory{

	public Editor getEditor(String editorType, WebDriver driver){
		if(editorType == null){
			return null;
		}		
		if(editorType.equals("cmsparagraphcomponent")){
			return new SimpleParagraphEditor(driver);

		} else if(editorType.equals("simpleresponsivebannercomponent")){
			return new SimpleResponsiveBannerEditor(driver);

		} else if(editorType.equals("simplebannercomponent")){
			return new SimpleBannerEditor(driver);

		}

		return null;
	}
}
