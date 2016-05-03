package de.hybris.tests;

import de.hybris.base.BusinessTest;
import de.hybris.data.Component;
import de.hybris.data.Content;
import de.hybris.pages.LoginPage;
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
		
		Component paragraphComponent = defaultParagraphComponent(); 
		page.enterUsername(username).enterPassword(password).signin().gotoCatalogVersion(paragraphComponent).createNewComponentAndFillIt(paragraphComponent).logout();
		validationList.add(paragraphComponent);
		
		page.enterUsername(username).enterPassword(password).signin().gotoCatalogVersion(paragraphComponent).validateExistence(validationList);
//		page.enterUsername(username).enterPassword(password).signin().gotoCatalogVersion("APPAREL DE", "Staged").addExistingComponent("ApparelDEHompageWomenStreetBannerComponent", "Section1Slot-ApparelDEHomepage");
//		page.enterUsername(username).enterPassword(password).signin().gotoCatalogVersion("APPAREL DE", "Staged").createNewComponent("CMSParagraphComponent", "Section2ASlot-ApparelDEHomepage");
//		page.enterUsername(username).enterPassword(password).signin().gotoCatalogVersion("APPAREL DE", "Staged").moveComponentToSlot("ApparelDEHompageWomenStreetBannerComponent", "Section2BSlot-Homepage");


	}

	private Component defaultParagraphComponent() {
	
		List<Content> contents = new ArrayList<>();
		
		Content content = new Content(EditorLanguageEnum.GERMAN, "This is a sample content", null, null);
		contents.add(content);
		return new Component(null, "CMSParagraphComponent", "Section1Slot-ApparelDEHomepage", contents, null, "Staged", "APPAREL DE");
	}
}