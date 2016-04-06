package de.hybris.base;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public interface Coordinatable {

	public Point findCoord(WebElement element);
	public Point findCoordById(String id);
	public Point findCoordByName(String name);

	
}
