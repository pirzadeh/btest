package de.hybris.tests;

import de.hybris.base.BusinessTest;
import de.hybris.data.Component;
import de.hybris.data.Content;
import de.hybris.pages.LoginPage;
import de.hybris.pages.framework.enums.EditorLanguageEnum;
import de.hybris.personas.Strategy;
import de.hybris.personas.Tom;

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
	public void scenario4() {

		final String username = "cmsmanager";
		final String password = "1234";
		
		page.enterUsername(username).enterPassword(password).signin().gotoCatalogVersion("APPAREL DE", "Staged").moveComponentToSlot("ApparelDEHompageWomenStreetBannerComponent", "Section2BSlot-Homepage");
	}
		
	@Test
	public void scenario2() {

		final String username = "cmsmanager";
		final String password = "1234";
		
		page.enterUsername(username).enterPassword(password).signin().gotoCatalogVersion("APPAREL DE", "Staged").addExistingComponent("ApparelDEHompageWomenStreetBannerComponent", "Section1Slot-ApparelDEHomepage");


	}
	
	@Test
	public void scenario3() {

		final String username = "cmsmanager";
		final String password = "1234";
		
		page.enterUsername(username).enterPassword(password).signin().gotoCatalogVersion("APPAREL DE", "Staged")
			.createNewComponent("CMSParagraphComponent", "Section2ASlot-ApparelDEHomepage").fillWithPredefinedContent();

	}
	
	
	

	@Test
	public void scenario1() {

		final String username = "cmsmanager";
		final String password = "1234";
		List<Component> validationList = new ArrayList<>();
		
		Component paragraphComponent = defaultParagraphComponent(); 
		
		page.enterUsername(username).enterPassword(password).signin().gotoCatalogVersion(paragraphComponent)
			.createNewComponentAndFillIt(paragraphComponent).logout();
		
		validationList.add(paragraphComponent);
		
		page.enterUsername(username).enterPassword(password).signin().gotoCatalogVersion(paragraphComponent).validateExistence(validationList);

	}
	
	
	
	
	
	@Test
	public void contentManagement(){
		Tom tom = new Tom();
		
		tom.predifinedScenario();
		
		tom.explore();
		
		tom.explore(Strategy.RECENTLY_CREATED);
		
		tom.reportExperience();
		
	}
	
	

	private Component defaultParagraphComponent() {
	
		List<Content> contents = new ArrayList<>();
		
		Content content = new Content(EditorLanguageEnum.GERMAN, "This is a sample content", null, null);
		contents.add(content);
		return new Component(null, "CMSParagraphComponent", "Section1Slot-ApparelDEHomepage", contents, null, "Staged", "APPAREL DE");
	}
}