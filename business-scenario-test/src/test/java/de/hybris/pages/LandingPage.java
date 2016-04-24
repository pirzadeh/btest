package de.hybris.pages;

import de.hybris.base.PageObject;
import de.hybris.pages.framework.SmartEdit;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends PageObject
{

	//Page URL
	private static String url = "https://e2e3.prod.wcms.b2c.ydev.hybris.com:9002/smartedit/#/";

	public LandingPage(final WebDriver driver)
	{
		super(driver);

	}

	//Locators

	
	//helpers
	
	private List<WebElement> getCatalogs(){
		return driver.findElements(By.cssSelector(".catalog-container"));
	}
	
	private WebElement catalogBodyOf(String term){
		 List<WebElement> bodies = driver.findElements(By.cssSelector(".catalog-body"));
		  Optional<WebElement> filteredBody = bodies.stream().filter(body -> body.getText().contains(term)).findFirst();		  
		  return filteredBody.get();
	}
	
	public WebElement findCatalogVersion(String catalog, String catalogVersion){
		
		return catalogBodyOf(catalog).findElement(By.xpath("//div[contains(text(),'"+catalogVersion+"')]"));
	}
	
	public SmartEdit gotoCatalogVersion(String catalog, String catalogVersion){
		
		findCatalogVersion(catalog, catalogVersion).click();
		return (new SmartEdit(driver));
	}	
	
	private WebElement catalogVersionOf(String term){
		 List<WebElement> bodies = driver.findElements(By.cssSelector(".catalog-thumbnail"));
		  Optional<WebElement> filteredVersion = bodies.stream().filter(body -> body.getText().contains(term)).findFirst();
		  return filteredVersion.get();
	}
	
	public WebElement selectCatalogPageNumber(int index){
		
		int STARTING_INDEX = 2;
		index =+ STARTING_INDEX;
		return driver.findElement(By.cssSelector(".pagination-container .pagination li:nth-child(" + index + ") a"));
	}
	
}
