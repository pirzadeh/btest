package de.hybris.pages.cms.base;

import java.util.List;

import org.openqa.selenium.WebElement;

import de.hybris.data.Component;
import de.hybris.data.Content;
import de.hybris.pages.framework.SmartEdit;

public interface Editor {


	void fillWithOptionalContent(List<Content> contents);

	SmartEdit save();

	String getComponentId();
	
}
