package de.hybris.pages.framework;

import org.openqa.selenium.WebDriver;

import de.hybris.pages.LoginPage;
import de.hybris.pages.framework.base.Container;

public class LeftPane extends Container{

	public LeftPane(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public LoginPage logout() {
		logDetail("Click on the Sign Out link");
		
		findElementByText("Sign Out").click();
		return new LoginPage(driver);
	}

	
}
