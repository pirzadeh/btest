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

/**
 * @author i839970
 *
 */
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
		PageObject.driver = driver;
	}

	
	/**
	 * @param durationMS the duration in millisecond
	 * causes the driver to wait for the specified duration
	 */
	public void delay(int durationMS){
		try {
			Thread.sleep(durationMS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * causes an extended delay that could be used for debugging
	 */
	public void delayForDebugging(){
		try {
			Thread.sleep(INVESTIGATION_DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * causes the driver to wait for a default duration. This method could be used to delay the next action during interactions where an animation or transition is going to happen
	 */
	public void delayForAnimation(){
		try {
			Thread.sleep(CSS_TRANSITION_DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param destinationElement this is the web element within which boundaries the mouse is going to jiggle
	 * @param clazzes the list of classes that if available on the page jiggling will stop
	 * 
	 * jiggles the mouse within the boundaries of the element until the attribute classes are available in the page
	 */
	public void jiggleWithinUntilAttributeIsPresent(WebElement destinationElement, List<String> clazzes){

		logInteraction("Jiggle the mouse within "+destinationElement.getAttribute("id")+ " until "+ clazzes.toString() +" are fulfilled");

		Point topLeft = destinationElement.getLocation();
		Dimension size = destinationElement.getSize();
		
		logDetail("Origin Point: "+topLeft.x+","+topLeft.y);
		logDetail("size: "+size.width+","+size.height);
		
		for (int i = 1 ; i < 15 ; i++){			
			Point randomPoint = randomPointBasedOn(size);
			Point destionationPoint = new Point(topLeft.x + randomPoint.x, topLeft.y + randomPoint.y);
			logDetail("Random Point: "+destionationPoint.x+","+destionationPoint.y);
			mouse.moveByOffset(destionationPoint.x, destionationPoint.y).build().perform();
			simulateUserMouseMovement(topLeft, destinationElement, randomPoint, clazzes);

			if(classesAreAllAvailable(clazzes))
			{
				logDetail("ready to drop");
				break;
			}
			mouse.moveByOffset(-1*destionationPoint.x,-1*destionationPoint.y).build().perform();
		}
	}

	private void simulateUserMouseMovement(Point topLeft, WebElement destinationElement, Point randomPoint, List<String> attributes) {
		if(!classesAreAllAvailable(attributes)){
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

	/**
	 * @param clazzes the class names for which the existence will be checked against the current page
	 * @return true if all of the classes are available on the page, otherwise false
	 */
	public boolean classesAreAllAvailable(List<String> clazzes) {
		for (String clazz : clazzes){
			if (!classIsAvailable(clazz)){
				logDetail(clazz+" is not available!");
				return false;
			}
			logDetail(clazz+" is available!");
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

	/**
	 * @param clazz the class name for which the existence will be checked against the current page
	 * @return true if the class is available on the page, otherwise false
	 */
	public boolean classIsAvailable(String clazz){
		setDriverImpliciteWait(CSS_TRANSITION_DELAY, TimeUnit.MILLISECONDS);  
		try  
		{  
			List<WebElement> elements = driver.findElements(By.className(clazz)); 
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
			setDriverImpliciteWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);  
		}  

	}

	private void setDriverImpliciteWait(int delay, TimeUnit unit) {
		driver.manage().timeouts().implicitlyWait(delay, unit);
	}

	/**
	 * @param xpath is the xpath selector to the element to which the attribute belongs
	 * @param attribute the target attribute against which the value is checked
	 * @param value the value that if equals with the content of the attribute the wait will be over
	 * 
	 * waits until the attribute of the element specified by the xpath has the specified value
	 */
	public void waitUntilXpathAtrributeValueAvailable(String xpath, String attribute, String value){
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);	
		wait.until(ExpectedConditions.attributeToBe(By.xpath(xpath), attribute, value));
		delay(CSS_TRANSITION_DELAY);
	}

	/**
	 * @param cssSelector is the CSS selector to the element to which the attribute belongs
	 * @param attribute the target attribute against which the value is checked
	 * @param value the value that if equals with the content of the attribute the wait will be over
	 * 
	 * waits until the attribute of the element specified by the CSS selector has the specified value
	 */
	public void waitUntilCssAtrributeValueAvailable(String cssSelector, String attribute, String value){
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);	
		wait.until(ExpectedConditions.attributeToBe(By.cssSelector(cssSelector), attribute, value));
		delay(CSS_TRANSITION_DELAY);
	}

	/**
	 * @param cssSelector is the CSS selector that if becomes available on the current page the wait will be over
	 * 
	 * waits until the element specified by the css selector becomes available
	 */
	public void waitUntilCssLocatorIsAvailable(String cssSelector){
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(cssSelector)));
		delay(CSS_TRANSITION_DELAY);
	}
	
	private Point calcOffset(Point destination, Point source) {

		Point offset = new Point(destination.x - source.x, destination.y - source.y);
		return offset;
	}

	
	/**
	 * @param visualText the visual text by which the driver tries to find an element
	 * @return an element based on the visual text
	 * 
	 * tries to find an element by the visual text on the page
	 */
	public WebElement findElementByText(String visualText){

		return findElementByText(null,visualText);
	}

	/**
	 * @param visualText the visual text by which the driver tries to find an element
	 * @return list of elements found based on the specified visual text
	 * 
	 * tries to find a list of elements by the specified visual text on the page
	 */
	public List<WebElement> findElementsByText(String text){

		return findElementsByText(null, text);
	}

	/**
	 * @param scope in which the search for visual text will happen
	 * @param visualText the visual text by which the driver tries to find an element
	 * @return an element based on the visual text
	 * 
	 * tries to find an element based on the visual text on the provided scope
	 */
	public WebElement findElementByText(WebElement scope, String text){

		WebElement element = null;
		if (scope == null)
			element = driver.findElement(By.xpath("//*[contains(text(), '"+text+"')]"));
		else
			element = scope.findElement(By.xpath("//*[contains(text(), '"+text+"')]"));
		return element;
	}

	/**
	 * @param scope in which the search for visual text will happen
	 * @param visualText the visual text by which the driver tries to find an element
	 * @return a list of elements based on the visual text
	 * 
	 * tries to find a list of elements based on the visual text on the provided scope
	 */
	public List<WebElement> findElementsByText(WebElement scope, String text){

		List<WebElement> elements = null;
		if (scope == null)
			elements = driver.findElements(By.xpath("//*[contains(text(), '"+text+"')]"));
		else
			elements = scope.findElements(By.xpath("//*[contains(text(), '"+text+"')]"));
		return elements;
	}

	/**
	 * @param clazz the class by which the driver tries to find an element
	 * @return an element based on the specified class
	 * 
	 * tries to find an element based on the provided class on the page
	 */
	public WebElement findElementByClass(String clazz){

		return findElementByClass(null, clazz);
	}

	/**
	 * @param clazz the class by which the driver tries to find an element
	 * @return list of elements found based on the specified class
	 * 
	 * tries to find a list of elements based on the specified class on the page
	 */
	public List<WebElement> findElementsByClass(String clazz){

		return findElementsByClass (null, clazz);
	}

	/**
	 * @param scope in which the search for visual text will happen
	 * @param clazz the class by which the driver tries to find an element
	 * @return an element based on the class
	 * 
	 * tries to find an element based on the class on the provided scope
	 */
	public WebElement findElementByClass(WebElement scope, String clazz){

		WebElement element = null;
		if (scope == null)
			element = driver.findElement(By.cssSelector("."+clazz+""));
		else
			element = scope.findElement(By.cssSelector("."+clazz+""));
		return element;
	}

	/**
	 * @param scope in which the search for visual text will happen
	 * @param clazz the class by which the driver tries to find an element
	 * @return a list of elements based on the class
	 * 
	 * tries to find a list of elements based on the class on the provided scope
	 */
	public List<WebElement> findElementsByClass(WebElement scope, String clazz){

		List<WebElement> elements = null;
		if (scope == null)
			elements = driver.findElements(By.cssSelector("."+clazz+""));
		else
			elements = scope.findElements(By.cssSelector("."+clazz+""));
		return elements;
	}

	
	/**
	 * @param y a position on the Y axes of the page
	 * 
	 * vertically scrolls the page to the position specified by y
	 */
	public void scrollToY(int y){
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,"+y+")");
	}

	
	/**
	 * @param element the web element for which the inner html is returned
	 * @return the inner html content of the specified element
	 */
	public String getElementsHtml(WebElement element){
		String html = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML;", element);
		return html;
	}
	
	/**
	 * @param element the web element for which the clickability is going to be checked
	 * @return true of the specified element is clickable, otherwise false
	 */
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

	public void logError(String log){

		BusinessScenarioLogger.getLogger().error(log);
	}

	public void logInteraction(String log){

		BusinessScenarioLogger.getLogger().info("STEP: "+log);
	}

	public void logInfo(String log){

		BusinessScenarioLogger.getLogger().info(log);
	}

	public void logDetail(String log){

		BusinessScenarioLogger.getLogger().info("\tDETAIL: "+log);
	}

	public void logDebug(String log){

		BusinessScenarioLogger.getLogger().debug(log);
	}
}
