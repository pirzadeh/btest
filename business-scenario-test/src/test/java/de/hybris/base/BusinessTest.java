package de.hybris.base;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class BusinessTest {
	protected WebDriver driver;
	private String currentBrowser="";
	//	@BeforeClass
	//    public static void setupClass() {
	////        ChromeDriverManager.getInstance().setup();
	//    }
	//	
	@Before
	public void basesetup(){
		//use FF Driver
		//		driver = new FirefoxDriver();

		driver = setupChrome();

		String currentWindow = driver.getWindowHandle();
		driver.switchTo().window(currentWindow);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	private WebDriver setupChrome() {
		String driverPath = "/Users/i839970/.m2/repository/webdriver/chromedriver/mac32/2.21/chromedriver";
		System.setProperty("webdriver.chrome.driver", driverPath);
		logInfo("*******************");
		logInfo("launching chrome browser");
		logInfo("*******************");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("window-size=1800,1000");
		setCurrentBrowser("chrome");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, Level.ALL);
		cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		driver = new ChromeDriver(cap);
		return driver;
	}

	public void analyzeLog() {

		if (currentBrowser.equals("chrome")){
			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
			for (LogEntry entry : logEntries) {
				logError(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());

				//do something useful with the data
				//[TODO] look into automatic ticket creation
			}
		}

	}

	@After
	public void baseclose(){
		analyzeLog();
		driver.close();
		if (driver != null) {
			driver.quit();
		}
	}

	public String getCurrentBrowser() {
		return currentBrowser;
	}

	public void setCurrentBrowser(String currentBrowser) {
		this.currentBrowser = currentBrowser;
	}

	public void logError(String log){

		BusinessScenarioLogger.getLogger().error(log);
	}
	
	public void logInfo(String log){

		BusinessScenarioLogger.getLogger().info(log);
	}
	
	public void logDebug(String log){

		BusinessScenarioLogger.getLogger().debug(log);
	}
	
}
