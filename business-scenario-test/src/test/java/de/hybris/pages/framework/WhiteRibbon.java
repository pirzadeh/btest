package de.hybris.pages.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import de.hybris.base.Coordinatable;
import de.hybris.base.PageObject;
import de.hybris.pages.framework.base.Container;

public class WhiteRibbon extends Container implements Coordinatable{

	
	public WhiteRibbon(WebDriver driver) {
		super(driver);
	}

	public Point prepareComponentTypeForMove(String type) {
		putMeInContainer();
		WebElement typeElement  = findComponentType(type);
		mouse.clickAndHold(typeElement).build().perform();
		Point source = findCoordWithin(typeElement);
		return source;
	}
	

	public Point prepareCustomizedComponentForMove(String item) {
		putMeInContainer();
		WebElement itemElement  = findComponentItem(item);
		mouse.clickAndHold(itemElement).build().perform();
		Point source = findCoordWithin(itemElement);
		return source;
	}

	@Override
	public Point findCoord(WebElement element) {
		putMeInContainer();
		Point point = element.getLocation();
		return point;
	}
	@Override
	public Point findCoordWithin(WebElement element) {
		putMeInContainer();
		Point point = element.getLocation();
		Dimension size = element.getSize();
		Point pointWithin = new Point(point.x + size.width/2, point.y + size.height/2);
		return pointWithin;
	}

	@Override
	public Point findCoordById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point findCoordByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public WhiteRibbon openComponentMenu() {

		logDetail("Open the component menu");
		
		putMeInContainer();
		clickOnRibbonButton("component-menu");
		return this;
	}

	public WhiteRibbon openPerspectiveMenu(){

		logDetail("Open the perspective menu");
		
		putMeInContainer();
		clickOnRibbonButton("perspective-selector");
		return this;
	}
	
	public WhiteRibbon selectPerspective(String perspectiveName){
		
		logDetail("Select the \""+perspectiveName+"\" view");
		
		putMeInContainer();
		openPerspectiveMenu();
		WebElement scope = findElementByClass("ySEPerspectiveList--item");
		findElementByText(scope,perspectiveName).click();
		return this;
	}
	
	public WebElement findComponentType(String type) {
		putMeInContainer();
		openComponentMenu();
		selectComponetMenuTab("Component Types");
		WebElement element = driver.findElement(By.cssSelector(".smartEditComponent[data-smartedit-component-type='"+type+"']"));
		return element;
	}

	public WebElement findComponentItem(String item) {
		putMeInContainer();
		openComponentMenu();
		selectComponetMenuTab("Customized Components");
		WebElement element = driver.findElement(By.cssSelector(".smartEditComponent[data-smartedit-component-id='"+item+"']"));
		return element;
	}
	
	public WhiteRibbon selectComponetMenuTab(String tabName){
		
		logDetail("Select the \""+tabName+"\" tab");
		
		putMeInContainer();
		WebElement tabElement = driver.findElement(By.cssSelector("[heading='"+tabName+"']"));
		tabElement.click();
		delayForAnimation();
		return this;
	}

	private WhiteRibbon clickOnRibbonButton(String buttonTagName){
		
		logDetail("Click on the \""+buttonTagName+"\" button");
		
		putMeInContainer();
		WebElement button = driver.findElement(By.tagName(buttonTagName));		
		button.click();
		delayForAnimation();
		return this;
	}

}
