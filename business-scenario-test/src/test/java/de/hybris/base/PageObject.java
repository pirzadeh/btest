package de.hybris.base;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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

	final static int CSS_TRANSITION_DELAY = 1000;
	final static int INVESTIGATION_DELAY = 20000;
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

	public void delayForDebugging(){
		try {
			Thread.sleep(INVESTIGATION_DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void jiggleWithinUntilAttributeIsPresent(WebElement destinationElement, List<String> attributes){
		Point topLeft = destinationElement.getLocation();
		Dimension size = destinationElement.getSize();
		System.out.println("Origin Point: "+topLeft.x+","+topLeft.y);
		System.out.println("size: "+size.width+","+size.height);
		for (int i = 1 ; i < 15 ; i++){			
			Point randomPoint = randomPointBasedOn(size);
			Point destionationPoint = new Point(topLeft.x + randomPoint.x, topLeft.y + randomPoint.y);
			System.out.println("Random Point: "+destionationPoint.x+","+destionationPoint.y);
			mouse.moveByOffset(destionationPoint.x, destionationPoint.y).build().perform();
			simulateUserMouseMovement(topLeft, destinationElement, randomPoint, attributes);
			
			if(atrributesAllAvailable(attributes))
			{
				System.out.println("ready to drop");
				break;
			}
			mouse.moveByOffset(-1*destionationPoint.x,-1*destionationPoint.y).build().perform();
		}
	}

	private void simulateUserMouseMovement(Point topLeft, WebElement destinationElement, Point randomPoint, List<String> attributes) {
		if(!atrributesAllAvailable(attributes)){
			int absOffset = (randomPoint.x > randomPoint.y ? randomPoint.x : randomPoint.y);
			float movetoY = 0;
			float movetoX= 0;
			
			for (int i = 0; i < absOffset; i++){
				delay(10);
				movetoY += (float) randomPoint.y / absOffset;
				movetoX += (float) randomPoint.x / absOffset;
				mouse.moveToElement(destinationElement, Math.round(movetoX), Math.round(movetoY)).build().perform();	
			}
		}
		
	}

	private boolean atrributesAllAvailable(List<String> attributes) {
		for (String attribute : attributes){
			if (!atrributeBecomesAvailable(attribute)){
				System.out.println(attribute+" is not available!");
				return false;
			}
		}
		return true;
	}

	private Point randomPointBasedOn(Dimension size) {
		Random rand = new Random();
		Point randomPoint = new Point(rand.nextInt(size.width-50), rand.nextInt(size.height-25));
		return randomPoint;
	}
	
	private Point randomPointWithin(Point origin, Dimension size) {

		Random rand = new Random();
		Point randomPoint = new Point(rand.nextInt(size.width) + origin.x, rand.nextInt(size.height) + origin.y);
		return randomPoint;
	}

	public boolean atrributeBecomesAvailable(String attribute){
		driver.manage().timeouts().implicitlyWait(CSS_TRANSITION_DELAY, TimeUnit.MILLISECONDS);  
        try  
        {  
        	List<WebElement> elements = driver.findElements(By.className(attribute)); 
        	if (elements.size() > 0)
        		return true;   
        	else 
        		return false;
        		
        }  
        catch(NoSuchElementException e)  
        {  
            return false;  
        }  
       finally  
       {  
           driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
       }  
		
	}

	public void waitUntilXpathAtrributeValueAvailable(String xpath, String attribute, String value){
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);	
		wait.until(ExpectedConditions.attributeToBe(By.xpath(xpath), attribute, value));
		delay(CSS_TRANSITION_DELAY);
	}

	public void waitUntilCssAtrributeValueAvailable(String selector, String attribute, String value){
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);	
		wait.until(ExpectedConditions.attributeToBe(By.cssSelector(selector), attribute, value));
		delay(CSS_TRANSITION_DELAY);
	}

	public void waitUntilCssLocatorIsAvailable(String selector){
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(selector)));
		delay(CSS_TRANSITION_DELAY);
	}
	private Point calcOffset(Point destination, Point source) {

		Point offset = new Point(destination.x - source.x, destination.y - source.y);
		return offset;
	}

	

	public WebElement findElementByClass(String clazz){
		WebElement element = driver.findElement(By.cssSelector("."+clazz+""));
		return element;
	}
	
	public void scrollToY(int y){
		JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,"+y+")");
	}
	
	public String getElementsHtml(WebElement element){
		return (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML;", element);
	}
	public static boolean isClickable(WebElement element)      
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
