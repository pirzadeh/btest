package de.hybris.base;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class BusinessTest {
	protected WebDriver driver;

//	@BeforeClass
//    public static void setupClass() {
////        ChromeDriverManager.getInstance().setup();
//    }
//	
	@Before
	public void basesetup(){
		//use FF Driver
//		driver = new FirefoxDriver();
		
		String driverPath = "/Users/i839970/.m2/repository/webdriver/chromedriver/mac32/2.21/chromedriver";
		System.setProperty("webdriver.chrome.driver", driverPath);
 		System.out.println("*******************");
		System.out.println("launching chrome browser");
		 ChromeOptions options = new ChromeOptions();
         options.addArguments("window-size=1800,1000");
         
         
         DesiredCapabilities cap = DesiredCapabilities.chrome();
         cap.setCapability(ChromeOptions.CAPABILITY, options);
         driver = new ChromeDriver(cap);
 
         String currentWindow = driver.getWindowHandle();
         driver.switchTo().window(currentWindow);
//		ChromeOptions co = new ChromeOptions();
//		//here "--start-maximized" argument is responsible to maximize chrome browser
//		co.addArguments("--start-maximized");
//		String driverPath = "/Users/i839970/.m2/repository/webdriver/chromedriver/mac32/2.21/chromedriver";
//		System.setProperty("webdriver.chrome.driver", driverPath);
//		driver = new ChromeDriver(co);
		
//		driver.manage().window().maximize();	
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
