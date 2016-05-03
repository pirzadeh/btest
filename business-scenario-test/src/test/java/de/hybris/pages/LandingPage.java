package de.hybris.pages;

import de.hybris.base.PageObject;
import de.hybris.data.Component;
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

	@FindBy(css = ".catalog-container")
	private List<WebElement> catalogs;

	@FindBy(css = ".catalog-body")
	private List<WebElement> catalogBodies;

	public LandingPage(final WebDriver driver)
	{
		super(driver);
	}

	public SmartEdit gotoCatalogVersion(String catalog, String catalogVersion){

		logInteraction("From "+catalog+", select the "+catalogVersion+" version");

		findCatalogVersion(catalog, catalogVersion).click();
		return (new SmartEdit(driver));
	}	

	//helpers

	private List<WebElement> getCatalogs(){
		//		return driver.findElements(By.cssSelector(".catalog-container"));
		return catalogs;
	}

	private WebElement catalogBodyOf(String term){
		//		List<WebElement> bodies = driver.findElements(By.cssSelector(".catalog-body"));
		Optional<WebElement> filteredBody = catalogBodies.stream().filter(body -> body.getText().contains(term)).findFirst();		  
		return filteredBody.get();
	}

	private WebElement findCatalogVersion(String catalog, String catalogVersion){

		return catalogBodyOf(catalog).findElement(By.xpath("//div[contains(text(),'"+catalogVersion+"')]"));
	}

	//	private WebElement catalogVersionOf(String term){
	//		List<WebElement> bodies = driver.findElements(By.cssSelector(".catalog-thumbnail"));
	//		Optional<WebElement> filteredVersion = bodies.stream().filter(body -> body.getText().contains(term)).findFirst();
	//		return filteredVersion.get();
	//	}

	private WebElement selectCatalogPageNumber(int index){

		int STARTING_INDEX = 2;
		index =+ STARTING_INDEX;
		return driver.findElement(By.cssSelector(".pagination-container .pagination li:nth-child(" + index + ") a"));
	}

	public SmartEdit gotoCatalogVersion(Component component) {

		return gotoCatalogVersion(component.getCatalogId(), component.getCatalogVersion());
	}

}
