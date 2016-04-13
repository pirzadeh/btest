package de.hybris.base;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
	public static WebDriver driver;
	public static Actions mouse;

	final static int CSS_TRANSIOTION_DELAY = 2000;
	final static int DEFAULT_TIMEOUT = 30;
	public PageObject(final WebDriver driver, final String url) {
		driver.get(url);
		pageSetup(driver);
		
	}

	public PageObject(WebDriver driver) {
		pageSetup(driver);
	}
	
	private void pageSetup(WebDriver driver) {
		this.setDriver(driver);
		PageFactory.initElements(driver, this);	
		setupMouse(driver);
	}
	
	private void setupMouse(WebDriver driver) {

		if (mouse == null)
			mouse = new Actions(driver);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(final WebDriver driver) {
		this.driver = driver;
	}

	public void delay(int durationMS){
		try {
			Thread.sleep(durationMS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void jiggleWithinUntilAttributeIsPresent(WebElement element, String attribute){
		Point topLeft = element.getLocation();
		Dimension size = element.getSize();
		for (int i = 1 ; i < 15 ; i++){			
			Point randomPoint = randomPointWithin(topLeft,size);
			System.out.println("Random Point: "+randomPoint.x+","+randomPoint.y);
			mouse.moveByOffset(randomPoint.x, randomPoint.y).build().perform();
			if(atrributeBecomesAvailable(attribute))
				break;
			mouse.moveByOffset(-1*randomPoint.x,-1*randomPoint.y).build().perform();
		}
	}
	
	public boolean atrributeBecomesAvailable(String attribute){
		delay(CSS_TRANSIOTION_DELAY);
		return driver.findElements(By.className(attribute)).size() != 0;
	}
	
	public void waitUntilXpathAtrributeValueAvailable(String xpath, String attribute, String value){
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);	
		wait.until(ExpectedConditions.attributeToBe(By.xpath(xpath), attribute, value));
		delay(CSS_TRANSIOTION_DELAY);
	}
	
	public void waitUntilCssAtrributeValueAvailable(String selector, String attribute, String value){
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);	
		wait.until(ExpectedConditions.attributeToBe(By.cssSelector(selector), attribute, value));
		delay(CSS_TRANSIOTION_DELAY);
	}
	
	public void waitUntilCssLocatorIsAvailable(String selector){
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(selector)));
		delay(CSS_TRANSIOTION_DELAY);
	}
	private Point calcOffset(Point destination, Point source) {
		
		Point offset = new Point(destination.x - source.x, destination.y - source.y);
		return offset;
	}
	
	private Point randomPointWithin(Point origin, Dimension size) {
		
		Random rand = new Random();
        Point randomPoint = new Point(rand.nextInt(size.width/2) + origin.x, rand.nextInt(size.height/2) + origin.y);
        return randomPoint;
	}
}
