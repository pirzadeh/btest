package de.hybris.tests;

import de.hybris.base.BusinessTest;
import de.hybris.pages.LoginPage;

import org.junit.Before;
import org.junit.Test;

public class LoginPageTest extends BusinessTest{

	LoginPage page;

	@Before
	public void setup(){
		page = new LoginPage(driver);
	}

	@Test
	public void Login() {

		final String username = "admin";
		final String password = "nimda";
		page.enterUsername(username).enterPassword(password).signin().gotoCatalogVersion("APPAREL DE", "Staged").value();
	}
}