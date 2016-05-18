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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

/**
 * @author i839970
 *
 */
public abstract class PageObject {
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

	//	/**
	//	 * causes an extended delay that could be used for debugging
	//	 */
	//	public void delayForDebugging(){
	//		try {
	//			Thread.sleep(INVESTIGATION_DELAY);
	//		} catch (InterruptedException e) {
	//			e.printStackTrace();
	//		}
	//	}
	//
	//
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

	/**
	 * @param cssSelector the css selector of an element for which the existence will be checked against the current page
	 * @return true if the the css selector is available on the page, otherwise false
	 */
	public boolean cssSelectorIsAvailable(String cssSelector){

		return cssSelectorIsAvailable(null, cssSelector);
	}

	/**
	 * @param cssSelector the css selector of an element for which the existence will be checked under the specified scope against the current page
	 * @param scope the scope (WebElement) under which the css selector will be evaluated. If scope is null the css selector will be evaluated against the whole page
	 * @return true if the the css selector is available on the page, otherwise false
	 */
	public boolean cssSelectorIsAvailable(WebElement scope, String cssSelector){
		setDriverImpliciteWait(CSS_TRANSITION_DELAY, TimeUnit.MILLISECONDS);  
		List<WebElement> elements;
		try  
		{  
			if (scope == null){
				elements = driver.findElements(By.cssSelector(cssSelector)); 
			}
			else{
				elements = scope.findElements(By.cssSelector(cssSelector)); 
			}
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


	private static void setDriverImpliciteWait(int delay, TimeUnit unit) {
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
	}

	/**
	 * @param cssSelector is the CSS selector that if becomes available on the current page the wait will be over
	 * 
	 * waits until the element specified by the css selector becomes available
	 */
	public void waitUntilCssLocatorIsAvailable(String cssSelector){
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(cssSelector)));
	}

