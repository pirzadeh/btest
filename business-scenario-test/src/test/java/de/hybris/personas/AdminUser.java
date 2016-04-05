package de.hybris.personas;

import org.junit.Before;
import org.junit.Test;

import de.hybris.base.Persona;
import de.hybris.tests.GoogleSearchTest;

public class AdminUser extends Persona {

	String name= "cmsAdmin";
	String role= "Admin";
	
	String username = "admin";
	String password = "nimda";
	private GoogleSearchTest page;
	
	public void login(){
		
	}
	
	@Before
	public void setup(){
		page = new GoogleSearchTest();
		page.setup();
	}
	
	@Test
	public void scenario() {

//		page.searchForSomething().gotoAccount();
		
	}
	@Override
	public String reportExperience() {
		// TODO Auto-generated method stub
		return null;
	}

}
