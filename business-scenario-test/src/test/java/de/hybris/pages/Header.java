package de.hybris.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import de.hybris.base.PageObject;

public class Header extends PageObject {

	

	public Header(WebDriver driver) {
		super(driver);
	}


	private WebElement appsSign = getDriver().findElement(By.xpath("//a[contains(@title,'Google apps')]"));


	public void clickAppsSign(){
		appsSign.click();
	}
}
