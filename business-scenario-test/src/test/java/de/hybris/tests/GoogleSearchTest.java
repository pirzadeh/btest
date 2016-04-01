package de.hybris.tests;

import de.hybris.base.BusinessTest;
import de.hybris.pages.GoogleSearch;
import org.junit.Test;

public class GoogleSearchTest extends BusinessTest{

	@Test
	public void searchForSomething() {

		String query = "selenium page object";
		GoogleSearch home = new GoogleSearch(driver);
		home
		.enterQuery(query)
		.clickOnSearchButton();
	}
}