	/**
	 * @param clazz is the class that if becomes available on the current page the wait will be over
	 * 
	 * waits until the element specified by the class  becomes available
	 */
	public static void waitUntilClassIsNotAvailable(String clazz){
		setDriverImpliciteWait(0, TimeUnit.MILLISECONDS); 
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
		wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className(clazz),1));
		setDriverImpliciteWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS); 

	}

	private Point calcOffset(Point destination, Point source) {

		Point offset = new Point(destination.x - source.x, destination.y - source.y);
		return offset;
	}

	/**
	 * @param id the id by which the driver tries to find an element
	 * @return an element based on the id
	 * 
	 * tries to find an element by the visual text on the page
	 */
	public WebElement findElementById(String id){

		return findElementById(null,id);
	}

	/**
	 * @param id the id by which the driver tries to find an element
	 * @return list of elements found based on the specified id
	 * 
	 * tries to find a list of elements by the specified id on the page
	 */
	public List<WebElement> findElementsById(String id){

		return findElementsById(null, id);
	}

	/**
	 * @param scope in which the search for id will happen
	 * @param id the id by which the driver tries to find an element
	 * @return an element based on the id
	 * 
	 * tries to find an element based on the id on the provided scope
	 */
	public WebElement findElementById(WebElement scope, String id){

		return findElement(scope, By.id(id));
	}

	/**
	 * @param scope in which the search for visual text will happen
	 * @param id the visual text by which the driver tries to find an element
	 * @return a list of elements based on the visual text
	 * 
	 * tries to find a list of elements based on the visual text on the provided scope
	 */
	public List<WebElement> findElementsById(WebElement scope, String id){

		return findElements(scope, By.id(id));
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

		return findElement(scope, By.xpath("//*[contains(text(), '"+text+"')]"));
	}

	/**
	 * @param scope in which the search for visual text will happen
	 * @param visualText the visual text by which the driver tries to find an element
	 * @return a list of elements based on the visual text
	 * 
	 * tries to find a list of elements based on the visual text on the provided scope
	 */
	public List<WebElement> findElementsByText(WebElement scope, String text){

		return findElements(scope, By.xpath("//*[contains(text(), '"+text+"')]"));
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

		return findElement(scope, By.cssSelector("."+clazz+""));
	}

	/**
	 * @param scope in which the search for visual text will happen
	 * @param clazz the class by which the driver tries to find an element
	 * @return a list of elements based on the class
	 * 
	 * tries to find a list of elements based on the class on the provided scope
	 */
	public List<WebElement> findElementsByClass(WebElement scope, String clazz){

		return findElements(scope, By.cssSelector("."+clazz+""));
	}


	/**
	 * @param cssSelector the cssSelector by which the driver tries to find an element
	 * @return an element based on the specified cssSelector
	 * 
	 * tries to find an element based on the provided cssSelector on the page
	 */
	public static WebElement findElementByCssSelector(String cssSelector){

		return findElementByCssSelector(null, cssSelector);
	}

	/**
	 * @param cssSelector the cssSelector by which the driver tries to find an element
	 * @return list of elements found based on the specified cssSelector
	 * 
	 * tries to find a list of elements based on the specified cssSelector on the page
	 */
	public List<WebElement> findElementsByCssSelector(String cssSelector){

		return findElementsByCssSelector(null, cssSelector);
	}

	/**
	 * @param scope in which the search for cssSelector will happen
	 * @param cssSelector the cssSelector by which the driver tries to find an element
	 * @return an element based on the cssSelector
	 * 
	 * tries to find an element based on the cssSelector on the provided scope
	 */
	public static WebElement findElementByCssSelector(WebElement scope, String cssSelector){

		return findElement(scope, By.cssSelector(cssSelector));
	}

	/**
	 * @param scope in which the search for cssSelector will happen
	 * @param cssSelector the cssSelector by which the driver tries to find an element
	 * @return a list of elements based on the cssSelector
	 * 
	 * tries to find a list of elements based on the cssSelector on the provided scope
	 */
	public List<WebElement> findElementsByCssSelector(WebElement scope, String cssSelector){

		return findElements(scope, By.cssSelector(cssSelector));
	}

	/**
	 * @param element the web element for which the clickability is going to be checked
	 * @return true of the specified element is clickable, otherwise false
	 */
	public static boolean isClickable(WebElement element)      
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, CSS_TRANSITION_DELAY);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		}
		catch (Exception e)
		{
			System.out.println("element not clickable");
			return false;
		}
	}

	/**
	 * @param element the web element for which the clickability is going to be checked
	 * @return true of the specified element is clickable, otherwise false
	 */
	public void clickOn(WebElement element)      
	{
		if (isClickable(element))
			element.click();
	}

	//	public WebElement findElement(By by) {
	//		final Wait<WebDriver> wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
	//		final WebElement element = wait.until(visibilityOfElementLocated(by));
	//		return element;
	//	}

	public WebElement findElement(By by) {
		return findElement(null, by);
	}

	public static WebElement findElement(WebElement scope, By by) {
		final Wait<WebDriver> wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
		final WebElement element = wait.until(visibilityOfElementLocated(scope, by));
		return element;
	}

	private static ExpectedCondition<WebElement> visibilityOfElementLocated(WebElement scope, final By by) {
		return new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(final WebDriver driver) {
				WebElement element = null;
				if (scope == null)
					element = driver.findElement(by);
				else
					element = scope.findElement(by);
				return element.isDisplayed() ? element : null;
			}
		};
	}



	public List<WebElement> findElements(By by) {
		return findElements(null, by);
	}

	public List<WebElement> findElements(WebElement scope, By by) {
		final Wait<WebDriver> wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
		final List<WebElement> elements = wait.until(visibilityOfElementsLocated(scope, by));
		return elements;
	}

	private ExpectedCondition<List<WebElement>> visibilityOfElementsLocated(WebElement scope, final By by) {
		return new ExpectedCondition<List<WebElement>>() {
			@Override
			public List<WebElement> apply(final WebDriver driver) {
				List<WebElement> elements = null;
				if (scope == null)
					elements = driver.findElements(by);
				else
					elements = scope.findElements(by);
				return elements.stream().anyMatch(p -> p.isDisplayed()) ? elements : null;
			}
		};
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


	//	public WebElement findElementPredicateWait(By by) {
	//		final FluentWait<By> fluentWait = new FluentWait<By>(by);
	//		fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
	//		fluentWait.withTimeout(15, TimeUnit.SECONDS);
	//		fluentWait.until(new PredicateWait(driver));
	//
	//		return driver.findElement(by);
	//	}
	//
	//	private class PredicateWait implements Predicate<By> {
	//
	//		private final WebDriver driver;
	//
	//		public PredicateWait(final WebDriver driver) {
	//			this.driver = driver;
	//		}
	//
	//		@Override
	//		public boolean apply(final By by) {
	//			try {
	//				return driver.findElement(by).isDisplayed();
	//			} catch (final NoSuchElementException ex) {
	//				return false;
	//			}
	//		}
	//	}

	public static void putMeInContainer(){

		driver.switchTo().defaultContent();
		waitUntilClassIsNotAvailable("modal-open");
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
