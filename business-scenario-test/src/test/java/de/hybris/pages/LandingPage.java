package de.hybris.pages;

import de.hybris.base.PageObject;

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

	//Username
	private final WebElement username = driver.findElement(By.xpath("//input[contains(@placeholder, 'Username')]"));

	//Password
	private final WebElement password = driver.findElement(By.xpath("//input[contains(@placeholder, 'Password')]"));

	@FindBy(name = "submit")
	private WebElement signin;
	
	//helpers
	
	private List<WebElement> getCatalogs(){
		return driver.findElements(By.cssSelector(".catalog-container"));
	}
	
	private WebElement catalogBodyOf(String term){
		 List<WebElement> bodies = driver.findElements(By.cssSelector(".catalog-body"));
		 bodies.forEach(b -> {System.out.println(b); System.out.println(b.getText());});

		  Optional<WebElement> filteredBody = bodies.stream().filter(body -> body.getText().contains(term)).findFirst();
		  
		  System.out.println(filteredBody.get().getText());
		  
		  return filteredBody.get();
	}
	
	public WebElement gotoCatalogVersion(String catalog, String catalogVersion){
		
		return catalogBodyOf(catalog).findElement(By.xpath("//div[contains(text(),'"+catalogVersion+"')]"));
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
	
	
/*
 *     catalogs: {
        get: function() {
            return element.all(by.css('.catalog-container'));
        }
    },
    firstCatalog: {
        get: function() {
            return element(by.css('.catalog-container .catalog-version-container'));
        }
    },
    selectCatalogPageNumber: {
        get: function(index) {
            var STARTING_POSITION = 2;
            index = index + STARTING_POSITION;
            return element(by.css('.pagination-container .pagination li:nth-child(' + index + ') a'));
        }
    },
    experienceSelectorWidgetText: {
        get: function() {
            browser.click(by.css('.navbar-header .navbar-toggle'));
            browser.sleep(500);
            browser.click(by.css('.leftNav a[data-ng-click="showSites()"]'));
            browser.sleep(500);
        }
    },

    clickSitesFromLeftMenu: {
        get: function() {

            return element(by.css('#slot1'));
        }
    },
    catalogBodies: {
        get: function() {
            return element.all(by.css('.catalog-body'));
        }
    },
    catalogBodyOf: {
        value: function(term) {
            return element(By.cssContainingText('.catalog-body', term));
        }
    },
    syncButtonOf: {
        value: function(term) {
            return this.catalogBodyOf(term).element(by.xpath('.//*[contains(@class,"catalog-sync-btn")]'));
        }
    },
    syncStatusOf: {
        value: function(term) {
            return this.catalogBodyOf(term).element(by.xpath('.//*[contains(@class,"ySESyncProgress")]'));
        }
    },
    failureStatusOf: {
        value: function(term) {
            return this.catalogBodyOf(term).element(by.xpath('.//*[contains(@class,"label-error")]'));
        }
    },
    syncDateOf: {
        value: function(term) {
            return this.catalogBodyOf(term).element(by.xpath('.//*[contains(@class,"catalog-last-synced")]'));
        }
    },
    syncConfirmPopup: {
        get: function() {
            return element(by.xpath('.//*[contains(@class,"modal-dialog")]'));
        }
    },
    syncConfirmOK: {
        get: function() {
            return element(by.id('confirmOk'));
        }
    },
 */
	
	
	
	public LandingPage enterUsername(final String name)
	{
		username.sendKeys(name);
		return this;
	}

	public LandingPage enterPassword(final String pass)
	{
		password.sendKeys(pass);
		return this;
	}

	public void signin()
	{
		signin.click();
	}
}
