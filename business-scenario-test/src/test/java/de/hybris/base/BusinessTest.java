package de.hybris.base;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class BusinessTest {
	protected WebDriver driver;

	@BeforeClass
    public static void setupClass() {
        ChromeDriverManager.getInstance().setup();
    }
	
	@Before
	public void basesetup(){
		//use FF Driver
//		driver = new FirefoxDriver();
		
		System.out.println("*******************");
		System.out.println("launching chrome browser");
		driver = new ChromeDriver();
		
		
		driver.manage().window().maximize();	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	

	@After
	public void baseclose(){
		driver.close();
		if (driver != null) {
            driver.quit();
        }
	}
}
