package de.hybris.pages.framework;

import java.util.List;

import org.openqa.selenium.WebElement;

import de.hybris.pages.cms.Component;
import de.hybris.pages.cms.Content;

public interface Editor {


	void fillWithOptionalContent(List<Content> contents);

	SmartEdit save();

	String getComponentId();
	
}
