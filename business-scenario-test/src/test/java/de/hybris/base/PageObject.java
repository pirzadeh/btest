package de.hybris.base;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class PageObject {
	public static WebDriver driver;
	public static Actions mouse;

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

	public void jiggleWithinUntilTextIsPresent(WebElement element, String text){
		Point topLeft = element.getLocation();
		Dimension size = element.getSize();
		Point bottomRight = new Point(topLeft.x + size.width, topLeft.y + size.height);
		Random rand = new Random();
		for (int i = 1 ; i < 15 ; i++){
			
			int randomX = rand.nextInt(bottomRight.x - topLeft.x) + topLeft.x;
			int randomY = rand.nextInt(bottomRight.y - topLeft.y) + topLeft.y;
			mouse.moveByOffset(randomX,randomY).build().perform();
			System.out.println(driver.getPageSource());
			if (driver.getPageSource().contains(text))
				{
				break;
				}
			mouse.moveByOffset(-1*randomX,-1*randomY).build().perform();
			if (driver.getPageSource().contains(text))
				break;
		}
	}
	public void delay(int durationMS){
		try {
			Thread.sleep(durationMS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void jiggleWithinUntilAttributeIsPresent(Point source, WebElement element, String attribute){
		Point topLeft = element.getLocation();
		Dimension size = element.getSize();
		Point bottomRight = new Point(topLeft.x + size.width, topLeft.y + size.height);
		System.out.println("Top Point: "+topLeft.x+","+topLeft.y);
		System.out.println("Bottom Point: "+bottomRight.x+","+bottomRight.y);

		Random rand = new Random();
		for (int i = 1 ; i < 15 ; i++){
			
			int randomX = rand.nextInt(size.width/3) + topLeft.x;
			int randomY = rand.nextInt(size.height/3) + topLeft.y;
			mouse.moveByOffset(randomX - source.x, randomY - source.y).build().perform();
			source.x = randomX - source.x;
			source.y = randomY - source.y;
			delay(2000);
			if(driver.findElements(By.className(attribute)).size() != 0)
				break;
			mouse.moveByOffset(-1*randomX,-1*randomY).build().perform();
			if(driver.findElements(By.className(attribute)).size() != 0)
				break;
		}
	}
}
