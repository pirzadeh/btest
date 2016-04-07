package de.hybris.base;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BusinessTest {
	protected WebDriver driver;

	@Before
	public void basesetup(){
		//use FF Driver
//		driver = new FirefoxDriver();
		
		System.out.println("*******************");
		System.out.println("launching chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	

	@After
	public void baseclose(){
		driver.close();
	}
}
