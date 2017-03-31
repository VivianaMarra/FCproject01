package com.foodcloud.test.server;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * FoodCloud Test Server 
 * This class is wrapping Selenium webdriver object and contains all utilities methods 
 *
 * @author Viviana
 */
public class FCTestServer {

	private WebDriver driver;
	public static int WAIT_TIMEOUT = 20;
	private static String HIDDEN_SPINNER_LOCATOR = "css=i.fa-spin.ng-hide";
	
	public FCTestServer(WebDriver webDriver) {
		this.driver = webDriver;
	}

	/**
	 * waits for an object or element on a page until it displays (or generates a timeout exception)  
	 * implements Explicit wait
	 * @param elementLocator
	 * @return expectedWebElement on page 
	 */
	public WebElement waitForElement(By elementLocator) {
	// Move WebDriver as internal variable 	 
		WebElement expectedWebElement = 
		new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(elementLocator));
	  
		return expectedWebElement;
	}

	/**
	 * Types a text string in a field given the locator 
	 * @param fieldLocator
	 * @param value
	 * @param driver
	 */
	public void type(String fieldLocator, String value) {
		WebElement we = driver.findElement(getLocatorType(fieldLocator));		
		we.clear();
		we.sendKeys(value);		
	} 
	
	
	public By getLocatorType(String locator){
		By by = null;
		
		if(locator.contains("//")) {
			by = By.xpath(locator);
		}
		else if(locator.contains("css=")) {
			by = By.cssSelector(locator.substring(4));
		}
		else if(locator.contains("id=")) {
			by = By.id(locator.substring(3));
		}
		else if(locator.contains("link=")) {
			by = By.linkText(locator.substring(5));
		}
		else if(locator.contains("name=")) {
			by = By.name(locator.substring(5));
		}
				
		return by; 
	}

	public WebDriver getDriver() {
		return driver;
	}

	public boolean isElementPresent(String elementLocator) {
		WebElement we = null;
		try {
		
			we = driver.findElement(getLocatorType(elementLocator));		
		
		}
		catch (NoSuchElementException e) {
			// Log exception e
			return false;
		} 
		
		if(we != null){
			return true;
		}
		else {		
			return false;
		}
	}

	public void openURL(String targetUrl) {
		driver.get(targetUrl);
		
	}

	/**
	 * clicks on a specific object given an existing string locator that uniquely identifies the object
	 * @param fieldLocator
	 */
	public void click(String fieldLocator) {
	
		WebElement we = driver.findElement(getLocatorType(fieldLocator));		
		we.click(); 
	}
	
	
	public WebElement waitForSpinnerToDisappear() {
					
		WebElement expectedWebElement = 
		new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(this.getLocatorType(HIDDEN_SPINNER_LOCATOR)));
	  
		return expectedWebElement;

	}
	
}
