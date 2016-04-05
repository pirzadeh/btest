package de.hybris.tests;

import de.hybris.base.BusinessTest;
import de.hybris.pages.LandingPage;
import de.hybris.pages.LoginPage;

import org.junit.Before;
import org.junit.Test;

public class LandingPageTest extends BusinessTest{

	LandingPage page;

	@Before
	public void setup(){
		page = new LandingPage(driver);
	}

	@Test
	public void clickOnApparelDeStaged() {

		page.gotoCatalogVersion("Apparel DE", "Staged");
	}
}
