package de.hybris.tests;

import de.hybris.base.BusinessTest;
import de.hybris.pages.LoginPage;
import de.hybris.pages.cms.Component;
import de.hybris.pages.cms.Content;
import de.hybris.pages.framework.enums.EditorLanguageEnum;

import java.util.ArrayList;
import java.util.List;

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

		final String username = "cmsmanager";
		final String password = "1234";
		List<Component> validationList = new ArrayList<>();
		List<Content> contents = new ArrayList<>();
		Content content = new Content(EditorLanguageEnum.GERMAN, "This is a sample content", null, null);
		contents.add(content);
		Component component = new Component(null, "CMSParagraphComponent", "Section1Slot-ApparelDEHomepage", contents);
//		page.enterUsername(username).enterPassword(password).signin().gotoCatalogVersion("APPAREL DE", "Staged").moveComponentToSlot("ApparelDEHompageWomenStreetBannerComponent", "Section2BSlot-Homepage");
		page.enterUsername(username).enterPassword(password).signin().gotoCatalogVersion("APPAREL DE", "Staged").createNewComponentAndFillIt(component).logout();
		validationList.add(component);
//		page.enterUsername(username).enterPassword(password).signin().gotoCatalogVersion("APPAREL DE", "Staged").addExistingComponent("ApparelDEHompageWomenStreetBannerComponent", "Section1Slot-ApparelDEHomepage");
//		page.enterUsername(username).enterPassword(password).signin().gotoCatalogVersion("APPAREL DE", "Staged").createNewComponent("CMSParagraphComponent", "Section2ASlot-ApparelDEHomepage");


	}
}