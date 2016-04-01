package de.hybris.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearch {
	private WebDriver driver;

	//Page URL
	private static String URL="https://scholar.google.ca/";

	//Locators

	@FindBy(name = "btnG")
	private WebElement searchButton;

	@FindBy(name = "q")
	private WebElement searchField;

	//Constructor
	public GoogleSearch(WebDriver driver){
		this.driver=driver;
		driver.get(URL);
		PageFactory.initElements(driver, this);
	}

	public void clickOnSearchButton(){

		searchButton.click();

	}

	public GoogleSearch enterQuery(String query){

		searchField.sendKeys(query);
		return this;

	}
}
