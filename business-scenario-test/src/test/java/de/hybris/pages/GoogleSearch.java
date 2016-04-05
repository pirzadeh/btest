package de.hybris.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import de.hybris.base.PageObject;

public class GoogleSearch extends PageObject{

	//Page URL
	private static String url="https://google.ca/";
	
	public GoogleSearch(WebDriver driver) {
		super(driver, url);
	}
	
	//Locators

	@FindBy(name = "btnG")
	private WebElement searchButton;

	@FindBy(name = "q")
	private WebElement searchField;

	public Header header = new Header(driver);
	
	//Actions
	public void clickOnSearchButton(){

		searchButton.click();
	}
	
	public GoogleSearch enterQuery(String query){

		searchField.sendKeys(query);
		return this;
	}
}
