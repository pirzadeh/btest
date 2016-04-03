package de.hybris.tests;

import de.hybris.base.BusinessTest;
import de.hybris.pages.GoogleSearch;

import org.junit.Before;
import org.junit.Test;

public class GoogleSearchTest extends BusinessTest{

	GoogleSearch page;
	
	@Before
	public void setup(){
		page = new GoogleSearch(driver);
	}
	
	@Test
	public void searchForSomething() {

		String query = "selenium page object";
		page.enterQuery(query).clickOnSearchButton();

	}
	
	@Test
	public void gotoAccount() {

		page.header.clickAppsSign();
	}
}
